package com.mygdx.game;

/**
 * Created by jfabiano on 10/23/2016.
 */
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    SnakeGame myGame;
    int port = 8005;
    ConnectionHandler myHandler;
    ServerSocket serverListener;
    //The new attempt to use a UDP connection for the game for performance
    //1. Need to test to ensure the old one still works as expected meaning network play...
    //2. Need to test to see if this will work as is, or what needs to change for it to work.
    //DatagramSocket testClientSocket;
    public Server()
    {

    }
    public Server(SnakeGame myGame)
    {
        this.myGame = myGame;
    }
    public Server(SnakeGame myGame, int port)
    {
        this.myGame = myGame;
        this.port = port;
    }

    public void setConnection()
    {
        try {
            System.out.println("Server called");
            serverListener = new ServerSocket(port);
//            while(true) {
                Socket clientSocket = serverListener.accept();
                //create new connection handler just accepted, and create the connection handler object, then create the thread, and then
                //pass it the thread
                myHandler = new ConnectionHandler(clientSocket, myGame);
                Thread handlingThread = new Thread(myHandler);
                handlingThread.start();
//            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
