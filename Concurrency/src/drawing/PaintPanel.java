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
        printer.println(shapes.size());
        for(Shape shape:shapes) {
            if(shape instanceof  Circle) {
                printer.println("Circle");
            }
            else if(shape instanceof  Rectangle) {
                printer.println("Rectangle");
            }
            //...
            shape.save(printer);
        }
        printer.println(animating);
    }

    public void load(Scanner scanner) {
        shapes=new ArrayList<>();
        int size=scanner.nextInt();
        while(size>0) {
            size--;
            String type=scanner.nextLine();
            Shape c=null;
            switch (type) {
                case "Circle":
                    c=new Circle(new Point(0,0),10);
                    c.load(scanner);
                    break;
                case "Rectangle":
                    c=new Rectangle(new Point(0,0),10,10);
                    c.load(scanner);
                    break;
            }
            shapes.add(c);
        }
        animating=scanner.nextBoolean();
    }


}
