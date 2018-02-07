import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by MAHE on 2/7/2018.
 */
public class client {
    public static void main(String[] args) throws Exception
    {
        BufferedReader rd= new BufferedReader(new InputStreamReader(System.in));
        try{
            DatagramSocket dgs= new DatagramSocket();
            InetAddress ipaddr=InetAddress.getByName("localhost");
            byte[] recv= new byte[1000];
            byte[] send= new byte[1000];
            System.out.println("Enter the message that has to be sent");
            String data=rd.readLine();
            send=data.getBytes();
            DatagramPacket st= new DatagramPacket(send,send.length,ipaddr,9000);
            dgs.send(st);
            dgs.close();
        }
        catch(SocketException e){}
        catch(IOException e){}
    }
}

