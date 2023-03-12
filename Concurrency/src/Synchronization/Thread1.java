package Synchronization;

import java.awt.*;
import java.util.concurrent.Semaphore;

public class Thread1 extends Thread{
    int start,end;
    String name;
    MySemaphore semaphore;
    static int counter=0;
    boolean sign;

    public Thread1(String name,int start, int end, MySemaphore semaphore,boolean sign) {
        this.start=start;
        this.end=end;
        this.name=name;
        this.semaphore=semaphore;
        this.sign=sign;
    }

    @Override
    public void run() {
        if(!sign) {
            semaphore.myWait();
        }
        for (int i = start; i < end; i++) {
            System.out.println("Thread1(" + name + ")");
            System.out.println(new Integer(i).toString());
            counter++;
            System.out.println("Counter: " + counter);
        }
        if(sign)
            semaphore.myNotify();
    }
}
