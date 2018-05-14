package drawing;

import java.awt.*;
import java.io.PrintStream;
import java.util.Scanner;

public class Rectangle2 extends Shape2 implements Animatable {

    protected double width;
    protected double height;

    private static void function(int a) {

    }

    public Rectangle2(double width, double height) {
        this(new Point2(0,0),width,height,Color.RED,Color.BLUE);
    }

    public Rectangle2(Point2 upperLeft , double width, double height) {
        this(upperLeft,width,height,Color.RED,Color.BLUE);
    }

    public Rectangle2(Point2 upperLeft , double width, double height, Color solidColor, Color borderColor) {
        super(upperLeft, solidColor, borderColor);
        this.width=width;
        this.height=height;
    }

    public Rectangle2(Scanner scanner) {
        super(scanner);
        load(scanner,false);
    }

    public static Rectangle2 RectangleFactory(Scanner scanner) {
        Rectangle2 c=new Rectangle2(scanner);//and load it if needed
        return c;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return (width*height);
    }

    @Override
    public void render(Graphics2D G) {
        Stroke stroke=G.getStroke();
        G.setColor(solidColor);
        G.fillRect((int)location.x,(int)location.y,(int)width,(int)height);

        G.setStroke(new BasicStroke(thickness));
        G.setColor(borderColor);
        G.drawRect((int)location.x,(int)location.y,(int)width,(int)height);

        G.setStroke(stroke);
    }


    public boolean isIn(Point2 p) {
        Point2 dif=p.subtract(location);
        if(dif.getX()>0&&dif.getY()>0&&dif.getX()<width&&dif.getY()<height)
            return true;
        return false;
    }

    public void save(PrintStream printer) {
        super.save(printer);
        printer.println(width);
        printer.println(height);
    }

    public void load(Scanner scanner) {
        load(scanner,true);
    }

    public void load(Scanner scanner, boolean loadSuper) {
        if(loadSuper) {
            super.load(scanner);
        }
        width = scanner.nextDouble();
        height = scanner.nextDouble();
    }

    public String toString() {
        return "Rectangle2 String...";
    }
}
