package autre;

import java.net.Socket;
import java.net.ServerSocket;
  
  import java.io.InputStreamReader;
  import java.io.BufferedReader;
  import java.io.PrintWriter;
  import java.io.IOException;
  
  public class EchoServer extends Thread {
     private final static int DEFAULT_PORT = 56789;
  
     private static void msg (String s) {
        System.out.println ("Server:  " + s);
     }
  
     public static void main (String[] args) {
        int port = DEFAULT_PORT;
        if (args.length == 1) {
           try {
              port = Integer.parseInt(args[0]);
           } catch (NumberFormatException e) {
              System.err.println ("First argument was not a number,");
              System.err.println ("so using default port instead.");
              port = DEFAULT_PORT;
           }
        }
        try {
           final ServerSocket listen_socket = new ServerSocket(port);
           msg ("listening on port " + port);
           while (true) {
              Socket client_socket = listen_socket.accept();
              // fork off an independent thread to deal with the client
              new EchoServer (client_socket);
           }
        } catch (IOException e) {
           e.printStackTrace (System.err);
        }
     }
 
     protected final Socket client;
     protected BufferedReader br;
     protected PrintWriter out;
     protected final static boolean auto_flush = true;
  
     public EchoServer (Socket client_socket) {
        client = client_socket;
        msg ("Server: connection from " + client.getInetAddress());
        try {
           br = new BufferedReader (
              new InputStreamReader (client.getInputStream()));
           out = new PrintWriter (client.getOutputStream(), auto_flush);
           this.start();
        } catch (IOException e) {
           e.printStackTrace (System.err);
           close ();
        }
     }
  
    public void run () {
        msg ("client thread running.");
        try {
           String line;
           while ((line=br.readLine()) != null) {
              out.println (line);
           }
        } catch (IOException e) {
           e.printStackTrace (System.err);
        } finally {
           close ();
        }
        msg ("client thread finished.");
     }
  
     protected void close () {
        try {
           if (client!=null) client.close();
        } catch (IOException e) {
           e.printStackTrace (System.err);
        }
     }
  }

