package drawing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class PaintPanel extends JPanel {

    ArrayList<Shape> shapes=new ArrayList<>();
    Point previousMouseLocation=null;
    Shape selectedShape=null;

    Thread animationThread;

    boolean animating;

    public PaintPanel(Dimension dimension) {
        this.setSize(dimension);
        initialize();
    }

    void initialize() {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setPreferredSize(new Dimension(2000,2000));

        animating=false;

        animationThread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if(animating==false) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }

                    Iterator<Shape> iterator=shapes.iterator();
                    while(iterator.hasNext()) {
                        Shape cur=iterator.next();
                        cur.step();
                    }
                    try {
                        repaint();
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point curLocation=new Point(e.getX(),e.getY());
                Point dif=curLocation.subtract(previousMouseLocation);
                previousMouseLocation=curLocation;
                if(selectedShape!=null) {
                    selectedShape.setLocation(selectedShape.getLocation().add(dif));
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //Point curLocation=new Point(e.getX(),e.getY());
                //Point dif=curLocation.subtract(previousMouseLocation);
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                previousMouseLocation=new Point(e.getX(),e.getY());
                Iterator<Shape> iterator=shapes.iterator();
                while(iterator.hasNext()) {
                    Shape cur=iterator.next();
                    if(cur.isIn(previousMouseLocation)) {
                        selectedShape=cur;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                previousMouseLocation=null;
                selectedShape=null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        animationThread.start();
    }

    @Override
    protected void paintComponent(Graphics G) {
        super.paintComponent(G);
        Iterator<Shape> iterator=shapes.iterator();
        while(iterator.hasNext()) {
            Shape cur=iterator.next();
            cur.render((Graphics2D)G);
        }
    }

    public boolean getAnimating() {
        return animating;
    }

    public void setAnimating(boolean animating) {
        this.animating = animating;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void clearShapes() {
        shapes.clear();
    }
}
