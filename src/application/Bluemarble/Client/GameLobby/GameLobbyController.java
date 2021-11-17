package application.Bluemarble.Client.GameLobby;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.MainController;
import application.Bluemarble.Client.Client;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameLobbyController extends Client implements Initializable{

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
    @FXML private Text tUserNickname;


    public void setResMsg(String str[]){

        //ex) payload[] >> [0]checkNickname, [1]true OR false, [2]"nickname"
        if(str[1].contains("true")){
            setMessage("동일한 닉네임이 존재합니다.", false);
        } else {
            nicSetWindow.setVisible(false);
            tUserNickname.setText(str[2]);
        }
    }

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
    // 게임 접속실패 창에서 메인으로 돌아가는 버튼
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

    @FXML
    void onClickSetNicknameModal(ActionEvent e) {
    	nicSetWindow.setVisible(true);    			// 닉네임 설정창 활성화
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
    void onClickGameLobbyConnect(MouseEvent e) {
//        System.out.println("GameLobby Mouse Event >> " + e);
        setMouseEvent(e);
        isConnect = true;
        final String nickname = tfUserInputNickName.getText();
//        .format 적용예정
//        send(String.format("%s%s%s", "@@payload", "##checkNickname", "##" + nickname));
        send("@@payload:" + "##checkNickname" + "##" + nickname);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	if(!isConnect) {
	        startClient("localhost", 5005);
	        nicSetWindow.setVisible(true);
    	}
    }
}
