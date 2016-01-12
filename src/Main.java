import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String implementation = ParametreFichier.getParametre("Implementation");
		if(implementation.equals("bas_niveau")){
			
		}else if(implementation.equals("haut_niveau")){
			Serveur.main(null);
			Client.main(null);
		}else{
			System.out.println("L'implementation choisi est non valide !");
		}
	}

}
