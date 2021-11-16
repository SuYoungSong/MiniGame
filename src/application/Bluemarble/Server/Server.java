package application.Bluemarble.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Server {
	Socket socket;

	public Server(Socket socket) {
		this.socket = socket;
		receive();
	}

	// 클라이언트로부터 메시지를 전달 받는 메소드
	public void receive() {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					//반복적으로 받아오기 위해
					while(true) {
						InputStream in = socket.getInputStream();
						// 한번에 512바이트만큼 받을 수 있게
						byte[] buffer = new byte[512];
						int length = in.read(buffer);
						while(length == -1) throw new IOException();
						System.out.println("[메시지 수신 성공]"+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName());
						String message = new String(buffer, 0, length, "UTF-8");
						//다른 클라이언트한태도 메시지 뿌려주기
						for(Server client : ServerController.clients) {
							client.send(message);
						}
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
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				} catch (Exception e){
					try {
						System.out.println("[메시지 송신 오류]"
								+ socket.getRemoteSocketAddress()
								+ ": " + Thread.currentThread().getName());
						ServerController.clients.remove(Server.this);
						socket.close();
					} catch(Exception e2) {
						e2.printStackTrace();
					}

				}
			}

		};
		ServerController.threadPool.submit(thread);
	}
}
