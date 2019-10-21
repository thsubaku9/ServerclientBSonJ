/**
 * Created by kernel on 19-03-2019.
 */

import java.io.Serializable;

public class MSG implements Serializable
{
    String message="";
    String checksum="";
    MSG(String message,String checksum)
    {
        this.message=message;
        this.checksum=checksum;
    }


}