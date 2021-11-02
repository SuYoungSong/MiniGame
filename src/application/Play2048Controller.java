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
    private byte boardSize = 4;
    ArrayList<Integer> boardZeroCheckX = new ArrayList<Integer>();
    ArrayList<Integer> boardZeroCheckY = new ArrayList<Integer>();
    private Stage stage;
    private int[][] board = new int[boardSize][boardSize];
    Stack stackTile = new Stack();
    Label temp;
    private int randomCoord, randomNewTileText;
    
    //배열로 관리하는 보드 초기화
    void initArrayBoard() {
    	for(int i = 0 ; i<boardSize ; i++ ) {
    		for(int k = 0 ; k<boardSize ; k++) {
    			board[i][k] = 0;
    		}
    	}
    }
    
    // 새로운 타일을 화면에 추가할때 타일이 없는 곳을 찾아서 ArrayList에 넣어준다.
    void boardZeroCheck(){
    	boardZeroCheckX.clear();
    	boardZeroCheckY.clear();
    	for(int i = 0 ; i<boardSize ; i++ ) {
    		for(int k = 0 ; k<boardSize ; k++) {
    			if(board[i][k]==0) {
    				boardZeroCheckX.add(k);
    				boardZeroCheckY.add(i);
    			}
    		}
    	}	
    }
    
    @FXML
    // 키보드 입력이 있는 경우
    void onPressKey(KeyEvent e) {
       	if( e.getCode() == KeyCode.UP) {
    		System.out.println("UP");
    		newTileAdd();
    	}
    	if( e.getCode() == KeyCode.DOWN) {
    		System.out.println("DOWN");
    	}
    	if( e.getCode() == KeyCode.LEFT) {
    		System.out.println("LEFT");
    	}
    	if( e.getCode() == KeyCode.RIGHT) {
    		System.out.println("RIGHT");
    	}	
    }
    
    void boardPrint() {
    	for(int i = 0 ; i<4 ; i++) {
			for(int k =0;k<4;k++) {
				System.out.print(board[i][k]);
			}
			System.out.println("");
		}
    	System.out.println("");
    }
    
    // 숫자에 따른 색 변경
    void setColor(Label tile) {
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
    	if(boardZeroCheckX.size()>0) {
    		System.out.println("추가");
    		randomCoord = (int)Math.floor(Math.random() * boardZeroCheckX.size());
    		randomNewTileText = (int)Math.floor(Math.random() * 100);
    		randomNewTileText = ( randomNewTileText < 70 ) ?2 : 4;
    		stackTile.push(new Label(String.valueOf(randomNewTileText)));
    		temp = (Label) stackTile.peek();
    		setColor(temp);
    		temp.setPrefSize(89, 89);
    		temp.setAlignment(Pos.CENTER);
    		board2048.add(temp, boardZeroCheckY.get(randomCoord), boardZeroCheckX.get(randomCoord));
    		board[boardZeroCheckX.get(randomCoord)][boardZeroCheckY.get(randomCoord)] = randomNewTileText;
    		board2048.setGridLinesVisible(true);
    		boardPrint();
    	}
}
    
    // 보드 초기화
    void initBoard() {
    	while(!stackTile.isEmpty()) {
    		board2048.getChildren().remove(stackTile.pop());
    	}
    	initArrayBoard();
    	newTileAdd();
    	newTileAdd();
    }
    
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