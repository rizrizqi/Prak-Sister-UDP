/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mypkg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author LAB_TI
 */
public class SimplePacketSend {
    public static void main(String[] args) throws SocketException, IOException{

            DatagramSocket socket = new DatagramSocket();
            String message = "Assalamu'alaikum";
            DatagramPacket packet = new DatagramPacket(message.getBytes(),
                    message.length(),InetAddress.getLocalHost(), 2000);
            socket.send(packet);
            socket.close();

    }
}
