package application.Bluemarble.Client.GameLobby;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class GameLobbyController{

    @FXML private ScrollPane GameRoomContainer;
    @FXML private AnchorPane GameRoomCreateWindow;
    @FXML private ScrollPane PlayerListContainer;
    @FXML private TextField TFGameRoomCreateName;

    // 게임 로비 방만들기 버튼 클릭
    @FXML
    void onClickCreateRoom(ActionEvent event) {
    	GameRoomCreateWindow.setVisible(true);
    }
    
    // 방만들기 창 방만들기 버튼 클릭
    @FXML
    void onClickGameRoomCreateMake(ActionEvent event) {

    }
    // 방만들기창 취소버튼 클릭
    @FXML
    void onClickGameRoomCreateCancle(ActionEvent event) {
    	GameRoomCreateWindow.setVisible(false);
    	TFGameRoomCreateName.clear();
    }
}
