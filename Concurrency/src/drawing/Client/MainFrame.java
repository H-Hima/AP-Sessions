package drawing.Client;

import drawing.*;
import drawing.Point;
import drawing.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import drawing.*;
import drawing.Point;
import drawing.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.Scanner;

public class MainFrame extends JFrame {

    JPanel contentPane;
    JPanel toolPanel;
    PaintPanel paintPanel;
    JButton saveButton;
    JButton loadButton;
    JTextField serverAddressField;
    JButton connectButton;

    String serverIP="127.0.0.1";
    int serverPort=9090;

    ClientThread transportThread;

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

        serverAddressField=new JFormattedTextField("127.0.0.1:9090");
        serverAddressField.setSize(100,0);

        connectButton=new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(transportThread!=null && transportThread.isAlive()) {
                    transportThread.interrupt();
                }
                String[] address=serverAddressField.getText().split(":");
                try {
                    serverIP = address[0];
                    serverPort = Integer.parseInt(address[1]);
                    Socket clientSocket=new Socket(serverIP,serverPort);
                    transportThread = new ClientThread(clientSocket,MainFrame.this);
                    transportThread.start();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        toolPanel.add(saveButton);
        toolPanel.add(loadButton);
        toolPanel.add(serverAddressField);
        toolPanel.add(connectButton);

        contentPane.add(paintPanel);
        contentPane.add(Box.createGlue());
        contentPane.add(toolPanel);

        setVisible(true);

    }
}
