package drawing.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ListenThread extends Thread {
    ArrayList<ServerThread> threads;
    ServerSocket serverSocket=null;
    int serverPort;
    MainFrame frame=null;

    ListenThread(int serverPort, MainFrame frame) {
        threads=new ArrayList<>();
        this.frame=frame;
        this.serverPort=serverPort;
    }

    public void run() {
        try {
            serverSocket=new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while(isInterrupted()==false && serverSocket.isClosed()==false) {
            try {
                Socket clientSocket=serverSocket.accept();
                ServerThread thread=new ServerThread(clientSocket,frame);
                threads.add(thread);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(ServerThread thread:threads) {
            thread.interrupt();
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
