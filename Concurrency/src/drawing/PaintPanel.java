package drawing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

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
                    if(getAnimating()==false) {
                        continue;
                    }

                    Iterator<Shape> iterator=shapes.iterator();
                    while(iterator.hasNext()) {
                        Shape cur=iterator.next();
                        cur.step();
                    }
                    try {
                        repaint();
                        //paintComponent(getGraphics());
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
        synchronized (this) {
            return animating;
        }
    }

    public void setAnimating(boolean animating) {
        synchronized (this) {
            this.animating = animating;
        }
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void clearShapes() {
        shapes.clear();
    }

    public void save(PrintStream printer) {
        synchronized (this) {
            printer.println(shapes.size());
            for (Shape shape : shapes) {
                printer.println(shape.getClass().getCanonicalName());
                shape.save(printer);
            }
            printer.println(animating);
        }
    }

    public void load(Scanner scanner) {
        synchronized (this) {
            setAnimating(false);
            shapes = new ArrayList<>();
            int size = scanner.nextInt();
            for (int i=0;i<size;i++) {
                scanner.nextLine();
                String type = scanner.nextLine();
                Shape shape = null;
                switch (type) {
                    case "drawing.Circle":
                        shape=new Circle(scanner);
                        break;
                    case "drawing.Rectangle":
                        shape=new Rectangle(scanner);
                        break;
                    case "drawing.Triangle":
                        //shape = new Rectangle(new Point(0, 0), 10, 10);
                        //shape.load(scanner);
                        break;
                    case "drawing.Line":
                        shape = new Line(scanner);
                        break;
                }
                shapes.add(shape);
            }
            animating = scanner.nextBoolean();
            repaint();
        }
    }
}
