package application.Bluemarble.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

//서버와의 연결과 각  클라이언트
public class Client {
	Socket socket;
	private int PORT = 5005;
	private String IP = "localhost";
	/* 메시지 송신을 위함 */
	OutputStream outputStream;
	DataOutputStream dataOutputStream;

	void start() {
		Client client = new Client();
		try {
			client.socket = new Socket(IP, PORT);	// 서버연결
			client.outputStream = client.socket.getOutputStream();
			client.dataOutputStream = new DataOutputStream(client.outputStream);
			
			MessageProcessing messageProcessing = new MessageProcessing(client, client.socket);
			messageProcessing.start();
		} catch(SocketException e) {
			System.out.println("[Client] 서버 연결 오류 > " + e.toString());
		} catch(IOException e) {
			System.out.println("[Client] 입출력 오류 > " + e.toString());
		}
	}
	/* 서버에 메시지 전송 */
	void sendMsg(String message) {
		try {
			dataOutputStream.writeUTF(message);
		} catch(Exception e) {
			System.out.println("[Client] 메시지 전송 오류 > " + e.toString());
		}
	}
}