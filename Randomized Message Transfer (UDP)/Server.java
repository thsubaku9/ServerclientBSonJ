/**
 * Created by kernel on 17-03-2019.
 */

import sun.rmi.runtime.Log;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.time.Instant;

public class Server {

    //MetaData for Packet/Connections
    int port = 9000;
    String group = "225.100.102.100";     //group uniformity should exist between server+client
    int TTL = 1;
    MulticastSocket mls;

    Server()
    {
        //Exception handling for Socket Init.
        try {
            mls = new MulticastSocket();
            //binding to a specific port not required for client
            //sending is another issue all together

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        Server myServer = new Server();

        MessageCreator create;      //generates the unique message
        MSG sender;


        try{
            while(true) {
                create=new MessageCreator();
                sender=new MSG(create.a1,create.checkSum);

                Thread.sleep(10000);


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(sender);

                DatagramPacket dpkt = new DatagramPacket(baos.toByteArray(),baos.toByteArray().length, InetAddress.getByName(myServer.group),myServer.port);
                myServer.mls.send(dpkt,(byte)myServer.TTL);
                //TTL not necessary. Added for just precautionary measures

                System.out.println(String.format("Current Time : %s", Instant.now().toString()));
                System.out.println(String.format("Message Sent with Checksum: %s <--> %s",sender.message,sender.checksum));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        myServer.mls.close();

    }
}

//The sender should generate and send new objects at fixed time intervals (10s)