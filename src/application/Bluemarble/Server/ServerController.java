package application.Bluemarble.Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController {

    @FXML private TextArea taLogData;
    @FXML private TextField tfMsgInput;
    String IP = "127.0.0.1";
	int port = 5005;
	
	// 쓰래드를 효율적으로 관리하기 위한 라이브러리    * 스레드의 갯수를 제한하여 서버 안정적 운영
		public static ExecutorService threadPool;
		// 접속한 클라이언트들을 관리하기 위함
		// 멀티 쓰레드 환경에서 안전하게 객체 추가와 삭제가 가능하다고 함
		public static Vector<Server> clients = new Vector<Server> ();

		ServerSocket serverSocket;

		//서버를 구동시켜서 클라이언트의 연결을 기다리는 메소드
		public void startServer(String IP, int port) {
			try {
				serverSocket = new ServerSocket();
				serverSocket.bind(new InetSocketAddress(IP, port));
			} catch (Exception e) {
				e.printStackTrace();
				if(!serverSocket.isClosed()) {
					stopServer();
				}
				return;
			}
			//클라이언트가 접속할 때 까지 계속 기다리는 쓰레드
			Runnable thread = new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							Socket socket = serverSocket.accept();
							clients.add(new Server(socket));
							System.out.println("[클라이언트 접속]"
									+ socket.getRemoteSocketAddress()
									+ ": "+Thread.currentThread().getName());
						}catch (Exception e) {
							if(serverSocket==null || !serverSocket.isClosed()) {
								stopServer();
							}
							break;
						}
					}
				}
			};
			threadPool = Executors.newCachedThreadPool();
			threadPool.submit(thread);
		}
		// 서버의 작동을 중지시키는 메소드
		public void stopServer() {
			try {
				//현재 작동중인 모든 소켓 닫기
				Iterator<Server> iterator = clients.iterator();
				while(iterator.hasNext()) {
					Server client = iterator.next();
					client.socket.close();
					iterator.remove();
				}
				// 서버 소켓 객체 닫기
				if(serverSocket != null&& !serverSocket.isClosed()) {
					serverSocket.close();
				}
				//쓰레드 풀 종료하기
				if(threadPool!=null&& !threadPool.isShutdown()) {
					threadPool.shutdown();
				}
			}catch( Exception e) {
				e.printStackTrace();
			}
		}
		
    @FXML
    void onClickMsgSend(ActionEvent event) {

    }

    @FXML
    void onClickServerButton(ActionEvent event) {
    	Button toggleButton = (Button) event.getSource();
    	if(toggleButton.getText().equals("시작하기")) {
			startServer(IP, port);
			Platform.runLater(()->{
				String message = String.format("[부루마블 서버 시작]\n",IP, port);
				taLogData.appendText(message);
				toggleButton.setText("종료하기");
			});
		}else {
			stopServer();
			Platform.runLater(()->{
				String message = String.format("[부루마블 서버 종료]\n",IP,port);
				taLogData.appendText(message);
				toggleButton.setText("시작하기");
			});
		}
    }

}
