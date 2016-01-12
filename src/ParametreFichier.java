<<<<<<< HEAD
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ParametreFichier {
	
	public ParametreFichier (){
		
	}
	
	static public String getParametre(String prop) throws IOException{
		Properties properties = new Properties();
		FileInputStream fileStream = null;
		String res = "";
		try {
			fileStream = new FileInputStream("config.txt");
			properties.load(fileStream);
			res = properties.getProperty(prop);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}finally {
		      fileStream.close();
		}
		return res;
	}
}
=======
/**
* Classe ParametreFichier
*
* la classe ParametreFichier permet de configurer : - le port utilisé
* 													- durée d'inactivité du client avant sa déconnexion
* 													- le nombre maximum de connexion simultanée
* 													- le type d'implémentation 
*
* @author  Gonnord Kevin, Chcouropat Youri
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ParametreFichier {
	
	public ParametreFichier (){
		
	}
	
	static public String getParametre(String prop) throws IOException{
		Properties properties = new Properties();
		FileInputStream fileStream = null;
		String res = "";
		try {
			fileStream = new FileInputStream("config.txt");
			properties.load(fileStream);
			res = properties.getProperty(prop);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}finally {
		      fileStream.close();
		}
		return res;
	}
}
>>>>>>> origin/master
