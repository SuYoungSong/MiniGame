package application.bluemarble;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import application.Main;
import application.MainController;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

public class BluemarbleGameController implements Initializable {


    @FXML private AnchorPane apPlayer1Container;
    @FXML private AnchorPane apPlayer2Container;
    @FXML private AnchorPane apPlayer3Container;
    @FXML private AnchorPane apPlayer4Container;
    @FXML private AnchorPane atheanaePane;
    @FXML private AnchorPane berlinPane;
    @FXML private AnchorPane buenosAiresPane;
    @FXML private AnchorPane busanPane;
    @FXML private AnchorPane cairoPane;
    @FXML private AnchorPane colombiaPane;
    @FXML private AnchorPane concordePane;
    @FXML private AnchorPane copenhagenPane;
    @FXML private AnchorPane goldCardPane1;
    @FXML private AnchorPane goldCardPane2;
    @FXML private AnchorPane goldCardPane3;
    @FXML private AnchorPane goldCardPane4;
    @FXML private AnchorPane goldCardPane5;
    @FXML private AnchorPane goldCardPane6;
    @FXML private AnchorPane hawaiiPane;
    @FXML private AnchorPane hongKongPane;
    @FXML private AnchorPane islandPane;
    @FXML private AnchorPane istanbulPane;
    @FXML private AnchorPane jejuPane;
    @FXML private AnchorPane lisbonPane;
    @FXML private AnchorPane londonPane;
    @FXML private AnchorPane madridPane;
    @FXML private AnchorPane manilaPane;
    @FXML private AnchorPane montrealPane;
    @FXML private AnchorPane newYorkPane;
    @FXML private Pane pPlayer1Profile;
    @FXML private Pane pPlayer2Profile;
    @FXML private Pane pPlayer3Profile;
    @FXML private Pane pPlayer4Profile;
    @FXML private AnchorPane parisPane;
    @FXML private AnchorPane queenElizabethPane;
    @FXML private AnchorPane romaPane;
    @FXML private AnchorPane saoPauloPane;
    @FXML private AnchorPane seoulPane;
    @FXML private AnchorPane singaporePane;
    @FXML private AnchorPane socialMoneyGetPane;
    @FXML private AnchorPane socialMoneyPayPane;
    @FXML private AnchorPane spacePane;
    @FXML private AnchorPane startPane;
    @FXML private AnchorPane stockholmPane;
    @FXML private AnchorPane sydneyPane;
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
    @FXML private AnchorPane taibeiPane;
    @FXML private AnchorPane tokyoPane;
    @FXML private AnchorPane zurichPane;

	final private byte goldCardNum = 10; // 황금카드 갯수
	Player[] player = new Player[5]; // 플레이어는 1 ~ 4번으로 0번 인덱스는 사용하지 않습니다.
	Image birdImage = new Image(Main.class.getResourceAsStream("texture/horse_bird.png"));
	Image dinosaurImage = new Image(Main.class.getResourceAsStream("texture/horse_dinosaur.png"));
	Image fairyImage = new Image(Main.class.getResourceAsStream("texture/horse_fairy.png"));
	Image ghostImage = new Image(Main.class.getResourceAsStream("texture/horse_ghost.png"));

	Text[] profileAsset = new Text[5];
	Text[] profileMoney = new Text[5];
	Text[] profileNickname = new Text[5];
	AnchorPane[] playerContainer = new AnchorPane[5];
	Pane[] profilePane = new Pane[5];
	ImageView[] playerHorse = new ImageView[5];
	byte[] playerGroundNumber = { 0, 0, 0, 0, 0 };

	// 인원수에 맞게 text 관리 배열 생성
	void profileSettting() {
		switch (playerCnt) {
		case 4:
			profileAsset[4] = tPlayer4Asset;
			profileMoney[4] = tPlayer4Money;
			profileNickname[4] = tPlayer4Nickname;
			playerContainer[4] = apPlayer4Container;
			profilePane[4] = pPlayer4Profile;
		case 3:
			profileAsset[3] = tPlayer3Asset;
			profileMoney[3] = tPlayer3Money;
			profileNickname[3] = tPlayer3Nickname;
			playerContainer[3] = apPlayer3Container;
			profilePane[3] = pPlayer3Profile;
		case 2:
			profileAsset[2] = tPlayer2Asset;
			profileMoney[2] = tPlayer2Money;
			profileNickname[2] = tPlayer2Nickname;
			playerContainer[2] = apPlayer2Container;
			profilePane[2] = pPlayer2Profile;
		case 1:
			profileAsset[1] = tPlayer1Asset;
			profileMoney[1] = tPlayer1Money;
			profileNickname[1] = tPlayer1Nickname;
			playerContainer[1] = apPlayer1Container;
			profilePane[1] = pPlayer1Profile;
			break;
		}
	}

	// 숫자 통화단위로 변환
	String convertNumberToCurrency(long num) {
		return df.format(num);
	}

	// 생성된 플레이어 객체 FX로 연결
	void connectObjectToFX() {
		ImageView[] iv = new ImageView[playerCnt + 1];
		for (int i = 1; i < playerCnt + 1; i++) {
			iv[i] = new ImageView(player[i].profileImgURI());
			iv[i].setFitHeight(120);
			iv[i].setFitWidth(120);
			profilePane[i].getChildren().add(iv[i]);
			profileAsset[i].setText(convertNumberToCurrency(player[i].asset()));
			profileMoney[i].setText(convertNumberToCurrency(player[i].money()));
			profileNickname[i].setText(player[i].nickname());
			playerContainer[i].setVisible(true);
		}
	}

	void printPlayerObject() {
		for (int i = 1; i < playerCnt + 1 ; i++) {
			System.out.println("player " + i + " nickname >> " + player[i].nickname());
			System.out.println("player " + i + " money >> " + player[i].money());
			System.out.println("player " + i + " asset >> " + player[i].asset());
			System.out.println("player " + i + " imageURI >> " + player[i].profileImgURI());
		}
		System.out.println();
	}
	@FXML private AnchorPane apGameRoom;
	// 플레이어를 시작Pane에 배치
	void playerPlaceStart() {
		for(int i = 1 ; i < playerCnt +1 ; i++ ) {
			playerHorse[i] = new ImageView( player[i].profileImgURI()  );
			playerHorse[i].setFitHeight(30);
			playerHorse[i].setFitWidth(30);
			playerHorse[i].setX(10);
			playerHorse[i].setY(10);
			apGameRoom.getChildren().add(playerHorse[i]);
			playerHorse[i].setLayoutX(startPane.getLayoutX());
			playerHorse[i].setLayoutY(startPane.getLayoutY());
		}
	}
/////////////////////////////////////////////////////////////
	//임시
	//
    @FXML  private ImageView dice1;
    @FXML  private ImageView dice2;
	int turnCount = 1;
	@FXML
	void onClickRunDice(ActionEvent e) {
		// 더블 구현해야함.
		ImageView[] diceIV = { dice1, dice2 };
		int randomDice = 0, sum = 0;	
		for(int i = 0 ; i<2 ; i++) {
			randomDice = (int)(Math.random()*6)+1;
			sum += randomDice;
			diceIV[i].setImage(new Image(Main.class.getResourceAsStream("texture/"+randomDice+".png")));
		}
		playerMove(sum, turnCount);
		// 턴 설정
		turnCount++;
		if(turnCount>playerCnt) turnCount = 1;
	}
////////////////////////////////////
	
	void playerMove(int moveNum, int playerNum) {
		AnchorPane[] groundNum = {startPane, taibeiPane, goldCardPane1, hongKongPane, manilaPane, jejuPane, singaporePane, goldCardPane2, cairoPane, istanbulPane,
				islandPane, atheanaePane, goldCardPane3, copenhagenPane, stockholmPane, concordePane, zurichPane, goldCardPane4, berlinPane, montrealPane,
				socialMoneyGetPane, buenosAiresPane, goldCardPane5, saoPauloPane, sydneyPane, busanPane, hawaiiPane, lisbonPane, queenElizabethPane, madridPane,
				spacePane, tokyoPane, colombiaPane, parisPane, romaPane, goldCardPane6, londonPane, newYorkPane, socialMoneyPayPane, seoulPane};
		System.out.printf("%d플레이어 이동전 땅 %s \n",playerNum, groundNum[playerGroundNumber[playerNum]]);
		System.out.printf("%d플레이어 이동전 땅 %f %f \n",playerNum, groundNum[playerGroundNumber[playerNum]].getLayoutX(),groundNum[playerGroundNumber[playerNum]].getLayoutY());
		AnchorPane beforePane = groundNum[playerGroundNumber[playerNum]];
		double afterX, afterY;
		// 기존 위치에서 주사위 굴린 만큼 이동한 결과 저장
		playerGroundNumber[playerNum] += moveNum;
		if(playerGroundNumber[playerNum]>=40) {
			playerGroundNumber[playerNum] -= 40;
		}

		// 플레이어 이동
		new TranslateTransition();
		TranslateTransition tt = new TranslateTransition(new Duration(500), playerHorse[playerNum]);
		// 이동을 시작할 위치
		tt.setFromX(beforePane.getLayoutX());
		tt.setFromY(beforePane.getLayoutY());
		// 이동할 목적지
		afterX = (groundNum[playerGroundNumber[playerNum]].getLayoutX());
		afterY = (groundNum[playerGroundNumber[playerNum]].getLayoutY());
        tt.setToX(afterX);
        tt.setToY(afterY);
        tt.play();
        playerHorse[playerNum].setLayoutX(afterX);
        playerHorse[playerNum].setLayoutY(afterY);
        System.out.printf("%d플레이어 이동후 땅 %s \n",playerNum, groundNum[playerGroundNumber[playerNum]]);
        System.out.printf("%d플레이어 이동전 후 %f %f \n",playerNum, groundNum[playerGroundNumber[playerNum]].getLayoutX(),groundNum[playerGroundNumber[playerNum]].getLayoutY());
//        beforePane.getChildren().remove(playerHorse[playerNum]);
//        groundNum[playerGroundNumber[playerNum]].getChildren().add(playerHorse[playerNum]);
		}
	
	// 마우스 호버 액션
	@FXML
	void onHoverEnter(MouseEvent e) {
		Node source = (Node) e.getSource();
		source.setStyle("-fx-cursor:hand;");
	}

	@FXML
	void onHoverExit(MouseEvent e) {
		Node source = (Node) e.getSource();
		source.setStyle("-fx-cursor:default;");
	}

	// ==================================================
	//    Start Bluemarble Modal
	//==================================================
	DecimalFormat df = new DecimalFormat("###,###");
	@FXML	private AnchorPane apStartBluemarbleModal;
	@FXML	private ToggleGroup startDistMoneyGroup;
	@FXML	private ToggleGroup PlayerCntGroup;
	@FXML	private RadioButton rb2Player;
	@FXML	private RadioButton rb3Player;
	@FXML	private RadioButton rb4Player;
	@FXML	private RadioButton rbDefaultDistMoney;
	@FXML	private RadioButton rbCustomDistMoney;
	@FXML	private TextField tfStartDistMoney;
	@FXML	private TextField tf1PlayerNickname;
	@FXML	private TextField tf2PlayerNickname;
	@FXML	private TextField tf3PlayerNickname;
	@FXML	private TextField tf4PlayerNickname;
	@FXML	private Text tStartModalMessage;
	@FXML	private Pane pPlayer1;
	@FXML	private Pane pPlayer2;
	@FXML	private Pane pPlayer3;
	@FXML	private Pane pPlayer4;
	private boolean[] selectPlayer = new boolean[5];
	private int playerCnt; // 선택 해야할 카드 개수 (총 플레이 인원 수)
	private int selectedCharacterCnt; // 선택된 카드 개수

	//캐릭터 선택 카드 토글
	@FXML
	void onToggleCharacterCard(MouseEvent e) {
		Node source = (Node) e.getSource();
		int i = getIndexFromSource(e);
		//카드 선택시 상태 확인
		if (!selectPlayer[i]) {
			if (selectedCharacterCnt < playerCnt) {
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

	//사용자 지정 금액: 숫자만 입력 허용
	@FXML
	void onType(KeyEvent e) {
		try {
			int position = tfStartDistMoney.getCaretPosition();
			String str = tfStartDistMoney.getText();
			String replaceStr = str.replaceAll("[^0-9]", "");
			Platform.runLater(() -> {
				tfStartDistMoney.setText(replaceStr);
				tfStartDistMoney.positionCaret(position);
			});
		} catch (Exception err) {
			System.out.println("[ Bluemarble ] 입력 에러 >> " + err.toString());
		}
	}

	//카드 배경 및 상태 초기화
	void setCard(int cnt) {
		Pane[] playerPane = { new Pane(), pPlayer1, pPlayer2, pPlayer3, pPlayer4 };
		// 카드 배경 초기화
		for(int i = 1 ; i < cnt+1 ; i++) {
			selectPlayer[i] = false;
			playerPane[i].setStyle("-fx-background-color: #000000;-fx-opacity: 0.5");
		}
		// 선택된 Pane 투명도 초기화
		for(int i = 1 ; i<cnt+1 ; i++) {
			selectedCharacterCnt = cnt;
			selectPlayer[i] = true;
			playerPane[i].setStyle("-fx-background-color: #000000;-fx-opacity: 0");
		}
	}

	//인원 수 선택
	@FXML
	void onClickPlayerCntButton(MouseEvent e) {
		int i = getIndexFromSource(e);
		switch (i) {
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
		default:
			break;
		}
	}

	//시작시 분배 금액 사용자 지정 버튼
	@FXML
	void onClickCustomDistMoneyButton(MouseEvent e) {
		tfStartDistMoney.setDisable(false);
		tfStartDistMoney.requestFocus();
	}

	//시작시 분배 금액 기본 금액 버튼
	@FXML
	void onClickDefaultDistMoneyButton(MouseEvent e) {
		if (rb2Player.isSelected())
			setStartDistMoney(586);
		else
			setStartDistMoney(293);
		tfStartDistMoney.setDisable(true);
	}

	//모달 하단 메세지 설정 (미완성)
	void setStartModalMessage(String str, Color color) {
		tStartModalMessage.setText(str);
		tStartModalMessage.setStyle("-fx-text-fill: #4d4ffa");
	}

	//모달 취소 버튼
	@FXML
	void onCloseCreateRoomModal(MouseEvent e) throws IOException {
		Node node = (Node) (e.getSource());
		Stage stage = (Stage) (node.getScene().getWindow());
		Parent root = FXMLLoader.load(MainController.class.getResource("MainUI.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("부루마블");
		stage.setScene(scene);
		stage.show();
	}

	//모달 확인 버튼
	@FXML
	void onSubmitCreateRoomModal(MouseEvent e) {
		if (tfStartDistMoney.getText().equals("")) {
			setStartModalMessage("시작시 분배할 금액을 입력해주세요.", Color.BLUE);
			return;
		}
		if (selectedCharacterCnt != playerCnt) {
			setStartModalMessage("인원수와 선택된 캐릭터 수가 같지 않습니다.", Color.BLUE);
			return;
		}
		int objectCnt = 1;
		for (int i = 1; i < selectPlayer.length; i++) {
			if (selectPlayer[i] == false)
				continue;
			String nickname = "";
			Image imageURI = null;
			System.out.println("submit i >> " + i);
			int money = Integer.parseInt(tfStartDistMoney.getText() + "0000");
			switch (i) {
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
			default:
				break;
			}
			try {
				// 인원 수에 맞춰 객체 생성
				player[objectCnt] = new Player(nickname, money, imageURI);
				System.out.println("[ Bluemarble ] 유저 객체 생성 완료 " + objectCnt);
				System.out.println("[ Bluemarble ] 선택된 카드 번호 [" + i + "]");
			} catch (Exception err) {
				System.out.println("[ Bluemarble ] 유저 객체 생성 에러 >> " + err.toString());
				return;
			}
			objectCnt++;
		}
		apStartBluemarbleModal.setVisible(false);
		profileSettting();
		connectObjectToFX();
		playerPlaceStart();
	}

	//캐릭터 카드 호버 시작
	@FXML
	void onHoverEnterCard(MouseEvent e) {
		Node source = (Node) e.getSource();
		String cardId = source.idProperty().getValue();
		int i = Integer.parseInt(cardId.replaceAll("[^0-9]", ""));
		if ((0 < i || i < 5) && (selectPlayer[i]))
			return;
		else
			source.setStyle("-fx-cursor:hand;-fx-background-color: #000000;-fx-opacity: 0.1");
	}

	//캐릭터 카드 호버 종료
	@FXML
	void onHoverExitCard(MouseEvent e) {
		Node source = (Node) e.getSource();
		String cardId = source.idProperty().getValue();
		int i = Integer.parseInt(cardId.replaceAll("[^0-9]", ""));
		if ((0 < i || i < 5) && (selectPlayer[i]))
			return;
		else
			source.setStyle("-fx-cursor:hand;-fx-background-color: #000000;-fx-opacity: 0.5");
	}

	//코드에서 숫자만 추출
	int getIndexFromSource(MouseEvent e) {
		Node source = (Node) e.getSource();
		String cardId = source.idProperty().getValue();
		return Integer.parseInt(cardId.replaceAll("[^0-9]", ""));
	}

	void setStartDistMoney(long v) {
		tfStartDistMoney.setText(df.format(v));
	}

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
