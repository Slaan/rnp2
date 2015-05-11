package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import persistenceComponent.IPersistenceService;
import pop3AccountComponent.IPOP3AccountService;
import Frontend.FrontendComponent;
import Frontend.IFrontend;
import entity.NumberOfActiveClientsHandler;

public class POP3FrontendHandler extends Thread {

  private Socket                        socket;
  private IFrontend                     frontend;
  private NumberOfActiveClientsHandler  amountOfClients;
  
  public POP3FrontendHandler(Socket socket, 
      NumberOfActiveClientsHandler amountOfClients,
      IPOP3AccountService accountService, 
      IPersistenceService persistence) {
    super();
    this.socket = socket;
    this.amountOfClients = amountOfClients;
    this.frontend = new FrontendComponent(accountService, persistence);
  }
   
  public void run() {
    try {
      PrintWriter    out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String inputLine;
      while (!frontend.isDone() && (inputLine = in.readLine()) != null) {
        out.println(frontend.handle(inputLine));
      }  
      amountOfClients.decrease_clientnumber();
      socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
}
