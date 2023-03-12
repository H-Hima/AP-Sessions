package Synchronization;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        MySemaphore semaphore=new MySemaphore(0,1);

        Thread1 th1=new Thread1("Thread1_1",0,10000,semaphore,true);
        Thread1 th2=new Thread1("Thread1_2",0,10000,semaphore,false);

        th1.start();
        th2.start();
    }
}
