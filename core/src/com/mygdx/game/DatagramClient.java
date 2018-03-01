package com.mygdx.game;

import java.io.IOException;
import java.net.*;

/**
 * Created by jfabiano on 1/8/2018.
 */
public class DatagramClient {
    SnakeGame myGame;
    private DatagramSocket socket;
    private MulticastSocket multiSocket;
    private InetAddress address;
    private InetAddress otherAddress;
    private InetAddress group;
    private InetAddress serverAddress;

    //private InetAddress serverAddress;
    //code for the multicast socket to initially send the server players ip address to any client players server that is listening in the ip group
    //multicast ips are: 224.0.0.0 to 239.255.255.255, but dont use 224.0.0.0 as it is reserved
    //private InetAddress group = InetAddress.getByName("203.0.113.0");
    //MulticastSocket socket = new MulticastSocket(4446);
    //InetAddress group = InetAddress.getByName("203.0.113.0");
    //socket.joinGroup(group);
    //socket.leaveGroup(group);

    DatagramServer myServer;
    //4446 is the default port of listening for the client's server. 4445 is the default port of listening for the server.
    int portOfServerSocket;
    private byte[] buf;
    boolean testConnection;

    public DatagramClient() {
        System.out.println("DatagramClient constructor");
        try {
            socket = new DatagramSocket();

            address = InetAddress.getByName("localhost");
            System.out.println("localhost IP address: " + address.toString());

            otherAddress = InetAddress.getLocalHost();
            System.out.println("other IP address: " + otherAddress.toString());


        }
        catch(IOException datagramClientConstructor) {
            datagramClientConstructor.printStackTrace();
        }
        try {
            group = InetAddress.getByName("228.5.6.7");
        }
        catch(UnknownHostException datagramClientConstructor){
            datagramClientConstructor.printStackTrace();
        }
    }

    public DatagramClient(SnakeGame myGame)
    {
        System.out.println("DatagramClient constructor");
        this.myGame = myGame;
        if(myGame.twoPlayerClient == true) {
            portOfServerSocket = 4445;
            //not sure if we need the clientsServer here yet
            System.out.println("Creating client's server");
            createClientsServer();

            //try {
            //    multiSocket = new MulticastSocket(portOfServerSocket);
            //    System.out.println("multiSocket IP address: " + multiSocket.toString());

            //    multiSocket.joinGroup(group);
            //}
            //catch(IOException datagramClientConstructor) {
            //    datagramClientConstructor.printStackTrace();
            //}

        }else if(myGame.twoPlayerServer == true) {
            portOfServerSocket = 4446;
        }

        try {
            socket = new DatagramSocket();

            address = InetAddress.getByName("localhost");
            System.out.println("localhost IP address: " + address.toString());

            otherAddress = InetAddress.getLocalHost();
            System.out.println("other IP address: " + otherAddress.toString());

        }
        catch(IOException echoClientConstructor) {
            echoClientConstructor.printStackTrace();
        }
        try {
            group = InetAddress.getByName("228.5.6.7");
        }
        catch(UnknownHostException datagramClientConstructor){
            datagramClientConstructor.printStackTrace();
        }
    }

    public InetAddress getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(InetAddress serverAddress) {
        this.serverAddress = serverAddress;
    }

    public void setTestConnection(boolean testConnectionVal) {
        testConnection = testConnectionVal;
    }

    public void initializeConnection(String msg) {
        sendData(msg);
        testConnection = true;
    }

    public void connectionInfoMulticast() {
        //sendData(msg);
        //testConnection = true;
        //buf = msg.getBytes();
        buf = otherAddress.toString().getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, portOfServerSocket);
        try {
            socket.send(packet);
            //packet = new DatagramPacket(buf, buf.length);
            //socket.receive(packet);
        }
        catch(IOException sendDataMethod){
            sendDataMethod.printStackTrace();
        }
    }

    public String sendEcho(String msg) {
        System.out.println("Sending echo from client to server");
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, portOfServerSocket);
        try {
            System.out.println("client: echo sent from client to server");
            socket.send(packet);
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            System.out.println("client: echo received from server");
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
        DatagramPacket packet;
        if(myGame.twoPlayerClient == true) {
            packet = new DatagramPacket(buf, buf.length, serverAddress, portOfServerSocket);
        }else{
            //packet = new DatagramPacket(buf, buf.length, address, portOfServerSocket);
            packet = new DatagramPacket(buf, buf.length, otherAddress, portOfServerSocket);
        }
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
        myServer.start();
        //myServer.setConnection();
        System.out.println("Client's server set connection called, and the server created");
    }

    public void close() {
        System.out.println("Sending the \"end\" text to the server to shut it off.");
        sendData("end");
        System.out.println("Closing client socket");
        socket.close();
        //if(myGame.twoPlayerClient) {

        //    try {
        //        multiSocket.leaveGroup(group);
        //    } catch (IOException datagramClientClose) {
        //       datagramClientClose.printStackTrace();
        //    }
        //    multiSocket.close();
        //}
    }
}
