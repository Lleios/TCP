package autre;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class TestClientTCP {
  final static int port = 9632;

  public static void main(String[] args) {

    Socket socket;
    DataInputStream userInput;
    PrintStream theOutputStream;

    try {
      InetAddress serveur = InetAddress.getLocalHost();
      socket = new Socket(serveur, port);

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintStream out = new PrintStream(socket.getOutputStream());

      out.println(args[1]);
      System.out.println(in.readLine());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}