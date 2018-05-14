package drawing;

import java.awt.*;
import java.io.PrintStream;
import java.util.Scanner;

public class Line extends Shape2 implements Drawable {
    Point2 end;

    public Line(Point2 start, Point2 end) {
        this(start,end, Color.RED,Color.BLACK);
    }

    public Line(Point2 start, Point2 end, Color solidColor, Color borderColor) {
        super(start,solidColor,borderColor);
    }

    public Line(Scanner scanner) {
        super(scanner);
        load(scanner,false);
    }

    public Point2 getEnd() {
        return end;
    }

    public void setEnd(Point2 end) {
        this.end = end;
    }

    public double getArea() {
        return 0;
    }

    @Override
    public void render(Graphics2D G) {
        ((Graphics2D)G).setStroke(new BasicStroke(thickness));
        G.setColor(this.solidColor);
        G.drawLine((int)location.getX(),(int)location.getY(),(int)end.getX(),(int)end.getY());
    }

    public boolean isIn(Point2 p) {
        return false;
    }

    public void save(PrintStream printer) {
        super.save(printer);
        end.save(printer);
    }

    public void load(Scanner scanner) {
        load(scanner,true);
    }

    public void load(Scanner scanner, boolean loadSuper) {
        if(loadSuper) {
            super.load(scanner);
        }
        end=new Point2(scanner);
    }
}
