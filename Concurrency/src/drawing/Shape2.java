package drawing;

import java.awt.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Shape2 implements Drawable, Animatable, Serializable {
    protected Point2 location;

    protected Color solidColor=Color.WHITE, borderColor=Color.BLACK;
    protected int thickness=3;

    protected ArrayList<Animation> animations=new ArrayList<>();

    Shape2(Point2 location) {
        this.location=location;
    }

    public Point2 getLocation() {
        return location;
    }

    public void setLocation(Point2 location) {
        this.location = location;
    }

    Shape2(Point2 location, Color solidColor, Color borderColor) {
        this.location=location;
        this.borderColor=borderColor;
        this.solidColor=solidColor;
    }

    public Shape2(Scanner scanner) {
        location = new Point2(scanner);
        thickness=scanner.nextInt();
        solidColor = new Color(scanner.nextInt());
        borderColor = new Color(scanner.nextInt());
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public Color getSolidColor() {
        return solidColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setSolidColor(Color color) {
        this.solidColor=color;
    }

    public void setBorderColor(Color color) {
        this.borderColor=color;
    }

    public abstract boolean isIn(Point2 p);

    public abstract double getArea();

    public void save(PrintStream printer) {
        location.save(printer);
        printer.println(thickness);
        printer.println(solidColor.getRGB());
        printer.println(borderColor.getRGB());
    }

    public void load(Scanner scanner) {
        location = new Point2(scanner);
        thickness=scanner.nextInt();
        solidColor = new Color(scanner.nextInt());
        borderColor = new Color(scanner.nextInt());
    }

    public void addAnimation(Animation animation) {
        animations.add(animation);
    }

    @Override
    public void step() {
        Iterator<Animation> cur=animations.iterator();
        while(cur.hasNext()) {
            cur.next().animate();
        }
    }
}
