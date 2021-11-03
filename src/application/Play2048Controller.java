package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Play2048Controller {

    @FXML	private GridPane board2048;
    @FXML   private Label lblScore;
    @FXML   private Label lblBestScore;
    private Label temp;
    private Stage stage;
    private ArrayList<Integer> boardZeroCheckX = new ArrayList<Integer>();
    private ArrayList<Integer> boardZeroCheckY = new ArrayList<Integer>();
    private Stack stackTile = new Stack();
    private byte boardSize = 4;
    private int[][] board = new int[boardSize][boardSize];
    private byte randomCoord, randomNewTileText;
    private boolean doubleSumProtection = false;
    
    //배열로 관리하는 보드 초기화
    void initArrayBoard() {
    	for(int i = 0 ; i<boardSize ; i++ ) {
    		for(int k = 0 ; k<boardSize ; k++) {
    			board[k][i] = 0;
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
    // 새로운 타일을 화면에 추가할때 타일이 없는 곳을 찾아서 좌표값을 ArrayList에 넣어준다.
    void boardZeroCheck(){
    	boardZeroCheckX.clear();
    	boardZeroCheckY.clear();
    	for(int i = 0 ; i<boardSize ; i++ ) {
    		for(int k = 0 ; k<boardSize ; k++) {
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
//    3. 새로운 타일을 생성															| (0,3) | (1,3) | (2,3) | (3,3) |
    @FXML
    // 키보드 입력이 있는 경우
    void onPressKey(KeyEvent e) {
       	if( e.getCode() == KeyCode.UP) {
       		// 1.  해당 방향으로 최대한 타일을 밀기
       		for(int g = 0 ; g<board.length ; g++) {
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = 0 ; k<board.length-1 ; k++) {
	       				if(board[i][k] == 0) {
	       					board[i][k] = board[i][k+1];
	       					board[i][k+1] = 0;
	       				}
	       			}	
	       		}
       		}
       		// 2. 해당 방향 부근에 인접한 숫자가 같은 숫자일경우 합친다.
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k = board.length-1 ; k>0 ; k--) {
       				if((board[i][k]==board[i][k-1])&&(!doubleSumProtection)&&(board[i][k]!=0)) {
       					board[i][k-1] *= 2;
       					board[i][k] = 0;
       					doubleSumProtection = true;
       				}
       			}
       			doubleSumProtection = false;
       		}
       		// 3번 새로운 타일을 생성
       		newTileAdd();
    	}
    	if( e.getCode() == KeyCode.DOWN) {
    		// 1.  해당 방향으로 최대한 타일을 밀기
    		for(int g = 0 ; g<board.length ; g++) {
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = board.length-1 ; k>0 ; k--) {
	       				if(board[i][k] == 0) {
	       					board[i][k] = board[i][k-1];
	       					board[i][k-1] = 0;
	       				}
	       			}	
	       		}
       		}
       		// 2. 해당 방향 부근에 인접한 숫자가 같은 숫자일경우 합친다.
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k = 0 ; k<board.length-1 ; k++) {
       				if((board[i][k]==board[i][k+1])&&(!doubleSumProtection)&&(board[i][k]!=0)) {
       					board[i][k+1] *= 2;
       					board[i][k] = 0;
       					doubleSumProtection = true;
       				}
       			}
       			doubleSumProtection = false;
       		}
       		// 3번 새로운 타일을 생성
       		newTileAdd();
    	}
    	if( e.getCode() == KeyCode.LEFT) {
    		// 1.  해당 방향으로 최대한 타일을 밀기
    		for(int g = 0 ; g<board.length ; g++) {
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = 0 ; k<board.length-1 ; k++) {
	       				if(board[k][i] == 0) {
	       					board[k][i] = board[k+1][i];
	       					board[k+1][i] = 0;
	       				}
	       			}	
	       		}
       		}
       		// 2. 해당 방향 부근에 인접한 숫자가 같은 숫자일경우 합친다.
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k = board.length-1 ; k>0 ; k--) {
       				if((board[k][i]==board[k-1][i])&&(!doubleSumProtection)&&(board[k][i]!=0)) {
       					board[k-1][i] *= 2;
       					board[k][i] = 0;
       					doubleSumProtection = true;
       				}
       			}
       			doubleSumProtection = false;
       		}
       		// 3번 새로운 타일을 생성
    		newTileAdd();
    	}
    	if( e.getCode() == KeyCode.RIGHT) {
    		// 1.  해당 방향으로 최대한 타일을 밀기
    		for(int g = 0 ; g<board.length ; g++) {
	       		for(int i = 0 ; i<board.length ; i++) {
	       			for(int k = board.length-1 ; k>0 ; k--) {
	       				if(board[k][i] == 0) {
	       					board[k][i] = board[k-1][i];
	       					board[k-1][i] = 0;
	       				}
	       			}	
	       		}
       		}
       		// 2. 해당 방향 부근에 인접한 숫자가 같은 숫자일경우 합친다.
       		for(int i = 0 ; i<board.length ; i++) {
       			for(int k = 0 ; k<board.length-1 ; k++) {
       				if((board[k][i]==board[k+1][i])&&(!doubleSumProtection)&&(board[k][i]!=0)) {
       					board[k+1][i] *= 2;
       					board[k][i] = 0;
       					doubleSumProtection = true;
       				}
       			}
       			doubleSumProtection = false;
       		}
       		// 3번 새로운 타일을 생성
    		newTileAdd();
    	}	
    }
    // 타일 설정
    void tileSetting(Label tile) {
    	tile.setPrefSize(89, 89);
		tile.setAlignment(Pos.CENTER);
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
    	if(Integer.parseInt(tile.getText())>=4096)	tile.setStyle("-fx-background-color:#37332E; -fx-text-fill:#C0BEBA;"); //4096
    }
    // 타일 추가하기
    void newTileAdd() {
    	boardZeroCheck();
    	// 빈공간이 있을경우 빈 공간들 중에 랜덤으로 타일을 추가
    	if(boardZeroCheckX.size()>0) {
    		randomCoord = (byte)Math.floor(Math.random() * boardZeroCheckX.size());
    		randomNewTileText = (byte)Math.floor(Math.random() * 100);
    		randomNewTileText = (byte) (( randomNewTileText < 70 ) ?2 : 4); 				// 2가 나올 확률 설정
    		
    		stackTile.push(new Label(String.valueOf(randomNewTileText)));
    		
    		temp = (Label) stackTile.peek();
    		tileSetting(temp);
    		board2048.add(temp, boardZeroCheckX.get(randomCoord), boardZeroCheckY.get(randomCoord));
    		board[boardZeroCheckX.get(randomCoord)][boardZeroCheckY.get(randomCoord)] = randomNewTileText; 
    		System.out.println(boardZeroCheckX.get(randomCoord) +","+boardZeroCheckY.get(randomCoord)+"에 "+randomNewTileText+"추가");
    		boardPrint();
    	}
    }
    // 보드 초기화
    void initBoard() {
    	Node gridLine = board2048.getChildren().get(0);
    	board2048.getChildren().clear();
    	board2048.getChildren().add(0,gridLine);		// 클리어 시키는경우 다시 선 긋기
    	
//    	while(!stackTile.isEmpty()) {
//    		board2048.getChildren().remove(stackTile.pop());
//    	}
    	
    	initArrayBoard();
    	// 초기에는 타일 2개 생성
    	newTileAdd();
    	newTileAdd();
    }
    // 메인화면으로 돌아가는 버튼
	@FXML
	void onClickMainButton(MouseEvent e) throws IOException {
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