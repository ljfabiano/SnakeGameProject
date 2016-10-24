package com.mygdx.game;

/**
 * Created by jfabiano on 10/23/2016.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    SnakeGame myGame;
    public Server()
    {

    }
    public Server(SnakeGame myGame)
    {
        this.myGame = myGame;
    }

    public void setConnection()
    {
        try {
            System.out.println("Server called");
            ServerSocket serverListener = new ServerSocket(8005);
//            while(true) {
                Socket clientSocket = serverListener.accept();
                //create new connection handler just accepted, and create the connection handler object, then create the thread, and then
                //pass it the thread
                ConnectionHandler myHandler = new ConnectionHandler(clientSocket, myGame);
                Thread handlingThread = new Thread(myHandler);
                handlingThread.start();
//            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
