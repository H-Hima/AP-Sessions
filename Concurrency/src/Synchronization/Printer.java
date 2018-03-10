package Synchronization;

public class Printer {
    int start,end;

    public Printer(int start, int end) {
        this.start=start;
        this.end=end;
    }

    synchronized void print(String str) {
        for(int i=start;i<end;i++) {
            System.out.println("Thread1("+str+")");
            System.out.println(new Integer(i).toString());
        }
    }
}
