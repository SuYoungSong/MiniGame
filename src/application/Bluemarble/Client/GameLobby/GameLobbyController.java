package application.Bluemarble.Client.GameLobby;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.MainController;
import application.Bluemarble.Client.GameRoom.GameRoomController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameLobbyController implements Initializable {

    @FXML private ScrollPane GameRoomContainer;
    @FXML private ScrollPane PlayerListContainer;
    @FXML private AnchorPane GameRoomCreateWindow;
    @FXML private AnchorPane connectFileWindows;
    @FXML private AnchorPane nicSetWindow;
    @FXML private TextField tfGameRoomCreateName;
    @FXML private TextField tfUserInputNickName;
    @FXML private AnchorPane serverConnectTryWindow;
    private boolean enableNickname = false;
    @FXML private Label lbMessage;
//    Client client = new Client();
    
    // 게임 로비 방만들기 버튼 클릭
    @FXML
    void onClickCreateRoom(ActionEvent event) {
    	GameRoomCreateWindow.setVisible(true);
    }
    
    // 방만들기 창 방만들기 버튼 클릭
    @FXML
    void onClickGameRoomCreateMake(ActionEvent e) throws IOException {
    	// 방 이름을 서버로 전송
    	String gameRoomName;
    	try {
    		gameRoomName = tfGameRoomCreateName.getText();
    	} catch (Exception err) {
    		gameRoomName = "부루마블 한판하실분";
		}
//    	client.send(gameRoomName, "CreateRoom");
    	
    	// 게임룸으로 화면 이동
    	Node node = (Node)(e.getSource());
        Stage stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(GameRoomController.class.getResource("GameRoomUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("부루마블");
        stage.setScene(scene);
        stage.show();
    }
    // 방만들기창 취소버튼 클릭
    @FXML
    void onClickGameRoomCreateCancle(ActionEvent event) {
    	GameRoomCreateWindow.setVisible(false);
    	tfGameRoomCreateName.clear();
    }
    // 게임 접속실패 칭에서 메인으로 돌아가는 버튼
    @FXML
    void onClickMain(ActionEvent e) throws IOException {
//    	client.stopClient();
    	Node node = (Node)(e.getSource());
        Stage stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(MainController.class.getResource("MainUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("부루마블");
        stage.setScene(scene);
        stage.show();
    }
    
    void setEnableNickname(boolean bool){ enableNickname = bool; }
    
    boolean isNickname(String nickname){
        //Server로 DB조회 요청 로직 작성
        return true;
    }
    
    void setMessage(String message, boolean type){
        //message: 메세지 / type: true(정상), false(불량)
        if (type){
            lbMessage.setText(message);
            lbMessage.setTextFill(Color.rgb(0, 148, 50));
        } else {
            lbMessage.setText(message);
            lbMessage.setTextFill(Color.rgb(238, 90, 36));
        }
    }
    // 닉네임 설정창에서 접속 버튼
    @FXML
    void onClickGameLobbyConnect(ActionEvent event) {
    	 final String NICKNAME = tfUserInputNickName.getText();
    	 // 닉네임 설정창에서 서버가 열려있지 않은경우 체크
//    	 if(!client.isConnect) {
//    		 connectFileWindows.setVisible(true);    	// 서버 접속실패 창 활성화				
//    	 }
//         if(NICKNAME.equals("")) {
//             setMessage("닉네임을 입력해주세요.", false);
//             return;
//         }
//         if(isNickname(NICKNAME)){
//             setMessage("사용 가능한 닉네임입니다.", true);
//             setEnableNickname(true);
////             return;
//         }
//         // 서버로 닉네임 전달
//         client.send(NICKNAME,"SETNICK");
//         nicSetWindow.setVisible(false);    			    		
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	// 서버에 접속 시도
//		client.startClient("127.0.0.1", 5005);
    	nicSetWindow.setVisible(true);    			// 닉네임 설정창 활성화
    		
    }
}
