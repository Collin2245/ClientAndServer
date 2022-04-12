package Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatWorkerThread extends Thread
{
    private Socket theClientSocket;
    private PrintStream clientOutput;
    private Scanner clientInput;
    private DataInputStream clientDataInputStream;
    private DataOutputStream clientDataOutputStream;

    public ChatWorkerThread(Socket theClientSocket)
    {
        try 
        {
            System.out.println("Connection Established...");
            this.theClientSocket = theClientSocket;
            this.clientOutput = new PrintStream(this.theClientSocket.getOutputStream());
            this.clientDataInputStream = new DataInputStream(this.theClientSocket.getInputStream());
            this.clientDataOutputStream = new DataOutputStream(this.theClientSocket.getOutputStream());
            //System.out.println("About to add a printstream");
            CORE.addClientThreadPrintStream(this.clientOutput);

            this.clientInput = new Scanner(this.theClientSocket.getInputStream());
        }
        catch (Exception e) 
        {
            System.err.println("Bad things happened in thread!!!!!");
            e.printStackTrace();
        }
        
    }

    public void run()
    {
        //this is what the thread does
        this.clientOutput.println("What is your name?");
        String name = clientInput.nextLine();
        CORE.broadcastMessage(name + " has joined!");
        String message;
        while(true)
        {
            message = clientInput.nextLine();
            if(message.equals("/quit"))
            {
                CORE.broadcastMessage(name + " has left");
                CORE.removeClientThreadPrintStream(clientOutput);
                break;
            }
            else if(message.startsWith("/up "))
            {

                //clientOutput.println("Uploading file...");
            }
            else if(message.startsWith("/down "))
            {
                //clientOutput.println("downloading file...");
            }
            else if(message.startsWith("/path "))
            {
                //clientOutput.println("downloading file...");
            }
            else
            {
                CORE.broadcastMessage(message);
            }
        }
    }
}
