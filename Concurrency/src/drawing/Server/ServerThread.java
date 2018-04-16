package drawing.Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {

    MainFrame frame=null;
    Socket clientSocket=null;
    BufferedOutputStream outputStream=null;
    BufferedInputStream inputStream=null;
    PrintStream printer=null;

    ServerThread(Socket clientSocket, MainFrame frame) {
        this.clientSocket=clientSocket;
        this.frame=frame;
    }

    public void run() {
        try {
            inputStream=new BufferedInputStream(this.clientSocket.getInputStream());
            outputStream=new BufferedOutputStream(this.clientSocket.getOutputStream());
            printer=new PrintStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while(isInterrupted()==false&&clientSocket.isConnected()) {
            synchronized (frame) {
                try {
                    frame.save(printer);
                    //Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
