package application.wordChainGame;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WordChainGameComputerController implements Initializable {
    private Stage stage;
    private int score;
    private int chatIndex = 0;
    private ArrayList<String> wordList = new ArrayList<String>();
    private List<Label> messageList = new ArrayList<>();
    private char lastWord;
    private char firstWord;
    private char subLastWord;
    private boolean phoneticRule = false;
    private String msgSubLastWord;
    private final int minLetterCnt = 2;
    private VBox chatBox = new VBox(5);
    @FXML private TextField inputWord;
    @FXML private Text tMessage;
    @FXML private Text tScore;
    @FXML private ScrollPane scrollContainer;
    
    
    // 두음법칙 처리하는 메소드
    void runPhoneticRule(){
        char afterLetter[] = {'나', '낙', '난', '날', '남', '납', '낭', '내', '냉', '엽',
                '노', '녹', '논', '농', '뇌', '용', '누', '윤', '율', '융',
                '늑', '늠', '능', '인', '임', '입', '냑', '략', '냥', '량',
                '녀', '려', '녁', '력', '년', '련', '녈', '렬', '념', '렴',
                '녕', '령', '녜', '례', '뇨', '료', '뉴', '류', '뉵', '륙',
                '니', '리', '랴'};
        char beforeLetter[] = {'라', '락', '란', '랄', '람', '랍', '랑', '래', '랭', '렵',
                '로', '록', '론', '롱', '뢰', '룡', '루', '륜', '률', '륭',
                '륵', '름', '릉', '린', '림', '립', '약', '약', '양', '양',
                '여', '여', '역', '역', '연', '연', '열', '열', '염', '염',
                '영', '영', '예', '예', '요', '요', '유', '유', '육', '육',
                '이', '이', '야'};

        for(int i = 0, letterLen = afterLetter.length; i < letterLen; i++){
            if(lastWord == afterLetter[i]){
                phoneticRule = true;
                subLastWord = beforeLetter[i];
                return;
            }
            if(lastWord == beforeLetter[i]){
                phoneticRule = true;
                subLastWord = afterLetter[i];
                return;
            }
        }
        phoneticRule = false;
    }
    
    // 컴퓨터가 입력하는 단어를 받아오는 메소드
    String computerWord(char word){
        try {
            String findWord;
            int randomNum;
            URL url = new URL("https://stdict.korean.go.kr/common/autoComplete.json?searchKeyword=" + word);
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
                    if (!wordList.contains(wordBox[randomNum]) && randomNum != 0){
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
    
    // 사용자가 입력하는 단어가 국어사전에 있는 단어인지 확인하는 메소드
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
    
    void setFirstWord(String word){
        firstWord = word.charAt(0);
    }
    
    void setLastWord(String word){
        lastWord = word.charAt(word.length() - 1);
    }
    
    void setMessage(String message){
        tMessage.setText(message);

    }
    
    void addScore(){
        tScore.setText(Integer.toString(++score));
    }
    
    void setChatList(String word){

        messageList.add(new Label(word));
        if(chatIndex % 2 == 0) {
            messageList.get(chatIndex).setAlignment(Pos.CENTER_RIGHT);
            chatBox.getChildren().add(messageList.get(chatIndex));
            chatBox.getStyleClass().add("right");
        } else {
            messageList.get(chatIndex).setAlignment(Pos.CENTER_LEFT);
            chatBox.getChildren().add(messageList.get(chatIndex));
            chatBox.getStyleClass().add("left");
        }
        chatIndex += 1;
    }
    
    @FXML	// 단어를 입력 받음
    void onPressEnter(KeyEvent e) {
        if( e.getCode() == KeyCode.ENTER ) {
            String word = inputWord.getText();
            if(word.length() < minLetterCnt) {
                setMessage("단어는 최소 " + minLetterCnt + "글자 이상부터 입력할 수 있습니다.");
                return;
            }
            if(wordList.contains(word)) {
                setMessage("중복된 단어입니다.");
                return;
            }
            if(phoneticRule){
                setFirstWord(word);
                if(!(firstWord == lastWord ^ firstWord == subLastWord)) return;
            } else {
                // 새로 입력한 단어가 전에 입력한 마지막 글자와 같은지 비교
                if((score>=1)&&(lastWord!=word.charAt(0))) {
                    setMessage("시작 글자는 \'" + lastWord + "\'입니다.");
                    return;
                }
            }
            setMessage("");
            if(isWord(word)) {
                setChatList(word);
                wordList.add(word);
                setLastWord(word);
                runPhoneticRule();
                String comWord;
                if(phoneticRule) comWord = computerWord(subLastWord);
                else comWord = computerWord(lastWord);
                if(comWord == null) {
                    setMessage("컴퓨터 패배!");
                    setChatList("단어가 떠오르지 않아요 ㅠㅠ");
                    inputWord.setVisible(false);
                    return;
                }
                setChatList(comWord);
                wordList.add(comWord);
                setLastWord(comWord);
                runPhoneticRule();
                if(!phoneticRule) subLastWord = Character.MIN_VALUE;
                if(subLastWord != Character.MIN_VALUE) msgSubLastWord = ", " + subLastWord;
                else msgSubLastWord = "";
                setMessage("\'" + lastWord + msgSubLastWord + "\' (으)로 시작하는 단어를 입력하세요.");
                inputWord.setText("");
                addScore();
                scrollToBottom();
            } else setMessage("등록되지 않은 단어입니다.");
        }
    }
    
    public void scrollToBottom() {
        Platform.runLater(() -> this.scrollContainer.vvalueProperty().setValue(1.0));
    }
    
    @FXML	// 메인으로 돌아가는 버튼
    void onClickMainButton(MouseEvent e) throws IOException {
        Node node = (Node)(e.getSource());
        stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("/application/MainUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("미니게임");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML	// 마우스 호버 시작
    void onHoverEnter(MouseEvent e) {
        Node source = (Node)e.getSource();
        source.setStyle("-fx-cursor:hand;");
    }
    
    @FXML	// 마우스 호버 끝
    void onHoverExit(MouseEvent e) {
        Node source = (Node)e.getSource();
        source.setStyle("-fx-cursor:default;");
    }
    
    @FXML	// 다시하기 버튼
    void onClickRefresh(MouseEvent e) throws IOException {
        Node node = (Node)(e.getSource());
        stage = (Stage)(node.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("WordChainGameComputerUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("컴퓨터와 함께하는 끝말잇기");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollContainer.setContent(chatBox);
        scrollContainer.setFitToWidth(true);
    }
}