/**
 * Classe ServeurEcho
 * 
 * La classe ServeurEcho permet de crééer un Thread pour le serveur
 * 
 * @author  Gonnord Kevin, Chcouropat Youri
 */

import java.net.Socket;
import java.net.ServerSocket;

  import java.io.InputStreamReader;
  import java.io.BufferedReader;
  import java.io.PrintWriter;
  import java.io.IOException;

  public class ServeurEcho extends Thread {
     private final static int PORT = Integer.parseInt(ParametreFichier.getParametre("Port"));
     private final static int LIMITECLIENTS = Integer.parseInt(ParametreFichier.getParametre("Nb_connexions"));

	 /**
	  * Méthode affiche
	  * 
	  * La méthode affiche la chaine de caractère entrée en paramètre
	  * 
	  * @param s
	  */
     private static void affiche (String s) {
        System.out.println ("Serveur:  " + s);
     }

     public static void main (String[] args) {
        try {
           final ServerSocket serveurSocket = new ServerSocket(PORT,LIMITECLIENTS);
           affiche("listening on port " + PORT);
           while (true) {
              Socket clientSocket = serveurSocket.accept();
              // fork off an independent thread to deal with the client
              new ServeurEcho (clientSocket);
           }
        } catch (IOException e) {
           e.printStackTrace (System.err);
        }
     }

     protected final Socket client;
     protected BufferedReader br;
     protected PrintWriter out;
     protected final static boolean auto_flush = true;

     /**
      * Constructeur de la classe ServeurEcho
      * 
      * Il prend en paramètre un Socket
      * 
      * @param clientSocket
      */
     public ServeurEcho (Socket clientSocket) {
        client = clientSocket;
        affiche ("Serveur: adresse du client -> " + client.getInetAddress());
        try {
           br = new BufferedReader (new InputStreamReader (client.getInputStream()));
           out = new PrintWriter (client.getOutputStream(), auto_flush);
           this.start();
        } catch (IOException e) {
           e.printStackTrace (System.err);
           close ();
        }
     }

     /**
      * Implémentation de la méthode run de la classe Thread
      * 
      * Recupère la saisie du client et arrête le thread si le client ecrit "."
      * 
      */
    public void run () {
        affiche ("Nouveau Thread client");
        try {
           String line;
           while (  !((line=br.readLine()).equals(".")) ) {
              out.println (line);
           }
        } catch (IOException e) {
           e.printStackTrace (System.err);
        } finally {
           close ();
        }
        affiche ("Arrêt du Thread client.");
     }

    /**
     * Méthode Close
     * 
     * La méthode ferme le serveur
     * 
     */
     protected void close () {
        try {
           if (client!=null) client.close();
        } catch (IOException e) {
           e.printStackTrace (System.err);
        }
     }
  }
