package drawing;

import javafx.scene.chart.Axis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainFrame extends JFrame {

    JPanel contentPane;
    JPanel toolPanel;
    PaintPanel paintPanel;
    JButton refreshButton;
    JButton addButton;

    MainFrame() {
        initialize();
    }

    void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane=(JPanel) getContentPane();
        setSize(new Dimension(500,500));
        setTitle("Paint Application");

        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
        paintPanel=new PaintPanel(new Dimension(0,contentPane.getSize().height-50));
        toolPanel=new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.LINE_AXIS));

        refreshButton=new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPanel.repaint();
            }
        });
        addButton=new JButton("Add Shapes");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPanel.clearShapes();
                paintPanel.addShape(new Rectangle(new Point(100,100), new Point(300,400)));
            }
        });

        toolPanel.add(addButton);
        toolPanel.add(refreshButton);

        contentPane.add(paintPanel);
        contentPane.add(Box.createGlue());
        contentPane.add(toolPanel);

        setVisible(true);
    }
}
