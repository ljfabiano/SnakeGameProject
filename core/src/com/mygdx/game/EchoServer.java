package com.mygdx.game;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Created by jfabiano on 12/19/2017.
 */

public class EchoServer extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public EchoServer() {
        System.out.println("Server constructor");
        try {
            socket = new DatagramSocket(4445);
        }catch(IOException echoServerConstructor){
            echoServerConstructor.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Server run method");
        running = true;

        while (running) {
            System.out.println("while loop in run method running again");
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException echoServerRunSocketReceive) {
                echoServerRunSocketReceive.printStackTrace();
            }
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received = new String(packet.getData(), 0, packet.getLength());

            if (received.equals("end")) {
                running = false;
                continue;
            }
            try {
                socket.send(packet);
            }catch(IOException echoServerRunSocketSend){
                echoServerRunSocketSend.printStackTrace();
            }
        }
        System.out.println("Closing run method");
        socket.close();
    }
}
