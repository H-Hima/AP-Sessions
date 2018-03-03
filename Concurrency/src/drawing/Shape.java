package drawing;

import java.awt.*;

public abstract class Shape implements Drawable {
    protected Point vertices[];
    protected Color solidColor=Color.WHITE, borderColor=Color.BLACK;
    protected int thickness=3;

    Shape(Point[] vertices) {
        this.vertices=vertices;
    }

    Shape(Point[] vertices,Color solidColor,Color borderColor) {
        this.vertices=vertices;
        this.borderColor=borderColor;
        this.solidColor=solidColor;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    int getNumberOfVertices() {
        return vertices.length;
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

    public abstract double getArea();

}
