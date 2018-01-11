package com.mygdx.game;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static java.lang.System.console;
import static java.lang.System.setIn;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by jfabiano on 12/19/2017.
 */

public class UDPTest {
    EchoClient client;

    @Before
    public void setup(){
        System.out.println("Setting up test");
        System.out.println("creating server");
        new EchoServer().start();
        System.out.println("Creating client");
        client = new EchoClient();
    }

    @Test
    public void whenCanSendAndReceivePacket_thenCorrect() {
        System.out.println("in whencansentand receivepacket method in test");
        String echo = client.sendEcho("hello server");
        System.out.println("echo string value after client.sendecho method call with hello server value: " + echo);
        assertEquals("hello server", echo);
        echo = client.sendEcho("server is working");
        System.out.println("echo string value after client.sendecho method call with server is working value: " + echo);
        assertFalse(echo.equals("hello server"));
        assertEquals("server is working", echo);
        echo = client.sendEcho("testing...");
        assertFalse(echo.equals("server is working"));
        assertEquals("testing...", echo);
    }

    @After
    public void tearDown() {
        String echoVal = "echo initial value";
        System.out.println("tearing down method");
        System.out.println("sending end value to client.sendecho");
        echoVal = client.sendEcho("end");
        System.out.println("echoval value after client.sendecho end command sent: " + echoVal);
        System.out.println("client.close method called");
        client.close();
        System.out.println("end of test teardown method");
    }

}