package Streams;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        PipedInputStream pipeInput=new PipedInputStream();
        PipedOutputStream pipeOutput=new PipedOutputStream(pipeInput);

        Thread thread1=new Thread(new Runnable() {

            @Override
            public void run() {
                Scanner stdSccanner=new Scanner(System.in);
                PrintStream pipePrinter=new PrintStream(pipeOutput);
                while(true) {
                    String command=stdSccanner.nextLine();
                    pipePrinter.println(command);
                    System.out.println("Thread1 sent: " + command );
                    if(command.equals("Exit")) {
                        break;
                    }
                }
            }
        });

        Thread thread2=new Thread(new Runnable() {

            @Override
            public void run() {
                Scanner pipeScanner=new Scanner(pipeInput);
                while(true) {
                    String command=pipeScanner.nextLine();
                    System.out.println("Thread2 recieved: "+command);
                    if(command.equals("Exit")) {
                        break;
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
