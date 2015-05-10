package server;

import java.io.IOException;
import java.net.ServerSocket;

import entity.NumberOfActiveClients;

public class POP3Server {

  private static int max_clients=3;  
  /**
   * Start program by starting main method
   * @param args to pass.
   */
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java EchoServer <port number>");
      System.exit(1);
    }
    NumberOfActiveClients cur_clients = new NumberOfActiveClients();
    int portNumber = Integer.parseInt(args[0]);
    System.out.println("Start server on port: " + portNumber);
    try {
      ServerSocket serverSocket = new ServerSocket(portNumber);
      boolean done = false;
      while (!done) {
        if (cur_clients.get_number_of_clients()<max_clients) {
          //add param cur_clients to threads
          POP3FrontendHandler newcon = new POP3FrontendHandler(serverSocket.accept());
          newcon.start();
          cur_clients.increase_clientnumber();;
        }
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
