/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mypkg;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author LAB_TI
 */
public class PacketSendDemo {
    public static void main(String[] args) {
        System.out.println("Packet Send\n========================");
        String hostname = "localhost";

        System.out.println("Binding to a local port ");

        try {
            DatagramSocket socket = new DatagramSocket();
            System.out.println("Bound to local port "+ socket.getLocalPort());

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            String b = buf.readLine();
            PrintStream pout = new PrintStream(bout);
            pout.print(b);

            byte[] barray = bout.toByteArray();

            DatagramPacket packet = new DatagramPacket(barray, barray.length);

            System.out.println("Looking for hostname "+ hostname);
            InetAddress remote_addr = InetAddress.getByName(hostname);

            System.out.println("Hostname Resolved as "+ remote_addr.getHostAddress());

            packet.setAddress(remote_addr);

            packet.setPort(2000);

            socket.send(packet);
            System.out.println("Packet Send");
        } catch(SocketException ex){
            System.err.println("Error: "+ ex.getMessage());

        } catch(IOException ex){
            System.err.println("Error : "+ ex.getMessage());
        }
    }
}
