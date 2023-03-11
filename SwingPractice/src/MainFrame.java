import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel contentPane;
    JButton btn1,btn2;
    JRadioButton radio1,radio2;
    JButton area;
    JCheckBox check;
    JPanel centerPanel;
    JLabel label;

    MainFrame() {
        initGrid();
    }

//    void initBordered() {
//        setLocation(new Point(200,100));
//        this.setSize(new Dimension(700,600));
//        contentPane=(JPanel) this.getContentPane();
//        contentPane.setLayout(new BorderLayout());
//
//        btn1=new JButton("First Button");
//        btn1.setSize(90,65);
//
//        btn2=new JButton("Second Button");
//        btn2.setMaximumSize(new Dimension(1000,60));
//        btn2.setToolTipText("Second Button Behaviour");
//        //btn2.setSize(90,65);
//
//
//        radio1=new JRadioButton("Radio number one");
//        radio1.setSize(new Dimension(100,60));
//
//        radio2=new JRadioButton("Second radio button");
//        radio2.setMaximumSize(new Dimension(1000,60));
//
//        area=new JButton();
//        area.setSize(new Dimension(300,150));
//
//        check=new JCheckBox("CheckBox");
//
//        label=new JLabel("This is a label");
//
//        centerPanel=new JPanel();
//        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.LINE_AXIS));
//
//        contentPane.add(btn1, BorderLayout.EAST);
//        contentPane.add(check,BorderLayout.NORTH);
//        contentPane.add(radio1, BorderLayout.SOUTH);
//        contentPane.add(radio2, BorderLayout.WEST);
//        contentPane.add(centerPanel);
//
//        centerPanel.add(label);
//        centerPanel.add(Box.createGlue());
//        centerPanel.add(btn2);
//        centerPanel.add(Box.createRigidArea(new Dimension(50,30)));
//        centerPanel.add(area);
//
//    }

    void initGrid() {
        setLocation(new Point(200,100));
        this.setSize(new Dimension(700,600));
        contentPane=(JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        btn1=new JButton("First Button");
        btn1.setSize(90,65);

        btn2=new JButton("Second Button");
//        btn2.setMaximumSize(new Dimension(1000,60));
        btn2.setPreferredSize(new Dimension(100,60));

//        //btn2.setSize(90,65);
//
//
        radio1=new JRadioButton("Radio number one");
        radio1.setSize(new Dimension(100,60));
//
        radio2=new JRadioButton("Second radio button");
        radio2.setMaximumSize(new Dimension(1000,160));

        area=new JButton("Center Button");
        area.setSize(new Dimension(300,150));
        area.setMaximumSize(new Dimension(400,500));

        check=new JCheckBox("CheckBox");

        label=new JLabel("This is a label");

        centerPanel=new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));

        contentPane.add(btn1, BorderLayout.EAST);
        contentPane.add(btn2, BorderLayout.WEST);
        contentPane.add(check,BorderLayout.SOUTH);
        contentPane.add(centerPanel);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radio1);
        bg.add(radio2);

        centerPanel.add(radio1);
        centerPanel.add(Box.createGlue());
        centerPanel.add(area);
        centerPanel.add(Box.createRigidArea(new Dimension(50,30)));
        centerPanel.add(radio2);

        contentPane.add(label, BorderLayout.NORTH);


//        contentPane.add(btn2);

//        contentPane.add(area);

    }
}
