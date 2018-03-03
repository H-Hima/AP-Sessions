package drawing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PaintPanel extends JPanel {
    ArrayList<Shape> shapes=new ArrayList<>();

    public PaintPanel(Dimension dimension) {
        this.setSize(dimension);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setPreferredSize(new Dimension(2000,2000));
    }

    @Override
    protected void paintComponent(Graphics G) {
        super.paintComponent(G);
        Iterator<Shape> it = shapes.iterator();
        while(it.hasNext()) {
            Shape cur=it.next();
            cur.render((Graphics2D)G);
        }
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void clearShapes() {
        shapes.clear();
    }
}
