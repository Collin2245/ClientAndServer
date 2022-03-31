import java.io.*;
import java.net.*;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {




    public static void main(String[] args) throws Exception
    {
        ServerSocket s = new ServerSocket(2222);
        ArrayList<ClientThread> theThreads = new ArrayList<ClientThread>();
        while(true)
        {
            System.out.println("Listenning for Connection...");
            Socket client = s.accept(); //blocks
            ClientThread t = new ClientThread(client);
            theThreads.add(t);
            t.start();
        }

    }


}