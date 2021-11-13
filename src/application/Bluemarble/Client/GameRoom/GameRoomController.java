package application.Bluemarble.Client.GameRoom;

import java.net.Socket;

import javafx.application.Platform;
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
    	Thread thread = new Thread() {
			public void run() {
				Platform.runLater(()->{
					int i=0,k=0;
						i = i + (int)(Math.random()*10);
						k = k + (int)(Math.random()*10);
						dice.group.rotateByX(i);
				    	dice.group.rotateByY	(i);
				    	dice2.group.rotateByX(k);
				    	dice2.group.rotateByY(k);
				});
			}
		};
    	try {
    		thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
			thread.start();
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
}
