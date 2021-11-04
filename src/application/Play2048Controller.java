package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Play2048Controller {

    @FXML	private GridPane board2048;
    @FXML   private Label lblScore;
    @FXML   private Label lblBestScore;
    private Stage stage;
    private ArrayList<Integer> boardZeroCheckX = new ArrayList<Integer>();
    private ArrayList<Integer> boardZeroCheckY = new ArrayList<Integer>();
    private byte boardSize = 4;
    private Label[][] boardLabel = new Label[boardSize][boardSize];
    private int[][] beforeBoard = new int[boardSize][boardSize];
    private int[][] board = new int[boardSize][boardSize];
    private byte randomCoord, randomNewTileText;
    private boolean doubleSumProtection = false;
    private int bestScore = 0;
    private int gameContinueCount = 0;
    //보드 초기화
    void initArrayBoard() {
    	for(int i = 0 ; i<boardSize ; i++ ) {
    		for(int k = 0 ; k<boardSize ; k++) {
    			board[k][i] = 0;
    		}
    	}
    	for(int i = 0 ; i<boardSize ; i++ ) {
    		for(int k = 0 ; k<boardSize ; k++) {
    			boardLabel[k][i] = null;
    		}
    	}
    }
    // 보드 상태 프린트
    void boardPrint() {
    	for(int i = 0 ; i<4 ; i++) {
			for(int k =0;k<4;k++) {
				System.out.print(board[k][i]+" ");
			}
			System.out.println("");
		}
    	System.out.println("");
    }
    // 보드 변화를 체크
    boolean isboardChange(int[][] beforeBoard) {
    	for(int i=0 ; i<boardSize ; i++) {
    		for(int k=0 ; k<boardSize ; k++) {
        		if(board[i][k]!=beforeBoard[i][k]) {
        			return true;
        		}
        	}
    	}
    	return false;
    }
    // 새로운 타일을 화면에 추가할때 타일이 없는 곳을 찾아서 좌표값을 ArrayList에 넣어준다.
    void boardZeroCheck(){
    	boardZeroCheckX.clear();
    	boardZeroCheckY.clear();
    	for(int i = 0 ; i<boardSize ; i++ ) {
    		for(int k = 0 ; k<boardSize ; k++) {
    			tileSetting(boardLabel[i][k],false);
    			if(board[k][i]==0) {
    				boardZeroCheckX.add(k);
    				boardZeroCheckY.add(i);
    			}
    		}
    	}	
    }
//    내가 생각한 2048 로직															| (0,0) | (1,0) | (2,0) | (3,0) |
//    1. 해당 방향으로 최대한 타일을 밀기											| (0,1) | (1,1) | (2,1) | (3,1) |
//    2. 해당 방향으로 근접한 위치에 같은 수가 있는경우 합치기						| (0,2) | (1,2) | (2,2) | (3,2) |
//    3. 해당 방향으로  타일을 또 밀기 												| (0,3) | (1,3) | (2,3) | (3,3) |
//	  4. 새로운 타일을 생성
    @FXML
    // 키보드 입력이 있는 경우
    void onPressKey(KeyEvent e) {
       	if( e.getCode() == KeyCode.UP) {
       		// 기존 보드를 저장 --> 방향키를 눌러도 보드가 변화가 없을경우 새로운 타일을 생성하지 않기 위함
       		for(int i = 0 ; i<boardSize ; i++) {
       			for(int k=0 ; k<boardSize ; k++) {
       				beforeBoard[i][k] = board[i][k];
       			}
       		}	
       		// 1.  해당 방향으로 최대한 타일을 밀기
       		for(int g = 0 ; g<board.length ; g++) {
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = 0 ; k<board.length-1 ; k++) {
	       				if(board[i][k] == 0) {
	       					board[i][k] = board[i][k+1];
	       					boardLabel[i][k].setText(String.valueOf(board[i][k]));
	       					tileSetting(boardLabel[i][k],false);
	       					board[i][k+1] = 0;
	       					boardLabel[i][k+1].setText("0");
	       					tileSetting(boardLabel[i][k+1],false);
	       				}
	       			}	
	       		}
       		}
       		// 2. 해당 방향 부근에 인접한 숫자가 같은 숫자일경우 합친다.
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k = 0 ; k<board.length-1 ; k++) {
       				if((board[i][k]==board[i][k+1])&&(!doubleSumProtection)&&(board[i][k]!=0)) {
       					
       					board[i][k] *= 2;
       					boardLabel[i][k].setText(String.valueOf(board[i][k]));
       					tileSetting(boardLabel[i][k],false);
       					
       					board[i][k+1] = 0;
       					boardLabel[i][k+1].setText("0");
       					tileSetting(boardLabel[i][k+1],false);
       					
       					doubleSumProtection = true;
       				}
       				doubleSumProtection = false;
       			}
       		}
       		// 3. 해당 방향으로  타일을 또 밀기 
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k = 0 ; k<board.length-1 ; k++) {
       				if(board[i][k] == 0) {
       					board[i][k] = board[i][k+1];
       					boardLabel[i][k].setText(String.valueOf(board[i][k]));
       					tileSetting(boardLabel[i][k],false);
       					board[i][k+1] = 0;
       					boardLabel[i][k+1].setText("0");
       					tileSetting(boardLabel[i][k+1],false);
       				}
       			}	
       		}
       		// 4. 새로운 타일을 생성
       		if(isboardChange(beforeBoard)) {
       			gameContinueCount++;
       			System.out.print("[UP]");
       			newTileAdd();
       		}else {
       			System.out.println("[UP]타일 변동X");
       			gameOVerCheck();
       		}
    	}
    	if( e.getCode() == KeyCode.DOWN) {
    		// 기존 보드를 저장 --> 방향키를 눌러도 보드가 변화가 없을경우 새로운 타일을 생성하지 않기 위함
       		for(int i = 0 ; i<boardSize ; i++) {
       			for(int k=0 ; k<boardSize ; k++) {
       				beforeBoard[i][k] = board[i][k];
       			}
       		}
    		// 1.  해당 방향으로 최대한 타일을 밀기
    		for(int g = 0 ; g<board.length ; g++) {
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = board.length-1 ; k>0 ; k--) {
	       				if(board[i][k] == 0) {
	       					board[i][k] = board[i][k-1];
	       					boardLabel[i][k].setText(String.valueOf(board[i][k-1]));
	       					tileSetting(boardLabel[i][k],false);

	       					board[i][k-1] = 0;
	       					boardLabel[i][k-1].setText("0");
	       					tileSetting(boardLabel[i][k-1],false);
	       				}
	       			}	
	       		}
       		}
       		// 2. 해당 방향 부근에 인접한 숫자가 같은 숫자일경우 합친다.
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k = board.length-1 ; k>0 ; k--) {
       				if((board[i][k]==board[i][k-1])&&(!doubleSumProtection)&&(board[i][k]!=0)) {
       					board[i][k] *= 2;
       					boardLabel[i][k].setText(String.valueOf(board[i][k]));
       					tileSetting(boardLabel[i][k],false);

       					board[i][k-1] = 0;
       					boardLabel[i][k-1].setText("0");
       					tileSetting(boardLabel[i][k-1],false);
       					doubleSumProtection = true;
       				}
       			}
       			doubleSumProtection = false;
       		}
       		// 3. 해당 방향으로  타일을 또 밀기 
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = board.length-1 ; k>0 ; k--) {
	       				if(board[i][k] == 0) {
	       					board[i][k] = board[i][k-1];
	       					boardLabel[i][k].setText(String.valueOf(board[i][k-1]));
	       					tileSetting(boardLabel[i][k],false);

	       					board[i][k-1] = 0;
	       					boardLabel[i][k-1].setText("0");
	       					tileSetting(boardLabel[i][k-1],false);
	       				}
	       		}
       		}
       		// 4. 새로운 타일을 생성
	       		if(isboardChange(beforeBoard)) {
	       			System.out.print("[DOWN]");
	       			gameContinueCount++;
	       			newTileAdd();
	       		}else {
	       			System.out.println("[DOWN]타일 변동X");
	       			gameOVerCheck();
	       		}
    	}
    	if( e.getCode() == KeyCode.LEFT) {
    		// 기존 보드를 저장 --> 방향키를 눌러도 보드가 변화가 없을경우 새로운 타일을 생성하지 않기 위함
       		for(int i = 0 ; i<boardSize ; i++) {
       			for(int k=0 ; k<boardSize ; k++) {
       				beforeBoard[i][k] = board[i][k];
       			}
       		}
    		// 1.  해당 방향으로 최대한 타일을 밀기
    		for(int g = 0 ; g<board.length ; g++) {
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = 0 ; k<board.length-1 ; k++) {
	       				if(board[k][i] == 0) {
	       					board[k][i] = board[k+1][i];
	       					boardLabel[k][i].setText(String.valueOf(board[k+1][i]));
	       					tileSetting(boardLabel[k][i],false);
	       					
	       					board[k+1][i] = 0;
	       					boardLabel[k+1][i].setText("0");
	       					tileSetting(boardLabel[k+1][i],false);
	       				}
	       			}	
	       		}
       		}
       		// 2. 해당 방향 부근에 인접한 숫자가 같은 숫자일경우 합친다.
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k =0 ; k<board.length-1 ; k++) {
       				if((board[k][i]==board[k+1][i])&&(!doubleSumProtection)&&(board[k][i]!=0)) {
       					board[k][i] *= 2;
       					boardLabel[k][i].setText(String.valueOf(board[k][i]));
       					tileSetting(boardLabel[k][i],false);
       					
       					board[k+1][i] = 0;
       					boardLabel[k+1][i].setText("0");
       					tileSetting(boardLabel[k+1][i],false);
       					doubleSumProtection = true;
       				}
       			}
       			doubleSumProtection = false;
       		}
       		// 3. 해당 방향으로  타일을 또 밀기 
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = 0 ; k<board.length-1 ; k++) {
	       				if(board[k][i] == 0) {
	       					board[k][i] = board[k+1][i];
	       					boardLabel[k][i].setText(String.valueOf(board[k+1][i]));
	       					tileSetting(boardLabel[k][i],false);
	       					
	       					board[k+1][i] = 0;
	       					boardLabel[k+1][i].setText("0");
	       					tileSetting(boardLabel[k+1][i],false);
	       				}
	       			}	
	       		}
       		// 4. 새로운 타일을 생성
	       		if(isboardChange(beforeBoard)) {
	       			System.out.print("[LEFT]");
	       			newTileAdd();
	       			gameContinueCount++;
	       		}else {
	       			System.out.println("[lEFT]타일 변동X");
	       			gameOVerCheck();
	       		}
    	}
    	if( e.getCode() == KeyCode.RIGHT) {
    		// 기존 보드를 저장 --> 방향키를 눌러도 보드가 변화가 없을경우 새로운 타일을 생성하지 않기 위함
       		for(int i = 0 ; i<boardSize ; i++) {
       			for(int k=0 ; k<boardSize ; k++) {
       				beforeBoard[i][k] = board[i][k];
       			}
       		}
    		// 1.  해당 방향으로 최대한 타일을 밀기
    		for(int g = 0 ; g<board.length ; g++) {
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = board.length-1 ; k>0 ; k--) {
	       				if(board[k][i] == 0) {
	       					board[k][i] = board[k-1][i];
	       					boardLabel[k][i].setText(String.valueOf(board[k-1][i]));
	       					tileSetting(boardLabel[k][i],false);
	       					
	       					board[k-1][i] = 0;
	       					boardLabel[k-1][i].setText("0");
	       					tileSetting(boardLabel[k-1][i],false);
	       				}
	       			}	
	       		}
       		}
       		// 2. 해당 방향 부근에 인접한 숫자가 같은 숫자일경우 합친다.
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k = board.length-1 ; k>0 ; k--) {
       				if((board[k][i]==board[k-1][i])&&(!doubleSumProtection)&&(board[k][i]!=0)) {
       					board[k][i] *= 2;
       					boardLabel[k][i].setText(String.valueOf(board[k][i]));
       					tileSetting(boardLabel[k][i],false);
       					
       					board[k-1][i] = 0;
       					boardLabel[k-1][i].setText("0");
       					tileSetting(boardLabel[k-1][i],false);
       					doubleSumProtection = true;
       				}
       			}
       			doubleSumProtection = false;
       		}
       		// 3. 해당 방향으로  타일을 또 밀기
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k = board.length-1 ; k>0 ; k--) {
       				if(board[k][i] == 0) {
       					board[k][i] = board[k-1][i];
       					boardLabel[k][i].setText(String.valueOf(board[k-1][i]));
       					tileSetting(boardLabel[k][i],false);
       					
       					board[k-1][i] = 0;
       					boardLabel[k-1][i].setText("0");
       					tileSetting(boardLabel[k-1][i],false);
       				}
       			}	
       		}
       		// 4. 새로운 타일을 생성
       		if(isboardChange(beforeBoard)) {
       			System.out.print("[RIGHT]");
       			gameContinueCount++;
       			newTileAdd();
       		}else {
       			System.out.println("[RIGHT]타일 변동X");
       			gameOVerCheck();
       		}
    	}	
    }
    // 타일 설정
    void tileSetting(Label tile,boolean isNew) {
    	tile.setPrefSize(89, 89);
		tile.setAlignment(Pos.CENTER);
		if(tile.getText().equals("0"))				
			tile.setVisible(false);
		else										
			tile.setVisible(true);
		if(isNew == true) {
			if(tile.getText().equals("2"))				tile.setStyle("-fx-background-color:#D5CEC4; -fx-text-fill:#6D675D;-fx-alignment: center;-fx-font-size:30;-fx-border-color:yellow;-fx-border-width:5"); //2
	    	if(tile.getText().equals("4"))				tile.setStyle("-fx-background-color:#D2CAB5; -fx-text-fill:#6A645B;-fx-alignment: center;-fx-font-size:30;-fx-border-color:yellow;-fx-border-width:5"); //4
	    	return;
		}
    	if(tile.getText().equals("2"))				tile.setStyle("-fx-background-color:#D5CEC4; -fx-text-fill:#6D675D;-fx-alignment: center;-fx-font-size:30;"); //2
    	if(tile.getText().equals("4"))				tile.setStyle("-fx-background-color:#D2CAB5; -fx-text-fill:#6A645B;-fx-alignment: center;-fx-font-size:30;"); //4
    	if(tile.getText().equals("8"))				tile.setStyle("-fx-background-color:#D1A272; -fx-text-fill:#E4DFBB;-fx-alignment: center;-fx-font-size:30;"); //8
    	if(tile.getText().equals("16"))				tile.setStyle("-fx-background-color:#D08B5F; -fx-text-fill:#E1D3C5;-fx-alignment: center;-fx-font-size:30;"); //16
    	if(tile.getText().equals("32"))				tile.setStyle("-fx-background-color:#CF775A; -fx-text-fill:#E1D1C3;-fx-alignment: center;-fx-font-size:30;"); //32
    	if(tile.getText().equals("64"))				tile.setStyle("-fx-background-color:#CF5F40; -fx-text-fill:#E2D5C6;-fx-alignment: center;-fx-font-size:30;"); //64
    	if(tile.getText().equals("128"))			tile.setStyle("-fx-background-color:#D1BA6F; -fx-text-fill:#E4DEC3;-fx-alignment: center;-fx-font-size:30;"); //128
    	if(tile.getText().equals("256"))			tile.setStyle("-fx-background-color:#D4BD54; -fx-text-fill:#D8D6A9;-fx-alignment: center;-fx-font-size:30;"); //256
    	if(tile.getText().equals("512"))			tile.setStyle("-fx-background-color:#CCB664; -fx-text-fill:#E2D7BC;-fx-alignment: center;-fx-font-size:30;"); //512
    	if(tile.getText().equals("1024"))			tile.setStyle("-fx-background-color:#CEB349; -fx-text-fill:#E4E1BB;-fx-alignment: center;-fx-font-size:30;"); //1024
    	if(tile.getText().equals("2048"))			tile.setStyle("-fx-background-color:#D7C647; -fx-text-fill:#E2D8BA;-fx-alignment: center;-fx-font-size:30;"); //2048
    	if(Integer.parseInt(tile.getText())>=4096)	tile.setStyle("-fx-background-color:#37332E; -fx-text-fill:#C0BEBA;-fx-alignment: center;-fx-font-size:30;"); //4096
    }
    // 타일 추가하기
    void newTileAdd() {
    	boardZeroCheck();
    	// 빈공간이 있을경우 빈 공간들 중에 랜덤으로 타일을 추가
    	if(boardZeroCheckX.size()>0) {
    		randomCoord = (byte)Math.floor(Math.random() * boardZeroCheckX.size());
    		randomNewTileText = (byte)Math.floor(Math.random() * 100);
    		randomNewTileText = (byte) (( randomNewTileText < 70 ) ?2 : 4); 				// 2가 나올 확률 설정
    		board[boardZeroCheckX.get(randomCoord)][boardZeroCheckY.get(randomCoord)] = randomNewTileText;     		
    		boardLabel[boardZeroCheckX.get(randomCoord)][boardZeroCheckY.get(randomCoord)].setText(String.valueOf(randomNewTileText));
    		tileSetting(boardLabel[boardZeroCheckX.get(randomCoord)][boardZeroCheckY.get(randomCoord)],true);
    		System.out.println(boardZeroCheckX.get(randomCoord) +","+boardZeroCheckY.get(randomCoord)+"에 "+randomNewTileText+"추가");
    		scoreCheck();
    		boardPrint();
    	}
    }
    // 더이상 타일을 움직일 수 없는지 체크
    void gameOVerCheck() {
    	// 게임판이 꽉찬 상태인지 확인
    	for(int i=0 ; i<boardSize-1 ; i++) {
    		for(int k=0 ; k<boardSize-1 ; k++) {
    			if(board[i][k] == 0) {
    				return;			// 게임판이 가득 차지 않으면 return
    			}
    		}
    	}
    	// 게임판이 꽉 찼을 경우 더이상 게임 진행이 가능한지 체크 << 가로 >>
    	for(int i=0 ; i<boardSize-1 ; i++ ) {
    		for(int k=0 ; k<boardSize-1 ; k++ ) {
    			if( board[k][i]==board[k+1][i]) {
    				return;
    			}
    		}
    	}
    	// 게임판이 꽉 찼을 경우 더이상 게임 진행이 가능한지 체크 << 세로 >>
    	for(int i=0 ; i<boardSize-1 ; i++ ) {
    		for(int k=0 ; k<boardSize-1 ; k++ ) {
    			if( board[i][k]==board[i][k+1]) {
    				return;
    			}
    		}
    	}
    	gameOverPopup();
    }
    // 게임 종료 팝업
    void gameOverPopup() {
    	System.out.println("게임 오버 : 더이상 타일을 생성할 수 없습니다.");
    	
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("게임 종료");
        alert.setHeaderText("당신의 점수는 : " + lblScore.getText() + "점 입니다.");
        alert.setContentText("게임을 다시 하시겠습니까?");
        Optional<ButtonType>btnResult = alert.showAndWait();
        if(btnResult.get()== ButtonType.OK){
        	System.out.println("다시시작");
        	initBoard();
        	lblScore.setText("0");
        }
    }
    // 베스트 스코어 설정
    void bestScoreCheck() {
    	int nowScore = Integer.parseInt(lblScore.getText());
    	if(bestScore <= nowScore) {
    		bestScore = nowScore;
    		lblBestScore.setText(String.valueOf(bestScore));
    	}
    }
    // 스코어 설정
    void scoreCheck() {
    	int sum = 0;
    	for(int i = 0; i<boardSize ; i++) {
    		for(int k = 0; k<boardSize ; k++) {
        		sum +=board[i][k];
        	}
    	}
    	lblScore.setText(String.valueOf(sum));
    	bestScoreCheck();
    }
    // 보드 초기화
    void initBoard() {
    	Node gridLine = board2048.getChildren().get(0);
    	board2048.getChildren().clear();
    	board2048.getChildren().add(0,gridLine);		// 클리어 시키는경우 다시 선 긋기
    	initArrayBoard();
    	for(int i=0 ; i<boardSize ; i++) {
    		for(int k=0 ; k<boardSize ; k++) {
    			boardLabel[i][k] = new Label("0");
    			tileSetting(boardLabel[i][k],false);
    			board2048.add(boardLabel[i][k], i, k);
    		}
    	}
    	// 초기에는 타일 2개 생성
    	newTileAdd();
    	newTileAdd();
    }
    // 메인화면으로 돌아가는 버튼
	@FXML
	void onClickMainButton(MouseEvent e) throws IOException {
		
		if (gameContinueCount > 0) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("2048");
			alert.setContentText("진행중인 게임을 종료하고 메인으로 이동할까요?");
			Optional<ButtonType> btnResult = alert.showAndWait();
			if (btnResult.get() == ButtonType.CANCEL)
				return;
		}
		Node node = (Node)(e.getTarget());
        stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("미니게임");
        stage.setScene(scene);
        stage.show();

		
	}
	public void initialize() {
		initBoard();
		board2048.setFocusTraversable(true); 
		
	}
}