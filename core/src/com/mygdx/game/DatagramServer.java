package com.mygdx.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by jfabiano on 1/8/2018.
 */
public class DatagramServer extends Thread {
    SnakeGame myGame;
    //4446 is the default port of listening for the client's server. 4445 is the default port of listening for the server.
    int port;
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];
    DatagramClient myClient;
    boolean receivedTestConnection;
    private MulticastSocket multiSocket;
    private InetAddress group;
    boolean receivedP1IPAddress = false;

    //Extras to prevent the errors in the sample method from the connectionHandler class
    //Socket connection;
    //DatagramSocket testClientSocket;
    //SnakeGame myGame;
    //Client myClient;
    //String ipAddress;
    //PrintWriter outputToClient;

    public DatagramServer() {
        System.out.println("DatagramServer constructor");
        try {
            socket = new DatagramSocket(port);
        }catch(IOException echoServerConstructor){
            echoServerConstructor.printStackTrace();
        }
    }

    public DatagramServer(SnakeGame myGame)
    {
        this.myGame = myGame;
        if(myGame.twoPlayerServer == true)
        {
            port = 4445;
            System.out.println("Creating Server's Client");
            createServersClient();
        }else if(myGame.twoPlayerClient == true){
            port = 4446;
        }

        try {
            group = InetAddress.getByName("228.5.6.7");
        }
        catch(UnknownHostException datagramServerConstructor){
            datagramServerConstructor.printStackTrace();
        }

        try {
            multiSocket = new MulticastSocket(port);
            System.out.println("multiSocket IP address: " + multiSocket.toString());

            multiSocket.joinGroup(group);
        }
        catch(IOException datagramServerConstructor) {
            datagramServerConstructor.printStackTrace();
        }

        //if(myGame.twoPlayerClient == true)
        //{
        //   port = 4446;
            //Not sure if the server's client is needed here yet
            //System.out.println("Creating Server's Client");
            //createServersClient();
        //}
        System.out.println("DatagramServer constructor");
        try {
            socket = new DatagramSocket(port);
        }catch(IOException datagramServerConstructor){
            datagramServerConstructor.printStackTrace();
        }
    }

    public void run() {
        System.out.println("DatagramServer run method");
        running = true;
        DatagramPacket serverIPPacket = new DatagramPacket(buf, buf.length);

        if(myGame.twoPlayerClient == true && receivedP1IPAddress == false)//or maybe a new variable for the ip address
        {

            try {
                multiSocket.receive(serverIPPacket);

                String serverAddressString = (serverIPPacket.getAddress()).getHostAddress();
                System.out.println("the string value of inet address datagram sent by p1 on multicast = " + serverAddressString);

                InetAddress serverAddress = serverIPPacket.getAddress();

                myGame.myDatagramClient.setServerAddress(serverAddress);

                receivedP1IPAddress = true;
            } catch (IOException datagramServerRunSocketReceive) {
                datagramServerRunSocketReceive.printStackTrace();
                receivedP1IPAddress = false;
            }
            //String received = new String(packet.getData(), 0, packet.getLength());
                try {
                    multiSocket.leaveGroup(group);
                } catch (IOException datagramClientClose) {
                   datagramClientClose.printStackTrace();
                }
                multiSocket.close();
        }

        //if(myGame.twoPlayerServer == true)
        //{
        //    System.out.println("Creating Server's Client");
        //    createServersClient();
        //}

        while (running) {
            System.out.println("while loop in run method running again");
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

                try {
                    socket.receive(packet);
                } catch (IOException datagramServerRunSocketReceive) {
                    datagramServerRunSocketReceive.printStackTrace();
                }
                //InetAddress address = packet.getAddress();
                //int port = packet.getPort();
                //Adding lines to test changing the values sent through to the server from the client

                //packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("The value of datagram packet as received by the server: " + received);

                //Adding lines to test the values sent through to the server from the client
                if (received.equals("W")) {
                    myGame.xDirectionalMovementP2 = 0;
                    myGame.yDirectionalMovementP2 = 1;
                } else if (received.equals("S")) {
                    myGame.xDirectionalMovementP2 = 0;
                    myGame.yDirectionalMovementP2 = -1;
                } else if (received.equals("A")) {
                    myGame.xDirectionalMovementP2 = -1;
                    myGame.yDirectionalMovementP2 = 0;
                } else if (received.equals("D")) {
                    myGame.xDirectionalMovementP2 = 1;
                    myGame.yDirectionalMovementP2 = 0;
                }
                if (received.equals("UP")) {
                    myGame.xDirectionalMovementP2 = 0;
                    myGame.yDirectionalMovementP2 = 1;
                } else if (received.equals("DOWN")) {
                    myGame.xDirectionalMovementP2 = 0;
                    myGame.yDirectionalMovementP2 = -1;
                } else if (received.equals("LEFT")) {
                    myGame.xDirectionalMovementP2 = -1;
                    myGame.yDirectionalMovementP2 = 0;
                } else if (received.equals("RIGHT")) {
                    myGame.xDirectionalMovementP2 = 1;
                    myGame.yDirectionalMovementP2 = 0;
                }

                if (received.startsWith("x")) {
                    received = received.substring(1);
                    System.out.println("substring of food x location = " + received);

                    myGame.myFood.setX(Integer.parseInt(received));
                    System.out.println("myGame.myFood.getx = " + myGame.myFood.getX());
                } else if (received.startsWith("y")) {
                    received = received.substring(1);
                    System.out.println("substring of food y location = " + received);
                    myGame.myFood.setY(Integer.parseInt(received));
                    System.out.println("myGame.myFood.gety = " + myGame.myFood.getY());

                    myGame.playGrid.coordinateGrid[myGame.myFood.getX()][myGame.myFood.getY()] = myGame.myFood;
                }
                //may need received.startsWith rather than received.equals.
            /*if(received.equals("Testing Connection") || received.equals("Testing Connection from player 2 client to player 1 server."))
            {
                System.out.println("if received.equals Testing connection if statement in server run method");
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                //Adding lines to test changing the values sent through to the server from the client
                packet = new DatagramPacket(buf, buf.length, address, port);

                try {
                    socket.send(packet);
                    System.out.println("packet response sent from server to client");
                }catch(IOException datagramServerRunSocketSend){
                    datagramServerRunSocketSend.printStackTrace();
                }

            }*/

                if (received.startsWith("Testing Connection P1")) {
                    System.out.println("P2 server receiving a message from P1 client to establish connection.");
                    //InetAddress address = packet.getAddress();
                    //int port = packet.getPort();
                    //Adding lines to test changing the values sent through to the server from the client
                    //packet = new DatagramPacket(buf, buf.length, address, port);
                    //may need to create a copy of the instance of the client when instantiating this server class for player 2, or just use the
                    //myGame.myDatagramClient as the game is already passed as a variable when instantiated.
                    //myGame.
                    //myClient.sendData(received);
                    //myGame.myDatagramClient.sendData(received);
                    //myGame.myDatagramClient.setTestConnection(true);
                    myGame.myDatagramClient.initializeConnection("P1 and P2 connections successful");
                    receivedTestConnection = true;
                }

                if (received.startsWith("Testing Connection P2")) {
                    System.out.println("P1 server receiving a message from P2 client to establish connection.");
                    //InetAddress address = packet.getAddress();
                    //int port = packet.getPort();
                    //Adding lines to test changing the values sent through to the server from the client
                    //myGame.
                    //myClient.sendData(received);
                    //myClient.setTestConnection(true);
                    //myClient.initializeConnection(received);
                    myClient.initializeConnection("Testing Connection P1");
                    receivedTestConnection = true;
                }
                if (received.startsWith("P1 and P2 connections successful")) {
                    System.out.println("P1 and P2 connections successful starting from this end.");
                    //Added for control for the server snake game rendering/resetting the game.
                    myGame.resetGame();
                }

                if (received.startsWith("end")) {//Changed from received.equals to received.startswith
                    running = false;
                    //continue;
                }
                //try {
                //    socket.send(packet);
                //}catch(IOException echoServerRunSocketSend){
                //    echoServerRunSocketSend.printStackTrace();
                //}
        }
        System.out.println("Run method datagram server closing, and closing the socket.");
        socket.close();
    }

    public void createServersClient()
    {
        myClient = new DatagramClient(myGame);
        //myClient.runClient();
        System.out.println("Server's client created, and the run client command called.");
    }

}
