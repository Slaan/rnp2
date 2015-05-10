package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.UUID;

import persistenceComponent.IPersistenceService;
import pop3AccountComponent.IPOP3Account;
import pop3AccountComponent.IPOP3AccountService;
import entity.EmailTyp;

public class POP3Backend extends Thread {

  private IPOP3AccountService accountService;
  private IPersistenceService persistenceService;
  private long sleepmilis = 30000;
  
  public POP3Backend(IPOP3AccountService accountS, IPersistenceService persi) {
    accountService = accountS;
    persistenceService = persi;
  }
  
  public void run() {
    boolean done = false;
    while(!done) {
      List<IPOP3Account> accounts = accountService.getAllPOP3Accounts();
      for (IPOP3Account account : accounts) {
        try {
          Socket socket = new Socket(account.getServer(), account.getPort());
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          out.println("USER " + account.getUsername());
          String userOK = in.readLine();
          if (!checkForOK(userOK)) {
            System.out.println("LogIn information for User " + account.getUsername() 
                + " incorrect");
            socket.close();
            break;
          }
          out.println("PASS " + account.getPassword());
          String passOK = in.readLine();
          if (!checkForOK(passOK)) {
            System.out.println("LogIn information for User " + account.getUsername() 
                + " incorrect");
            socket.close();
            break;
          }
          out.println("STAT");
          String statinfo = in.readLine();
          if (!checkForOK(statinfo)) {
            System.out.println("An Error has occured for User " + account.getUsername() +
                " ,STAT command");
            socket.close();
            break;
          }
          String statarr[] = statinfo.split(" ", 3);
          int message_amount= Integer.valueOf(statarr[1]);
          for (int i=1;i<message_amount+1;i++) {
            out.println("RETR " + i);
            String retrinfo = in.readLine();
            if (!checkForOK(retrinfo)) {
              System.out.println("Could not retrieve message " + i);
            }
            String retrarr[] = retrinfo.split(" ", 3);
            int bytesize = Integer.valueOf(retrarr[1]);
            String content=in.readLine();
            System.out.println(content);
//            String line;
//            while ((line = in.readLine()) != null) {
//              System.out.println(line);
//              content = content + line;
//            }
            UUID uid_created = UUID.randomUUID();
            String uid = uid_created.toString();
            EmailTyp email = new EmailTyp(content, uid, bytesize);
            account.addEmail(email);
            persistenceService.store(account, email);
            out.println("DELE " + i);
            String delinfo = in.readLine(); 
            if (!checkForOK(delinfo)) {
              System.out.println("Could not delete message " + i);
            }
          }
          out.println("QUIT");
          socket.close();
          
        } catch (IOException e) {
          System.out.println("Exception thrown:" + e);
        }
      }
      try {
        sleep(sleepmilis);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  public void setSleepMilis(long time) {
    sleepmilis = time;
    System.out.println(""+sleepmilis);
  }
  
  public boolean checkForOK(String s) {
    boolean result = false;
    String arr[] = s.split(" ", 2);
    if (arr[0].equals("+OK")||s.equals("+OK")) {
      result = true;
    }    
    return result;
  }
}
