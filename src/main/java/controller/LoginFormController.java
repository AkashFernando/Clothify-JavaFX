package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.custom.EmployeeService;
import service.custom.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.util.List;

public class LoginFormController {

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {
            login();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        adminInitialize();
    }

    private EmployeeService employeeService = new EmployeeServiceImpl();

    private boolean isDuplicate(Employee employee) {
        List<Employee> adminList = employeeService.getAdmins();
        for (Employee employee1 : adminList) {
            if (employee1.getPassword().equals(employee.getPassword())) {
                return true;
            }
        }
        return false;
    }

    private void adminInitialize() {
        Employee employee = new Employee("Akash",
                null,
                "0766133577",
                "Akash@gmail.com",
                "Panadura",
                "12345",
                null,
                "admin");
        if (!isDuplicate(employee)) {
            employeeService.saveEmployee(employee);
        }
    }

    private void login() throws IOException {
        List<Employee> employees = employeeService.getAllEmployee();
        boolean isValidEmployee = false;
        for (Employee employee : employees) {
            if (employee.getEmail().equals(txtEmail.getText()) && employee.getPassword().equals(txtPassword.getText())) {
                if (employee.getRole().equalsIgnoreCase("admin")) {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
                    stage.show();
                }
                if (employee.getRole().equalsIgnoreCase("employee")) {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employeeDashboard.fxml"))));
                    stage.show();
                }
                isValidEmployee = true;
                break;
            }
        }
        if (!isValidEmployee) {
            new Alert(Alert.AlertType.ERROR, "Wrong Inputs!").show();
        }
    }

}
