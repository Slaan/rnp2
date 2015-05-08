package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import entity.IPOP3Account;

public class POP3Backend extends Thread {

private Socket socket;
  
  public POP3Backend(Socket socket) {
    super();
    this.socket = socket;
  }
  
  public void run() {
    ArrayList<IPOP3Account> accounts = new ArrayList<IPOP3Account>();
    boolean done = false;
    while(!done) {
      //get list of accounts
      for (IPOP3Account account : accounts) {
        try {
          Socket socket = new Socket(account.getServer(), account.getPort());
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          out.println("USER " + account.getUsername());
          //TODO check return message
          out.println("PASS " + account.getPassword());
          //TODO 
          out.println("STAT");
          int message_amount=0;
          //TODO int message_amount init
          for (int i=1;i<message_amount+1;i++) {
            out.println("RETR " + i);
            //TODO safe email
            out.println("DELE " + i);
          }
          out.println("QUIT");
          socket.close();
          
        } catch (IOException e) {
          System.out.println("Exception thrown:" + e);
        }
        
        
        
        
      }
    }
  }
}
