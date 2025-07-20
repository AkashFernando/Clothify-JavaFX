package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.CustomerService;
import service.custom.impl.CustomerServiceImpl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    public ComboBox titleComboBox;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colConNumber;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colCustName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtConNumber;

    @FXML
    private JFXTextField txtCustId;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtEmail;

    CustomerService customerService = new CustomerServiceImpl();
    Customer customer;

    public void btnAddOnAction(ActionEvent actionEvent) {
        saveCustomer();
        loadTable();
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        customer.setAddress(txtAddress.getText());
        customer.setEmail(txtEmail.getText());
        customer.setName(txtCustName.getText());
        customer.setContactNumber(txtConNumber.getText());
        customerService.updateCustomer(customer);
        loadTable();
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        customerService.deleteCustomer(customer);
        loadTable();
        clearFields();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        customer = customerService.searchCustomer(Integer.valueOf(txtCustId.getText()));
        if (customer != null) {
            setFields(customer);
        }
    }

    private void loadTable() {

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        customerObservableList.addAll(customerService.getAllCustomer());
        tblCustomer.setItems(customerObservableList);

    }

    public void saveCustomer(){
        boolean success=false;
        if (!(txtCustName.getText().isEmpty()
                && txtConNumber.getText().isEmpty()
                && txtAddress.getText().isEmpty()
                && txtEmail.getText().isEmpty())) {

            success = customerService.saveCustomer(new Customer(
                    txtCustName.getText(),
                    null,
                    txtConNumber.getText(),
                    txtEmail.getText(),
                    txtAddress.getText()

            ));
        }
        if (success){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Not Added!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleComboBox.setItems(FXCollections.observableArrayList("Mr.", "Mrs.", "Ms."));
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal != null) {
                setFields(newVal);
                customer = newVal;
            }
        });
        setCellValueFactory();
        loadTable();
        clearFields();
    }
    private void setCellValueFactory() {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colConNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    private void setFields(Customer newVal) {
        txtCustId.setText(String.valueOf(newVal.getId()));
        txtEmail.setText(newVal.getEmail());
        txtAddress.setText(newVal.getAddress());
        txtConNumber.setText(newVal.getContactNumber());
        txtCustName.setText(newVal.getName());
    }
    private void clearFields() {
//       txtCustId.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtConNumber.setText("");
        txtCustName.setText("");

    }


}
