package Synchronization;

public class Main {
    public static void main(String args[]) {
        Printer printer=new Printer(0,10000);
        Printer2 printer2=new Printer2();
        Printer2 printer2_2=new Printer2();
        Thread1 th1=new Thread1("First Thread",0,10000,printer);
        Thread1 th2=new Thread1("Second Thread",0,10000,printer);
        Thread2 th2_1=new Thread2("First Thread",0,10000,printer2);
        Thread2 th2_2=new Thread2("Second Thread",0,10000,printer2_2);
        th2_1.start();
        th2_2.start();
    }
}
