package application.Bluemarble.Client.ConnectModal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.MainController;
import application.Bluemarble.Client.Client;
import application.Bluemarble.Client.SetNicknameModal.SetNicknameModalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConnectModalController implements Initializable {


    @FXML private Label lbMessage;
	Client client = new Client();
	
    @FXML
    void onClickDummy(ActionEvent e) throws IOException {
        Node node = (Node)(e.getSource());
        Stage stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(SetNicknameModalController.class.getResource("SetNicknameModalUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("부루마블");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onClickCancel(ActionEvent e) throws IOException {
        Node node = (Node)(e.getSource());
        Stage stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(MainController.class.getResource("MainUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("부루마블");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
