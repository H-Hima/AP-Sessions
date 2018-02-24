package ap.drawing;

public enum Shapes {
    Point(ap.drawing.Point.class.toString()),
    Line(ap.drawing.Line.class.toString()),
    Triangle(ap.drawing.Triangle.class.toString());

//    Point("Point"),
//    Line("Line"),
//    Triangle("Triangle");

    private final String str;
    Shapes(String str) {
        this.str=str;
    }

    public String getStr() {
        return str;
    }
}
