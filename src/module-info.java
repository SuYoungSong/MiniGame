module MiniGame {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	opens application to javafx.graphics, javafx.fxml;
	opens application.bluemarble to javafx.graphics, javafx.fxml;
}
