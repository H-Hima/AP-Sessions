package Synchronization;

public class Thread1 extends Thread{
    int start,end;
    String name;
    Printer printer;

    public Thread1(String name,int start, int end, Printer printer) {
        this.start=start;
        this.end=end;
        this.name=name;
        this.printer=printer;
    }

    @Override
    public synchronized void run() {
        printer.print(name);
//
// for(int i=start;i<end;i++) {
//            printer.print("Thread1("+name+")");
//            printer.print(new Integer(i).toString());
//        }
    }
}
