package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {

    @FXML
    private JFXComboBox<?> CustomerComboTxt;

    @FXML
    private JFXButton btnAddCart;

    @FXML
    private JFXButton btnRemove;

    @FXML
    private JFXButton btnSearchItem;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private Pane discount;

    @FXML
    private Label finalTotal;

    @FXML
    private Label lblCashierID;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyCount;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<?> tblPlaceOrder;

    @FXML
    private JFXTextField txtCustAddress;

    @FXML
    private JFXTextField txtCustEmail;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtCustPhone;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    void CustomerCombo(ActionEvent event) {

    }

    @FXML
    void btnAddCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrintBillOnAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

    @FXML
    void searchCustOnAction(ActionEvent event) {

    }

    @FXML
    void searchItemOnAction(ActionEvent event) {

    }

    private void localDateAndTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline= new Timeline(
                new KeyFrame(Duration.ZERO,e->{
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour()+" : "+now.getMinute()+" : "+now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localDateAndTime();
    }
}
