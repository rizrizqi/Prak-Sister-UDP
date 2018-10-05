/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mypkg;

import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Message1_1Impl;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.InterruptedIOException;

/**
 *
 * @author LAB_TI
 */
public class EchoClient {
    private static final int SERVICE_PORT= 7;
    private static final int BUFSIZE = 256;

    public static void main(String[] args) throws SocketException, IOException {
        String hostname = "localhost";
        InetAddress addr = InetAddress.getByName(hostname);

        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(2000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (;;){
            System.out.println("Write Your Message Here...");
            ByteArrayOutputStream bout  = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream(bout);
           // pout.print(reader.readLine());
            String message = reader.readLine();

            if(!message.matches("exit")){
                pout.print(message);
            } else{
                System.out.println("Program dihentikan");
                break;
            }


            byte[] barray = new byte[BUFSIZE];
            DatagramPacket packet = new DatagramPacket(barray, barray.length, addr, SERVICE_PORT);
            System.out.println("Sending Packet to "+hostname);
            socket.send(packet);

            byte[] recbuf = new byte[BUFSIZE];
            DatagramPacket receivedPacket = new DatagramPacket(recbuf, BUFSIZE);
            boolean timeout = false;

            try{
                socket.receive(receivedPacket);
            } catch(InterruptedIOException ioe){
                timeout = true;
            }
            if(!timeout){
                System.out.println("Packet Received!");
                System.out.println("Details : " + receivedPacket.getAddress());

                ByteArrayInputStream bin = new ByteArrayInputStream(receivedPacket.getData(),
                        0, receivedPacket.getLength());

                BufferedReader reader2 = new BufferedReader(new InputStreamReader(bin));

                   for(;;){
                   String line = reader2.readLine();
                   if(line == null){
                    break;
                   }else {
                       System.out.println(line);
                   }
                   }
                //System.out.println(reader2.readLine());
            } else {
                System.out.println("Packet Lost!");
            }
        }

    }
}
