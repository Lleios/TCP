import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientEcho {
    public static void main(String[] args) throws IOException {

        String AdresseServeur = "localhost";
        int port = Integer.parseInt(ParametreFichier.getParametre("Port"));
        
        System.out.println ("Adresse serveur : " + AdresseServeur + "\nPort : "+port);
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
        	System.out.println("Connexion à l'adresse : " + AdresseServeur + " sur le Port : "+port);
            echoSocket = new Socket(AdresseServeur, port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Adresse inconnu : "+ AdresseServeur);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Impossible de se connecter à l'adresse : " + AdresseServeur);
            System.exit(1);
        }

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		
		    System.out.println ("Taper 'quitter' pour se déconnecter)");
		while ((userInput = stdIn.readLine()) != null){
			out.println(userInput);
	        if (userInput.equals("quitter")){
	        	break;
	        }
		    System.out.println("echo: " + in.readLine());
		}

	out.close();
	in.close();
	stdIn.close();
	echoSocket.close();
    }
}

