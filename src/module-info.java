module MiniGame {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;

	opens application to javafx.graphics, javafx.fxml;
	opens application.bluemarble to javafx.graphics, javafx.fxml;
	opens application.omok to javafx.graphics, javafx.fxml;
	opens application.play2048 to javafx.graphics, javafx.fxml;
	opens application.wordChainGame to javafx.graphics, javafx.fxml;
}
