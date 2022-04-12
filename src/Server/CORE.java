package Server;

import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;

public class CORE 
{
    private static ArrayList<PrintStream> theClientStreams = new ArrayList<PrintStream>();

    private static FileOutputStream COREFiles;

    {
        try {
            COREFiles = new FileOutputStream("files");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void addClientThreadPrintStream(PrintStream ps)
    {
        System.out.println("adding client thread");
        CORE.theClientStreams.add(ps);
    }

    public static synchronized void removeClientThreadPrintStream(PrintStream ps)
    {
        System.out.println("removing client thread");
        CORE.theClientStreams.remove(ps);
    }

    public static synchronized void addFileToCORE(byte[] BAInput)
    {
        try {
            COREFiles.write(BAInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(String message)
    {
        System.out.println("About to broadcast....");
        for (PrintStream ps : CORE.theClientStreams)
        {
            ps.println(message);
        }
    }
}
