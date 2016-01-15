package autre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

  public class ClientEcho {
   
      public static final int PORT = 56789;
  
     public static void usage() {
        System.out.println ("Usage: java ClientEcho <hote> [<port>]");
        System.exit (0);
     }
  
     private static void msg (String s) {
        System.out.println ("Client:  " + s);
     }
  
     public static void main (String[] args) {
        int port = PORT;
        Socket s = null;
        
        try {
           // Creer un socket pour communiquer avec le hote et le port specifie
           s = new Socket ("localhost", port);
           
           // Creer un stream pour lire et ecrire des lignes de texte depuis et vers ce socket
           BufferedReader sin = new BufferedReader (new InputStreamReader (s.getInputStream()));
           PrintWriter sout = new PrintWriter (s.getOutputStream(), true);
           
           // Creer un stream pour lire les lignes de texte depuis la console 
          BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
           
           // Dire au client qu'il est connecte 
           msg ("connecte a "+s.getInetAddress()+" au port "+s.getPort());
  
           String line;
           while (true) {
              System.out.print ("> "); 
              System.out.flush();
              // Lire la ligne 
              line = in.readLine();
              if (line == null) break;
              msg ("ligne envoye au serveur: " + line);
              sout.println (line);
              line = sin.readLine();
  
              // Verifier si la connection est ferme 
              if (line == null) { 
                 msg ("connection ferme par le serveur");
                 break;
              }
              // Ecrire dans la console
              msg ("recu du serveur:   " + line);
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
