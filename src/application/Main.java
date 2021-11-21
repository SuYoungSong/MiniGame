package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("texture/favicon_main2.png")));
			primaryStage.setTitle("미니게임");
			primaryStage.setResizable(false);		// 창 크기 임의로 못늘리게 설정
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}