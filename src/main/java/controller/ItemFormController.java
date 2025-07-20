package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Item;
import dto.Supplier;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.hibernate.Session;
import service.custom.ItemService;
import service.custom.impl.ItemServiceImpl;
import util.HibernateUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    public ImageView imgItem;

    public JFXComboBox <Supplier> cmbSupplier;
    @FXML
    private JFXComboBox cmbSize;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colprice;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQuantity;
    private File selectedPhotoFile;
    

    ItemService itemService = new ItemServiceImpl();
    Item item;

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        saveItem();
        loadTable();
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        itemService.deleteItem(item);
        loadTable();
        clearFields();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        item = itemService.searchItem(Integer.valueOf(txtItemCode.getText()));

        if (item != null) {
            setFields(item);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        item.setDescription(txtDescription.getText());
        item.setQuantity(Integer.valueOf(txtQuantity.getText()));
        item.setPrize(Double.valueOf(txtPrice.getText()));
        item.setSize(cmbSize.getValue().toString());
        itemService.updateItem(item);
        loadTable();
        clearFields();
    }

    public void saveItem() throws IOException {
        boolean success=false;
        if (!(txtDescription.getText().isEmpty()
                && txtQuantity.getText().isEmpty()
                && txtPrice.getText().isEmpty()
                && cmbSize.getItems().isEmpty())) {

            Supplier selectedSupplier = (Supplier) cmbSupplier.getValue();
            if (selectedSupplier == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a supplier!").show();
                return;
            }
            success = itemService.saveItem(new Item(
                    null,
                    txtDescription.getText(),
                    Integer.parseInt(txtQuantity.getText()),
                    Double.parseDouble(txtPrice.getText()),
                    cmbSize.getValue().toString(),
                    selectedPhotoFile.getPath(),
                    selectedSupplier
            ));
        }
        String savedPhotoPath = null;
        if (selectedPhotoFile != null) {
            Path destDir = Paths.get("photos/employees");
            if (!Files.exists(destDir)) Files.createDirectories(destDir);

            Path destFile = destDir.resolve(txtItemCode + ".png");
            Files.copy(selectedPhotoFile.toPath(), destFile, StandardCopyOption.REPLACE_EXISTING);
            savedPhotoPath = destFile.toString();
        }
        if (success){
            new Alert(Alert.AlertType.INFORMATION,"Item Added!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Item Not Added!").show();
        }
        loadTable();
        setCellValueFactory();
        clearFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal != null) {
                setFields(newVal);
                item = newVal;
            }
        });
        setCellValueFactory();
        loadTable();
        clearFields();
        loadSizes();
        loadSuppliers();

    }

    private void loadSuppliers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<SupplierEntity> supplierEntities = session.createQuery("from SupplierEntity", SupplierEntity.class).list();

            List<Supplier> suppliers = supplierEntities.stream()
                    .map(entity -> new Supplier(
                            entity.getCompanyName(),
                            entity.getId(),
                            entity.getContactNumber(),
                            entity.getEmail(),
                            entity.getAddress()
                    ))
                    .toList();

            cmbSupplier.setItems(FXCollections.observableArrayList(suppliers));

            cmbSupplier.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(Supplier supplier, boolean empty) {
                    super.updateItem(supplier, empty);
                    setText(empty || supplier == null ? null : supplier.getId() + " - " + supplier.getCompanyName());
                }
            });

            cmbSupplier.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(Supplier supplier, boolean empty) {
                    super.updateItem(supplier, empty);
                    setText(empty || supplier == null ? null : supplier.getId() + " - " + supplier.getCompanyName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load suppliers: " + e.getMessage()).show();
        }
    }



    private void loadSizes() {
        cmbSize.setItems(FXCollections.observableArrayList("XS", "S", "M", "L", "XL", "XXL"));
    }




    private void setFields(Item newVal) {
        txtItemCode.setText(String.valueOf(newVal.getCode()));
        txtDescription.setText(newVal.getDescription());
        txtQuantity.setText(String.valueOf(newVal.getQuantity()));
        txtPrice.setText(String.valueOf(newVal.getPrize()));
        cmbSize.setValue(newVal.getSize());
        cmbSupplier.setValue(newVal.getSupplier());

        if (newVal.getPhotoPath() != null && !newVal.getPhotoPath().isEmpty()) {
            File photoFile = new File(newVal.getPhotoPath());
            if (photoFile.exists()) {
                imgItem.setImage(new Image(photoFile.toURI().toString()));
                selectedPhotoFile = photoFile;
            } else {
                imgItem.setImage(null);
            }
        } else {
            imgItem.setImage(null);
        }
    }

    private void clearFields() {
        txtItemCode.clear();
        txtDescription.clear();
        txtQuantity.clear();
        txtPrice.clear();
        cmbSize.getSelectionModel().clearSelection();
        cmbSupplier.getSelectionModel().clearSelection();
        selectedPhotoFile= null;
        imgItem.setImage(null);


    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("prize"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplier"));
    }

    private void loadTable() {

        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        itemObservableList.addAll(itemService.getAllItem());
        tblItem.setItems(itemObservableList);

    }

    public void btnAddItemImageOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Employee Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png")
        );

        File file = fileChooser.showOpenDialog(imgItem.getScene().getWindow());

        if (file != null) {
            selectedPhotoFile = file;
            imgItem.setImage(new Image(file.toURI().toString()));
        }
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        loadTable();
        clearFields();
    }
}
