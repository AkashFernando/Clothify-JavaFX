package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.EmployeeService;
import service.custom.SupplierService;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.SupplierServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSupplierFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colComName;

    @FXML
    private TableColumn<?, ?> colConNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtComName;

    @FXML
    private JFXTextField txtConNumber;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtSupId;

    SupplierService supplierService = new SupplierServiceImpl();
    Supplier supplier;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        saveSupplier();
        loadTable();
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        supplierService.deleteSupplier(supplier);
        loadTable();
        clearFields();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        supplier = supplierService.searchSupplier(Integer.valueOf(txtSupId.getText()));
        if (supplier != null) {
            setFields(supplier);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        supplier.setAddress(txtAddress.getText());
        supplier.setEmail(txtEmail.getText());
        supplier.setCompanyName(txtComName.getText());
        supplier.setContactNumber(txtConNumber.getText());
        supplierService.updateSupplier(supplier);
        loadTable();
        clearFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal != null) {
                setFields(newVal);
                supplier = newVal;
            }
        });
        setCellValueFactory();
        loadTable();
        clearFields();
    }
    private void setFields(Supplier newVal) {
        txtSupId.setText(String.valueOf(newVal.getId()));
        txtEmail.setText(newVal.getEmail());
        txtAddress.setText(newVal.getAddress());
        txtConNumber.setText(newVal.getContactNumber());
        txtComName.setText(newVal.getCompanyName());
    }

    private void clearFields() {
//      txtSupId.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtConNumber.setText("");
        txtComName.setText("");

    }

    private void setCellValueFactory() {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colComName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colConNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadTable() {

        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
        supplierObservableList.addAll(supplierService.getAllSupplier());
        tblSupplier.setItems(supplierObservableList);

    }
    private void saveSupplier() {
        boolean success=false;
        if (!(txtComName.getText().isEmpty()
                && txtConNumber.getText().isEmpty()
                && txtAddress.getText().isEmpty()
                && txtEmail.getText().isEmpty())) {

            success = supplierService.saveSupplier(new Supplier(
                    txtComName.getText(),
                    null,
                    txtConNumber.getText(),
                    txtEmail.getText(),
                    txtAddress.getText()));
        }
        if (success){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Added!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Supplier Not Added!").show();
        }

    }
}
