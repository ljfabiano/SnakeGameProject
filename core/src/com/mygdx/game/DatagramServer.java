package com.mygdx.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by jfabiano on 1/8/2018.
 */
public class DatagramServer extends Thread {
    SnakeGame myGame;
    int port = 4445;
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];
    DatagramClient myClient;

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
            System.out.println("Creating Server's Client");
            createServersClient();
        }
        if(myGame.twoPlayerClient == true)
        {
            port = 4446;
            //Not sure if the server's client is needed here yet
            //System.out.println("Creating Server's Client");
            //createServersClient();
        }
        System.out.println("DatagramServer constructor");
        try {
            socket = new DatagramSocket(port);
        }catch(IOException echoServerConstructor){
            echoServerConstructor.printStackTrace();
        }
    }

    public void run() {
        System.out.println("DatagramServer run method");
        running = true;

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
            InetAddress address = packet.getAddress();
            //int port = packet.getPort();
            //Adding lines to test changing the values sent through to the server from the client

            //packet = new DatagramPacket(buf, buf.length, address, port);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("The value of datagram packet as received by the server: " + received);

            //Adding lines to test the values sent through to the server from the client
            if(received.equals("W"))
            {
                myGame.xDirectionalMovementP2 = 0;
                myGame.yDirectionalMovementP2 = 1;
            }
            else if(received.equals("S"))
            {
                myGame.xDirectionalMovementP2 = 0;
                myGame.yDirectionalMovementP2 = -1;
            }
            else if(received.equals("A"))
            {
                myGame.xDirectionalMovementP2 = -1;
                myGame.yDirectionalMovementP2 = 0;
            }
            else if(received.equals("D")) {
                myGame.xDirectionalMovementP2 = 1;
                myGame.yDirectionalMovementP2 = 0;
            }
            if(received.equals("UP"))
            {
                myGame.xDirectionalMovementP2 = 0;
                myGame.yDirectionalMovementP2 = 1;
            }
            else if(received.equals("DOWN"))
            {
                myGame.xDirectionalMovementP2 = 0;
                myGame.yDirectionalMovementP2 = -1;
            }
            else if(received.equals("LEFT"))
            {
                myGame.xDirectionalMovementP2 = -1;
                myGame.yDirectionalMovementP2 = 0;
            }
            else if(received.equals("RIGHT")) {
                myGame.xDirectionalMovementP2 = 1;
                myGame.yDirectionalMovementP2 = 0;
            }

            if(received.startsWith("x")) {
                received = received.substring(1);
                System.out.println("substring of food x location = " + received);

                myGame.myFood.setX(Integer.parseInt(received));
                System.out.println("myGame.myFood.getx = " + myGame.myFood.getX());
            }
            else if(received.startsWith("y"))
            {
                received = received.substring(1);
                System.out.println("substring of food y location = " + received);
                myGame.myFood.setY(Integer.parseInt(received));
                System.out.println("myGame.myFood.gety = " + myGame.myFood.getY());

                myGame.playGrid.coordinateGrid[myGame.myFood.getX()][myGame.myFood.getY()] = myGame.myFood;
            }
            if(received.equals("Testing Connection"))
            {

                int port = packet.getPort();
                //Adding lines to test changing the values sent through to the server from the client
                packet = new DatagramPacket(buf, buf.length, address, port);

                try {
                    socket.send(packet);
                }catch(IOException datagramServerRunSocketSend){
                    datagramServerRunSocketSend.printStackTrace();
                }

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
