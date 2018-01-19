package com.mygdx.game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by jfabiano on 1/8/2018.
 */
public class DatagramClient {
    SnakeGame myGame;
    private DatagramSocket socket;
    private InetAddress address;
    DatagramServer myServer;
    int port = 4445;
    private byte[] buf;

    public DatagramClient() {
        System.out.println("DatagramClient constructor");
        try {
            socket = new DatagramSocket();

            address = InetAddress.getByName("localhost");
        }
        catch(IOException echoClientConstructor) {
            echoClientConstructor.printStackTrace();
        }
    }

    public DatagramClient(SnakeGame myGame)
    {
        System.out.println("DatagramClient constructor");
        this.myGame = myGame;
        if(myGame.twoPlayerServer == true) {
            port = 4446;
            //not sure if we need the clientsServer here yet
            System.out.println("Creating client's server");
            createClientsServer();
        }
        try {
            socket = new DatagramSocket();

            address = InetAddress.getByName("localhost");
        }
        catch(IOException echoClientConstructor) {
            echoClientConstructor.printStackTrace();
        }

    }

    public String sendEcho(String msg) {
        System.out.println("Sending echo from client to server");
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
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
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
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

    public void createClientsServer()
    {
        myServer = new DatagramServer(myGame);
        //myServer.setConnection();
        System.out.println("Client's server set connection called, and the server created");
    }

    public void close() {
        System.out.println("Sending the \"end\" text to the server to shut it off.");
        sendData("end");
        System.out.println("Closing client");
        socket.close();
    }
}
