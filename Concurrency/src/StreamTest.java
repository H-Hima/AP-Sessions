import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.Scanner;

public class StreamTest {
    public static void main(String args[]) throws IOException {
        Boolean writeMode=false;

        Scanner sc=new Scanner(System.in);
        System.out.println("Hello");

        if(writeMode) {
            FileOutputStream foutput = new FileOutputStream("test.txt");
            PrintStream printer = new PrintStream(foutput);
            printer.print(3234324);
            printer.println(88);
            printer.println("NextLine");
        }
        else {
            FileInputStream finput=new FileInputStream("test.txt");
            Scanner fsc=new Scanner(finput);
            System.out.println(fsc.nextInt());
            System.out.println(fsc.next());
        }
        RandomAccessFile raf=new RandomAccessFile("test.dat","wr");
        raf.seek(500);
        raf.getFilePointer();
    }
}
