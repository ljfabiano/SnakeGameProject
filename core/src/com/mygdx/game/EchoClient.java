package com.mygdx.game;

/**
 * Created by jfabiano on 12/19/2017.
 */
import java.io.*;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class EchoClient {
    SnakeGame myGame;
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public EchoClient() {
        System.out.println("Client constructor");
        try {
            socket = new DatagramSocket();

            address = InetAddress.getByName("localhost");
        }
        catch(IOException echoClientConstructor) {
            echoClientConstructor.printStackTrace();
        }
    }

    public EchoClient(SnakeGame myGame)
    {
        this.myGame = myGame;
    }

    public String sendEcho(String msg) {
        System.out.println("Sending echo from client to server");
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        try {
            socket.send(packet);
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
        }
        catch(IOException sendEchoMethod){
            sendEchoMethod.printStackTrace();
        }
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void sendData(String msg) {
        //attempt to create method to send data from client to server. The data type of the message to be sent may need to change...
        //Need to be sending relevant game data, so crafting the proper packet is important here.
        System.out.println("Sending data from client to server");
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        try {
            socket.send(packet);
            //packet = new DatagramPacket(buf, buf.length);
            //socket.receive(packet);
        }
        catch(IOException sendDataMethod){
            sendDataMethod.printStackTrace();
        }
        //String received = new String(packet.getData(), 0, packet.getLength());
        //return received;
    }

    public void close() {
        System.out.println("Closing client");
        socket.close();
    }
}
