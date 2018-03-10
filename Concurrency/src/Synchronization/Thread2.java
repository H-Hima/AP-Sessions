package Synchronization;

import java.awt.*;

public class Thread2 extends Thread{
    int start,end;
    String name;
    Printer2 printer;

    public Thread2(String name, int start,int end,Printer2 printer) {
        this.start=start;
        this.end=end;
        this.name=name;
        this.printer=printer;
    }

    @Override
    public void run() {
        synchronized (Color.red) {
            for (int i = start; i < end; i++) {
                printer.print("Thread2(" + name + ")");
                printer.print(new Integer(i).toString());
            }
        }
    }
}
