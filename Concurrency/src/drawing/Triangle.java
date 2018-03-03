package drawing;

import java.awt.*;

public class Triangle extends Shape implements Drawable {

    public Triangle(Point a,Point b, Point c,Color solidColor, Color borderColor) {
        super(new Point[]{a,b,c},solidColor,borderColor);
    }

    public Triangle(Point a,Point b, Point c) {
        super(new Point[]{a,b,c},Color.BLUE,Color.ORANGE);
    }

    public double getArea() {
        return 1;
    }


    @Override
    public void render(Graphics2D G) {
        G.setColor(solidColor);
        int Xs[]=new int[]{(int)vertices[0].getX(),(int)vertices[1].getX(),(int)vertices[2].getX()};
        int Ys[]=new int[]{(int)vertices[0].getY(),(int)vertices[1].getY(),(int)vertices[2].getY()};
        G.fillPolygon(Xs,Ys,vertices.length);

        G.setStroke(new BasicStroke(thickness));
        G.setColor(borderColor);
        G.drawPolygon(Xs,Ys,vertices.length);
    }
}
