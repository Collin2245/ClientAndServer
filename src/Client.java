import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // code
        System.out.println("Starting Client");
        try{
            Socket s=new Socket("localhost",6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            System.out.println("Please enter your name: ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String name = reader.readLine();
            dout.writeUTF(name);
            dout.flush();
            dout.close();
            s.close();
        }catch(Exception e){System.out.println(e);}
    }
}
