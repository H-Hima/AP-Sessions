package drawing;

import java.awt.*;

public class Circle extends Shape implements Drawable {

    protected double radius=100;

    Circle(Point center, double radius) {
        this(center,radius,Color.GREEN,Color.PINK);
    }

    Circle(Point center, double radius, Color solidColor, Color borderColor) {
        super(new Point[]{center}, solidColor, borderColor);
        this.radius=radius;
    }

    @Override
    public void render(Graphics2D G) {
        G.setColor(solidColor);
        G.fillOval((int)vertices[0].x,(int)vertices[0].y,(int)radius*2,(int)radius*2);

        G.setStroke(new BasicStroke(thickness));
        G.setColor(borderColor);
        G.drawOval((int)vertices[0].x,(int)vertices[0].y,(int)radius*2,(int)radius*2);
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
}