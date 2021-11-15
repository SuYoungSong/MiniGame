package application.Bluemarble.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;

public class Server {
	ServerSocket serverSocket = null;
	// 클라이언트 나누기 위한 벡터
	Vector<ClientInteraction> allClient;		//서버에 연결된 모든 클라이언트
	Vector<ClientInteraction> lobbyClient;		//게임로비에 연결되 있는 클라이언트
	Vector<Room> room;						//만들어진 방
	
	public static void main(String[] args) {
		Server server = new Server();
		
		server.allClient = new Vector<>();
		server.lobbyClient = new Vector<>();
		server.room = new Vector<>();
		
		try {
			//서버 소켓 준비
			server.serverSocket = new ServerSocket(5005);
			System.out.println("[Server] 서버 소켓 준비 완료");
			
			//클라이언트의 연결 요청을 상시 대기. 
			while(true) {
				Socket socket = server.serverSocket.accept();
				ClientInteraction CI = new ClientInteraction(socket, server);	//소켓과 서버를 넘겨 CCUser(접속한 유저 관리)객체 생성
				
				CI.start();	//CCUser 스레드 시작
			}
		} catch(SocketException e) {	//각 오류를 콘솔로 알린다.
			System.out.println("[Server] 서버 소켓 오류 > " + e.toString());
		} catch(IOException e) {
			System.out.println("[Server] 입출력 오류 > " + e.toString());
		}
	}
}
