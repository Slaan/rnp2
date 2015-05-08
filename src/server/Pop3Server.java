package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Pop3Server {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
        System.err.println("Usage: java EchoServer <port number>");
        System.exit(1);
    }
    int portNumber = Integer.parseInt(args[0]);
    System.out.println("here we go");
    try {
      ServerSocket serverSocket = new ServerSocket(portNumber);
      while(true) {
        MultiServerThread newcon = new MultiServerThread(serverSocket.accept());
        newcon.start();
      }
    } catch (IOException e) {
        String err_msg = "Exception caught when trying to listen on port "
                         + portNumber + " or listening for a connection";
        System.err.println(err_msg);
        System.err.println(e.getMessage());
    }
  }

}
