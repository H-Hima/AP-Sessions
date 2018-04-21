package drawing.Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {
    MainFrame frame=null;
    Socket clientSocket=null;
    BufferedOutputStream outputStream=null;
    BufferedInputStream inputStream=null;
    Scanner scanner=null;
    PrintStream printer=null;

    ClientThread(Socket clientSocket, MainFrame frame) {
        this.clientSocket=clientSocket;
        this.frame=frame;
    }

    public void run() {
        try {
            inputStream=new BufferedInputStream(clientSocket.getInputStream());
            outputStream=new BufferedOutputStream(clientSocket.getOutputStream());
            scanner=new Scanner(inputStream);
            printer=new PrintStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while(isInterrupted()==false&&clientSocket.isConnected()) {
            printer.println();
            printer.flush();
            frame.load(scanner);
            //frame.repaint();
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
