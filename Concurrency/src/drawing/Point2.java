package drawing;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Scanner;

public class Point2 implements Serializable {
    protected double x,y;

    public Point2(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public Point2(Scanner scanner) {
        load(scanner);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x=x;
    }
    public void setY(double y) {
        this.y=y;
    }

    public Point2 subtract(Point2 p) {
        return new Point2(x-p.x,y-p.y);
    }

    public Point2 add(Point2 p) {
        return new Point2(p.x+x,p.y+y);
    }

    public double dotProduct(Point2 p) {
        return (x*p.x+y*p.y);
    }

    public double crossProduct(Point2 p) {
        return (x*p.y-y*p.x);
    }

    public double getRad() {
        return Math.sqrt(x*x+y*y);
    }

    public void save(PrintStream printer) {
        printer.println(x);
        printer.println(y);
    }

    public void load(Scanner scanner) {
        x=scanner.nextDouble();
        y=scanner.nextDouble();
    }

    public String toString() {
        return "Before Copy: ("+x+","+y+")...";
    }
}
