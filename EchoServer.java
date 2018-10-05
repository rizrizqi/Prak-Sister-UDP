/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mypkg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author LAB_TI
 */
public class EchoServer {
    private static final int SERVICE_PORT= 7;

    private static final int BUFSIZE = 4096;
    private DatagramSocket socket;

    public EchoServer(){
        try{
            socket = new DatagramSocket(SERVICE_PORT);
            System.out.println("Server Active on port "+socket.getLocalPort());
        }catch(IOException e){
            System.err.println("Unable to bind port");
        }
    }
    public void serviceClients(){
        byte[] buffer = new byte[BUFSIZE];
        for(;;){
            try{
                DatagramPacket packet = new DatagramPacket(buffer, BUFSIZE);
                socket.receive(packet);
                System.out.println("Packet Received from "
                        + packet.getAddress() +": "
                        + packet.getPort()
                        + "Of Length "+ packet.getLength());
                socket.send(packet);
            }catch(IOException ioe){
                System.err.println("Error : "+ ioe);
            }
        }
    }
    public static void main(String[] args) {
        EchoServer server = new EchoServer();
        server.serviceClients();
    }

}
