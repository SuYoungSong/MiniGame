package application.Bluemarble.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import application.Bluemarble.Client.GameLobby.GameLobbyController;
import javafx.application.Platform;

public class Client{
	Socket socket;
	
	//클라이언트↔서버 간 메시지 구분 태그
	final String overlapNickCheck = "OVERLAPNICKCHECK";		// 닉네임 중복확인
	final String createRoom = "CREATEROOM";					// 방 만들기
	final String roomInfo = "ROOMINFO";						// 방 정보 조회 ( 제목, 인원 )
	final String roomExit = "ROOMEXIT";						// 방 퇴장
	final String roomJoin = "ROOMJOIN";						// 방 들어가기
	final String nomalMsg = "NOMALMSG";						// 게임방에서 유저의 일반 대화
	final String blueMarble = "BLUEMARBLE";					// 부루마블과 관련된 내용
			
	//클라이언트 프로그램 동작 메소드
	public void startClient(String IP, int port) {
		Thread thread = new Thread() {
			public void run() {
				try {
					System.out.println("스타트");
					socket = new Socket(IP, port);
					receive();
				}catch (Exception e) {
					if(socket==null || !socket.isClosed()) {
						stopClient();
						System.out.println("[서버 접속 실패]");
//						Platform.exit();
					}
				}
			}
		};
		thread.start();
	}

	// 클라이언트 프로그램 종료 메소드
	public void stopClient() {
		try {
			if(socket!=null&&!socket.isClosed()) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 서버로부터 메시지를 전달받는 메소드
	public void receive() {
		while(true) {
			try {
				InputStream in = socket.getInputStream();
				byte[] buffer = new byte[512];
				int length = in.read(buffer);
				if(length == -1 ) throw new IOException();
				String message = new String(buffer, 0, length, "UTF-8");
				Platform.runLater(()->{
//					textArea.appendText(message);
				});
			}catch (Exception e) {
				stopClient();
				break;
			}
		}
	}
	OutputStream out = null;
	// 서버로부터 메시지를 전송하는 메소드
	public void send(String message) {
		Thread thread = new Thread() {
			public void run() {
				try {
					out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					System.out.println("[클라이언트] 전송 : "+buffer);
					out.write(buffer);
					out.flush();
				}catch (Exception e) {
					stopClient();
				}
			}
		};
		thread.start();
	}
}
