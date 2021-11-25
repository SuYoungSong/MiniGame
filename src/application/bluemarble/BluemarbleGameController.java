package application.bluemarble;

import application.Main;
import application.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class BluemarbleGameController implements Initializable{

    @FXML private AnchorPane apPlayer1Container;
    @FXML private AnchorPane apPlayer2Container;
    @FXML private AnchorPane apPlayer3Container;
    @FXML private AnchorPane apPlayer4Container;
    @FXML private Pane pPlayer1Profile;
    @FXML private Pane pPlayer2Profile;
    @FXML private Pane pPlayer3Profile;
    @FXML private Pane pPlayer4Profile;
    @FXML private Text tPlayer1Asset;
    @FXML private Text tPlayer1Money;
    @FXML private Text tPlayer1Nickname;
    @FXML private Text tPlayer2Asset;
    @FXML private Text tPlayer2Money;
    @FXML private Text tPlayer2Nickname;
    @FXML private Text tPlayer3Asset;
    @FXML private Text tPlayer3Money;
    @FXML private Text tPlayer3Nickname;
    @FXML private Text tPlayer4Asset;
    @FXML private Text tPlayer4Money;
    @FXML private Text tPlayer4Nickname;

    final private byte goldCardNum = 10;	// 황금카드 갯수
	Player[] player = new Player[5];        // 플레이어는 1 ~ 4번으로 0번 인덱스는 사용하지 않습니다.
    Image birdImage = new Image(Main.class.getResourceAsStream("texture/horse_bird.png"));
    Image dinosaurImage = new Image(Main.class.getResourceAsStream("texture/horse_dinosaur.png"));
    Image fairyImage = new Image(Main.class.getResourceAsStream("texture/horse_fairy.png"));
    Image ghostImage = new Image(Main.class.getResourceAsStream("texture/horse_ghost.png"));

    //숫자 통화단위로 변환
    String convertNumberToCurrency(long num){
        return df.format(num);
    }
    //생성된 플레이어 객체 FX로 연결
    void connectObjectToFX(){
        for(int i = 1; i < playerCnt + 1; i++){
                switch(i){
                    case 1:
                        ImageView iv = new ImageView();
                        pPlayer1Profile.getChildren().add(iv);
                        iv.setFitHeight(120);
                        iv.setFitWidth(120);
                        iv.setImage(player[i].profileImgURI());
                        tPlayer1Nickname.setText(player[i].nickname());
                        tPlayer1Asset.setText(convertNumberToCurrency(player[i].asset()));
                        tPlayer1Money.setText(convertNumberToCurrency(player[i].money()));
                        apPlayer1Container.setVisible(true);
                        break;
                    case 2:
                        ImageView iv2 = new ImageView();
                        pPlayer2Profile.getChildren().add(iv2);
                        iv2.setFitHeight(120);
                        iv2.setFitWidth(120);
                        iv2.setImage(player[i].profileImgURI());
                        tPlayer2Nickname.setText(player[i].nickname());
                        tPlayer2Asset.setText(convertNumberToCurrency(player[i].asset()));
                        tPlayer2Money.setText(convertNumberToCurrency(player[i].money()));
                        apPlayer2Container.setVisible(true);
                        break;
                    case 3:
                        ImageView iv3 = new ImageView();
                        pPlayer3Profile.getChildren().add(iv3);
                        iv3.setFitHeight(120);
                        iv3.setFitWidth(120);
                        iv3.setImage(player[i].profileImgURI());
                        tPlayer3Nickname.setText(player[i].nickname());
                        tPlayer3Asset.setText(convertNumberToCurrency(player[i].asset()));
                        tPlayer3Money.setText(convertNumberToCurrency(player[i].money()));
                        apPlayer3Container.setVisible(true);
                        break;
                    case 4:
                        ImageView iv4 = new ImageView();
                        pPlayer4Profile.getChildren().add(iv4);
                        iv4.setFitHeight(120);
                        iv4.setFitWidth(120);
                        iv4.setImage(player[i].profileImgURI());
                        tPlayer4Nickname.setText(player[i].nickname());
                        tPlayer4Asset.setText(convertNumberToCurrency(player[i].asset()));
                        tPlayer4Money.setText(convertNumberToCurrency(player[i].money()));
                        apPlayer4Container.setVisible(true);
                        break;
                    default: break;
                }
        }
    }

    void printPlayerObject(){
        for(int i = 1; i < selectPlayer.length; i++){
            System.out.println("player " + i + " nickname >> " + player[i].nickname());
            System.out.println("player " + i + " money >> " + player[i].money());
            System.out.println("player " + i + " asset >> " + player[i].asset());
            System.out.println("player " + i + " imageURI >> " + player[i].profileImgURI());
        }
        System.out.println();
    }
    @FXML void onClickRunDice(ActionEvent e) {
        printPlayerObject();
	  }
	 
	//   마우스 호버 액션
	  @FXML void onHoverEnter(MouseEvent e) {
	      Node source = (Node)e.getSource();
	      source.setStyle("-fx-cursor:hand;");
	  }
	  @FXML void onHoverExit(MouseEvent e) {
	      Node source = (Node)e.getSource();
	      source.setStyle("-fx-cursor:default;");
	  }


//  ==================================================
//               Start Bluemarble Modal
//  ==================================================
    DecimalFormat df =  new DecimalFormat("###,###");
    @FXML private AnchorPane apStartBluemarbleModal;
    @FXML private ToggleGroup startDistMoneyGroup;
    @FXML private ToggleGroup PlayerCntGroup;
    @FXML private RadioButton rb2Player;
    @FXML private RadioButton rb3Player;
    @FXML private RadioButton rb4Player;
    @FXML private RadioButton rbDefaultDistMoney;
    @FXML private RadioButton rbCustomDistMoney;
    @FXML private TextField tfStartDistMoney;
    @FXML private TextField tf1PlayerNickname;
    @FXML private TextField tf2PlayerNickname;
    @FXML private TextField tf3PlayerNickname;
    @FXML private TextField tf4PlayerNickname;
    @FXML private Text tStartModalMessage;
    @FXML private Pane pPlayer1;
    @FXML private Pane pPlayer2;
    @FXML private Pane pPlayer3;
    @FXML private Pane pPlayer4;
    private boolean[] selectPlayer = new boolean[5];
    private int playerCnt; //선택 해야할 카드 개수 (총 플레이 인원 수)
    private int selectedCharacterCnt; //선택된 카드 개수

//    캐릭터 선택 카드 토글
    @FXML void onToggleCharacterCard(MouseEvent e){
        Node source = (Node)e.getSource();
        int i = getIndexFromSource(e);
        //카드 선택시 상태 확인
        if(!selectPlayer[i]){
            if(selectedCharacterCnt < playerCnt) {
                source.setStyle("-fx-opacity: 0;-fx-background-color: #000000");
                selectedCharacterCnt++;
                selectPlayer[i] = true;
            }
        } else {
            source.setStyle("-fx-opacity: 0.5;-fx-background-color: #000000");
            selectedCharacterCnt--;
            selectPlayer[i] = false;
        }
    }
//    사용자 지정 금액: 숫자만 입력 허용
    @FXML void onType(KeyEvent e) {
        try{
            int position = tfStartDistMoney.getCaretPosition();
            String str = tfStartDistMoney.getText();
            String replaceStr = str.replaceAll("[^0-9]", "");
            Platform.runLater(() -> {
                        tfStartDistMoney.setText(replaceStr);
                        tfStartDistMoney.positionCaret(position);
                    }
            );
        } catch(Exception err) {
            System.out.println("[ Bluemarble ] 입력 에러 >> " + err.toString());
        }
    }
    //카드 배경 및 상태 초기화
    void setCard(int cnt){
        selectPlayer[1] = false;
        selectPlayer[2] = false;
        selectPlayer[3] = false;
        selectPlayer[4] = false;
        pPlayer1.setStyle("-fx-background-color: #000000;-fx-opacity: 0.5");
        pPlayer2.setStyle("-fx-background-color: #000000;-fx-opacity: 0.5");
        pPlayer3.setStyle("-fx-background-color: #000000;-fx-opacity: 0.5");
        pPlayer4.setStyle("-fx-background-color: #000000;-fx-opacity: 0.5");
        switch (cnt){
            case 2:
                selectedCharacterCnt = 2;
                selectPlayer[1] = true;
                selectPlayer[2] = true;
                pPlayer1.setStyle("-fx-background-color: #000000;-fx-opacity: 0");
                pPlayer2.setStyle("-fx-background-color: #000000;-fx-opacity: 0");
                return;
            case 3:
                selectedCharacterCnt = 3;
                selectPlayer[1] = true;
                selectPlayer[2] = true;
                selectPlayer[3] = true;
                pPlayer1.setStyle("-fx-background-color: #000000;-fx-opacity: 0");
                pPlayer2.setStyle("-fx-background-color: #000000;-fx-opacity: 0");
                pPlayer3.setStyle("-fx-background-color: #000000;-fx-opacity: 0");
                return;
            case 4:
                selectedCharacterCnt = 4;
                selectPlayer[1] = true;
                selectPlayer[2] = true;
                selectPlayer[3] = true;
                selectPlayer[4] = true;
                pPlayer1.setStyle("-fx-background-color: #000000;-fx-opacity: 0");
                pPlayer2.setStyle("-fx-background-color: #000000;-fx-opacity: 0");
                pPlayer3.setStyle("-fx-background-color: #000000;-fx-opacity: 0");
                pPlayer4.setStyle("-fx-background-color: #000000;-fx-opacity: 0");
                return;
            default: return;
        }
    }
//    인원 수 선택
    @FXML void onClickPlayerCntButton(MouseEvent e){
        int i = getIndexFromSource(e);
        switch (i){
            case 2:
                setStartDistMoney(586);
                playerCnt = 2;
                setCard(2);
                break;
            case 3:
                setStartDistMoney(293);
                playerCnt = 3;
                setCard(3);
                break;
            case 4:
                setStartDistMoney(293);
                playerCnt = 4;
                setCard(4);
                break;
            default: break;
        }
    }
//    시작시 분배 금액 사용자 지정 버튼
    @FXML void onClickCustomDistMoneyButton(MouseEvent e) {
        tfStartDistMoney.setDisable(false);
        tfStartDistMoney.requestFocus();
    }
//    시작시 분배 금액 기본 금액 버튼
    @FXML void onClickDefaultDistMoneyButton(MouseEvent e) {
        if(rb2Player.isSelected()) setStartDistMoney(586);
        else setStartDistMoney(293);
        tfStartDistMoney.setDisable(true);
    }
    //모달 하단 메세지 설정 (미완성)
    void setStartModalMessage(String str, Color color){
        tStartModalMessage.setText(str);
        tStartModalMessage.setStyle("-fx-text-fill: #4d4ffa");
    }
//    모달 취소 버튼
    @FXML void onCloseCreateRoomModal(MouseEvent e) throws IOException {
        Node node = (Node)(e.getSource());
        Stage stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(MainController.class.getResource("MainUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("부루마블");
        stage.setScene(scene);
        stage.show();
    }
//    모달 확인 버튼
    @FXML void onSubmitCreateRoomModal(MouseEvent e) {
        if(tfStartDistMoney.getText().equals("")) {
            setStartModalMessage("시작시 분배할 금액을 입력해주세요.", Color.BLUE);
            return;
        }
        if(selectedCharacterCnt != playerCnt) {
            setStartModalMessage("인원수와 선택된 캐릭터 수가 같지 않습니다.", Color.BLUE);
            return;
        }
        int objectCnt = 1;
        for(int i = 1; i < selectPlayer.length; i++){
            if(selectPlayer[i] == false) continue;
            String nickname = "";
            Image imageURI = null;
            System.out.println("submit i >> " + i);
            int money = Integer.parseInt(tfStartDistMoney.getText() + "0000");
            switch(i){
                case 1:
                    nickname = tf1PlayerNickname.getText();
                    imageURI = birdImage;
                    break;
                case 2:
                    nickname = tf2PlayerNickname.getText();
                    imageURI = dinosaurImage;
                    break;
                case 3:
                    nickname = tf3PlayerNickname.getText();
                    imageURI = fairyImage;
                    break;
                case 4:
                    nickname = tf4PlayerNickname.getText();
                    imageURI = ghostImage;
                    break;
                default: break;
            }
            try {
            //인원 수에 맞춰 객체 생성
                player[objectCnt] = new Player(nickname, money, imageURI);
                System.out.println("[ Bluemarble ] 유저 객체 생성 완료 " + objectCnt);
                System.out.println("[ Bluemarble ] 선택된 카드 번호 [" + i + "]");
            } catch(Exception err) {
                System.out.println("[ Bluemarble ] 유저 객체 생성 에러 >> " + err.toString());
                return;
            }
            objectCnt++;
        }
        apStartBluemarbleModal.setVisible(false);
        connectObjectToFX();
    }
//    캐릭터 카드 호버 시작
    @FXML void onHoverEnterCard(MouseEvent e) {
        Node source = (Node)e.getSource();
        String cardId = source.idProperty().getValue();
        int i  = Integer.parseInt(cardId.replaceAll("[^0-9]", ""));
        if((0 < i || i < 5) && (selectPlayer[i])) return;
        else source.setStyle("-fx-cursor:hand;-fx-background-color: #000000;-fx-opacity: 0.1");
    }
//    캐릭터 카드 호버 종료
    @FXML void onHoverExitCard(MouseEvent e) {
        Node source = (Node)e.getSource();
        String cardId = source.idProperty().getValue();
        int i  = Integer.parseInt(cardId.replaceAll("[^0-9]", ""));
        if((0 < i || i < 5) && (selectPlayer[i])) return;
        else source.setStyle("-fx-cursor:hand;-fx-background-color: #000000;-fx-opacity: 0.5");
    }
//    코드에서 숫자만 추출
    int getIndexFromSource(MouseEvent e){
        Node source = (Node)e.getSource();
        String cardId = source.idProperty().getValue();
        return Integer.parseInt(cardId.replaceAll("[^0-9]", ""));
    }
    void setStartDistMoney(long v){ tfStartDistMoney.setText(df.format(v)); }
    void initStartBluemarbleModal() {
        setCard(2);
        playerCnt = 2;
        setStartDistMoney(586);
        tfStartDistMoney.setDisable(true);
        rbDefaultDistMoney.setSelected(true);
        rb2Player.setSelected(true);
        apStartBluemarbleModal.setVisible(true);
        apPlayer1Container.setVisible(false);
        apPlayer2Container.setVisible(false);
        apPlayer3Container.setVisible(false);
        apPlayer4Container.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initStartBluemarbleModal(); // 부루마블 시작 모달 초기화
    }
}

	