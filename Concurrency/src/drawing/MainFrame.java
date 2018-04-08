package drawing;

import javafx.scene.chart.Axis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

public class MainFrame extends JFrame {

    JPanel contentPane;
    JPanel toolPanel;
    PaintPanel paintPanel;
    JButton refreshButton;
    JButton addButton;
    JButton saveButton;
    JButton animationButton;

    MainFrame() {
        initialize();
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
                System.out.println(chooser.getSelectedFile().toString());
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
                Rectangle rect = new Rectangle(new Point(100, 100), 300, 400);
                Circle circle = new Circle(new Point(300, 300), 200);

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

                paintPanel.addShape(rect);
                paintPanel.addShape(circle);
                paintPanel.repaint();
            }
        });

        toolPanel.add(saveButton);
        toolPanel.add(addButton);
        toolPanel.add(refreshButton);
        toolPanel.add(animationButton);

        contentPane.add(paintPanel);
        contentPane.add(Box.createGlue());
        contentPane.add(toolPanel);

        setVisible(true);
    }
}
