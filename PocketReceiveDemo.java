/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mypkg;

import com.sun.org.apache.xml.internal.security.Init;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author LAB_TI
 */
public class PocketReceiveDemo {
    public static void main(String[] args) {
        System.out.println("Packet Receive\n==================");
        System.out.println("Binding to Local port 2000");
                try {
                    DatagramSocket socket = new DatagramSocket(2000);

                    System.out.println("Bound to local port "+ socket.getLocalPort());

                    DatagramPacket packet = new DatagramPacket(new byte [256], 256);

                    socket.receive(packet);
                    System.out.println("Packet received!");
                    InetAddress remote_addr = packet.getAddress();
                    System.out.println("Send By : "+ remote_addr.getHostAddress());
                    System.out.println("Send Frol : "+packet.getPort());

                    ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData());
                    BufferedReader br = new BufferedReader(new InputStreamReader(bin));
                    System.out.print("Message : "+br.readLine());
                }catch(IOException ioe){
                    System.err.println("Error: "+ioe.getMessage());
                }
    }

}
