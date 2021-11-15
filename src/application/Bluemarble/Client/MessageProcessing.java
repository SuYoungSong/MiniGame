package application.Bluemarble.Client;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class MessageProcessing extends Thread{
	
	MessageProcessing(Client client, Socket socket) {
		this.client = client;
		this.socket = socket;
	}
	
	Socket socket;
	Client client;
	
	/* 메시지 수신을 위함 */
	InputStream inputStream;
	DataInputStream dataInputStream;
	
	String msg;	//수신 메시지 저장
	
	//클라이언트↔서버 간 메시지 구분 태그
		final String overlapNickCheck = "OVERLAPNICKCHECK";		// 닉네임 중복확인
		final String createRoom = "CREATEROOM";					// 방 만들기
		final String roomInfo = "ROOMINFO";						// 방 정보 조회 ( 제목, 인원 )
		final String roomExit = "ROOMEXIT";						// 방 퇴장
		final String roomJoin = "ROOMJOIN";						// 방 들어가기
		final String nomalMsg = "NOMALMSG";						// 게임방에서 유저의 일반 대화
		final String blueMarble = "BLUEMARBLE";					// 부루마블과 관련된 내용
		
		
	public void run() {
		try {
			inputStream = this.socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			
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
			
		}catch(Exception e) {
			System.out.println("[Client] Error: 메시지 받기 오류 > " + e.toString());
		}
	}
}
