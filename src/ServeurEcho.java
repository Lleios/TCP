import java.net.*; 
import java.io.*; 

public class ServeurEcho extends Thread
{ 
 protected Socket socketClient;
 
 private int nbClients = 0;

 public static void main(String[] args) throws IOException 
   { 
    ServerSocket serverSocket = null; 
    int limiteClient = Integer.parseInt(ParametreFichier.getParametre("Nb_connexions"));
    int port = Integer.parseInt(ParametreFichier.getParametre("Port"));
    
    try { 
         serverSocket = new ServerSocket(port,limiteClient); 
         System.out.println ("Connexion");
         try { 
              while (true)
                 {
                  System.out.println ("En attente de connexion");
                  new ServeurEcho (serverSocket.accept()); 
                 }
             } 
         catch (IOException e) 
             { 
              System.err.println("Connexion refusée"); 
              System.exit(1); 
             } 
        } 
    catch (IOException e) 
        { 
         System.err.println("le port "+port+"n'est pas disponible"); 
         System.exit(1); 
        } 
    finally
        {
         try {
              serverSocket.close(); 
             }
         catch (IOException e)
             { 
              System.err.println("Impossible de fermer le port "+port); 
              System.exit(1); 
             } 
        }
   }

 private ServeurEcho (Socket clientSoc)
   {
    socketClient = clientSoc;
    start();
   }

 public void run()
   {
    System.out.println ("Nouveau client connecté");
    try { 
         PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true); 
         BufferedReader in = new BufferedReader(new InputStreamReader( socketClient.getInputStream() ) ); 
         String inputLine; 

         while ((inputLine = in.readLine()) != null) 
             { 
              System.out.println ("Serveur: " + inputLine); 
              out.println(inputLine); 

              if (inputLine.equals("quitter")){
            	  break; 
              }
             } 

         out.close(); 
         in.close(); 
         socketClient.close(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Problème avec le serveur");
         System.exit(1); 
        } 
    }
} 