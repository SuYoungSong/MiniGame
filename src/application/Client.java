package application;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;

        Scanner sc = new Scanner(System.in);

        final int PORT = 5005;

        try {
            socket = new Socket("localhost", PORT);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                System.out.print("Send >> ");
                String outMessage = sc.nextLine();

                if(outMessage.equals("exit")){
                    out.write(outMessage + "\n");
                    out.flush();
                    System.out.println("Exit Server...");
                    break;
                }

                out.write(outMessage + "\n");
                out.flush();

                String inMessage = in.readLine();
                System.out.println("Server >> " + inMessage);




            }

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            sc.close();
            in.close();
            out.close();
            socket.close();


        }

    }
}
