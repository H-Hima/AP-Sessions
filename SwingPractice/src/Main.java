import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Main {
    static Random random=new Random();
    public static void main(String args[]) {
        JFrame frame=new JFrame("My Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700,700));

        JPanel content_pane=(JPanel) frame.getContentPane();
        content_pane.setLayout(new BorderLayout());

        JLabel my_label=new JLabel("Hi");
        content_pane.add(my_label,BorderLayout.SOUTH);

        JPanel main_panel=new JPanel();
        main_panel.setPreferredSize(new Dimension(0,content_pane.getSize().height-100));
        main_panel.setBackground(Color.YELLOW);
        main_panel.setLayout(null);

        content_pane.add(main_panel,BorderLayout.CENTER);

        JButton button=new JButton("Click me!");
        button.setLocation(new Point(100,100));
        button.setSize(new Dimension(100,60));
        main_panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_panel.setBackground(Color.red);
            }
        });

        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setLocation(new Point(random.nextInt(main_panel.getSize().width-40),random.nextInt(main_panel.getSize().height-40)));
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.setVisible(true);

        XOFrame myFrame=new XOFrame();
        myFrame.setVisible(true);

        Graphics g=myFrame.getGraphics();
        while(true) {
            g.drawLine(10, 10, 500, 500);
        }
    }
}