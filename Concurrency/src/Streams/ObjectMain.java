package Streams;

import drawing.Circle;
import drawing.Point;

import java.io.*;
import java.util.ArrayList;

public class ObjectMain implements Serializable{

    private final static long serialVersionUID=234234234;

    public static void main(String args[]) throws IOException {
        PipedInputStream pipeInput=new PipedInputStream();
        PipedOutputStream pipeOutput=new PipedOutputStream(pipeInput);

        Thread thread1=new Thread(new Runnable() {

            ObjectOutputStream objectOutput = null;
            ObjectInputStream objectInput = null;

            @Override
            public void run() {
                try {
                    objectOutput = new ObjectOutputStream(pipeOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ArrayList<Object> myList=new ArrayList<>();
                while(true) {
                    myList.add("First");
                    myList.add(100);
                    myList.add(100.0);
                    myList.add(new Circle(new Point(10,10),10));
                    try {
                        objectOutput.reset();
                        objectOutput.writeObject(myList);
                        Thread.sleep(5000);
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

            ObjectOutputStream objectOutput = null;
            ObjectInputStream objectInput=null;

            @Override
            public void run() {
                try {
                    objectInput= new ObjectInputStream(pipeInput);
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
