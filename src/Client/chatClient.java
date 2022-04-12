package Client;

import java.io.DataInputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Paths;

public class chatClient 
{
    public static void main(String[] args) throws Exception
    {
        Socket s = new Socket("localhost", 2222); 
        Scanner clientInput = new Scanner(s.getInputStream());
        String question = clientInput.nextLine();
        System.out.println(question);
        Scanner localInput = new Scanner(System.in);
        PrintStream clientOutput = new PrintStream(s.getOutputStream());
        DataInputStream clientDatInput = new DataInputStream(s.getInputStream());
        

        Thread lt = new Thread() {
            public void run()
            {
                String line;
                while(true)
                {
                    line = clientInput.nextLine();
                    System.out.println(line);
                }
            }
        };

        lt.start();

        String line;
        String path = "NO PATH ENTERED. TYPE /PATH";
        while(true)
        {
            line = localInput.nextLine();
            clientOutput.println(line);
            if(line.equals("/quit"))
            {
                localInput.close();
                clientInput.close();
                System.exit(1);
                break;
            }
            else if(line.startsWith("/up "))
            {
                clientOutput.println("Uploading file " + line.substring(4) +" at path " + path);
                File file = null;
                String temp = path.concat(line.substring(3));
                temp = temp.replaceAll("\\s+","");
                byte[] ba = Files.readAllBytes(Paths.get(temp));

                clientOutput.println(ba);
            }
            else if(line.startsWith("/down "))
            {
                clientOutput.println("downloading file..."+ line.substring(5));
            }
            else if(line.startsWith("/path "))
            {
                clientOutput.println("Updating path to "+ line.substring(5));
                path = line.substring(5);
            }
            else
            clientOutput.println(localInput.nextLine());
        }
        System.exit(0);
        
    }
}
