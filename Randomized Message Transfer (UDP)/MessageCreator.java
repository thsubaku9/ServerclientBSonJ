/**
 * Created by kernel on 17-03-2019.
 */

import sun.rmi.runtime.Log;
import javax.xml.bind.DatatypeConverter;
import java.lang.Math;
import java.security.MessageDigest;

public class MessageCreator {

    //MetaData for message creation
    String a1="";
    int strLen;
    String CharsReq = "QWERTYUIOPASDFGHJKLZXCVBNM";
    String IntSeq = "1234567890";
    String checkSum;

    MessageCreator()
    {
        strLen=(int)(Math.random()*100);    //MaxValue possible for length = 100
        for( int i = 0 ; i < strLen ; i++)
        {
            int choice = (int)(Math.random()*3);
            int loc;

            // random "choice" ensure selection of values is equal, switch cases assigned are arbitrary
            switch ( choice )
            {
                case 0 :
                    loc = (int)(Math.random()*CharsReq.length());
                    a1 += ""+CharsReq.substring(loc,loc+1);
                    break;
                case 1 :
                    loc =  (int)(Math.random()*IntSeq.length());
                    a1 += ""+IntSeq.substring(loc,loc+1);
                    break;
                case 2 :
                    loc = (int)(Math.random()*CharsReq.length());
                    a1 += ""+CharsReq.substring(loc,loc+1).toLowerCase();
                    break;
                default :
                    break;
            }
        }

        checkSum=getMD5Hash(a1);
        //MD5 provides more unique values as compared to a simple XORSUM technique and requires less length compared to a SHA technique
    }

    private static String getMD5Hash(String data) {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] byteDigest = digest.digest(data.getBytes("UTF-8"));
            return DatatypeConverter.printHexBinary(byteDigest);
        }
        catch(Exception ex) {
            Log.getLog("Checksum Error",ex.getMessage(),-1);
        }
        return "ERRORMSG";
    }

}

