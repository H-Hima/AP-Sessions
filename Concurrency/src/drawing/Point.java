package drawing;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Scanner;

public class Point implements Serializable {
    protected double x,y;

    public Point(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public Point(Scanner scanner) {
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

    public Point subtract(Point p) {
        return new Point(x-p.x,y-p.y);
    }

    public Point add(Point p) {
        return new Point(p.x+x,p.y+y);
    }

    public double dotProduct(Point p) {
        return (x*p.x+y*p.y);
    }

    public double crossProduct(Point p) {
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
