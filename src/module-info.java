module MiniGame {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;

	opens application to javafx.graphics, javafx.fxml;
	opens application.Bluemarble.Server to javafx.graphics, javafx.fxml;	
	opens application.Bluemarble.Client.GameRoom to javafx.graphics, javafx.fxml;
	opens application.Bluemarble.Client.GameLobby to javafx.graphics, javafx.fxml;
}
