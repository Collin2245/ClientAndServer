import java.net.Socket;
import java.util.ArrayList;

public class ClientCORE 
{
    private static ArrayList<String> theConnectedClientIPs= new ArrayList<String>();

    public static void updateTheConnectedClientIPs(String commaDilimitedListOfIPs)
    {
        ClientCORE.theConnectedClientIPs.clear();
        String[] theIPs = commaDilimitedListOfIPs.split(",");
        for(int i = 0 ; i < theIPs.length; i++)
        {
            ClientCORE.theConnectedClientIPs.add(theIPs[i]);
        }
        System.out.println("Client IP List Updated");
        try
        {
            ClientCORE.RequestByte();
        }
        catch (Exception e)
        {

        }
    }

    public static void RequestByte() throws Exception
    {
        for(String Client : ClientCORE.theConnectedClientIPs)
        {
            String[] IPandPort = Client.split(":");
            Socket s = new Socket(IPandPort[0], Integer.parseInt(IPandPort[1]));
            System.out.println("Connection established with Client. Hostname: " + IPandPort[0] + ", Port: " + IPandPort[1]);

        }
    }
}