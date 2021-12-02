package application.bluemarble;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BluemarbleGameController implements Initializable {
    private Stage stage;

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
    
    final long salaryMoney = 200000;	// 출발지 지날경우 월급 설정
    final private byte goldCardNum = 5; // 황금카드 갯수
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
    String[] playerColor = new String[5];

    // 주사위 이미지 저장
    @FXML  private ImageView dice1;
    @FXML  private ImageView dice2;
    @FXML  private ImageView ivSocialMoney;
    int turnCount = 1;	// 시작 플레이어 설정
    BuildingData building = new BuildingData();

    //총 턴수
    int totalTurnCount;

    // 플레이어가 가진돈과 자산 정보를 불러옴
    void refreshMoney() {
        for(int i = 1 ; i <= playerCnt ; i++) {
            profileMoney[i].setText(String.valueOf(convertNumberToCurrency((player[i].money()))));
            profileAsset[i].setText(String.valueOf(convertNumberToCurrency((player[i].asset()))));
        }
    }
    
    AnchorPane getIslandPane() {
    	return islandPane;
    }
    AnchorPane getStartPane() {
    	return startPane;
    }
    AnchorPane getSocialMoneyGetPane() {
    	return socialMoneyGetPane;
    }
    AnchorPane getSpacePane() {
    	return spacePane;
    }
    // 땅 클릭하면 작동하는 메소드
    @FXML
    void onClickLandPane(MouseEvent event) {
    	if(isArrivalSpaceTravel[turnCount]) {
	    	AnchorPane apTemp = (AnchorPane) event.getSource();
	    	spaceTraval(apTemp);
    	}
    }
    void spaceTraval(AnchorPane selectPane) {
    	// 우주여행 상태인경우
    	if(isArrivalSpaceTravel[turnCount]) {
    		isArrivalSpaceTravel[turnCount] = false;
    		byte selectLandNum = 0;
    		boolean passStartPane = false;
    		final byte spaceLandNum = 30;
	    	AnchorPane[] LandPaneList = {startPane, taibeiPane, goldCardPane1, hongKongPane, manilaPane,
	                jejuPane, singaporePane, goldCardPane2, cairoPane, istanbulPane,
	                islandPane, athenaePane, goldCardPane3, copenhagenPane, stockholmPane,
	                concordePane, zurichPane, goldCardPane4, berlinPane, montrealPane,
	                socialMoneyGetPane, buenosAiresPane, goldCardPane5, saoPauloPane, sydneyPane,
	                busanPane, hawaiiPane, lisbonPane, queenElizabethPane, madridPane,
	                spacePane, tokyoPane, colombiaPane, parisPane, romaPane,
	                goldCardPane6, londonPane, newYorkPane, socialMoneyPayPane, seoulPane, startPane};
	    	int durationLine1= 400, durationLine2= 400, durationLine3 =400,durationLine4 =400;
	    	// 유저가 선택한 땅 번호를 가져옴
	    	for(int i = 0 ; i<LandPaneList.length ; i++) {
	    		if(LandPaneList[i] == selectPane) {
	    			selectLandNum = (byte)i;
	    		}
	    	}
	    	
	    	if(selectLandNum == spaceLandNum) {
	    		return;	// 우주여행 땅을 고른경우 우주여행 취소
	    	}
	    	
	    	byte paneNumDiff = (byte)(spaceLandNum-selectLandNum);
	    	TranslateTransition line1 = new TranslateTransition(new Duration(durationLine1), playerHorseImg[turnCount]);
	    	TranslateTransition line2 = new TranslateTransition(new Duration(durationLine2), playerHorseImg[turnCount]);
	    	TranslateTransition line3 = new TranslateTransition(new Duration(durationLine3), playerHorseImg[turnCount]);
	    	TranslateTransition tt = new TranslateTransition(new Duration(durationLine4), playerHorseImg[turnCount]);
            tt.setToX(LandPaneList[selectLandNum].getLayoutX()-startPane.getLayoutX());
            tt.setToY(LandPaneList[selectLandNum].getLayoutY()-startPane.getLayoutY());
	    	SequentialTransition st = null;	
	    	
	    	if( (paneNumDiff<11)&&(paneNumDiff>0) ) {	// 3라인을 선택한 경우
	    		// 3라인 ( 1(마드리드) ~ 10(사회복지기금)
	    		passStartPane = true; // 시작지 지나서 월급지급
	    		line1.setToX(startPane.getLayoutX() - startPane.getLayoutX());
	    		line1.setToY(startPane.getLayoutY() - startPane.getLayoutY());
	    		line2.setToX(islandPane.getLayoutX() - startPane.getLayoutX());
	    		line2.setToY(islandPane.getLayoutY() - startPane.getLayoutY());
	    		line3.setToX(socialMoneyGetPane.getLayoutX() - startPane.getLayoutX());
	    		line3.setToY(socialMoneyGetPane.getLayoutY() - startPane.getLayoutY());
	    		st = new SequentialTransition(playerHorseImg[turnCount],line1,line2,line3,tt);
	    		st.play();
	    	}
	    	if( (paneNumDiff<21)&&(paneNumDiff>10) ) {	// 2라인을 선택한 경우
	    		// 2라인 ( 11(몬트리올) ~ 20(무인도) )
	    		passStartPane = true; // 시작지 지나서 월급지급
	    		line1.setToX(startPane.getLayoutX() - startPane.getLayoutX());
	    		line1.setToY(startPane.getLayoutY() - startPane.getLayoutY());
	    		line2.setToX(islandPane.getLayoutX() - startPane.getLayoutX());
	    		line2.setToY(islandPane.getLayoutY() - startPane.getLayoutY());
	    		st = new SequentialTransition(playerHorseImg[turnCount],line1,line2,tt);
	    		st.play();
	    	}
	    	if( (paneNumDiff<31)&&(paneNumDiff>20) ) {	// 1라인을 선택한 경우
	    		// 1라인 ( 30(출발지) ~ 21(이스탄불) )
	    		passStartPane = true; // 시작지 지나서 월급지급
	    		line1.setToX(startPane.getLayoutX() - startPane.getLayoutX());
	    		line1.setToY(startPane.getLayoutY() - startPane.getLayoutY());	
	    		st = new SequentialTransition(playerHorseImg[turnCount],line1,tt);
	    		st.play();
	    	}
	    	if( paneNumDiff<0) {	// 4라인을 선택한 경우
	    		st = new SequentialTransition(playerHorseImg[turnCount],tt);
		    	st.play();
	    	}
	    	if(passStartPane) {
	    		// 월급 20만원 지급
	    		player[turnCount].setMoney(player[turnCount].money()+salaryMoney);
	    		refreshMoney();
	    	}
	    	playerPosition[turnCount] = selectLandNum;			// 플레이어 절대위치
	    	playerTotalPosition[turnCount] += selectLandNum+10;	// 플레이어 누적위치
	    	st.setOnFinished(e -> {
	    		playerMove(0);
//	    		nextTurn();
	        });
    	}
    }
    // ==================================================
    //                    Test Code
    // ==================================================
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
                playerColor[4] = "#3498db";
            case 3:
                profileAsset[3] = tPlayer3Asset;
                profileMoney[3] = tPlayer3Money;
                profileNickname[3] = tPlayer3Nickname;
                playerContainer[3] = apPlayer3Container;
                profilePane[3] = pPlayer3Profile;
                profileHighlight[3] = pPlayer3Highlight;
                playerColor[3] = "#2ecc71";
            case 2:
                profileAsset[2] = tPlayer2Asset;
                profileMoney[2] = tPlayer2Money;
                profileNickname[2] = tPlayer2Nickname;
                playerContainer[2] = apPlayer2Container;
                profilePane[2] = pPlayer2Profile;
                profileHighlight[2] = pPlayer2Highlight;
                playerColor[2] = "#d35400";
            case 1:
                profileAsset[1] = tPlayer1Asset;
                profileMoney[1] = tPlayer1Money;
                profileNickname[1] = tPlayer1Nickname;
                playerContainer[1] = apPlayer1Container;
                profilePane[1] = pPlayer1Profile;
                profileHighlight[1] = pPlayer1Highlight;
                playerColor[1] = "#f1c40f";
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
        for (int i = 1; i <= playerCnt; i++) {
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
    @FXML private Pane pDiceShadow;

    // 다음턴
    void nextTurn(){
        //더블이 아니면 다음턴 전환
        if(!isDouble)turnCount++;
    	if(turnCount > playerCnt) turnCount = 1; // 플레이어 턴 재배정
        showProfileHighlight();
        showDiceButton();
        // 우주여행을 가야하는 경우 주사위 숨기기
        if(isArrivalSpaceTravel[turnCount]) {
        	System.out.println(turnCount+"플레이어 우주여행 시작");
        	hideDiceButton();
        }
        currentLandOwner = null;
        currentLandType = null;
    }

    //현재 턴인 유저 프로필에 하이라이트 추가
    void showProfileHighlight() {
        if (turnCount > playerCnt) turnCount = 1;
        //전체 프로필 초기화
        for (Pane p : profileHighlight) {
            if (p == null) continue;
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

    boolean isDouble;
    long socialMoneyStack = 0;	
    @FXML private Button btnRunDice;
    @FXML	// 주사위를 던지는 메소드
    void onClickRunDice(ActionEvent e) {
        if(turnCount > playerCnt) turnCount = 1; // 플레이어 턴 재배정
        isDouble = false;
        btnRunDice.setDisable(true);

        int[] diceResult = new int[2];			// 주사위 결과 저장 -> 더블 체크용도
        ImageView[] diceIV = { dice1, dice2 };	// 주사위 이미지
        for(int i = 0 ; i < 2 ; i++) {
            diceResult[i] = (int)(Math.random()*6)+1;
            diceIV[i].setImage(new Image(Main.class.getResourceAsStream("texture/"+diceResult[i]+".png")));
        }
        // 무인도 체크하는 조건
        if( !(diceResult[0] == diceResult[1]) && islandExitCount[turnCount]>0) {
        	islandExitCount[turnCount]--;
        	System.out.println("[무인도 탈출 실패] 탈출까지 "+islandExitCount[turnCount]+"턴 남았습니다.");
        	btnRunDice.setDisable(false);
        	nextTurn();
        }else {
        	islandExitCount[turnCount] = 0;
        	playerMove(diceResult[0]+diceResult[1]);
        }
//        playerMove(diceResult[0] + diceResult[1]);

        // 더블이 아닌경우 다음턴으로 넘어간다.
        if(diceResult[0] == diceResult[1]) {
            isDouble = true;
            showDiceNumber(diceResult[0]+diceResult[1], true);
            System.out.println("더블 입니다 "+turnCount+"플레이어가 한 번 더 주사위를 돌립니다.");
        }else {
            showDiceNumber(diceResult[0]+diceResult[1], false);
        }
    }

    // 플레이어의 말을 저장하는 배열
    ImageView[] playerHorseImg = new ImageView[5];
    // 현재 플레이어의 위치를 숫자로 저장하는 배열
    int playerTotalPosition[] = new int[5];
    int playerPosition[] = new int[5];
    byte[] islandExitCount = new byte[5]; 	// 무인도 탈출까지 몇 턴 남았는지 저장하는 배열
    boolean[] isArrivalSpaceTravel = new boolean[5]; // 플레이어별로 우주여행에 도착했는지 확인하는 배열
    byte[] diceMulitple = { 1,1,1,1,1 };		// [황금카드]주사위 보정값
    boolean[] isTollFree = new boolean[5];		// [황금카드]통행료 무료 여부

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
    int prePlayerPosition;	// 월급 주기위해 체크하려고 만든 변수
    // 주사위 굴렸을때 플레이어 이동에 관련된 메소드
    void playerMove(int diceNum) {
        int LandPaneTotalCnt = 40;
        diceNum *= diceMulitple[turnCount]; // 황금카드 주사위 2배 확인
        diceMulitple[turnCount] = 1;
        
        AnchorPane[] LandPaneList = {startPane, taibeiPane, goldCardPane1, hongKongPane, manilaPane,
                jejuPane, singaporePane, goldCardPane2, cairoPane, istanbulPane,
                islandPane, athenaePane, goldCardPane3, copenhagenPane, stockholmPane,
                concordePane, zurichPane, goldCardPane4, berlinPane, montrealPane,
                socialMoneyGetPane, buenosAiresPane, goldCardPane5, saoPauloPane, sydneyPane,
                busanPane, hawaiiPane, lisbonPane, queenElizabethPane, madridPane,
                spacePane, tokyoPane, colombiaPane, parisPane, romaPane,
                goldCardPane6, londonPane, newYorkPane, socialMoneyPayPane, seoulPane, startPane};

        int originPosition = playerTotalPosition[turnCount] % LandPaneTotalCnt;
        int movePosition = (originPosition + diceNum) % LandPaneTotalCnt;
        double endX = LandPaneList[movePosition].getLayoutX() - startPane.getLayoutX();
        double endY = LandPaneList[movePosition].getLayoutY() - startPane.getLayoutY();
        int[] goldCardPaneNum = { 2, 7, 12, 17, 22, 35 };
        SequentialTransition st;	// 애니메이션을 차례대로 동작시키는 함수
        int lineDiff = (originPosition/10)-(movePosition/10);
        lineDiff = (lineDiff<0)?lineDiff*(-1):lineDiff;
        setBuildingPrice(LandPaneList[movePosition].getId().toString());
        // 목적지 까지 가는 이동 애니매이션
        TranslateTransition tt = new TranslateTransition(new Duration(setDuration(diceNum)), playerHorseImg[turnCount]);
        tt.setToX(endX);
        tt.setToY(endY);

        // 목적지로 이동할때 보드를 횡단하지 않기위해 추가한 코드
        if( lineDiff != 0 ) {
        	if(lineDiff == 2) {
        		int secondPane = ((movePosition/10)*10);
        		int fisrtPane = (secondPane-10<0)?30:secondPane-10;
        		System.out.println(fisrtPane);
        		System.out.println(secondPane);
        		// 2개 라인을 넘어가는 경우
        		TranslateTransition tt2 = new TranslateTransition(new Duration(setDuration(diceNum/2)), playerHorseImg[turnCount]);
        		TranslateTransition tt3 = new TranslateTransition(new Duration(setDuration(diceNum/2)), playerHorseImg[turnCount]);
 	            tt2.setToX(LandPaneList[secondPane].getLayoutX() - startPane.getLayoutX());
 	            tt2.setToY(LandPaneList[secondPane].getLayoutY() - startPane.getLayoutY());
 	            tt3.setToX(LandPaneList[fisrtPane].getLayoutX() - startPane.getLayoutX());
 	            tt3.setToY(LandPaneList[fisrtPane].getLayoutY() - startPane.getLayoutY());
 	            st = new SequentialTransition(playerHorseImg[turnCount],tt3,tt2,tt);
        	}else {
        		// 1개 라인을 넘어가는 경우
	            TranslateTransition tt2 = new TranslateTransition(new Duration(setDuration(diceNum)), playerHorseImg[turnCount]);
	            tt2.setToX(LandPaneList[((movePosition/10)*10)].getLayoutX() - startPane.getLayoutX());
	            tt2.setToY(LandPaneList[((movePosition/10)*10)].getLayoutY() - startPane.getLayoutY());
	            // 매개변수 (움직일것, 애니메이션1, 애니메이션2, ... , 애니메이션N)  -> 앞에서 순차적으로 실행
	            st = new SequentialTransition(playerHorseImg[turnCount],tt2,tt);
        	}
        } else {
            st = new SequentialTransition(playerHorseImg[turnCount],tt);
        }

        prePlayerPosition = playerPosition[turnCount];
        playerTotalPosition[turnCount] += diceNum;	// 플레이어 위치 누적 기록
        playerPosition[turnCount] = movePosition;    // 플레이어 절대 위치 기록
        if(prePlayerPosition>playerPosition[turnCount]) {
        	// 출발지를 지난경우 월급 지급
        	player[turnCount].setMoney(player[turnCount].money() +salaryMoney);
        	refreshMoney();
        }
        //이동 종료
        st.setOnFinished(e -> {
            boolean isGoldCardPane = false;

            //이전 빌딩 체크상태 및 메시지 초기화
            cbVillaPrice.setSelected(false);
            cbBuildingPrice.setSelected(false);
            cbHotelPrice.setSelected(false);
            cbVillaPrice.setDisable(false);
            cbBuildingPrice.setDisable(false);
            cbHotelPrice.setDisable(false);
            tDocumentMessage.setText("");

            //무시할 대상 초기화
            ignoreVilla = false;
            ignoreBuilding = false;
            ignoreHotel = false;

            //땅 주인이 없을 때
            if(currentLandOwner == null) {
            	System.out.println(currentLandOwner);
                System.out.println("NULL 땅 주인이 존재하지 않음");

                if(LandPaneList[playerPosition[turnCount]] == startPane) {
                    // 도착한곳이 출발지 인 경우
                	nextTurn();
                }else if(LandPaneList[playerPosition[turnCount]] == islandPane){
                    // 도착한 곳이 무인도 인 경우
                	islandExitCount[turnCount] = 3;
                	isDouble = false;
                	nextTurn();
                }else if (LandPaneList[playerPosition[turnCount]] == spacePane) {
                    // 도착한 곳이 우주여행 인 경우
                	isArrivalSpaceTravel[turnCount] = true;
                	isDouble = false;
                	nextTurn();
                }else if(LandPaneList[playerPosition[turnCount]] == socialMoneyPayPane){
                	// 도착한 곳이 사회복지기금 내는곳 인 경우
                	player[turnCount].setMoney(player[turnCount].money()-150000);
                	socialMoneyStack += 150000;
                	refreshMoney();
                	player[turnCount].refreshAsset();
                	ivSocialMoney.setVisible(true);
                	nextTurn();
                }else if(LandPaneList[playerPosition[turnCount]] == socialMoneyGetPane) {
                	// 도착한 곳이 사회복지기금 받는곳인 경우
                	player[turnCount].setMoney(player[turnCount].money()+socialMoneyStack);
                	socialMoneyStack = 0;
                	refreshMoney();
                	player[turnCount].refreshAsset();
                	ivSocialMoney.setVisible(false);
                	nextTurn();
                } else {
                    for(int i = 0 ; i < goldCardPaneNum.length ; i++) {
                        if( playerPosition[turnCount] == goldCardPaneNum[i]) {
                            isGoldCardPane = true;
                        }
                    }
                    if(isGoldCardPane) {
                        // 도착한 곳이 골드카드 인 경우
                        goldcard.choiceRandomGoldCard();
                        nextTurn();
                    }else {
                        onShowGroundDocumentModal(LandListKor[movePosition]);
                    }
                }
                //땅 주인과 현재 플레이어가 같을 때
            } else if(currentLandOwner.equals(player[turnCount].nickname())){
            	System.out.println(currentLandOwner);
                System.out.println("O 땅 주인과 현재 플레이어가 동일함");
                isTollFree[turnCount] = false; 
                //모든 건물을 구매했으면 바로 다음 턴
                if(currentLandType.equals("111")) {
                    System.out.println("모든 건물을 구매함");
                } else {
                    char[] typeArr = currentLandType.toCharArray();
                    System.out.println("currentLandType >> " + currentLandType);

    //                구매된 토지 중복 선택 불가
                    if(typeArr[0] == '1'){
                        ignoreVilla = true;
                        cbVillaPrice.setSelected(true);
                        cbVillaPrice.setDisable(true);
                    }
                    if(typeArr[1] == '1'){
                        ignoreBuilding = true;
                        cbBuildingPrice.setSelected(true);
                        cbBuildingPrice.setDisable(true);
                    }
                    if(typeArr[2] == '1'){
                        ignoreHotel = true;
                        cbHotelPrice.setSelected(true);
                        cbHotelPrice.setDisable(true);
                    }
                    onShowGroundDocumentModal(LandListKor[playerPosition[turnCount]]);
                }
                //땅 주인과 현재 플레이어가 다를 때
            } else {
            	System.out.println(currentLandOwner);
                System.out.println("X 땅 주인과 현재 플레이어가 동일하지 않음");

                char[] payLandType = currentLandType.toCharArray();
                long playerMoney = player[turnCount].money();
                int payMoney = building.passLand();

                if(payLandType[0] == '1'){
                    payMoney += building.passVilla();
                }
                if(payLandType[1] == '1'){
                    payMoney += building.passBuilding();
                }
                if(payLandType[2] == '1'){
                    payMoney += building.passHotel();
                }

                // 플레이어가 차감될 금액이 충분할 때 or 황금카드 통행료 면제 뽑앗을 경우
                if((playerMoney >= payMoney || isTollFree[turnCount])){
                	if(isTollFree[turnCount]) {
                		isTollFree[turnCount] = false;                		
                		System.out.println("[ Bluemarble ] 통행료 납부 >> " + turnCount + "플레이어가 황금카드 효과로 통행료가 면제되었습니다.");
                		nextTurn();
                	}else {
	                    //현재 플레이어 통행료 납부
	                    player[turnCount].setMoney(playerMoney - payMoney);
	                    System.out.println("[ Bluemarble ] 통행료 납부 >> " + turnCount + " 플레이어가 " + payMoney + "원을 지불했습니다.");
	                    
	                    //땅 주인에게 통행료 전달
	                    for(int i = 1; i <= playerCnt; i++){
	                        if(currentLandOwner.equals(player[i].nickname())){
	//                            System.out.println("지불 대상 존재");
	                            player[i].setMoney(player[i].money() + payMoney);
	                        }
	                    }
	
	                    //턴이 넘어가는 경우(토지 구매, 토지 구매 취소, 통행료 납부)
	                    //더블이 아니면 다음 턴
	                    nextTurn();
                	}
                } else {
                // 플레이어가 지불할 금액이 모자랄 때
                /* 게임 종료 조건
                * 지정된 턴 수를 초과 했을 때, 한 사람이 파산했을 때 시점으로
                * 가장 현금이 많은 사람이 승리
                 */

                    //현재 유저 파산 처리
                    player[turnCount].setMoney(0);
                    setGameOver();
                    onShowGameOverModal();
                    System.out.println("[ Bluemarble ] 게임이 종료되었습니다.");
                }
            }
            refreshMoney();
            btnRunDice.setDisable(false);
        });
        st.play();
    }

    void showDiceButton(){
        pDiceShadow.setVisible(true);
        btnRunDice.setVisible(true);
    }

    void hideDiceButton(){
        pDiceShadow.setVisible(false);
        btnRunDice.setVisible(false);
    }


    void initDice(){
        lbDiceNumber.setVisible(false);
        lbDiceDouble.setVisible(false);
        hideDiceButton();
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

    //플레이어가 현재 밟은 땅, 땅의 주인, 땅의 타입
    String currentLand;
    String currentLandOwner;
    String currentLandType;

    void setBuildingPrice(String landId){
        landId = landId.replaceAll("Pane", "");
        char[] arr = landId.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        landId = new String(arr);

        currentLand = landId;

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

        //건물 가격 정보 삽입
        tLandPrice.setText(Integer.toString(building.buyLand()));
        tVillaPrice.setText(Integer.toString(building.buyVilla()));
        tBuildingPrice.setText(Integer.toString(building.buyBuilding()));
        tHotelPrice.setText(Integer.toString(building.buyHotel()));

//        System.out.println("landId >> " + landId);
//        System.out.println("currentLandOwner >> " + currentLandOwner);
//        System.out.println("currentLandType >> " + currentLandType);
//        System.out.println("building Land price >> " + building.buyLand());
//        System.out.println("building Villa price >> " + building.buyVilla());
//        System.out.println("building Building price >> " + building.buyBuilding());
//        System.out.println("building Hotel price >> " + building.buyHotel());
//        System.out.println();
    }

    //playMove 함수에서 사용하는 변수(구매한 땅 중복 클릭 방지)
    boolean ignoreVilla, ignoreBuilding, ignoreHotel;
    @FXML void onToggleBuildCard(MouseEvent e) {
        // 토지 정보에 토지 주인, 어느토지까지 구매했는지 작성
        Node source = (Node) e.getSource();
        String buildId = source.idProperty().getValue();

       if(buildId.equals("pVillaPrice")){
            if (!ignoreVilla) {
                System.out.println("빌라 무시 안함");
                if (cbVillaPrice.isSelected()) cbVillaPrice.setSelected(false);
                else cbVillaPrice.setSelected(true);
            }
        }
        if(buildId.equals("pBuildingPrice")) {
            if(!ignoreBuilding) {
                System.out.println("빌딩 무시 안함");
                if (cbBuildingPrice.isSelected()) cbBuildingPrice.setSelected(false);
                else cbBuildingPrice.setSelected(true);
            }
        }
        if(buildId.equals("pHotelPrice")) {
            if(!ignoreHotel) {
                System.out.println("호텔 무시 안함");
                if (cbHotelPrice.isSelected()) cbHotelPrice.setSelected(false);
                else cbHotelPrice.setSelected(true);
            }
        }
    }

    void onShowGroundDocumentModal(String title){
        //주사위 숨기기
        hideDiceButton();

        boolean isSpecialRegin = false;
        //모달 제목
        tDocumentTitle.setText(title);
        //토지 가격(토지 -> 빌라 -> 빌딩 -> 호텔)순
        //setBuildingPrice() 함수에서 빌딩별 가격 적용함, 플레이어가 이동할 때 호출
        apGroundDocumentModal.setVisible(true);
    }

    // 건물 그려주는 함수
    void drawBuilding(String type){
        AnchorPane[] LandPaneList = {startPane, taibeiPane, goldCardPane1, hongKongPane, manilaPane,
                jejuPane, singaporePane, goldCardPane2, cairoPane, istanbulPane,
                islandPane, athenaePane, goldCardPane3, copenhagenPane, stockholmPane,
                concordePane, zurichPane, goldCardPane4, berlinPane, montrealPane,
                socialMoneyGetPane, buenosAiresPane, goldCardPane5, saoPauloPane, sydneyPane,
                busanPane, hawaiiPane, lisbonPane, queenElizabethPane, madridPane,
                spacePane, tokyoPane, colombiaPane, parisPane, romaPane,
                goldCardPane6, londonPane, newYorkPane, socialMoneyPayPane, seoulPane, startPane};

        ImageView iv = new ImageView();
        Pane pPlayerType = new Pane();

        LandPaneList[playerPosition[turnCount]].getChildren().addAll(iv, pPlayerType);
        iv.setFitHeight(50);
        iv.setFitWidth(50);
        iv.setX(0);
        iv.setY(-44);

        pPlayerType.setStyle("-fx-background-color: " + playerColor[turnCount]);
        pPlayerType.setPrefSize(20,50);
        pPlayerType.setLayoutX(15);
        pPlayerType.setLayoutY(0);

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


    @FXML void onSubmitBuy(MouseEvent e){
        char[] selectTypeArr = new char[3];
        int buyMoney = building.buyLand();
        long playerMoney = player[turnCount].money();

        //현재 체크된 건물 타입설정
        if(cbVillaPrice.isSelected()) {
            selectTypeArr[0] = '1';
            buyMoney += building.buyVilla();
        } else {
            selectTypeArr[0] = '0';
        }

        if(cbBuildingPrice.isSelected()) {
            selectTypeArr[1] = '1';
            buyMoney += building.buyBuilding();
        } else {
            selectTypeArr[1] = '0';
        }

        if(cbHotelPrice.isSelected()) {
            selectTypeArr[2] = '1';
            buyMoney += building.buyHotel();
        } else {
            selectTypeArr[2] = '0';
        }

        String selectType = new String(selectTypeArr);

        // 플레이어가 땅을 구매할 금액이 충분할 때
        if(playerMoney >= buyMoney){

            //이전의 땅 구매 내역이 있으면 비교해서 금액 합산
            if(!(currentLandType == null)) {
                char[] currentLandTypeArr = currentLandType.toCharArray();
                System.out.println("[ Bluemarble ] 땅 구매 내역 존재");
                buyMoney = Integer.parseInt(tLandPrice.getText());

                if(currentLandTypeArr[0] == selectTypeArr[0]){
                    System.out.println("빌리 이미 구매");
                    buyMoney += Integer.parseInt(tVillaPrice.getText());
                }
                if(currentLandTypeArr[1] == selectTypeArr[1]){
                    System.out.println("빌딩 이미 구매");
                    buyMoney += Integer.parseInt(tBuildingPrice.getText());
                }
                if(currentLandTypeArr[2] == selectTypeArr[2]){
                    System.out.println("호텔 이미 구매");
                    buyMoney += Integer.parseInt(tHotelPrice.getText());
                }
            }

            //땅 구매
            player[turnCount].setMoney(playerMoney - buyMoney);
            System.out.println("[ Bluemarble ] 땅 구매 >> " + turnCount + " 플레이어가 " + buyMoney + "원을 지불했습니다.");

            try {
                Class<?> cls = Class.forName(building.getClass().getName());
                Method mId = cls.getDeclaredMethod(currentLand);
                Method mOwner = cls.getDeclaredMethod("set" + currentLand + "Owner", String.class);
                Method mType = cls.getDeclaredMethod("set" + currentLand + "Type", String.class);
                mId.invoke(building);
                mOwner.invoke(building, player[turnCount].nickname());
                mType.invoke(building, selectType);
            } catch(Exception exc) {
                System.out.println(exc.toString());
            }

            //설치한 건물을 그려주는 함수
            drawBuilding(selectType);
            apGroundDocumentModal.setVisible(false);

            //더블이 아니면 다음 턴
            nextTurn();
            showDiceButton();
            // 플레이어가 구매할 금액이 모자랄 때
        } else {
            tDocumentMessage.setText("금액이 충분하지 않습니다.");
            System.out.println("[ Bluemarble ] 금액이 충분하지 않습니다.");
        }

        refreshMoney();
    }

    @FXML void onCancelBuy(MouseEvent e){
        onCloseGroundDocumentModal();
    }

    void onCloseGroundDocumentModal(){
        apGroundDocumentModal.setVisible(false);
        showDiceButton();
        nextTurn();
    }

    void initGroundDocumentModal() {
        apGroundDocumentModal.setVisible(false);
    }
    // ==================================================
    //              Gold Card Modal
    // ==================================================
    @FXML private ImageView ivGoldCardImage;
    @FXML private AnchorPane apGoldCardModal;
    @FXML
    void onClickGoldCardModalClose(MouseEvent event) {
    	apGoldCardModal.setVisible(false);
    	 btnRunDice.setDisable(false);
    }
    void goldCardSetImage(Image image) {
    	 btnRunDice.setDisable(true);
    	ivGoldCardImage.setImage(image);
    	apGoldCardModal.setVisible(true);
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
    @FXML private Pane pInformationModal;
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
        showDiceButton();
        totalTurnCount = playerCnt * 50;
        System.out.println("totalTurnCount = " + totalTurnCount);
    }

    @FXML
    void onShowInformationModal(MouseEvent e) {
        pInformationModal.setVisible(true);
    }

    @FXML
    void onCloseInformationModal(MouseEvent e) {
        pInformationModal.setVisible(false);
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
        pInformationModal.setVisible(false);
    }

    // ==================================================
    //              End Bluemarble Modal
    // ==================================================
    @FXML private AnchorPane apGameOverModel;
    @FXML private Label lbWinPlayer;
    @FXML private Label lbWinPlayerMoney;


    void setGameOver(){
        int winPlayerIndex = 1;
        long max = player[1].money();
        for (int i = 2; i < playerCnt + 1; i++) {
            if(player[i].money() > max){
                max = player[i].money();
                winPlayerIndex = i;
            }
        }

        System.out.println("승리한 플레이어 >> " + player[winPlayerIndex].nickname());
        System.out.println("승리한 플레이어의 현금 >> " + player[winPlayerIndex].money());
        lbWinPlayer.setText(player[winPlayerIndex].nickname());
        lbWinPlayerMoney.setText(convertNumberToCurrency(player[winPlayerIndex].money()));
    }

    void onShowGameOverModal(){
        apGameOverModel.setVisible(true);
    }

    @FXML void onRestartBluemarble(MouseEvent e) throws IOException {
        Node node = (Node)(e.getSource());
        stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("BluemarbleGameUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("부루마블");
        stage.setScene(scene);
        stage.show();
    }

    @FXML void onCloseBluemarble(MouseEvent e) throws IOException {
        Node node = (Node)(e.getSource());
        stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("/application/MainUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("미니게임");
        stage.setScene(scene);
        stage.show();
    }

    void initGameOverModal(){
        apGameOverModel.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initStartBluemarbleModal(); // 부루마블 시작 모달 초기화
        initGroundDocumentModal();  // 땅 문서 구매 모달 초기화
        initDice();                 //주사위 초기화
        initGameOverModal();        //게임 종료 모달 초기회
    }
}