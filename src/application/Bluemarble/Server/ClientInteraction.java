package application.Bluemarble.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientInteraction extends Thread {
	// 생성자
	ClientInteraction(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		
		allClient = server.allClient;
		lobbyClient = server.lobbyClient;
		room = server.room;		
	}
	
	Server server;
	Socket socket;
	
	/* 각 객체를 Vector로 관리 */
	Vector<ClientInteraction> allClient;	//연결된 모든 클라이언트
	Vector<ClientInteraction> lobbyClient;	//대기실에 있는 클라이언트
	Vector<Room> room;						//생성된 Room
	
	/* 메시지 송수신을 위함 */
	OutputStream outPutStream;
	InputStream inputStream;
	DataOutputStream dataOutputStream;
	DataInputStream dataInputStream;
	
	String msg;			//수신 메시지를 저장할 공간
	String nickname;	//클라이언트의 닉네임을 저장할 공간

	Room myRoom;		//입장한 방 객체를 저장할 공간
	
	// 클라이언트↔서버 간 메시지 구분 태그
	final String overlapNickCheck = "OVERLAPNICKCHECK";		// 닉네임 중복확인
	final String createRoom = "CREATEROOM";					// 방 만들기
	final String roomInfo = "ROOMINFO";						// 방 정보 조회 ( 제목, 인원 )
	final String roomExit = "ROOMEXIT";						// 방 퇴장
	final String roomJoin = "ROOMJOIN";						// 방 들어가기
	final String nomalMsg = "NOMALMSG";						// 게임방에서 유저의 일반 대화
	final String blueMarble = "BLUEMARBLE";					// 부루마블과 관련된 내용
	
	public void run() {
		try {
			System.out.println("[Server] 클라이언트 접속 > " + this.socket.toString());
			outPutStream = this.socket.getOutputStream();
			inputStream = this.socket.getInputStream();
			
			while(true) {
					msg = dataInputStream.readUTF();	//메시지 수신을 상시 대기한다.
					String[] m = msg.split("##");	// 태그 체크
					
					if(m[0].equals(overlapNickCheck)) { 	// 닉네임 중복 확인
						
					}else if(m[0].equals(createRoom)) {		// 방 만들기
						
					}else if(m[0].equals(roomInfo)) {		// 방 정보 확인
							
					}else if(m[0].equals(roomExit)) {		// 방 퇴장
						
					}else if(m[0].equals(roomJoin)) {		// 방 참가
						
					}else if(m[0].equals(nomalMsg)) {		// 게임방에서 유저의 일반대화
						
					}else if(m[0].equals(blueMarble)) {		// 부루마블과 관련된 내용
						// 후순위 개발
				}
			}
		} catch (Exception e) {
			System.out.println("[Server] 오류발생");
		}
	}
}
