package ap.drawing;

import java.awt.*;

public abstract class Shape {
    protected Point vertices[];
    protected Color solidColor=Color.BLACK, borderColor=Color.WHITE;

    Shapes shapeType;

    abstract double getArea();

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
}
