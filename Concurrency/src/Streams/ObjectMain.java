package Streams;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjectMain implements Serializable{

    private final static long serialVersionUID=234234234;

    public static void main(String args[]) throws IOException {
        PipedInputStream pipeInput=new PipedInputStream();
        PipedOutputStream pipeOutput=new PipedOutputStream(pipeInput);

        Thread thread1=new Thread(new Runnable() {

            ObjectOutputStream objectPrinter = null;

            @Override
            public void run() {
                try {
                    objectPrinter = new ObjectOutputStream(pipeOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while(true) {
                    ArrayList<Object> myList=new ArrayList<>();
                    myList.add("First");
                    myList.add(100);
                    myList.add(100.0);
                    try {
                        objectPrinter.writeObject(myList);
                        Thread.sleep(10000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread1 sent: " + myList.toString() );
                }
            }
        });

        Thread thread2=new Thread(new Runnable() {

            ObjectInputStream objectInput=null;

            @Override
            public void run() {
                try {
                    objectInput= new ObjectInputStream(pipeInput);
                    Object str="My String";
                    Class c=str.getClass();
                    c=String.class;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while(true) {
                    try {
                        Object object = objectInput.readObject();
                        System.out.println("Thread2 recieved: " + object.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
