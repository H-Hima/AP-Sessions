package drawing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PaintPanel extends JPanel {

    ArrayList<Shape2> shape2s =new ArrayList<>();
    Point2 previousMouseLocation=null;
    Shape2 selectedShape2 =null;

    Thread animationThread;

    boolean animating;

    private void test() {

    }

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

                    Iterator<Shape2> iterator= shape2s.iterator();
                    while(iterator.hasNext()) {
                        Shape2 cur=iterator.next();
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
                Point2 curLocation=new Point2(e.getX(),e.getY());
                Point2 dif=curLocation.subtract(previousMouseLocation);
                previousMouseLocation=curLocation;
                if(selectedShape2 !=null) {
                    selectedShape2.setLocation(selectedShape2.getLocation().add(dif));
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //Point2 curLocation=new Point2(e.getX(),e.getY());
                //Point2 dif=curLocation.subtract(previousMouseLocation);
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                previousMouseLocation=new Point2(e.getX(),e.getY());
                Iterator<Shape2> iterator= shape2s.iterator();
                while(iterator.hasNext()) {
                    Shape2 cur=iterator.next();
                    if(cur.isIn(previousMouseLocation)) {
                        selectedShape2 =cur;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                previousMouseLocation=null;
                selectedShape2 =null;
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
        synchronized (this) {
            super.paintComponent(G);
            Iterator<Shape2> iterator = shape2s.iterator();
            while (iterator.hasNext()) {
                Shape2 cur = iterator.next();
                cur.render((Graphics2D) G);
            }
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

    public void addShape(Shape2 shape2) {
        synchronized (this) {
            shape2s.add(shape2);
        }
    }

    public void clearShapes() {
        synchronized (this) {
            shape2s.clear();
        }
    }

    public void save(PrintStream printer) {
        synchronized (this) {
            printer.println(shape2s.size());
            for (Shape2 shape2 : shape2s) {
                printer.println(shape2.getClass().getCanonicalName());
                shape2.save(printer);
            }
            printer.println(animating);
        }
    }

    public void load(Scanner scanner) {
        synchronized (this) {
            setAnimating(false);
            shape2s = new ArrayList<>();
            int size = scanner.nextInt();
            for (int i=0;i<size;i++) {
                scanner.nextLine();
                String type = scanner.nextLine();
                Shape2 shape2 = null;
                switch (type) {
                    case "drawing.Circle":
                        shape2 =new Circle(scanner);
                        break;
                    case "drawing.Rectangle2":
                        shape2 =new Rectangle2(scanner);
                        break;
                    case "drawing.Triangle":
                        //shape2 = new Rectangle2(new Point2(0, 0), 10, 10);
                        //shape2.load(scanner);
                        break;
                    case "drawing.Line":
                        shape2 = new Line(scanner);
                        break;
                }
                shape2s.add(shape2);
            }
            animating = scanner.nextBoolean();
            repaint();
        }
    }

    public void save(ObjectOutputStream printer) {
        synchronized (this) {
            try {
                printer.reset();
                printer.writeObject(shape2s);
                System.out.println(shape2s.size());
                printer.writeBoolean(animating);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load(ObjectInputStream scanner) {
        synchronized (this) {
            setAnimating(false);
            try {
                shape2s =(ArrayList<Shape2>) scanner.readObject();
                System.out.println(shape2s.size());
                animating = scanner.readBoolean();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}
