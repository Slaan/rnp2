package server;

import java.io.IOException;
import java.net.ServerSocket;

import persistenceComponent.IPersistenceService;
import persistenceComponent.PersistenceService;
import pop3AccountComponent.IPOP3Account;
import pop3AccountComponent.IPOP3AccountService;
import pop3AccountComponent.POP3Account;
import pop3AccountComponent.POP3AccountService;
import backend.POP3Backend;
import entity.NumberOfActiveClientsHandler;
import gui.MainWindow;

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
    NumberOfActiveClientsHandler cur_clients = new NumberOfActiveClientsHandler();
    int portNumber = Integer.parseInt(args[0]);
    System.out.println("Start server on port: " + portNumber);
    IPOP3AccountService accountService = new POP3AccountService();
    IPersistenceService persistenceService = new PersistenceService();
    POP3Backend backend = new POP3Backend(accountService, persistenceService);
    MainWindow mainwindow = new MainWindow(backend, accountService, cur_clients);
    backend.start();
    try {
      ServerSocket serverSocket = new ServerSocket(portNumber);
      boolean done = false;
      while (!done) {
        if (!cur_clients.max_clients_reached()) {
          //TODO add param cur_clients to threads
          POP3FrontendHandler newcon = new POP3FrontendHandler(serverSocket.accept());
          newcon.start();
          cur_clients.increase_clientnumber();;
        }
      }
      System.out.println("Ende");
      serverSocket.close();
    } catch (IOException e) {
      String errMsg = "Exception caught when trying to listen on port "
                         + portNumber + " or listening for a new connection";
      System.err.println(errMsg);
      System.err.println(e.getMessage());
    }
  }

}
