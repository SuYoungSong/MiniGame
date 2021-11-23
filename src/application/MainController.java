package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController {
    private Stage stage;
    
    // 이동 함수
    void stageChange(MouseEvent e, String fxmlUrl, String title) throws IOException {
    	Node node = (Node)(e.getSource());
    	stage = (Stage)(node.getScene().getWindow());
    	Parent root = FXMLLoader.load(getClass().getResource(fxmlUrl));
    	Scene scene = new Scene(root);
    	stage.setTitle(title);
    	stage.setScene(scene);
    	stage.show();
    }
    @FXML	// 오목 게임으로 이동
    void onClickOmokButton(MouseEvent e) throws IOException {
    	stageChange(e,"omok/OmokUI.fxml", "오목");
    }
    
    @FXML	// 끝말잇기(개인)으로 이동
    void onClickWorkChainGameButton(MouseEvent e) throws IOException {
    	stageChange(e,"wordChainGame/WordChainGameUI.fxml", "끝말잇기");
    }
    
    @FXML	// 끝말잇기(컴퓨터)로 이동
    void onClickWorkChainGameComputerButton(MouseEvent e) throws IOException {
    	stageChange(e,"wordChainGame/WordChainGameComputerUI.fxml", "컴퓨터와 함께하는 끝말잇기");
    }
    
    @FXML	// 2048로 이동
    void onClick2048Button(MouseEvent e) throws IOException {
    	stageChange(e,"play2048/Play2048UI.fxml", "2048");
    }
    
    @FXML	// 부루마블로 이동
    void onClickBluemarbleButton(MouseEvent e) throws IOException {
    	stageChange(e,"bluemarble/BluemarbleGameUI.fxml", "부루마블");
    }
    
    @FXML	// 마우스 호버 이벤트
    void onHoverEnter(MouseEvent e) {
        Node source = (Node)e.getSource();
        source.setStyle("-fx-cursor:hand;");
    }
    @FXML	// 마우스 호버 끝난경우
    void onHoverExit(MouseEvent e) {
        Node source = (Node)e.getSource();
        source.setStyle("-fx-cursor:default;");
    }
}