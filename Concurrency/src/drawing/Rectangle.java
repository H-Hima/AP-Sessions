package drawing;

import java.awt.*;

public class Rectangle extends Shape implements Animatable {

    protected double width;
    protected double height;

    Rectangle(Point upperLeft , double width, double height) {
        this(upperLeft,width,height,Color.RED,Color.BLUE);
    }

    Rectangle(Point upperLeft , double width,double height, Color solidColor, Color borderColor) {
        super(upperLeft, solidColor, borderColor);
        this.width=width;
        this.height=height;
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


    public boolean isIn(Point p) {
        Point dif=p.subtract(location);
        if(dif.getX()>0&&dif.getY()>0&&dif.getX()<width&&dif.getY()<height)
            return true;
        return false;
    }
}
