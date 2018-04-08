package Synchronization;

import java.awt.*;
import java.util.concurrent.Semaphore;

public class Thread2 extends Thread{
    int start,end;
    String name;
    Printer2 printer;
    Semaphore semaphore;

    public Thread2(String name, int start,int end,Printer2 printer,Semaphore semaphore) {
        this.semaphore=semaphore;
        this.start=start;
        this.end=end;
        this.name=name;
        this.printer=printer;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = start; i < end; i++) {
            printer.print("Thread2(" + name + ")");
            printer.print(new Integer(i).toString());
        }
        semaphore.release();
    }
}
