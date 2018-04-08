package drawing;

import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Shape implements Drawable, Animatable {
    protected Point location;

    protected Color solidColor=Color.WHITE, borderColor=Color.BLACK;
    protected int thickness=3;

    protected ArrayList<Animation> animations=new ArrayList<>();

    Shape(Point location) {
        this.location=location;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    Shape(Point location,Color solidColor,Color borderColor) {
        this.location=location;
        this.borderColor=borderColor;
        this.solidColor=solidColor;
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

    public abstract boolean isIn(Point p);

    public abstract double getArea();

    public void save(PrintStream printer) {
        printer.println(solidColor.toString());
    }

    public void load(Scanner scanner) {
        //printer.println(solidColor.toString());
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
