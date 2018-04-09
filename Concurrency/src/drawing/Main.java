package drawing;

import Synchronization.Printer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main extends JFrame{

    static int staticVariable=100;

    public static void main(String args[]) throws IOException {
        System.out.println(new Circle(new Point(10,10),10).getClass().toString());
        System.out.println(Circle.class.getCanonicalName());
        MainFrame frame=new MainFrame();
    }
}
