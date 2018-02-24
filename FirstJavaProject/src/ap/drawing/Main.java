package ap.drawing;

import com.sun.javafx.iio.ImageStorage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{

    static int staticVariable=100;

    public static void main(String args[]) {
        System.out.println(staticVariable);

        Scanner sc=new Scanner(System.in);

        Shape shapes[]=new Shape[100];
        shapes[0]=new Triangle();
        shapes[1]=new Line();
        shapes[2]=new Line(new Point(600,100),new Point(100,600),Color.RED,Color.WHITE);
        shapes[3]=shapes[2];

        Drawable drawables[]=new Drawable[50];
        drawables[0]=(Line)shapes[1];
        drawables[1]=(Line)shapes[2];
        drawables[2]=(Triangle)shapes[0];

        System.out.println(shapes[0].getArea());
        System.out.println(shapes[1].getArea());
        System.out.println(shapes[2].getArea());
        System.out.println(shapes[3].getArea());

        Line l=(Line)shapes[1];
        System.out.println("=============");

        int b=5;

        Integer i=new Integer(15);

        Point p=new Point(5,5);

        System.out.println(b);
        System.out.println(p.y);

        JFrame frame=new JFrame("My frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,800));
        frame.setVisible(true);

        Graphics G=frame.getContentPane().getGraphics();

        while(1!=2) {
            for(Drawable shape:drawables) {
                try {
                    switch (((Shape)shape).shapeType) {
                        case Line:
                            System.out.println("Line");
                            break;
                        case Triangle:
                            System.out.println("Triangle");
                            break;
                    }

                    Line aa=null;

                    if(aa.shapeType.getStr().equals(Line.class.toString())) {

                    }

                    shape.render(G);
                }
                catch (Exception ex) {
                    //ex.printStackTrace();
                }
            }
        }
    }
}
