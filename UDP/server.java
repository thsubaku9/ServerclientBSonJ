import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by MAHE on 2/7/2018.
 */
public class server {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket ser=null;
        try {
            ser = new DatagramSocket(9000);
            byte[] recv= new byte[1000];
            byte[] send= new byte[1000];
            while(true)
            {
                DatagramPacket reP=new DatagramPacket(recv,recv.length);
                ser.receive(reP);
                String sen= new String(reP.getData());
                System.out.println(sen);
                System.out.println("bopadopa");
                ser.close();
            }
        }
        catch (SocketException e){}
        catch (IOException e ){}
    }
}
