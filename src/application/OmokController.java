package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class OmokController implements Initializable{

	@FXML private Label turnText;
	@FXML private Label lbLimitTime;
	@FXML private GridPane omokBoard;
	private Stage stage;

	private final int boardSize = 15;
	private final int dx[][]={{0, 0},{-1, 1},{-1, 1},{-1, 1}};
	private final int dy[][]={{-1, 1},{0, 0},{-1, 1},{1, -1}};
	private int limitTime = 10;
	private short count = 1;
	private byte gameTurn = 1;
	char stoneType;
	char[][]board = new char[boardSize][boardSize];
	Stack imageAdress = new Stack();

	void initBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j]= 'O';
			}
		}
	}

	void printBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				System.out.printf("%c ", board[i][j]);
			}
			System.out.printf("\n");
		}
		System.out.printf("\n");
	}

	void setBoard(int x, int y, char type) {
		board[x][y]= type;
		printBoard();
	}

	boolean checkBoard(int x, int y) {
		if(board[x][y]!= 'O')return true;
		return false;
	}

	char isWin(int x, int y) {
		boolean check[][]= new boolean[15][15];
		for(int i = 0; i < 4; i++) {
			int stoneCnt = 0;
			Queue<Integer>q = new LinkedList<>();
			q.add(x);
			q.add(y);
			while(q.size()> 0) {
				int tmpX = q.poll();
				int tmpY = q.poll();
				for(int j = 0; j < 2; j++) {
					int rX = tmpX + dx[i][j];
					int rY = tmpY + dy[i][j];
					if(0 <= rX && rX < boardSize && 0 <= rY && rY < boardSize) {
						if(board[rX][rY]!= stoneType)continue;
						if(!check[rX][rY]) {
							check[rX][rY]= true;
							q.add(rX);
							q.add(rY);
							stoneCnt += 1;
						}
						if(stoneType == 'B' && stoneCnt == 5)return stoneType;
						if(stoneType == 'W' && stoneCnt >= 5)return stoneType;
					}//if
				}//for-j
			}//while
		}//for-i
		return 'n';
	}

	void turnCheck() {
		if(count % 2 == 0) {
			gameTurn = 1;
			turnText.setText("흑돌의 차례입니다");
		}else{
			gameTurn = 2;
			turnText.setText("백돌의 차례입니다");
		}
	}

	TimerTask timerTask;
	Timer timer = new Timer("boardTimer");
	public TimerTask createTask() {
		limitTime = 10;
		return new TimerTask() {
			@Override
			public void run() {
				setLimitTime(limitTime);
				limitTime--;
				if(limitTime <= 0) {
					cancel();
					Platform.runLater(()->{
//char swapStoneType = (stoneType == 'B') ? 'W' : 'B';
						//onShowResetModal(swapStoneType, 't');
						onShowResetModal(stoneType, 't');

					});
				}
			}
		};
	}

	void runTimer() {
		if(timerTask != null) {
			timerTask.cancel();
		}
		timerTask = createTask();
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}

	void setLimitTime(int seconds) {
		Platform.runLater(()->{
			lbLimitTime.setText(Integer.toString(seconds));
		});
	}

	// hover 상태일때
	@FXML
	void hoverBoardTile(MouseEvent e) {
		Node source =(Node)e.getSource();

		if(gameTurn == 1) {
			source.setStyle("-fx-background-color:#000000; -fx-background-radius: 100;");
		}else{
			source.setStyle("-fx-background-color:#ffffff; -fx-background-radius: 100;");
		}
	}

	// hover 상태가 끝
	@FXML
	void hoverExit(MouseEvent e) {
		Node source =(Node)e.getSource();
		source.setStyle("-fx-background-color:#C69D30");
	}
	// 오목 렌즈룰
	boolean is33(int x,int y) {
		byte boCheckNum=0, countNum=0;
		byte[][][]check33 ={
				{{0,-1},{0,1},{0,2},{0,3},{0,4}},     //           각 번호 체크 방향
				{{-1,-1},{1,1},{2,2},{3,3},{4,4}},        //           ↖   ↑  ↗
				{{-1,0},{1,0},{2,0},{3,0},{4,0}},         //            ⑥⑦⑧
				{{-1,1},{1,-1},{2,-2},{3,-3},{4,-4}},    //            ← ⑤.●① →
				{{0,1},{0,-1},{0,-2},{0,-3},{0,-4}},     //            ④③②
				{{1,1},{-1,-1},{-2,-2},{-3,-3},{-4,-4}},  //               ↙   ↓  ↘
				{{1,0},{-1,0},{-2,0},{-3,0},{-4,0}},     //
				{{1,-1},{-1,1},{-2,2},{-3,3},{-4,4}}//
		}; //BXBBBB
		byte checkX, checkY;
		char[][]roomData ={ {'O', 'B','B','O'},{'O', 'B','O'},{'O', 'B','O','B','O'},{'O','O','B','B','O'}};
		// 33수 체크
		for(byte i = 0 ; i<check33.length ; i++) {
			char[]compareData ={'0', '0', '0', '0', '0'};
			byte checkNum = 0;
			// 1번 0XBBO
			for(byte k=0 ; k<4 ; k++) {
				checkX = check33[i][k][0];
				checkY = check33[i][k][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[k]= board[x+checkX][y+checkY];
				}
			}
			if( (compareData[0]==(roomData[0][0]))&&(compareData[1]==(roomData[0][1]))&&(compareData[2]==(roomData[0][2]))&&(compareData[3]==(roomData[0][3])) ){
				checkNum++;
			}
// 2번 XB0
			for(byte j=1 ; j<3 ; j++) {
				checkX = check33[i][j][0];
				checkY = check33[i][j][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[j]= board[x+checkX][y+checkY];
				}
			}
			if((compareData[1]==(roomData[1][1]))&&(compareData[2]==(roomData[1][2]))){
				if(i%2==0) {
					boCheckNum++;
				}else{
					boCheckNum--;
				}
			}
// 3번 0XB0B0
			for(byte h=0 ; h<5 ; h++) {
				checkX = check33[i][h][0];
				checkY = check33[i][h][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[h]= board[x+checkX][y+checkY];
				}
			}
			if((compareData[0]==(roomData[2][0]))&&(compareData[1]==(roomData[2][1]))&&(compareData[2]==(roomData[2][2]))&&(compareData[3]==(roomData[2][3]))&&(compareData[4]==(roomData[2][4]))){
				checkNum++;
			}
// 4번 0X0BB0
			for(byte g=0 ; g<5 ; g++) {
				checkX = check33[i][g][0];
				checkY = check33[i][g][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[g]= board[x+checkX][y+checkY];
				}
			}
			if((compareData[0]==(roomData[2][0]))&&(compareData[1]==(roomData[2][1]))&&(compareData[2]==(roomData[2][2]))&&(compareData[3]==(roomData[2][3]))&&(compareData[4]==(roomData[2][4]))){
				checkNum++;
			}
			if(checkNum>0)countNum++;
		}
		if((countNum>=2)||(boCheckNum>=4)||((countNum>=1)&&(boCheckNum>=2))) {
			return true;
		}else{
			return false;
		}
	}
	boolean is44(int x,int y) {
		byte checkNum = 0;
		byte[][][]check44 ={
				{{0,-1},{0,1},{0,2},{0,3},{0,4}},     //           각 번호 체크 방향
				{{-1,-1},{1,1},{2,2},{3,3},{4,4}},        //           ↖   ↑  ↗
				{{-1,0},{1,0},{2,0},{3,0},{4,0}},         //            ⑥⑦⑧
				{{-1,1},{1,-1},{2,-2},{3,-3},{4,-4}},    //            ← ⑤.●① →
				{{0,1},{0,-1},{0,-2},{0,-3},{0,-4}},     //            ④③②
				{{1,1},{-1,-1},{-2,-2},{-3,-3},{-4,-4}},  //               ↙   ↓  ↘
				{{1,0},{-1,0},{-2,0},{-3,0},{-4,0}},     //
				{{1,-1},{-1,1},{-2,2},{-3,3},{-4,4}}//
		}; //BXBBBB
		byte checkX, checkY;
		char[][]roomData ={ {'B','B','B'},{'B','B','O','B'},{'B','O','B','B'},{'O','B','B','B'},{'B','X','B','B'},{'B','X','B','O','B'},{'B','X','O','B','B'} };
		// 4수 체크
		for(byte i = 0 ; i<check44.length ; i++) {
			char[]compareData ={'0', '0', '0', '0', '0'};
			// 1번 BBB
			for(byte k=1 ; k<4 ; k++) {
				checkX = check44[i][k][0];
				checkY = check44[i][k][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[k]= board[x+checkX][y+checkY];
				}
			}
			if( (compareData[1]==(roomData[0][0]))&&(compareData[2]==(roomData[0][1]))&&(compareData[3]==(roomData[0][2])) ){
				checkNum++;
			}
// 2번 BB0B
			for(byte j=1 ; j<5 ; j++) {
				checkX = check44[i][j][0];
				checkY = check44[i][j][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[j]= board[x+checkX][y+checkY];
				}
			}
			if((compareData[1]==(roomData[1][0]))&&(compareData[2]==(roomData[1][1]))&&(compareData[3]==(roomData[1][2]))&&(compareData[4]==(roomData[1][3]))){
				checkNum++;
			}
// 3번 B0BB
			for(byte h=1 ; h<5 ; h++) {
				checkX = check44[i][h][0];
				checkY = check44[i][h][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[h]= board[x+checkX][y+checkY];
				}
			}
			if((compareData[1]==(roomData[2][0]))&&(compareData[2]==(roomData[2][1]))&&(compareData[3]==(roomData[2][2]))&&(compareData[4]==(roomData[2][3]))){
				checkNum++;
			}
// 4번 0BBB
			for(byte g=1 ; g<5 ; g++) {
				checkX = check44[i][g][0];
				checkY = check44[i][g][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[g]= board[x+checkX][y+checkY];
				}
			}
			if((compareData[1]==(roomData[3][0]))&&(compareData[2]==(roomData[3][1]))&&(compareData[3]==(roomData[3][2]))&&(compareData[4]==(roomData[3][3]))){
				checkNum++;
			}
// 5번 BXBB
			for(byte c=0 ; c<3 ; c++) {
				checkX = check44[i][c][0];
				checkY = check44[i][c][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[c]= board[x+checkX][y+checkY];
				}
			}
			if((compareData[0]==(roomData[4][0]))&&(compareData[1]==(roomData[4][2]))&&(compareData[2]==(roomData[4][3]))){
				checkNum++;
			}
// 6번 BXBOB
			for(byte n=0 ; n<4 ; n++) {
				checkX = check44[i][n][0];
				checkY = check44[i][n][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[n]= board[x+checkX][y+checkY];
				}
			}
			if((compareData[0]==(roomData[5][0]))&&(compareData[1]==(roomData[5][2]))&&(compareData[2]==(roomData[5][3]))&&(compareData[3]==(roomData[5][4]))){
				checkNum++;
			}
// 7번 BXOBB
			for(byte z=0 ; z<4 ; z++) {
				checkX = check44[i][z][0];
				checkY = check44[i][z][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[z]= board[x+checkX][y+checkY];
				}
			}
			if((compareData[0]==(roomData[6][0]))&&(compareData[1]==(roomData[6][2]))&&(compareData[2]==(roomData[6][3]))&&(compareData[3]==(roomData[6][4]))){
				checkNum++;
			}
		}

		if(checkNum>=2) {
			return true;
		}else{
			return false;
		}
	}

	//흑 5개 가능할경우 렌즈룰 무시
	boolean isBlack5Stone(int x,int y) {
		byte[][][]check5 ={
				{{0,-2},{0,-1},{0,1},{0,2},{0,3},{0,4}},     //           각 번호 체크 방향
				{{-2,-2},{-1,-1},{1,1},{2,2},{3,3},{4,4}},       //           ↖   ↑  ↗
				{{-2,0},{-1,0},{1,0},{2,0},{3,0},{4,0}},         //            ⑥⑦⑧
				{{-2,2},{-1,1},{1,-1},{2,-2},{3,-3},{4,-4}},    //            ← ⑤.●① →
				{{0,2},{0,1},{0,-1},{0,-2},{0,-3},{0,-4}},      //            ④③②
				{{2,2},{1,1},{-1,-1},{-2,-2},{-3,-3},{-4,-4}},   //               ↙   ↓  ↘
				{{2,0},{1,0},{-1,0},{-2,0},{-3,0},{-4,0}},      //
				{{2,-2},{1,-1},{-1,1},{-2,2},{-3,3},{-4,4}}//
		}; // BBXBBBB
		byte checkX, checkY;
		char[][]roomData ={ {'B','B','X','B','B'},{'B','X','B','B','B'},{'X','B','B','B','B'} };
		char[]compareData ={'O', 'O', 'O', 'O', 'O', '0'};
		byte checkNum = 0;
		// 5수 체크
		for(byte i = 0 ; i<check5.length ; i++) {
// 1번 BBXBB
			for(byte k=0 ; k<5 ; k++) {
				checkX = check5[i][k][0];
				checkY = check5[i][k][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[k]= board[x+checkX][y+checkY];
				}
			}
			if( (compareData[0]==(roomData[0][0]))&&(compareData[1]==(roomData[0][1]))&&(compareData[2]==(roomData[0][3]))&&(compareData[3]==(roomData[0][4])) ){
				checkNum++;
			}
// 2번 BXBBB
			for(byte k=1 ; k<6 ; k++) {
				checkX = check5[i][k][0];
				checkY = check5[i][k][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[k]= board[x+checkX][y+checkY];
				}
			}
			if( (compareData[1]==(roomData[1][0]))&&(compareData[2]==(roomData[1][2]))&&(compareData[3]==(roomData[1][3]))&&(compareData[4]==(roomData[1][4])) ){
				checkNum++;
			}
// 3번 XBBBB
			for(byte k=3 ; k<6 ; k++) {
				checkX = check5[i][k][0];
				checkY = check5[i][k][1];
				if(!((x+checkX>=15)||(x+checkX<0)||(y+checkY>=15)||(y+checkY<0))) {
					compareData[k]= board[x+checkX][y+checkY];
				}
			}
			if( (compareData[2]==(roomData[2][1]))&&(compareData[3]==(roomData[2][2]))&&(compareData[4]==(roomData[2][3]))&&(compareData[5]==(roomData[2][4])) ){
				checkNum++;
			}
		}
		if(checkNum>=1) {
			return true;
		}else{
			return false;
		}
	}

	void resetBoard(){
		initBoard();
		while(!imageAdress.isEmpty() ) {
			omokBoard.getChildren().remove(imageAdress.pop());
		}
		count = 1;
		gameTurn = 1;
		turnText.setText("흑돌의 차례입니다");
	}

	void onShowResetModal(char type, char option){
// type >> "B(Black)", "W(White)"을 매개변수로 받음
		// option >> "d(default)", t(timeout)을 매겨변수로 받음

		String originStoneName =(type == 'B')? "흑돌" : "백돌";
		String originOptionName =(option == 'd')? "" : "시간초과로 ";
		turnText.setText(originStoneName + " 승");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("대국 결과");
		alert.setHeaderText(originOptionName + originStoneName + "이 승리했습니다.");
		alert.setContentText("게임을 다시 하시겠습니까?");
		Optional<ButtonType>btnResult = alert.showAndWait();
		if(btnResult.get()== ButtonType.OK){
			resetBoard();
		}
	}


	@FXML
	void onClickBoard(MouseEvent e) {
		runTimer();
		Node source =(Node)e.getSource();
		Integer row = GridPane.getRowIndex(source);
		Integer col = GridPane.getColumnIndex(source);
		if(row == null)row = 0;
		if(col == null)col = 0;
		System.out.println("gameTurn >> " + gameTurn);
		System.out.println("stoneType >> " + stoneType);
		if(checkBoard(row, col))return;
		if(stoneType == 'W'){
			System.out.println("stoneType = " + stoneType);
			if(!isBlack5Stone(row,col))if((is33(row, col))||(is44(row, col))) {
				turnText.setText("흑돌이 착수할 수 없는 위치 입니다");
				return;
			}
		}
		if(gameTurn == 1) {
			stoneType = 'B';
			imageAdress.push(new BorderPane());
			omokBoard.add((Node)imageAdress.peek(), col, row);  // AnchorPane 를 덮어 착수된 위치는 hover이 되지 않기위해
			imageAdress.push(new ImageView(new Image(getClass().getResourceAsStream("texture/black.png"))));
			omokBoard.add((Node)imageAdress.peek(),col, row);
		}else{
			stoneType = 'W';
			imageAdress.push(new BorderPane());
			omokBoard.add((Node)imageAdress.peek(), col, row);  // AnchorPane 를 덮어 착수된 위치는 hover이 되지 않기위해
			imageAdress.push(new ImageView(new Image(getClass().getResourceAsStream("texture/white.png"))));
			omokBoard.add((Node)imageAdress.peek(),col, row);
		}
		setBoard(row, col, stoneType);

		char result = isWin(row, col);
		if(result == 'W' || result == 'B') {
			timerTask.cancel();
			onShowResetModal(result, 'd');
			return;
		}

		turnCheck();
		count++;
	}

	void onShowMain(ActionEvent e)throws IOException{
		if(timerTask != null)timerTask.cancel();
		Node node =(Node)(e.getSource());
		stage =(Stage)(node.getScene().getWindow());
		Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("미니게임");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void onClickMainButton(ActionEvent e)throws IOException{
		if(count > 1){
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("오목");
			alert.setContentText("진행중인 게임을 종료하고 메인으로 이동할까요?");
			Optional<ButtonType>btnResult = alert.showAndWait();
			if(btnResult.get()== ButtonType.OK)onShowMain(e);
			return;
		}
		onShowMain(e);
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		initBoard();
	}
}