package application.Bluemarble.Client.GameRoom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class GameRoomController {
	
    @FXML private AnchorPane diceZone;
    Dice dice = new Dice();
    Dice dice2 = new Dice();
    private boolean isDiceMake = false;
    

    @FXML
    void asdf(ActionEvent event) {
    	if(isDiceMake) {
    		rolling(dice, dice2);
    	} else {
    		isDiceMake = true;
	    	dice.diceMake(diceZone,450,400,20);
	    	dice2.diceMake(diceZone, 350, 400, 20);
	    	rolling(dice, dice2);
    }}
    void rolling(Dice dice, Dice dice2) {
    	// 주사위 돌아가는거 구현해야함.    	
    }
}
