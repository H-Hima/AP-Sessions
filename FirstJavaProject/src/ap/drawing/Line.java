package ap.drawing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Line extends Shape implements Drawable {
    public Line() {
        shapeType=Shapes.Line;
        vertices=new ap.drawing.Point[2];
        vertices[0]=new Point(100,100);
        vertices[1]=new Point(400,400);
        this.solidColor=Color.BLACK;
        this.borderColor=Color.YELLOW;
    }

    public Line(Point start,Point end,Color solidColor, Color borderColor) {
        shapeType=Shapes.Line;
        vertices=new ap.drawing.Point[2];
        vertices[0]=start;
        vertices[1]=end;
        this.solidColor=solidColor;
        this.borderColor=borderColor;
    }

    double getArea() {
        return 0;
    }

    @Override
    public void render(Graphics G) {
        G.setColor(this.solidColor);
        G.drawLine((int)vertices[0].getX(),(int)vertices[0].getY(),(int)vertices[1].getX(),(int)vertices[1].getY());
    }
}
