package Streams;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainStream {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("abcd.txt");
        Socket socket=null;
        inputStream=socket.getInputStream();

        OutputStream outputStream = new FileOutputStream("c:/adsa.txt");
        outputStream=socket.getOutputStream();
        PrintStream printer=new PrintStream(outputStream);

        outputStream=System.out;
    }
}
