package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.in;

public class DashboardController implements Initializable {

    public AnchorPane ancpane;

   public void btnEmployeeOnAction(ActionEvent actionEvent) {

       try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/addEmployee.fxml"));
            ancpane.getChildren().clear();
            ancpane.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnDashboardOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            ancpane.getChildren().clear();
            ancpane.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/addCustomer.fxml"));
            ancpane.getChildren().clear();
            ancpane.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
            ancpane.getChildren().clear();
            ancpane.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/addSupplier.fxml"));
            ancpane.getChildren().clear();
            ancpane.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAboutOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/about.fxml"));
            ancpane.getChildren().clear();
            ancpane.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ancpane.getChildren().clear();
        ancpane.getChildren().add(pane);

    }
}
