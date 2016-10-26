package com.mygdx.game;

/**
 * Created by jfabiano on 10/23/2016.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    SnakeGame myGame;
    Server myServer;
    Socket clientSocket;
    int port = 8005;
    String ipAddress = "localhost";//"192.168.86.135"; Dom's ip address when we tested on his computer
    PrintWriter out;
    BufferedReader in;
    public Client()
    {

    }
    public Client(SnakeGame myGame)
    {
        this.myGame = myGame;
    }
    public Client(SnakeGame myGame, String ipAddress, int port)
    {
        this.myGame = myGame;
        this.ipAddress = ipAddress;
        this.port = port;
    }
    public static void main(String[] args) {
        System.out.println("This is the client");
        try {
            //Scanner for the console
            //Scanner consoleInput = new Scanner(System.in);
            // connect to the server on the target port
            Socket clientSocket = new Socket("localhost", 8005);
            //Change this string to point to the appropriate ip address, or localhost for myself, 127.0.0.1 = me, 10.0.0.139 = Ben
            // once we connect to the server, we also have an input and output stream
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //the input is the socket/inputstream, the input streamReader changes the stream into characters, and the Bufferedreader
            //turns the characters into lines to use the readline() function
            System.out.println("Sending the ip address to the server.");
            //String name = consoleInput.nextLine();
            String input = InetAddress.getLocalHost().toString();
            out.println(input);
            String serverResponse = in.readLine();
            System.out.println(serverResponse);
            if(serverResponse.equals("I have your ip address.")) {

                System.out.println("The server is ready to receive commands from the button listeners.");
                //createClientsServer();
                //input = consoleInput.nextLine();

                //out.println(input);
                //if the server/client is waiting for a input from the other the stream is blocked. always have a closed loop for the back and
                //forth between the server and the client.
            }
            else
            {
                System.out.println("Sorry the connection failed.");
                    //input = consoleInput.nextLine();
                //clientSocket.close();
                }

            //use a .split on the input stream. connect the ip address/socket to the name sent?
            // close the connection

            //the close connection needs to be activated once the exit to main menu button is selected rather than here.
            System.out.println("Thanks for sending messages! Goodbye!");
            clientSocket.close();
            //closeConnection();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void runClient(){
        System.out.println("This is the client");
        try {
            //Scanner for the console
            //Scanner consoleInput = new Scanner(System.in);
            // connect to the server on the target port
            clientSocket = new Socket("localhost", port);//"192.168.86.135" Dom's ip address when we tested multiplayer on his computer, also 8006 was the port for testing with Dom.  may have been the original port number here
            //Change this string to point to the appropriate ip address, or localhost for myself, 127.0.0.1 = me, 10.0.0.139 = Ben
            // once we connect to the server, we also have an input and output stream
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //the input is the socket/inputstream, the input streamReader changes the stream into characters, and the Bufferedreader
            //turns the characters into lines to use the readline() function
            System.out.println("Sending the ip address to the server.");
            //String name = consoleInput.nextLine();
            String input = InetAddress.getLocalHost().toString();
            out.println(input);
            String serverResponse = in.readLine();
            System.out.println(serverResponse);
            if(serverResponse.equals("I have your ip address.")) {

                System.out.println("The server is ready to receive commands from the button listeners.");
                if(myGame.twoPlayerClient == true) {
                    System.out.println("Creating client's server");
                    createClientsServer();
                }

                //input = consoleInput.nextLine();

                //out.println(input);
                String response = dialogWithServer("up");
                System.out.println("The response from server on a command: " + response.toString());
                response = dialogWithServer("down");
                System.out.println("The response from server on a command: " + response.toString());
                response = dialogWithServer("left");
                System.out.println("The response from server on a command: " + response.toString());
                response = dialogWithServer("right");
                System.out.println("The response from server on a command: " + response.toString());
                //Client's Server setup complete
                response = dialogWithServer("Client's Server setup complete");
                System.out.println("The response from server on a command: " + response.toString());
                if(myGame.twoPlayerServer == true) {
                    System.out.println("Server's client attempting to send the food to the client's server");
                    myGame.myServer.myHandler.myClient.dialogWithServer("x" + myGame.myFood.getX());
                    myGame.myServer.myHandler.myClient.dialogWithServer("y" + myGame.myFood.getY());
                }
                //if the server/client is waiting for a input from the other the stream is blocked. always have a closed loop for the back and
                //forth between the server and the client.
            }
            else
            {
                System.out.println("Sorry the connection failed.");
                //input = consoleInput.nextLine();
                //clientSocket.close();
            }

            //use a .split on the input stream. connect the ip address/socket to the name sent?
            // close the connection

            //the close connection needs to be activated once the exit to main menu button is selected rather than here.
//            System.out.println("Thanks for sending messages! Goodbye!");
//            clientSocket.close();
            //closeConnection();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public Socket connectToServer(String ipAddress, int portNumber)
    {
        Socket clientSocket = null;
        String connectionResponse;
        try {
            clientSocket = new Socket(ipAddress, portNumber);
            System.out.println("Succeeded in connecting to the server.");
            connectionResponse = "Succeeded in connecting to the server.";
        }
        catch(IOException ex)
        {
            System.out.println("Failed to connect to the server.");
            ex.printStackTrace();
            connectionResponse = "Failed to connect to the server.";
        }
        return clientSocket;
    }
    public String dialogWithServer(String messageToServer)
    {
        String messageFromServer = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(messageToServer);
            messageFromServer = in.readLine();
        }
        catch(IOException ex) {

        }
        return messageFromServer;
    }
    //overload for sending objects rather than strings(Integers for the food location)
    public String dialogWithServer(Object messageToServer)
    {
        //JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        //Object jsonStringStroke = jsonSerializer.serialize(myStroke);
        String messageFromServer = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(messageToServer);
            messageFromServer = in.readLine();
        }
        catch(IOException ex) {

        }
        return messageFromServer;
    }

    public void createClientsServer()
    {
        myServer = new Server(myGame, 8006);
        myServer.setConnection();
        System.out.println("Client's server set connection called, and the server created");
    }
    public void closeConnection()
    {
        try {
            System.out.println("Thanks for sending messages! Goodbye!");
            clientSocket.close();
        }
        catch(IOException ioe)
        {
            System.out.println("There was an error attempting to close the client socket connection");
            ioe.printStackTrace();
        }
    }
}
