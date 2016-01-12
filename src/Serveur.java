import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void main(String[] args) throws IOException{
		
		ServerSocket socket;
		try {
			String port = ParametreFichier.getParametre("Port");
			socket = new ServerSocket(Integer.parseInt(port));
			Thread t = new Thread(new Accepter_clients(socket));
			t.start();
			System.out.println("Le serveur écoute sur le port " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Accepter_clients implements Runnable {

	   private ServerSocket socketserver;
	   private Socket socket;
	   private int nbrclient = 1;
	   
	   public Accepter_clients(ServerSocket s){
		   socketserver = s;
	   }
		
		public void run() {
	        try {
	        	while(nbrclient <= Integer.parseInt(ParametreFichier.getParametre("Nb_connexions"))){
	        		socket = socketserver.accept(); // Un client se connecte on l'accepte
	                System.out.println("Le client numéro " + nbrclient + " est connecté !");
	                nbrclient++;
	                socket.close();
	        	}
	        } catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
