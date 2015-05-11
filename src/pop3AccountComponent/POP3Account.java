package pop3AccountComponent;

import java.util.ArrayList;
import java.util.List;

import entity.EmailTyp;


public class POP3Account implements IPOP3Account {

  private String username;
  private String password;
  private String server;
  private int port;
  private int amountofEmail;
  private List<String> uids;
  private int mailDropsize;
  
  
  public POP3Account(String name, String pass, String server, int port) {
    this.username = name;
    this.password = pass;
    this.server = server;
    this.port = port;
    this.amountofEmail = 0;
    this.mailDropsize = 0;
    this.uids = new ArrayList<String>();
  }
  
  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public int getMailDropsize() {
    return mailDropsize;
  }

  @Override
  public int getAmountOfEmails() {
    return amountofEmail;
  }

  @Override
  public String getServer() {
    return server;
  }

  @Override
  public int getPort() {
    return port;
  }

  @Override
  public void handleQuit() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<String> getAllMailUIDS() {
    return uids;
  }

  @Override
  public String getMailUidByID(int id) {
    return this.uids.get(id-1);
  }

  @Override
  public void addEmailToDelete(int id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addEmail(EmailTyp email) {
    this.amountofEmail++;
    this.getAllMailUIDS().add(email.get_uid());
    this.mailDropsize += email.get_bytesize();
  }

  @Override
  public void handleRset() {
    // TODO Auto-generated method stub
    
  }

}
