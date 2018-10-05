/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;




import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 *
 * @author Rizqi
 */
public class TaskServer {
      // UDP port to which service is bound
    public static final int SERVICE_PORT = 7;
    // Max size of packet, large enough for almost any client
    public static final int BUFSIZE = 4096;
    // Socket used for reading and writing UDP packets
    private DatagramSocket socket;
   
    public TaskServer(){
        try{
            // Bind to specified UDP port, to listen for incoming data packets
            socket = new DatagramSocket(SERVICE_PORT);
            System.out.println("Server active on port "+ socket.getLocalPort());
        }catch(Exception e){
            System.err.println("Unable to bind port");
        }
    }
    
    public void serviceClients(){
        // Create a buffer large enough for incoming packet
        byte[]buffer = new byte[BUFSIZE];
        
        for(;;){
            try{
                
                // Create a DatagramPacket for reading packets
                DatagramPacket packet = new DatagramPacket(buffer, BUFSIZE);
                // Receive incoming packets
                socket.receive(packet);
                System.out.println("Packet received form "+packet.getAddress() +":"+packet.getPort()+" of length "+packet.getLength());
                String dataReceived = new String(packet.getData(), 0, packet.getLength());
                try(FileOutputStream fou = new FileOutputStream("d:/dataBaru.txt")){
                    for(int i =0; i<dataReceived.length(); i++){
                        fou.write(dataReceived.charAt(i));
                    }
                }            
            }catch(IOException ioe){
                System.err.println("Error "+ioe);
            }
        }
    }
    public static void main(String[] args) {
        TaskServer server = new TaskServer();
        server.serviceClients();
        
    }
}