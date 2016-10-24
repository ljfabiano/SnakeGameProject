package com.mygdx.game;

/**
 * Created by jfabiano on 10/23/2016.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionHandler implements Runnable{
    Socket connection;
    SnakeGame myGame;
    Client myClient;
    public ConnectionHandler()
    {

    }
    public ConnectionHandler(Socket incomingConnection)
    {
        this.connection = incomingConnection;
    }
    public ConnectionHandler(Socket incomingConnection, SnakeGame myGame)
    {
        this.connection = incomingConnection;
        this.myGame = myGame;
    }
    public void run()
    {
        handleInput();
    }
    public void handleInput() {
        try
        {
            ArrayList<String> aList = new ArrayList<String>();
            String ipAddress;
            String inputLine;
            // start a server on a specific port. This is what needs to happen in a thread
            // display information about who just connected to our server
            System.out.println("Incoming connection from " + connection.getInetAddress().getHostAddress());

            // this is how we read from the client
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            // this is how we write back to the client
            PrintWriter outputToClient = new PrintWriter(this.connection.getOutputStream(), true);

            // read from the input until the client disconnects
            //On the server side, make sure the first message that each client sends is their ipAddress using a message structure
            //like this: ipAddress=ipAddress-of-client
            ipAddress = inputFromClient.readLine();
            System.out.println("Ip Address = " + ipAddress);

                outputToClient.println("I have your ip address.");
//            //create a server client
//            if(myGame.twoPlayerServer == true) {
//                createServersClient();
//            }
            if(myGame.twoPlayerServer == true)
            {
                System.out.println("Creating Server's Client");
                createServersClient();
            }
                while ((inputLine = inputFromClient.readLine()) != null) {
                        //inputLine;
                    if(inputLine.equals("W"))
                    {
                        myGame.xDirectionalMovementP2 = 0;
                        myGame.yDirectionalMovementP2 = 1;
                    }
                    else if(inputLine.equals("S"))
                    {
                        myGame.xDirectionalMovementP2 = 0;
                        myGame.yDirectionalMovementP2 = -1;
                    }
                    else if(inputLine.equals("A"))
                    {
                        myGame.xDirectionalMovementP2 = -1;
                        myGame.yDirectionalMovementP2 = 0;
                    }
                    else if(inputLine.equals("D")) {
                        myGame.xDirectionalMovementP2 = 1;
                        myGame.yDirectionalMovementP2 = 0;
                    }
                    if(inputLine.equals("UP"))
                    {
                        myGame.xDirectionalMovementP2 = 0;
                        myGame.yDirectionalMovementP2 = 1;
                    }
                    else if(inputLine.equals("DOWN"))
                    {
                        myGame.xDirectionalMovementP2 = 0;
                        myGame.yDirectionalMovementP2 = -1;
                    }
                    else if(inputLine.equals("LEFT"))
                    {
                        myGame.xDirectionalMovementP2 = -1;
                        myGame.yDirectionalMovementP2 = 0;
                    }
                    else if(inputLine.equals("RIGHT")) {
                        myGame.xDirectionalMovementP2 = 1;
                        myGame.yDirectionalMovementP2 = 0;
                    }
//                    }else if (inputLine.equals("Client's Server setup complete"))
//                    {
//                        if(myGame.twoPlayerServer == true) {
//                            System.out.println("Creating Server's Client");
//                            createServersClient();
//                        }
//                    }
                    System.out.println("inputLine = " + inputLine);
                    outputToClient.println("received from client: " + outputToClient);

                        //aList.add(inputLine);

                      //add a sys out to see what is actually happening here in the aList
                        //Not working quite right. The array of strings is not showing the latest addition to it in the printout.
                    }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public void createServersClient()
    {
        myClient = new Client(myGame, "localhost", 8006);
        myClient.runClient();
        System.out.println("Server's client created, and the run client command called.");
    }
}
