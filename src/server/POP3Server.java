package server;

import java.io.IOException;
import java.net.ServerSocket;

public class POP3Server {

  /**
   * Start program by starting main method
   * @param args to pass.
   */
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java EchoServer <port number>");
      System.exit(1);
    }
    int portNumber = Integer.parseInt(args[0]);
    System.out.println("Start server on port: " + portNumber);
    try {
      ServerSocket serverSocket = new ServerSocket(portNumber);
      boolean done = false;
      while (!done) {
        POP3Handler newcon = new POP3Handler(serverSocket.accept());
        newcon.start();
      }
      serverSocket.close();
    } catch (IOException e) {
      String errMsg = "Exception caught when trying to listen on port "
                         + portNumber + " or listening for a new connection";
      System.err.println(errMsg);
      System.err.println(e.getMessage());
    }
  }

}
