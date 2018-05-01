package Streams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPTest {
    public static void main(String args[]) throws IOException {
        DatagramSocket socketUDPSender=new DatagramSocket(2020);
        DatagramSocket socketUDPReciever=new DatagramSocket(9090);

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        PrintStream printer=new PrintStream(outputStream);
        printer.println("Salaam!!");
        byte[] sendMessage=outputStream.toByteArray();
        InetSocketAddress destination=new InetSocketAddress("localhost",9090);
        DatagramPacket sendPacket=new DatagramPacket(sendMessage,sendMessage.length,destination);
        socketUDPSender.send(sendPacket);

        byte[] recieveMessage=new byte[10000];
        DatagramPacket recievePacket=new DatagramPacket(recieveMessage,recieveMessage.length);
        socketUDPReciever.receive(recievePacket);
        ByteArrayInputStream inputStream=new ByteArrayInputStream(recievePacket.getData());
        Scanner scanner=new Scanner(inputStream);
        System.out.println(scanner.next());

    }
}
