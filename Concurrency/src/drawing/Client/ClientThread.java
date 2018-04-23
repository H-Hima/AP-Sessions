package drawing.Client;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ClientThread extends Thread {
    MainFrame frame=null;
    Socket clientSocket=null;
    BufferedOutputStream outputStream=null;
    BufferedInputStream inputStream=null;
    ObjectInputStream objectInputStream=null;
    ObjectOutputStream objectOutputStream=null;

    ClientThread(Socket clientSocket, MainFrame frame) {
        this.clientSocket=clientSocket;
        this.frame=frame;
    }

    public void run() {
        try {
            outputStream=new BufferedOutputStream(clientSocket.getOutputStream());
            inputStream=new BufferedInputStream(clientSocket.getInputStream());
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
            try {
                objectOutputStream.write(10);
                objectOutputStream.flush();
                frame.load(objectInputStream);
                //frame.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
