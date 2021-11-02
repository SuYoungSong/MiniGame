package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class WordChainGameController {
    private Stage stage;
    private int score;
    private ArrayList<String> wordList = new ArrayList<String>();
    private char lastWord;
    @FXML private TextField inputWord;
    @FXML private Label lbMessage;
    @FXML private Label lbPrevWord;
    @FXML private Label lbScore;
    private final int minLetterCnt = 2;
    
     String computerWord(){
    	try {
			String findWord;
			int randomNum;
			URL url = new URL("https://stdict.korean.go.kr/common/autoComplete.json?searchKeyword=");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			findWord = (br.readLine());
			// 단어 전처리 과정
			findWord = findWord.substring(findWord.indexOf("(")+1, findWord.indexOf(")"));
			String[] wordBox = findWord.split(",");
			for(int i=1 ; i<wordBox.length ; i++) {
				wordBox[i] = wordBox[i].substring(wordBox[i].indexOf("|")+2,wordBox[i].length()-1);
			}
			if(wordBox[0].equals("''")) {
				return null; // 컴퓨터가 입력할 단어가 없어서 패배
			}else {
				for(int i = 1; i<wordBox.length;i++) {
					randomNum = (int) Math.floor(Math.random()* wordBox.length);
					if (!wordList.contains(wordBox[randomNum])){
						return wordBox[randomNum];
					}
				}
				return null;  // 컴퓨터가 입력할 단어가 없어서 패배
			}
	    } catch (Exception e){
	      e.printStackTrace();
	    } 
    	return null;    // 컴퓨터가 입력할 단어가 없어서 패배
    }
    
    boolean isWord(String word){
        try {
            URL url = new URL("https://stdict.korean.go.kr/common/autoComplete.json?searchKeyword=" + word);
            String line = "";
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = br.readLine()) != null) {
                if(line.contains("||"+word+"'")) return true;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
//  boolean isWord(String word){
//  try {
//      //시간 남으면 key 따로 분리할 예정
//      String key = "0255BF41748A78C0AAFCAC21A3D9BE1D";
//      URL url = new URL("https://stdict.korean.go.kr/api/search.do?key=" + key + "&type_search=search&q=" + word);
//      String line = "";
//      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//      connection.setRequestMethod("GET");
//      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//      while ((line = br.readLine()) != null) {
//          if(line.contains("<word>")) return true;
//      }
//      br.close();
//  } catch (Exception e) {
//      e.printStackTrace();
//  }
//  return false;
//}
    
    void setLastWord(String word){
        lastWord = word.charAt(word.length() - 1);
        System.out.println("lastWord = " + lastWord);
    }

    void setMessage(String message){
        lbMessage.setText(message);
    }

    void addScore(){
        lbScore.setText(Integer.toString(++score));
    }

    @FXML
    void onPressEnter(KeyEvent e) {
//        System.out.println("score = " + score);
        if( e.getCode() == KeyCode.ENTER ) {
            String word = inputWord.getText();
            System.out.println("size >> " + word.length());
            if(word.length() < minLetterCnt) {
                setMessage("단어는 최소 " + minLetterCnt + "글자 이상부터 입력할 수 있습니다.");
                return;
            }
            if(wordList.contains(word)) {
                setMessage("중복된 단어입니다.");
                return;
            }
            // 새로 입력한 단어가 전에 입력한 마지막 글자와 같은지 비교
            if((score>=1)&&(lastWord!=word.charAt(0))) {
           		setMessage("시작 글자는[ " + lastWord + " ]입니다.");
           		return;
            }
            setMessage("");
            if(isWord(word)) {
                wordList.add(word);
                setLastWord(word);
                lbPrevWord.setText(word);
                setMessage("[ " + lastWord + " ]로 시작하는 단어를 입력하세요.");
                inputWord.setText("");
                addScore();
            } else setMessage("등록되지 않은 단어입니다.");
        }
    }

    @FXML
    void onClickMainButton(ActionEvent e) throws IOException {
        Node node = (Node)(e.getSource());
        stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("미니게임");
        stage.setScene(scene);
        stage.show();
    }

}