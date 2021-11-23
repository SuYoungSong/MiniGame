package application.bluemarble;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import application.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BluemarbleGameController implements Initializable{
	final private byte goldCardNum = 10;	// 황금카드 갯수
	
	 @FXML void onClickRunDice(ActionEvent e) {
	  }
	 
	//   마우스 호버 액션
	  @FXML
	  void onHoverEnter(MouseEvent e) {
	      Node source = (Node)e.getSource();
	      source.setStyle("-fx-cursor:hand;");
	  }
	  @FXML
	  void onHoverExit(MouseEvent e) {
	      Node source = (Node)e.getSource();
	      source.setStyle("-fx-cursor:default;");
	  }
  
	
	
	
	
//  ==================================================
//               Start Bluemarble Modal
//  ==================================================
  DecimalFormat df =  new DecimalFormat("###,###");
  @FXML private AnchorPane apStartBluemarbleModal;
  @FXML private ToggleGroup PlayerCntGroup;
  @FXML private ToggleGroup startDistMoneyGroup;
  @FXML private RadioButton rb2Player;
  @FXML private RadioButton rb3Player;
  @FXML private RadioButton rb4Player;
  @FXML private RadioButton rbDefaultDistMoney;
  @FXML private RadioButton rbCustomDistMoney;
  @FXML private TextField tfStartDiskMoney;
 
  
  @FXML void onChangeStartDistMoney(KeyEvent e) {
	int lastCurser = tfStartDiskMoney.getCaretPosition();			// 커서위치 고정을 위한 코드
	NumberFormat commaSetting = NumberFormat.getNumberInstance();	// 콤마
	// 지우기 키 (delete, backspace) 입력시 발동
	if(e.getCode() == KeyCode.DELETE || e.getCode() == KeyCode.BACK_SPACE) { return; }
	try {
		int inputKey = Integer.parseInt(e.getText());
		System.out.println(inputKey);
	// 기능키 이외의 키를 입력하였을 경우
	}catch (Exception err) {
		String str = tfStartDiskMoney.getText().replace(e.getCode().toString(), "");
        System.out.println("str = " + str);
        Platform.runLater(() -> {
       	 tfStartDiskMoney.setText(str);
		 });
        System.out.println("[ Bluemarble ] 숫자만 입력할 수 있습니다.");
	  } 
	double comma = Double.parseDouble(tfStartDiskMoney.getText().replace(",", ""));
	tfStartDiskMoney.setText(String.valueOf(commaSetting.format( comma )));
	tfStartDiskMoney.positionCaret(lastCurser);		// 커서위치 고정을 위한 코드
  } 

  @FXML void onClick2PlayerButton(ActionEvent e) {
      if(!rbDefaultDistMoney.isSelected()) return;
      setStartDistMoney(5860000);
  }
  @FXML void onClick3PlayerButton(ActionEvent e) {
      if(!rbDefaultDistMoney.isSelected()) return;
      setStartDistMoney(2930000);
  }
  @FXML void onClick4PlayerButton(ActionEvent e) {
      if(!rbDefaultDistMoney.isSelected()) return;
      setStartDistMoney(2930000);
  }
  @FXML void onClickCustomDistMoneyButton(ActionEvent e) {
      tfStartDiskMoney.setDisable(false);
      tfStartDiskMoney.requestFocus();
  }
  @FXML void onClickDefaultDistMoneyButton(ActionEvent e) {
      if(rb2Player.isSelected()) setStartDistMoney(5860000);
      else setStartDistMoney(2930000);
      tfStartDiskMoney.setDisable(true);
  }
  @FXML void onCloseCreateRoomModal(MouseEvent e) throws IOException {
      Node node = (Node)(e.getSource());
      Stage stage = (Stage)(node.getScene().getWindow());
      Parent root = FXMLLoader.load(MainController.class.getResource("MainUI.fxml"));
      Scene scene = new Scene(root);
      stage.setTitle("부루마블");
      stage.setScene(scene);
      stage.show();
  }
  @FXML void onSubmitCreateRoomModal(MouseEvent e) {
      System.out.println("확인");
  }
  void setStartDistMoney(long v){ tfStartDiskMoney.setText(df.format(v)); }
  void initStartBluemarbleModal() {
      setStartDistMoney(5860000);
      tfStartDiskMoney.setDisable(true);
      rbDefaultDistMoney.setSelected(true);
      rb2Player.setSelected(true);
      apStartBluemarbleModal.setVisible(true);
  }
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
      initStartBluemarbleModal();
  }
}

	