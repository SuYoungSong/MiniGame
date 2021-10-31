package application;

import java.io.IOException;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Play2048Controller {

    @FXML	private GridPane board2048;
    @FXML   private Label lblScore;
    @FXML   private Label lblBestScore;
    private byte boardSize = 4;
    private Stage stage;
    Stack stackTile = new Stack();
    Label temp;
    @FXML
    void onPressKey(KeyEvent e) {
       	if( e.getCode() == KeyCode.UP) {
    		System.out.println("UP");
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
    // 색은 인터넷에서 퍼와서 바꿔야될수도
    void setColor(Label tile) {
    	if(tile.getText()=="2")		tile.setStyle("-fx-background-color:#D5CEC4; -fx-text-fill:#6D675D;"); //2
    	if(tile.getText()=="4")		tile.setStyle("-fx-background-color:#D2CAB5; -fx-text-fill:#6A645B;"); //4
    	if(tile.getText()=="8")		tile.setStyle("-fx-background-color:#D1A272; -fx-text-fill:#E4DFBB;"); //8
    	if(tile.getText()=="16")	tile.setStyle("-fx-background-color:#D08B5F; -fx-text-fill:#E1D3C5;"); //16
    	if(tile.getText()=="32")	tile.setStyle("-fx-background-color:#CF775A; -fx-text-fill:#E1D1C3;"); //32
    	if(tile.getText()=="64")	tile.setStyle("-fx-background-color:#CF5F40; -fx-text-fill:#E2D5C6;"); //64
    	if(tile.getText()=="128")	tile.setStyle("-fx-background-color:#D1BA6F; -fx-text-fill:#E4DEC3;"); //128
    	if(tile.getText()=="256")	tile.setStyle("-fx-background-color:#D4BD54; -fx-text-fill:#D8D6A9;"); //256
    	if(tile.getText()=="512")	tile.setStyle("-fx-background-color:#CCB664; -fx-text-fill:#E2D7BC;"); //512
    	if(tile.getText()=="1024")	tile.setStyle("-fx-background-color:#CEB349; -fx-text-fill:#E4E1BB;"); //1024
    	if(tile.getText()=="2048")	tile.setStyle("-fx-background-color:#D7C647; -fx-text-fill:#E2D8BA;"); //2048
    	if(Integer.parseInt(tile.getText())>=4096)	tile.setStyle("-fx-background-color:#37332E; -fx-text-fill:#C0BEBA;"); //4096
    }
    void initBoard() {
//    	board2048.getChildren().clear();
//    	for(int i =0 ; i<boardSize ; i++) {
//    		for(int k=0 ; k<boardSize ; k++) {
//    			stackTile.push(new Label(i+","+k));
//    			temp = (Label) stackTile.peek();
//    			temp.setPrefSize(99, 99);
//    			board2048.add(temp, i, k);
//    		}
//    	}
//    	
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