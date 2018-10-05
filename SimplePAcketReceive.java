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
public class SimplePAcketReceive {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(2000);

        DatagramPacket packet = new DatagramPacket(new byte [256], 256);
        socket.receive(packet);

        String massage = new String(packet.getData(), 0, packet.getLength());
        System.out.println(massage);
    }

}
