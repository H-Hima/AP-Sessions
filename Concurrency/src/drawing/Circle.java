package drawing;

import java.awt.*;
import java.io.PrintStream;
import java.util.Scanner;

public class Circle extends Shape implements Drawable {

    protected double radius=100;

    public Circle(Point center, double radius) {
        this(center,radius,Color.GREEN,Color.PINK);
    }

    public Circle(Point location, double radius, Color solidColor, Color borderColor) {
        super(location, solidColor, borderColor);
        this.radius=radius;
    }

    public Circle(Scanner scanner) {
        super(scanner);
        //radius=scanner.nextDouble();
        load(scanner,false);
    }

    @Override
    public void render(Graphics2D G) {
        G.setColor(solidColor);
        G.fillOval((int)(location.x-radius),(int)(location.y-radius),(int)radius*2,(int)radius*2);

        Stroke stroke=G.getStroke();
        G.setStroke(new BasicStroke(thickness));
        G.setColor(borderColor);
        G.drawOval((int)(location.x-radius),(int)(location.y-radius),(int)radius*2,(int)radius*2);
        G.setStroke(stroke);
    }

    public boolean isIn(Point p) {
        double dist=location.subtract(p).getRad();
        return dist<radius;
    }

    @Override
    public double getArea() {
        return Math.PI*radius*radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void save(PrintStream printer) {
        super.save(printer);
        printer.println(radius);
    }

    public void load(Scanner scanner) {
        load(scanner,true);
    }

    public void load(Scanner scanner, boolean loadSuper) {
        if(loadSuper) {
            super.load(scanner);
        }
        radius = scanner.nextDouble();
    }
}
