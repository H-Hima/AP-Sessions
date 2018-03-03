package drawing;

import java.awt.*;

public class Line extends Shape implements Drawable {
    public Line(Point start, Point end) {
        super(new Point[]{start,end},Color.RED,Color.BLACK);
    }

    public Line(Point start,Point end,Color solidColor, Color borderColor) {
        super(new Point[]{start,end},solidColor,borderColor);
    }

    public double getArea() {
        return 0;
    }

    @Override
    public void render(Graphics2D G) {
        ((Graphics2D)G).setStroke(new BasicStroke(thickness));
        G.setColor(this.solidColor);
        G.drawLine((int)vertices[0].getX(),(int)vertices[0].getY(),(int)vertices[1].getX(),(int)vertices[1].getY());
    }
}
