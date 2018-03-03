package drawing;

import java.awt.*;

public class Rectangle extends Shape {

    Rectangle(Point upperLeft , Point lowerRight) {
        this(upperLeft,lowerRight,Color.RED,Color.BLUE);
    }

    Rectangle(Point upperLeft , Point lowerRight, Color solidColor, Color borderColor) {
        super(new Point[]{upperLeft,lowerRight}, solidColor, borderColor);
        rectify();
    }

    public void rectify() {
        if(vertices[0].x>vertices[1].x) {
            double x=vertices[0].x;
            vertices[0].x=vertices[1].x;
            vertices[1].x=x;
        }
        if(vertices[0].y>vertices[1].y) {
            double y=vertices[0].y;
            vertices[0].y=vertices[1].y;
            vertices[1].y=y;
        }
    }

    @Override
    public double getArea() {
        Point dif=vertices[1].subtract(vertices[0]);
        return Math.abs(dif.x*dif.y);
    }

    @Override
    public void render(Graphics2D G) {
        Point dif=vertices[1].subtract(vertices[0]);

        G.setColor(solidColor);
        G.fillRect((int)vertices[0].x,(int)vertices[0].y,(int)dif.x,(int)dif.y);

        G.setStroke(new BasicStroke(thickness));
        G.setColor(borderColor);
        G.drawRect((int)vertices[0].x,(int)vertices[0].y,(int)dif.x,(int)dif.y);
    }
}
