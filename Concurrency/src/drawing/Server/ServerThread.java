package drawing.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {

    MainFrame frame=null;
    Socket clientSocket=null;
    BufferedOutputStream outputStream=null;
    BufferedInputStream inputStream=null;
    ObjectInputStream objectInputStream=null;
    ObjectOutputStream objectOutputStream=null;


    ServerThread(Socket clientSocket, MainFrame frame) {
        this.clientSocket=clientSocket;
        this.frame=frame;
    }

    public void run() {
        try {
            outputStream=new BufferedOutputStream(this.clientSocket.getOutputStream());
            inputStream=new BufferedInputStream(this.clientSocket.getInputStream());
            objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.write(5);
            objectOutputStream.flush();
            objectInputStream=new ObjectInputStream(inputStream);
            objectInputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while(isInterrupted()==false&&clientSocket.isConnected()) {
            synchronized (frame) {
                try {
                    objectInputStream.read();
                    frame.save(objectOutputStream);
                    objectOutputStream.flush();
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
