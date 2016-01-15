


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

  public class EchoClient {
   
      public static final int DEFAULT_PORT = 56789;
  
     public static void usage() {
        System.out.println ("Usage: java EchoClient <hostname> [<port>]");
        System.exit (0);
     }
  
     private static void msg (String s) {
        System.out.println ("Client:  " + s);
     }
  
     public static void main (String[] args) {
        int port = DEFAULT_PORT;
        Socket s = null;
          
        // Parse the port specification
//        if ((args.length != 1) && (args.length != 2)) usage();
//        if (args.length == 1) {
           port = DEFAULT_PORT;
//        } else {
//           try {
//              port = Integer.parseInt(args[1]);
//           } catch (NumberFormatException e) {
//              usage();
//           }
//        }
        
        try {
           // Create a socket to communicate to the specified host and port
           s = new Socket ("localhost", port);
           // Create streams for reading and writing lines of text
           // from and to this socket.
           BufferedReader sin = new BufferedReader (new InputStreamReader (s.getInputStream()));
           PrintWriter sout = new PrintWriter (s.getOutputStream(), true);
           // Create a stream for reading lines of text from the console
          BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
           
           // Tell the user that we've connected
           msg ("connected to "+s.getInetAddress()+" at port "+s.getPort());
  
           String line;
           while (true) {
              // print a prompt
              System.out.print ("> "); 
              System.out.flush();
              // read a line from the console; check for EOF
              line = in.readLine();
              if (line == null) break;
              msg ("sending line to server: " + line);
              sout.println (line);
              line = sin.readLine();       // Read a line from the server.
  
              // Check if connection is closed (i.e. for EOF)
              if (line == null) { 
                 msg ("connection closed by server.");
                 break;
              }
              // And write the line to the console.
              msg ("received from server:   " + line);
           }
        } catch (IOException e) {
           e.printStackTrace (System.err);
        } finally {
          try {
              if (s != null) s.close();
           } catch (IOException e) {
              e.printStackTrace (System.err);
           };
        }
     }
  }
