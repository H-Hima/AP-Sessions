import javax.swing.*;
import java.awt.*;

public class SwingMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("First swing program");
        frame.setSize(new Dimension(600,400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = (JPanel)frame.getContentPane();

        JButton firstButton = new JButton("Click me!");
        mainPanel.add(firstButton);

        JButton secondButton = new JButton("Click me2!");
        mainPanel.add(secondButton);

        frame.setVisible(true);

    }
}
