package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import service.custom.EmployeeService;
import service.custom.impl.EmployeeServiceImpl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class AddEmployeeFormController implements Initializable {

    public ImageView imgEmployee;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colConNumber;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtConNumber;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtEmpId;

    @FXML
    private JFXTextField txtEmpName;
    private File selectedPhotoFile;


    EmployeeService employeeService = new EmployeeServiceImpl();
    Employee employee;


    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        saveEmployee();
        loadTable();
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        employeeService.deleteEmployee(employee);
        loadTable();
        clearFields();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        employee = employeeService.searchEmployee(Integer.valueOf(txtEmpId.getText()));
        if (employee != null) {
            setFields(employee);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        employee.setAddress(txtAddress.getText());
        employee.setEmail(txtEmail.getText());
        employee.setName(txtEmpName.getText());
        employee.setContactNumber(txtConNumber.getText());
        employeeService.updateEmployee(employee);
        loadTable();
        clearFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal != null) {
                setFields(newVal);
                employee = newVal;
            }
        });
        setCellValueFactory();
        loadTable();
        clearFields();

    }

    private void setFields(Employee newVal) {
        txtEmpId.setText(String.valueOf(newVal.getId()));
        txtEmail.setText(newVal.getEmail());
        txtAddress.setText(newVal.getAddress());
        txtConNumber.setText(newVal.getContactNumber());
        txtEmpName.setText(newVal.getName());
        if (newVal.getPhotoPath() != null && new File(newVal.getPhotoPath()).exists()) {
            imgEmployee.setImage(new Image(new File(newVal.getPhotoPath()).toURI().toString()));
        } else {
            imgEmployee.setImage(null);
        }
    }

    private void clearFields() {
       txtEmpId.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtConNumber.setText("");
        txtEmpName.setText("");
        txtPassword.setText("");
        selectedPhotoFile= null;
        imgEmployee.setImage(null);
    }

    private void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colConNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadTable() {

        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        employeeObservableList.addAll(employeeService.getAllEmployee());
        tblEmployee.setItems(employeeObservableList);

    }

    private void saveEmployee() throws IOException {
        boolean success=false;
        if (!(txtEmpName.getText().isEmpty()
                && txtConNumber.getText().isEmpty()
                && txtAddress.getText().isEmpty()
                && txtEmail.getText().isEmpty()
                && txtPassword.getText().isEmpty())) {

             success = employeeService.saveEmployee(new Employee(
                    txtEmpName.getText(),
                    null,
                    txtConNumber.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                     txtPassword.getText(),
                     selectedPhotoFile.getPath(),
                    "employee"
            ));
        }
        String savedPhotoPath = null;
        if (selectedPhotoFile != null) {
            Path destDir = Paths.get("photos/employees");
            if (!Files.exists(destDir)) Files.createDirectories(destDir);

            Path destFile = destDir.resolve(txtEmpId + ".png");
            Files.copy(selectedPhotoFile.toPath(), destFile, StandardCopyOption.REPLACE_EXISTING);
            savedPhotoPath = destFile.toString();
        }
        if (success){
            new Alert(Alert.AlertType.INFORMATION,"Employee Added!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Employee Not Added!").show();
        }

    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        loadTable();
        clearFields();
    }

    public void btnAddEmployeeImageOnAction(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Employee Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png")
        );

        File file = fileChooser.showOpenDialog(imgEmployee.getScene().getWindow());

        if (file != null) {
            selectedPhotoFile = file;
            imgEmployee.setImage(new Image(file.toURI().toString()));
        }
    }
}
