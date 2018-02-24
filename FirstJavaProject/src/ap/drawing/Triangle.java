package ap.drawing;

import java.awt.*;

public class Triangle extends Shape implements Drawable {

    public Triangle() {
        shapeType=Shapes.Triangle;
        vertices=new Point[]{new Point(200,200),new Point(500,300),new Point(600,700)};
    }

    double getArea() {
        return 1;
    }


    @Override
    public void render(Graphics G) {
        G.setColor(solidColor);
        int Xs[]=new int[]{(int)vertices[0].getX(),(int)vertices[1].getX(),(int)vertices[2].getX()};
        int Ys[]=new int[]{(int)vertices[0].getY(),(int)vertices[1].getY(),(int)vertices[2].getY()};
        G.fillPolygon(Xs,Ys,vertices.length);
    }
}
