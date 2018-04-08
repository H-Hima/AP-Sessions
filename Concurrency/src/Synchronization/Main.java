package Synchronization;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        Semaphore semaphore=new Semaphore(1);

        Printer printer=new Printer(0,10000);
        Printer2 printer2=new Printer2();
        Printer2 printer2_2=new Printer2();
        Thread1 th1=new Thread1("First Thread",0,10000,printer);
        Thread1 th2=new Thread1("Second Thread",0,10000,printer);
        Thread2 th2_1=new Thread2("First Thread",0,10000,printer2,semaphore);
        Thread2 th2_2=new Thread2("Second Thread",0,10000,printer2_2,semaphore);
        th2_1.start();
        th2_2.start();
    }
}
