package application.Bluemarble.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
	Socket socket;
	
	public Server(Socket socket) {
		this.socket = socket;
		receive();
	}
    
	/* 각 메시지를 구분하기 위한 태그 */
    final String checkNicknameTag = "checkNickname";
    final String createRoomTag = "createRoom";
    final String enterRoomTag = "enterRoom";
    final String leaveRoomTag = "leaveRoom";
	Room myRoom;
	String nickname;
	
	OutputStream out;
	InputStream in;
	
	// 클라이언트로부터 메시지를 전달 받는 메소드
	public void receive() {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					//반복적으로 받아오기 위해
					while(true) {
						in = socket.getInputStream();
						// 한번에 512바이트만큼 받을 수 있게
						byte[] buffer = new byte[512];
						int length = in.read(buffer);
						while(length == -1) throw new IOException();
						System.out.println("[메시지 수신 성공]"+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName());
						String message = new String(buffer, 0, length, "UTF-8");
						
						// 닉네임 중복 체크
						if(message.contains("##checkNicknameTag")) {	
							message = message.replace("##checkNicknameTag", "");
							checkNickname(message);
							
						// 방 만들기
						}else if(message.contains("##createRoomTag")) {
							message = message.replace("##createRoomTag", "");
							createRoom(message);
							
						// 방 입장
						}else if(message.contains("##enterRoomTag")) {
							message = message.replace("##enterRoomTag", "");
							enterRoom(message);
							
						// 방 퇴장
						}else if(message.contains("##leaveRoomTag")) {
							message = message.replace("##leaveRoomTag", "");
							leaveRoom(message);
						}
						
//						System.out.println("[서버] 수신 : "+message + "["+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]");
						//다른 클라이언트한태도 메시지 뿌려주기
//						for(Server client : ServerController.clients) {
//							client.send(message);
//						}
					}
				}catch(Exception e) {
					try {
						System.out.println("[메시지 수신 오류]"
								+socket.getRemoteSocketAddress()
								+ ": "+ Thread.currentThread().getName());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		ServerController.threadPool.submit(thread);
	}

	// 클라이언트로부터 메세지를 전달 하는 메소드
	public void send(String message) {
		System.out.println("[서버] 전송 : "+message);
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				} catch (Exception e){
					try {
						System.out.println("[메시지 송신 오류]"
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
						ServerController.allUser.remove(Server.this);
						socket.close();
					} catch(Exception e2) {
						e2.printStackTrace();
					}

				}
			}

		};
		ServerController.threadPool.submit(thread);
	}
	
	// 닉네임 중복 체크
	void checkNickname(String checkNickname) {
		 boolean flag = false;
		 for(int i = 0; i < ServerController.allUser.size(); i++){
	            if(nickname.equals(ServerController.allUser.get(i).nickname)) flag = true;
	        }
	        if (!flag) {
	            ServerController.allUser.add(this);
	            ServerController.lobbyUser.add(this);
	            System.out.println("checkRoomList >> " + checkRoomList());
	            
	            sendWaitRoom(checkRoomList());
	            
	            System.out.println("[ Server ] 닉네임 중복 체크 >> 닉네임 사용가능");
	            send("@@payload:" + "##checkNickname" + "##false" + "##" + checkNickname);
	        } else {
	            System.out.println("[ Server ] 닉네임 중복 체크 >> 동일한 닉네임 존재");
	            send("@@payload:" + "##checkNickname" + "##true" + "##" + checkNickname);
	        }
	    }
	
	// 클라이언트 분류 로비로 변경
	void sendWaitRoom(String str){
        for(int i = 0; i < ServerController.lobbyUser.size(); i++){
            try{
            	ServerController.lobbyUser.get(i).out.write(str.getBytes(StandardCharsets.UTF_8));
            } catch(Exception e){
            	ServerController.lobbyUser.remove(i--);
            }
        }
    }
	
	// 클라이언트 분류 게임룸 으로 변경
	void sendGameRoom(String str){
        for(int i = 0; i < myRoom.connectUsers.size(); i++){
            try{
                myRoom.connectUsers.get(i).out.write(str.getBytes(StandardCharsets.UTF_8));
            } catch(Exception e){
                myRoom.connectUsers.remove(i--);
            }
        }
    }
	
	// 클라이언트 종료
	void disconnectClient(){
		ServerController.allUser.remove(this);
		ServerController.lobbyUser.remove(this);
    }
	
	// 방 입장
	 void enterRoom(String payload){
	        for(int i = 0; i < ServerController.room.size(); i++){
	            if(payload.equals(ServerController.room.get(i).title)) {
	                if (ServerController.room.get(i).userCnt < 2) {
	                    System.out.println("[ Server ] 방 입장 >> Succeed");

	                    System.out.println();
	                    myRoom = ServerController.room.get(i);
	                    myRoom.userCnt++;

	                    ServerController.lobbyUser.remove(this);
	                    myRoom.connectUsers.add(this);

	                    sendWaitRoom(checkRoomList());
	                    sendGameRoom(checkRoomUser());

	                    send("@@payload:##" + enterRoomTag + "##true" + "##null");
	                } else {
	                    System.out.println("[ Server ] 방 입장 >> Failed: 인원이 초과");
	                    send("@@payload:##" + enterRoomTag + "##false" + "##인원을 초과했습니다.");
	                }
	            } else {
	                System.out.println("[ Server ] 방 입장 >> Failed: 존재하지 않는 방");
	                send("@@payload:##" + enterRoomTag + "##false" + "##방이 존재하지 않습니다.");

	            }
	        }
	    }
	// 방 퇴장
	 void leaveRoom(String payload){
	        myRoom.connectUsers.remove(this);
	        myRoom.userCnt--;
	        ServerController.lobbyUser.add(this);
	        System.out.println("[ Server ] 방 퇴장 >> Succeed: " + myRoom.title);
	        if(myRoom.userCnt < 1){
	        	ServerController.room.remove(myRoom);
	            System.out.println("[ Server ] 방 퇴장 >> " + myRoom.title + "에 인원이 존재하지 않아 방을 삭제했습니다.");
	        }
	        if(ServerController.room.size() > 0){
	            sendGameRoom(checkRoomUser());
	        }
	    }
	 // 방 만들기
	 void createRoom(String payload){
	        myRoom = new Room();
	        myRoom.userCnt++;
	        myRoom.title = payload;
	        ServerController.room.add(myRoom);
	        myRoom.connectUsers.add(this);
	        ServerController.lobbyUser.remove(this);
	        System.out.println("[ Server ] 방 생성 >> Succeed");
	    }
	 // printPayload
	 void printPayload(String str) {
	        System.out.printf("[ Receive ] Payload[] >> ");
	           System.out.printf("%s ", str);
	        System.out.println();
	    }

	 // checkRoomUser
	 String checkRoomUser(){
	        String str = "@@payload:##" + enterRoomTag;
	        for(int i = 0; i < myRoom.connectUsers.size(); i++){
	            str += myRoom.connectUsers.get(i).nickname + "##";
	        }
	        return str;
	    }
	 //checkRoomList
	 String checkRoomList(){
	        String str = "@@payload:##" + createRoomTag;
	        for(int i = 0; i < ServerController.room.size(); i ++) {
	            str += "##" + ServerController.room.get(i).title + "##" + ServerController.room.get(i).userCnt;
	        }
	        return str;
	    }
	}
