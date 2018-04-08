package Painting;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String args[]) {
        JFrame frame=new JFrame("Paint Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);

        JPanel contentPaint=(JPanel) frame.getContentPane();

        while(true) {
            Graphics2D G = (Graphics2D) contentPaint.getGraphics();
            G.setColor(Color.RED);
            G.fillOval(100,100,200,200);
            G.setColor(Color.GREEN);
            G.setStroke(new BasicStroke(10));
            G.drawOval(100,100,200,200);
        }
    }

}
