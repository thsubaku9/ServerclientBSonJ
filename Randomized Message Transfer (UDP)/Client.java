/**
 * Created by kernel on 17-03-2019.
 */

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.time.Instant;


public class Client {

    //MetaData for Packet/Connections
    int port = 9000;
    String group = "225.100.102.100";     //group uniformity should exist between server+client
    MulticastSocket mls;

    Client()
    {
        //Exception handling for Socket Init.
        try {
            mls = new MulticastSocket(port);
            mls.joinGroup(InetAddress.getByName(group));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        byte buf[] = new byte[1024];

        Client myClient = new Client();
        MSG received;


        DatagramPacket pack = new DatagramPacket(buf, buf.length);  //Defines size of Datagram receivable

        try {
            while(true) {
                myClient.mls.receive(pack);

                ByteArrayInputStream bais = new ByteArrayInputStream(buf);
                ObjectInputStream ois = new ObjectInputStream(bais);
                try{
                    Object got = ois.readObject();
                    if(got instanceof MSG)      //to ensure typecasting can be done successfully
                    {
                        received=(MSG)got;
                        System.out.println(String.format("Current Time : %s",Instant.now().toString()));
                        System.out.println(String.format("Message Received with Checksum: %s <--> %s",received.message,received.checksum));
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        myClient.mls.close();
    }
}


