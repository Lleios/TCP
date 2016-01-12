/**
* Classe Client
*
* la classe Client, représente un client se connectant au serveur
*
* @author  Gonnord Kevin, Chcouropat Youri
*/

import java.io.IOException;
import java.net.Socket;

public class Client{

	public static void main(String[] args){
		Socket socket;
		try {
			String port = ParametreFichier.getParametre("Port");
			socket = new Socket("localhost", Integer.parseInt(port));
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}