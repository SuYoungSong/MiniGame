package application.Bluemarble.Server;

import java.util.Vector;

public class Room {
    Vector<Server> connectUsers;
    String title;
    int userCnt;
    Room(){
        connectUsers = new Vector<>();
    }
}