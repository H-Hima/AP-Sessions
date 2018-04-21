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
    Scanner scanner=null;
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
            scanner=new Scanner(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while(isInterrupted()==false&&clientSocket.isConnected()) {
            synchronized (frame) {
                try {
                    scanner.nextLine();
                    frame.save(printer);
                    printer.flush();
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
