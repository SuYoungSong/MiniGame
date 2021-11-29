package application.bluemarble;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import application.MainController;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML private AnchorPane taibeiPane;
    @FXML private AnchorPane hongKongPane;
    @FXML private AnchorPane goldCardPane1;
    @FXML private AnchorPane manilaPane;
    @FXML private AnchorPane jejuPane;
    @FXML private AnchorPane singaporePane;
    @FXML private AnchorPane goldCardPane2;
    @FXML private AnchorPane cairoPane;
    @FXML private AnchorPane istanbulPane;
    @FXML private AnchorPane islandPane;
    @FXML private AnchorPane athenaePane;
    @FXML private AnchorPane goldCardPane3;
    @FXML private AnchorPane copenhagenPane;
    @FXML private AnchorPane stockholmPane;
    @FXML private AnchorPane concordePane;
    @FXML private AnchorPane zurichPane;
    @FXML private AnchorPane goldCardPane4;
    @FXML private AnchorPane berlinPane;
    @FXML private AnchorPane montrealPane;
    @FXML private AnchorPane socialMoneyGetPane;
    @FXML private AnchorPane buenosAiresPane;
    @FXML private AnchorPane goldCardPane5;
    @FXML private AnchorPane saoPauloPane;
    @FXML private AnchorPane sydneyPane;
    @FXML private AnchorPane busanPane;
    @FXML private AnchorPane hawaiiPane;
    @FXML private AnchorPane lisbonPane;
    @FXML private AnchorPane queenElizabethPane;
    @FXML private AnchorPane madridPane;
    @FXML private AnchorPane spacePane;
    @FXML private AnchorPane tokyoPane;
    @FXML private AnchorPane colombiaPane;
    @FXML private AnchorPane parisPane;
    @FXML private AnchorPane romaPane;
    @FXML private AnchorPane goldCardPane6;
    @FXML private AnchorPane londonPane;
    @FXML private AnchorPane newYorkPane;
    @FXML private AnchorPane socialMoneyPayPane;
    @FXML private AnchorPane seoulPane;
    @FXML private AnchorPane startPane;

    @FXML private Pane pPlayer1Profile;
    @FXML private Pane pPlayer2Profile;
    @FXML private Pane pPlayer3Profile;
    @FXML private Pane pPlayer4Profile;
    @FXML private Pane pPlayer1Highlight;
    @FXML private Pane pPlayer2Highlight;
    @FXML private Pane pPlayer3Highlight;
    @FXML private Pane pPlayer4Highlight;

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

    final private byte goldCardNum = 6; // 황금카드 갯수
    GoldCard goldcard = new GoldCard(goldCardNum,this);
    Player[] player = new Player[5]; // 플레이어는 1 ~ 4번으로 0번 인덱스는 사용하지 않습니다.
    //플레이어 프로필 이미지
    Image birdImage = new Image(Main.class.getResourceAsStream("texture/horse_bird.png"));
    Image dinosaurImage = new Image(Main.class.getResourceAsStream("texture/horse_dinosaur.png"));
    Image fairyImage = new Image(Main.class.getResourceAsStream("texture/horse_fairy.png"));
    Image ghostImage = new Image(Main.class.getResourceAsStream("texture/horse_ghost.png"));
    //빌딩 이미지  [ 1 -> 건설된 건물, 0-> 건설안된건물  ex) 호텔만 건설된 경우 001, 소형건물과 호텔이 건설된 경우 101 ]
    Image building000Image = new Image(Main.class.getResourceAsStream("texture/building_flag.png"));
    Image building100Image = new Image(Main.class.getResourceAsStream("texture/building_100.png"));
    Image building010Image = new Image(Main.class.getResourceAsStream("texture/building_010.png"));
    Image building001Image = new Image(Main.class.getResourceAsStream("texture/building_001.png"));
    Image building110Image = new Image(Main.class.getResourceAsStream("texture/building_110.png"));
    Image building011Image = new Image(Main.class.getResourceAsStream("texture/building_011.png"));
    Image building101Image = new Image(Main.class.getResourceAsStream("texture/building_101.png"));
    Image building111Image = new Image(Main.class.getResourceAsStream("texture/building_111.png"));

    String[] LandListKor = {null, "타이베이", "황금카드1", "홍콩", "마닐라",
            "제주도", "싱가포르", "황금카드2", "카이로", "이스탄불",
            "무인도", "아테네", "황금카드3", "코펜하겐", "스톡홀름",
            "콩코드여객기", "취리히", "황금카드4", "베를린", "몬트리올",
            "사회복지기금-지급", "부에노스아이레스", "황금카드5", "상파울루", "시드니",
            "부산", "하와이", "리스본", "퀸엘리자베스", "마드리드",
            "우주정거장", "도쿄", "콜롬비아", "파리", "로마",
            "황금카드6", "런던", "뉴욕", "사회복지기금-납부", "서울", null};

    // FXML을 반복문으로 사용하기 위해 주소를 저장할 배열
    Text[] profileAsset = new Text[5];
    Text[] profileMoney = new Text[5];
    Text[] profileNickname = new Text[5];
    AnchorPane[] playerContainer = new AnchorPane[5];
    Pane[] profilePane = new Pane[5];
    Pane[] profileHighlight = new Pane[5];

    // 주사위 이미지 저장
    @FXML  private ImageView dice1;
    @FXML  private ImageView dice2;
    int turnCount = 1;	// 시작 플레이어 설정
    BuildingData building = new BuildingData();
    byte[] islandExitCount = new byte[5]; 
    boolean isOneMoreTurn;
    // 플레이어가 가진돈의 정보를 불러옴
    void refreshMoney() {
        for(int i = 1 ; i <= playerCnt ; i++) {
            profileMoney[i].setText(String.valueOf(convertNumberToCurrency((player[i].money()))));
        }
    }

    // ==================================================
    //                    Test Code
    // ==================================================
    @FXML void onClickFunc3(ActionEvent e) { goldcard.choiceRandomGoldCard(); }
    @FXML void onClickFunc2(ActionEvent e) {}
    @FXML void onClickFunc(ActionEvent e) {}

    // 반복문으로 사용할 수 있게 배열에 관련 자료를 저장
    void profileSettting() {
        switch (playerCnt) {
            case 4:
                profileAsset[4] = tPlayer4Asset;
                profileMoney[4] = tPlayer4Money;
                profileNickname[4] = tPlayer4Nickname;
                playerContainer[4] = apPlayer4Container;
                profilePane[4] = pPlayer4Profile;
                profileHighlight[4] = pPlayer4Highlight;
            case 3:
                profileAsset[3] = tPlayer3Asset;
                profileMoney[3] = tPlayer3Money;
                profileNickname[3] = tPlayer3Nickname;
                playerContainer[3] = apPlayer3Container;
                profilePane[3] = pPlayer3Profile;
                profileHighlight[3] = pPlayer3Highlight;
            case 2:
                profileAsset[2] = tPlayer2Asset;
                profileMoney[2] = tPlayer2Money;
                profileNickname[2] = tPlayer2Nickname;
                playerContainer[2] = apPlayer2Container;
                profilePane[2] = pPlayer2Profile;
                profileHighlight[2] = pPlayer2Highlight;
            case 1:
                profileAsset[1] = tPlayer1Asset;
                profileMoney[1] = tPlayer1Money;
                profileNickname[1] = tPlayer1Nickname;
                playerContainer[1] = apPlayer1Container;
                profilePane[1] = pPlayer1Profile;
                profileHighlight[1] = pPlayer1Highlight;
                break;
        }
    }

    // 숫자 통화단위로 변환
    String convertNumberToCurrency(long num) {
        return df.format(num);
    }

    // 생성된 플레이어 객체 -> FX로 연결
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
    // 플레이어 정보 출력
    void printPlayerObject() {
        for (int i = 1; i < playerCnt + 1 ; i++) {
            System.out.println("player " + i + " nickname >> " + player[i].nickname());
            System.out.println("player " + i + " money >> " + player[i].money());
            System.out.println("player " + i + " asset >> " + player[i].asset());
            System.out.println("player " + i + " imageURI >> " + player[i].profileImgURI());
        }
        System.out.println();
    }

    
    // ==================================================
    //                      Dice
    // ==================================================

    @FXML private Label lbDiceNumber;
    @FXML private Label lbDiceDouble;
    //현재 턴인 유저 프로필에 하이라이트 추가
    void showProfileHighlight(){
        for(Pane p:profileHighlight){
            if(p == null) continue;
            p.setStyle("");
        }
        profileHighlight[turnCount].setStyle("-fx-border-color: red;-fx-border-width: 12px;-fx-border-radius: 8px");
    }

    Timer timerDice;
    TimerTask timerTaskDice;
    //주사위 결과 출력
    void showDiceNumber(int num, boolean isDouble){
        if(timerDice != null) timerDice.cancel();
        if(isDouble) lbDiceDouble.setVisible(true);
        else lbDiceDouble.setVisible(false);
        lbDiceNumber.setText(Integer.toString(num));
        lbDiceNumber.setVisible(true);
        timerDice = new Timer();
        timerTaskDice = new TimerTask() {
            @Override
            public void run() {
                lbDiceNumber.setVisible(false);
                lbDiceDouble.setVisible(false);
            }
        };
        timerDice.schedule(timerTaskDice, 2000);
    }

    @FXML private Button btnRunDice;
    @FXML	// 주사위를 던지는 메소드
    void onClickRunDice(ActionEvent e) {
    	// 더블이 나와도 특수팬(우주여행, 무인도) 의 경우 턴이 한번 더 진행되지 않게 설정
    	isOneMoreTurn = true;
        btnRunDice.setDisable(true);

        int[] diceResult = new int[2];			// 주사위 결과 저장 -> 더블 체크용도
        ImageView[] diceIV = { dice1, dice2 };	// 주사위 이미지
        for(int i = 0 ; i < 2 ; i++) {
            diceResult[i] = (int)(Math.random()*6)+1;
            diceIV[i].setImage(new Image(Main.class.getResourceAsStream("texture/"+diceResult[i]+".png")));
        }
        System.out.println(diceResult[0]+diceResult[1]);

        // 무인도 체크
        if( !(diceResult[0] == diceResult[1]) && islandExitCount[turnCount]>0) {
        	islandExitCount[turnCount]--;
        	System.out.println("[무인도 탈출 실패] 탈출까지 "+islandExitCount[turnCount]+"턴 남았습니다.");
        	btnRunDice.setDisable(false);
        }else {
        	islandExitCount[turnCount] = 0;
        	playerMove(diceResult[0]+diceResult[1], turnCount);
        }

        // 더블이 아닌경우 다음턴으로 넘어간다.
        if( !(diceResult[0] == diceResult[1]) ) {
            showDiceNumber(diceResult[0]+diceResult[1], false);
            turnCount++;
            if(turnCount > playerCnt) turnCount = 1; // 플레이어 턴 재배정
        }else {
        	if(isOneMoreTurn) {
        		showDiceNumber(diceResult[0]+diceResult[1], true);
            	System.out.println("더블 입니다 "+turnCount+"플레이어가 한 번 더 주사위를 돌립니다.");
        	}else {
        		System.out.println("무인도, 우주여행에서 더블 시 한턴 더 진행이 불가능합니다. 턴이 넘어갑니다.");
	    		turnCount++;
	            if(turnCount > playerCnt) turnCount = 1; // 플레이어 턴 재배정
        	}
        }
    }

    // 플레이어의 말을 저장하는 배열
    ImageView[] playerHorseImg = new ImageView[5];
    // 현재 플레이어의 위치를 숫자로 저장하는 배열
    int playerTotalPosition[] = new int[5];
    int playerPosition[] = new int[5];

    // 게임 시작시 스타트팬에 캐릭터 추가
    void assignPlayer() {
        for(int i = 1 ; i<playerCnt+1 ; i++) {
            playerHorseImg[i] = new ImageView(player[i].profileImgURI());
            startPane.getChildren().add(playerHorseImg[i]);
            playerHorseImg[i].setFitWidth(45);
            playerHorseImg[i].setFitHeight(45);
            playerHorseImg[i].setRotate(45);
            playerHorseImg[i].setX(10);
            playerHorseImg[i].setY(10);
        }
    }

    int setDuration(int val) { return val * 100; }

    // 주사위 굴렸을때 플레이어 이동에 관련된 메소드
    void playerMove(int diceNum, int turn) {
        int LandPaneTotalCnt = 40;
        AnchorPane[] LandPaneList = {startPane, taibeiPane, goldCardPane1, hongKongPane, manilaPane,
                jejuPane, singaporePane, goldCardPane2, cairoPane, istanbulPane,
                islandPane, athenaePane, goldCardPane3, copenhagenPane, stockholmPane,
                concordePane, zurichPane, goldCardPane4, berlinPane, montrealPane,
                socialMoneyGetPane, buenosAiresPane, goldCardPane5, saoPauloPane, sydneyPane,
                busanPane, hawaiiPane, lisbonPane, queenElizabethPane, madridPane,
                spacePane, tokyoPane, colombiaPane, parisPane, romaPane,
                goldCardPane6, londonPane, newYorkPane, socialMoneyPayPane, seoulPane, startPane};

        int originPosition = playerTotalPosition[turn] % LandPaneTotalCnt;
        int movePosition = (originPosition + diceNum) % LandPaneTotalCnt;
        double endX = LandPaneList[movePosition].getLayoutX() - startPane.getLayoutX();
        double endY = LandPaneList[movePosition].getLayoutY() - startPane.getLayoutY();
        int[] goldCardPaneNum = { 2, 7, 12, 17, 22, 35 };
        SequentialTransition st;	// 애니메이션을 차례대로 동작시키는 함수

        System.out.println("playerTotalPosition = " + playerTotalPosition[turn]);
        setBuildingPrice(LandPaneList[movePosition].getId().toString());
        /*
         * <<<<수정해야할 사항>>>>
         * Duratuion을 이동하는 칸에 비례해서 계산하는 식을 하나 짜야할듯함.
         * 지금 돌려보면 판은 제대로 돌아도 커브를 돌때 이동속도가 제각각이됨.
         * ++ 바라보는 각도 변경
         */
        // 목적지 까지 가는 이동 애니매이션
        TranslateTransition tt = new TranslateTransition(new Duration(setDuration(diceNum)), playerHorseImg[turn]);
        tt.setToX(endX);
        tt.setToY(endY);

        // 목적지로 이동할때 보드를 횡단하지 않기위해 추가한 코드
        if( (originPosition/10) != (movePosition/10) ) {
            TranslateTransition tt2 = new TranslateTransition(new Duration(setDuration(diceNum)), playerHorseImg[turn]);
            tt2.setToX(LandPaneList[((movePosition/10)*10)].getLayoutX() - startPane.getLayoutX());
            tt2.setToY(LandPaneList[((movePosition/10)*10)].getLayoutY() - startPane.getLayoutY());
            // 매개변수 (움직일것, 애니메이션1, 애니메이션2, ... , 애니메이션N)  -> 앞에서 순차적으로 실행
            st = new SequentialTransition(playerHorseImg[turn],tt2,tt);
        } else {
            st = new SequentialTransition(playerHorseImg[turn],tt);
        }

        playerTotalPosition[turn] += diceNum;	// 플레이어 위치 누적 기록
        playerPosition[turn] = movePosition;    // 플레이어 절대 위치 기록

        for(int i:playerPosition){
            System.out.println("UIU i = " + i);
        }

        //이동 종료
        st.setOnFinished(e -> {
            boolean isGoldCardPane = false;
            showProfileHighlight();
            if(LandPaneList[playerPosition[turn]] == startPane) {
                // 도착한곳이 출발지 인 경우
            }else if(LandPaneList[playerPosition[turn]] == islandPane){
                // 도착한 곳이 무인도 인 경우
            	islandExitCount[turn] = 3;
            	isOneMoreTurn = false;
            }else if (LandPaneList[playerPosition[turn]] == spacePane) {
                // 도착한 곳이 우주여행 인 경우
            	isOneMoreTurn = false;
            }else {
                for(int i = 0 ; i< goldCardPaneNum.length ; i++) {
                    if( playerPosition[turn] == goldCardPaneNum[i]) {
                        isGoldCardPane = true;
                    }
                }
                if(isGoldCardPane) {
                    // 도착한 곳이 골드카드 인 경우
                    goldcard.choiceRandomGoldCard();
                }else {
                    onShowGroundDocumentModal(LandListKor[movePosition], turn);
                }
            }
            btnRunDice.setDisable(false);
        });
        st.play();
    }

    void initDice(){
        lbDiceNumber.setVisible(false);
        lbDiceDouble.setVisible(false);
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
    //              Ground #Document Modal
    // ==================================================
    // 모달은 선택항목 반환하는 역할만 수행
    @FXML AnchorPane apGroundDocumentModal;
    @FXML Text tDocumentTitle;
    @FXML Text tLandPrice;
    @FXML Text tVillaPrice;
    @FXML Text tBuildingPrice;
    @FXML Text tHotelPrice;
    @FXML Text tDocumentMessage;
    @FXML CheckBox cbLandPrice;
    @FXML CheckBox cbVillaPrice;
    @FXML CheckBox cbBuildingPrice;
    @FXML CheckBox cbHotelPrice;

    //현재 땅, 땅의 주인, 땅의 타입
    String currentLand;
    String currentLandOwner;
    String currentLandType;

    void setBuildingPrice(String landId){
        landId = landId.replaceAll("Pane", "");
        char[] arr = landId.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        landId = new String(arr);

        //땅 정보 불러올 예정
        try {
            Class<?> cls = Class.forName(building.getClass().getName());
            Method mId = cls.getDeclaredMethod(landId);
            Method mOwner = cls.getDeclaredMethod(landId + "Owner");
            Method mType = cls.getDeclaredMethod(landId + "Type");
            mId.invoke(building);
            currentLandOwner = (String) mOwner.invoke(building);
            currentLandType = (String) mType.invoke(building);
        } catch(Exception exc) {
            System.out.println(exc.toString());
        }

        // 가격 정보 없는 부지 정리
        System.out.println("landId >> " + landId);
        System.out.println("landOwner >> " + currentLandOwner);
        System.out.println("landType >> " + currentLandType);
        System.out.println("building Land price >> " + building.buyLand());
        System.out.println("building Villa price >> " + building.buyVilla());
        System.out.println("building Building price >> " + building.buyBuilding());
        System.out.println("building Hotel price >> " + building.buyHotel());


        //땅 주인이 없을 때
        if(currentLandOwner == null) {
            System.out.println("주인 없음");

            //땅 주인과 현재 플레이어가 같을 때
        } else if(currentLandOwner.equals(player[turnCount].nickname())){
            System.out.println("어서와");

            char[] typeArr = currentLandType.toCharArray();

            //구매된 토지 중복 선택 불가
            if(typeArr[0] == '1'){
                cbVillaPrice.setSelected(true);
                cbVillaPrice.setDisable(true);
            } else if(typeArr[1] == '1'){
                cbBuildingPrice.setSelected(true);
                cbBuildingPrice.setDisable(true);
            } else if(typeArr[2] == '1'){
                cbHotelPrice.setSelected(true);
                cbHotelPrice.setDisable(true);
            }

        //땅 주인과 현재 플레이어가 다를 때
        } else {
            System.out.println("침입자");
        }
        tLandPrice.setText(Integer.toString(building.buyLand()));
        tVillaPrice.setText(Integer.toString(building.buyVilla()));
        tBuildingPrice.setText(Integer.toString(building.buyBuilding()));
        tHotelPrice.setText(Integer.toString(building.buyHotel()));
    }

    @FXML void onToggleBuildCard(MouseEvent e) {
        // 토지 정보에 토지 주인, 어느토지까지 구매했는지 작성
        Node source = (Node) e.getSource();
        String buildId = source.idProperty().getValue();
        switch (buildId){
            case "pVillaPrice":
                if(cbVillaPrice.isSelected()) cbVillaPrice.setSelected(false);
                else cbVillaPrice.setSelected(true);
                break;
            case "pBuildingPrice":
                if(cbBuildingPrice.isSelected()) cbBuildingPrice.setSelected(false);
                else cbBuildingPrice.setSelected(true);
                break;
            case "pHotelPrice":
                if(cbHotelPrice.isSelected()) cbHotelPrice.setSelected(false);
                else cbHotelPrice.setSelected(true);
                break;
            default: break;
        }
    }

    void onShowGroundDocumentModal(String title,int turn){

        boolean isSpecialRegin = false;
        //모달 제목
        tDocumentTitle.setText(title);
        //토지 가격(토지 -> 빌라 -> 빌딩 -> 호텔)순
        //setBuildingPrice() 함수에서 빌딩별 가격 적용함
        apGroundDocumentModal.setVisible(true);
    }

    // 건물 그려주는 함수
    void drawBuilding(String type){
        ImageView iv = new ImageView();
        AnchorPane[] LandPaneList = {startPane, taibeiPane, goldCardPane1, hongKongPane, manilaPane,
                jejuPane, singaporePane, goldCardPane2, cairoPane, istanbulPane,
                islandPane, athenaePane, goldCardPane3, copenhagenPane, stockholmPane,
                concordePane, zurichPane, goldCardPane4, berlinPane, montrealPane,
                socialMoneyGetPane, buenosAiresPane, goldCardPane5, saoPauloPane, sydneyPane,
                busanPane, hawaiiPane, lisbonPane, queenElizabethPane, madridPane,
                spacePane, tokyoPane, colombiaPane, parisPane, romaPane,
                goldCardPane6, londonPane, newYorkPane, socialMoneyPayPane, seoulPane, startPane};

        System.out.println("LandPaneList[playerPosition[turnCount]] = " + LandPaneList[playerPosition[turnCount]]);
        System.out.println("playerPosition[turnCount] = " + playerPosition[turnCount]);
        System.out.println("turnCount = " + turnCount);
        System.out.println("turnCount - 1 = " + (turnCount - 1));

//        turnCount는 이미 턴을 넘긴상태로 가져와져서 아래와 같이 이전턴으로 처리
        int preTurn = 0;
        if(turnCount == 1) preTurn = playerCnt;
        else preTurn = turnCount - 1;

        LandPaneList[playerPosition[preTurn]].getChildren().add(iv);
        iv.setFitHeight(50);
        iv.setFitWidth(50);
        iv.setX(0);
        iv.setY(-44);

        switch(type){
            case "000":
                iv.setImage(building000Image);
                return;
            case "100":
                iv.setImage(building100Image);
                return;
            case "010":
                iv.setImage(building010Image);
                return;
            case "001":
                iv.setImage(building001Image);
                return;
            case "110":
                iv.setImage(building110Image);
                return;
            case "011":
                iv.setImage(building011Image);
                return;
            case "101":
                iv.setImage(building101Image);
                return;
            case "111":
                iv.setImage(building111Image);
                return;
        }
    }

    // 건물 구매 버튼
    @FXML void onSubmitBuy(MouseEvent e){
        char[] selectTypeArr = new char[3];

        if(cbVillaPrice.isSelected()) selectTypeArr[0] = '1';
        else selectTypeArr[0] = '0';
        if(cbBuildingPrice.isSelected()) selectTypeArr[1] = '1';
        else selectTypeArr[1] = '0';
        if(cbHotelPrice.isSelected()) selectTypeArr[2] = '1';
        else selectTypeArr[2] = '0';

        String selectType = new String(selectTypeArr);
        System.out.println("selectType >> " + selectType);

        building.setBerlinOwner(player[turnCount].nickname());
        try {
            Class<?> cls = Class.forName(building.getClass().getName());
            Method mId = cls.getDeclaredMethod(currentLand);
            Method mOwner = cls.getDeclaredMethod("set" + currentLand + "Owner", String.class);
            Method mType = cls.getDeclaredMethod("set" + currentLand + "Type", String.class);
            mId.invoke(building);
            currentLandOwner = (String) mOwner.invoke(building, player[turnCount].nickname());
            currentLandType = (String) mType.invoke(building, selectType);
        } catch(Exception exc) {
            System.out.println(exc.toString());
        }
        apGroundDocumentModal.setVisible(false);
        drawBuilding(selectType);
    }

    @FXML void onCancelBuy(MouseEvent e){
        apGroundDocumentModal.setVisible(false);
    }
    void initGroundDocumentModal() {
        apGroundDocumentModal.setVisible(false);
    }

    // ==================================================
    //              Start Bluemarble Modal
    // ==================================================
    DecimalFormat df = new DecimalFormat("###,###");
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
        Pane[] playerPane = { null, pPlayer1, pPlayer2, pPlayer3, pPlayer4 };
        // 카드 배경 초기화
        for(int i = 1 ; i < playerPane.length ; i++) {
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
        if (rb2Player.isSelected()) setStartDistMoney(586);
        else setStartDistMoney(293);
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
            if (selectPlayer[i] == false) continue;
            String nickname = "";
            Image imageURI = null;
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
        assignPlayer();
        showProfileHighlight();
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
        initGroundDocumentModal(); // 땅 문서 구매 모달 초기화
        initDice(); //주사위 초기화
    }
}