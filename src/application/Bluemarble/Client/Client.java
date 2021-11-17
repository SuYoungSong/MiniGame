package application.Bluemarble.Client;

import application.Bluemarble.Client.GameLobby.GameLobbyController;
import application.Bluemarble.Client.GameRoom.GameRoomController;
import application.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client{
    //현재 클릭 이벤트가 발생한 아이템(지점)
    private MouseEvent me;
    protected boolean isConnect;
    
    public void setMouseEvent(MouseEvent e){ me = e; }

    Socket socket;
    TextArea textArea;

    void printPayload(String str[]){
        System.out.printf("payload >> ");
        for(String s:str){
            System.out.printf("%s ", s);
        }
        System.out.println();
    }

    //클라이언트 프로그램 동작 메서드
    public void startClient(String IP, int PORT){
        Thread thread = new Thread(() -> {
            try {
                socket = new Socket(IP, PORT);
                //서버로부터 메시지를 전달받기 위한 메서드
                isConnect = true;
                receive();
            } catch(Exception e) {
                if(socket==null || !socket.isClosed()){
                    stopClient();
                    isConnect = false;
                    System.out.println("[서버 접속 실패]");
                    //프로그램 종료
//                    Platform.exit();
                }
            }
        });
        thread.start();
    }

    //클라이언트 프로그램 종료 메서드
    public void stopClient(){
        try {
            if(socket != null && !socket.isClosed()){
            	isConnect = false;
                socket.close();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //서버로부터 메시지를 전달받는 메서드
    public void receive(){
        while(true){
            try {
                InputStream in = socket.getInputStream();
                byte[] buffer = new byte[512];
                int length = in.read(buffer);
                if(length == -1) throw new IOException();
                String message = new String(buffer, 0, length, "UTF-8");
                System.out.println("[receive] message >> " + message);
                Platform.runLater(() -> {
                    Node node = (Node)me.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(GameLobbyController.class.getResource("GameLobbyUI.fxml"));
                    Parent root = null;
                    try {
                        root = (Parent)loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    GameLobbyController controller = loader.<GameLobbyController>getController();

                    if(message.contains("@@payload:")) {
                        String payload[] = message.replace("@@payload:##", "").split("##");
                        printPayload(payload);
                        controller.setResMsg(payload);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                });


            } catch(Exception e) {
                stopClient();
                break;
            }
        }
    }

    //서버로 메시지를 전송하는 메서드
    public void send(String message){
        Thread thread = new Thread(() -> {
            try {
                OutputStream out = socket.getOutputStream();
                //보내고자 하는 정보를 UTF-8로 인코딩해서 보내준다
                //서버도 UTF-8로 받을 수 있게 되있음
                byte[] buffer = message.getBytes(StandardCharsets.UTF_8);
                System.out.println("[send] message >> " + message);
                out.write(buffer);
                //메시지 전송의 끝을 알림
                out.flush();
            } catch(Exception e){
                stopClient();
            }
        });
        thread.start();
    }

}