package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("미니게임");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//primaryStage.setOnCloseRequest(event -> terminate());
	}


	public static void main(String[] args) {
		launch(args);
	}
}