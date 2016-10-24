package com.mygdx.game;

/**
 * Created by jfabiano on 10/23/2016.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    SnakeGame myGame;
    int port = 8005;
    ConnectionHandler myHandler;
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
            ServerSocket serverListener = new ServerSocket(port);
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
