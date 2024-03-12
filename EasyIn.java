// EasyIn.java

import java.io.*;

public abstract class EasyIn
{
  static String s = new String();
  static byte[] b = new byte[512];
  static int bytesRead = 0;

  public static String getString()
  {
     boolean ok = false;
     while(!ok)
     {
        try
        {
           bytesRead = System.in.read(b);
           s = new String(b,0,bytesRead-1);
           s=s.trim();
           ok = true;
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
     }
   return s;
   }
}
