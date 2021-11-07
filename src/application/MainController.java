package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import application.bluemarble.testController;

public class MainController {
    private Stage stage;

    void stageChange(Node node, String fxmlUrl, String title) throws IOException {
    	stage = (Stage)(node.getScene().getWindow());
    	Parent root = FXMLLoader.load(getClass().getResource(fxmlUrl));
    	Scene scene = new Scene(root);
    	stage.setTitle(title);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML
    void onClickOmokButton(MouseEvent e) throws IOException {
    	Node node = (Node)(e.getSource());
    	stageChange(node,"OmokUI.fxml", "오목");
    }
    @FXML
    void onClickWorkChainGameButton(MouseEvent e) throws IOException {
    	Node node = (Node)(e.getSource());
    	stageChange(node,"WordChainGameUI.fxml", "끝말잇기");
    }
    @FXML
    void onClickWorkChainGameComputerButton(MouseEvent e) throws IOException {
    	Node node = (Node)(e.getSource());
    	stageChange(node,"WordChainGameComputerUI.fxml", "컴퓨터와 함께하는 끝말잇기");
    }
    @FXML
    void onClick2048Button(MouseEvent e) throws IOException {
    	Node node = (Node)(e.getSource());
    	stageChange(node,"Play2048UI.fxml", "2048");
    }
    @FXML
    void onClickBluemarbleButton(MouseEvent e) throws IOException {
    	Node node = (Node)(e.getSource());
    	stageChange(node,"bluemarble/testUI.fxml", "부루마블");
    }
    @FXML
    void onHoverEnter(MouseEvent e) {
        Node source = (Node)e.getSource();
        source.setStyle("-fx-cursor:hand;");
    }
    @FXML
    void onHoverExit(MouseEvent e) {
        Node source = (Node)e.getSource();
        source.setStyle("-fx-cursor:default;");
    }
}