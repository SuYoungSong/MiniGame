package application.bluemarble;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class GoldCard {
	byte goldCardNum;
	BluemarbleGameController bgc;

	GoldCard(byte no, BluemarbleGameController bgc){
		goldCardNum = no;
		this.bgc = bgc;
	}
	// 황금카드에 도착한경우 작동하는 메소드
	void choiceRandomGoldCard() {
		int n = (int) Math.floor(Math.random() *goldCardNum);	// 카드를 랜덤으로 뽑기 위해숫자
			String imgPath = "/application/texture/bluemarbleGoldCard"+(n+1)+".png";
			Image cardImg = new Image(getClass().getResourceAsStream(imgPath));
			bgc.goldCardSetImage(cardImg);
		switch(n+1) {
			case 1:
				System.out.println(("[황금카드]은행에서 100만원 받기"));
				bgc.player[bgc.turnCount].setMoney(bgc.player[bgc.turnCount].money()+1000000);
				bgc.refreshMoney();
				bgc.player[bgc.turnCount].refreshAsset();
				break;
			case 2:
				System.out.println("[황금카드]내가 갖고있는 돈의 10%를 각 플레이어에게 나눠주기");
				long tenPercent = (bgc.player[bgc.turnCount].money()/10);
				for(int i = 1 ; i<=bgc.turnCount ; i++) {
					if(i != bgc.turnCount) {
						bgc.player[i].setMoney(bgc.player[i].money()+tenPercent);
						bgc.player[bgc.turnCount].setMoney(bgc.player[bgc.turnCount].money() - tenPercent);
						bgc.refreshMoney();
//						bgc.player[i].refreshAsset();
					}
				}
				bgc.refreshMoney();
				break;
			case 3:
				System.out.println("[황금카드]다음 주사위를 굴린 수치에 2배를 이동");
				bgc.diceMulitple[bgc.turnCount] = 2;
				break;
			case 4:
				System.out.println("[황금카드]다음턴을 한정으로 상대땅을 밟으면 통행료 면제");
				bgc.isTollFree[bgc.turnCount] = true;
				break;
			case 5:
				System.out.println("[황금카드] 무인도로 이동");
				
				TranslateTransition line2 = new TranslateTransition(new Duration(400), bgc.playerHorseImg[bgc.turnCount]);
		    	TranslateTransition line3 = new TranslateTransition(new Duration(400), bgc.playerHorseImg[bgc.turnCount]);
		    	TranslateTransition line4 = new TranslateTransition(new Duration(400), bgc.playerHorseImg[bgc.turnCount]);
		    	TranslateTransition tt = new TranslateTransition(new Duration(400), bgc.playerHorseImg[bgc.turnCount]);
	            tt.setToX(bgc.getIslandPane().getLayoutX()-bgc.getStartPane().getLayoutX());
	            tt.setToY(bgc.getIslandPane().getLayoutY()-bgc.getStartPane().getLayoutY());
		    	SequentialTransition st = null;	
		    	
		    	if( bgc.playerPosition[bgc.turnCount]>=0 && bgc.playerPosition[bgc.turnCount]<10 ) {
		    		// 플레이어 캐릭 위치 1라인
		    		st = new SequentialTransition(bgc.playerHorseImg[bgc.turnCount],tt);
			    	st.play();
		    	}
		    	if( bgc.playerPosition[bgc.turnCount]>9 && bgc.playerPosition[bgc.turnCount]<20 ) {
		    		// 플레이어 캐릭 위치 2라인
		    		line2.setToX(bgc.getSocialMoneyGetPane().getLayoutX() - bgc.getStartPane().getLayoutX());
		    		line2.setToY(bgc.getSocialMoneyGetPane().getLayoutY() - bgc.getStartPane().getLayoutY());
		    		line3.setToX(bgc.getSpacePane().getLayoutX() - bgc.getStartPane().getLayoutX());
		    		line3.setToY(bgc.getSpacePane().getLayoutY() - bgc.getStartPane().getLayoutY());
		    		line4.setToX(bgc.getStartPane().getLayoutX() - bgc.getStartPane().getLayoutX());
		    		line4.setToY(bgc.getStartPane().getLayoutY() - bgc.getStartPane().getLayoutY());
		    		st = new SequentialTransition(bgc.playerHorseImg[bgc.turnCount],line2,line3,line4,tt);
		    		st.play();
		    	}
		    	if( bgc.playerPosition[bgc.turnCount]>19 && bgc.playerPosition[bgc.turnCount]<30) {
		    		// 플레이어 캐릭 위치 3라인
		    		line3.setToX(bgc.getSpacePane().getLayoutX() - bgc.getStartPane().getLayoutX());
		    		line3.setToY(bgc.getSpacePane().getLayoutY() - bgc.getStartPane().getLayoutY());
		    		line4.setToX(bgc.getStartPane().getLayoutX() - bgc.getStartPane().getLayoutX());
		    		line4.setToY(bgc.getStartPane().getLayoutY() - bgc.getStartPane().getLayoutY());
		    		st = new SequentialTransition(bgc.playerHorseImg[bgc.turnCount],line3,line4,tt);
		    		st.play();
		    	}
		    	if( bgc.playerPosition[bgc.turnCount]>29 && bgc.playerPosition[bgc.turnCount]<40) {
		    		// 플레이어 캐릭 위치 4라인
		    		line4.setToX(bgc.getStartPane().getLayoutX() - bgc.getStartPane().getLayoutX());
		    		line4.setToY(bgc.getStartPane().getLayoutY() - bgc.getStartPane().getLayoutY());
		    		st = new SequentialTransition(bgc.playerHorseImg[bgc.turnCount],line4,tt);
		    		st.play();
		    	}
		    	// 출발지 지날경우 월급 20만원 지급 => 수정 황금카드로 무인도 가는경우 월급 미지급
//		    	if(bgc.playerPosition[bgc.turnCount]>10) bgc.player[bgc.turnCount].setMoney(bgc.player[bgc.turnCount].money() +bgc.salaryMoney);
		    	
		    	bgc.playerTotalPosition[bgc.turnCount] = 10;	// 플레이어 누적위치
		    	bgc.playerPosition[bgc.turnCount] = 10;			// 플레이어 절대위치
		    	
		    	bgc.playerMove(0);
		    	bgc.nextTurn();
		    	bgc.showProfileHighlight();
				break;
		}
	}

}