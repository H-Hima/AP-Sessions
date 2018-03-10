package Synchronization;

public class Printer2 {
    synchronized void print(String str) {
        System.out.println("Thread2("+str+")");
    }
}
