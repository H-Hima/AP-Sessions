package drawing.Server;

import drawing.*;
import drawing.Point;
import drawing.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.Scanner;

public class MainFrame extends JFrame {

    JPanel contentPane;
    JPanel toolPanel;
    PaintPanel paintPanel;
    JButton refreshButton;
    JButton addButton;
    JButton saveButton;
    JButton loadButton;
    JButton animationButton;

    int serverPort=9090;
    ListenThread listenThread;

    MainFrame() {
        initialize();
    }

    public void save(ObjectOutputStream objectOutputStream) {
        synchronized (this) {
            paintPanel.save(objectOutputStream);
        }
    }

    public void load(ObjectInputStream objectInputStream) {
        synchronized (this) {
            paintPanel.load(objectInputStream);
        }
    }

    public void save(PrintStream printer) {
        synchronized (this) {
            paintPanel.save(printer);
        }
    }

    public void load(Scanner scanner) {
        synchronized (this) {
            paintPanel.load(scanner);
        }
    }

    void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane=(JPanel) getContentPane();
        setSize(new Dimension(800,800));
        setTitle("Paint Application");

        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
        paintPanel=new PaintPanel(new Dimension(0,contentPane.getSize().height-50));
        toolPanel=new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.LINE_AXIS));

        animationButton=new JButton("Animate");
        animationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPanel.setAnimating(!paintPanel.getAnimating());
            }
        });

        saveButton=new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser=new JFileChooser();
                chooser.showOpenDialog(MainFrame.this);
                try {
                    PrintStream printer=new PrintStream(chooser.getSelectedFile());
                    save(printer);
                    printer.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        loadButton=new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser=new JFileChooser();
                chooser.showOpenDialog(MainFrame.this);
                try {
                    Scanner scanner=new Scanner(chooser.getSelectedFile());
                    load(scanner);
                    scanner.close();
                    revalidate();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        refreshButton=new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPanel.repaint();
            }
        });
        addButton=new JButton("Add Shape");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPanel.clearShapes();
                drawing.Rectangle rect = new Rectangle(new Point(100, 100), 300, 400);
                Circle circle = new Circle(new Point(300, 300), 200);

                /*
                circle.addAnimation(new Animation(10) {

                    boolean increasingRadius = true;

                    @Override
                    public void step() {
                        if (increasingRadius && circle.getRadius() > 100) {
                            increasingRadius = false;
                        }
                        if (increasingRadius == false && circle.getRadius() < 10) {
                            increasingRadius = true;
                        }

                        if (increasingRadius) {
                            circle.setRadius(circle.getRadius() + 1);
                        } else {
                            circle.setRadius(circle.getRadius() - 1);
                        }
                    }
                });

                circle.addAnimation(new Animation(100) {

                    boolean increasingBorder = true;

                    @Override
                    public void step() {
                        if (increasingBorder && circle.getThickness() > 30) {
                            increasingBorder = false;
                        }
                        else if (increasingBorder == false && circle.getThickness() < 3) {
                            increasingBorder = true;
                        }

                        if (increasingBorder)
                            circle.setThickness(circle.getThickness() + 1);
                        else
                            circle.setThickness(circle.getThickness() - 1);
                    }
                });

                rect.addAnimation(new Animation(5) {
                    boolean increasingBorder = true;

                    @Override
                    public void step() {

                        if (increasingBorder && rect.getThickness() > 30) {
                            increasingBorder = false;
                        }
                        else if (increasingBorder == false && rect.getThickness() < 3) {
                            increasingBorder = true;
                        }

                        if (increasingBorder)
                            rect.setThickness(rect.getThickness() + 1);
                        else
                            rect.setThickness(rect.getThickness() - 1);
                    }
                });
*/
                paintPanel.addShape(rect);
                paintPanel.addShape(circle);
                paintPanel.repaint();
            }
        });

        toolPanel.add(saveButton);
        toolPanel.add(loadButton);
        toolPanel.add(addButton);
        toolPanel.add(refreshButton);
        toolPanel.add(animationButton);

        contentPane.add(paintPanel);
        contentPane.add(Box.createGlue());
        contentPane.add(toolPanel);

        setVisible(true);

        if(listenThread != null && listenThread.isAlive()) {
            listenThread.interrupt();
        }
        listenThread=new ListenThread(serverPort,this);
        listenThread.start();
    }
}
