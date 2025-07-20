package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeDashboardController {


    public AnchorPane ancpane;

    public void btnLogoutOnAction(ActionEvent actionEvent) {

         Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/placeOrder.fxml"));
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

    public void btnAboutOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/about.fxml"));
            ancpane.getChildren().clear();
            ancpane.getChildren().add(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


