package pop3AccountComponent;

import java.util.List;

import entity.EmailTyp;

public interface IPOP3Account {
  
  public String getUsername();
  
  public String getPassword();
  
  public int getMailDropsize();
  
  public int getAmountOfEmails();
  
  public String getServer();
  
  public int getPort();
  
  public void handleQuit();
  
  public void handleRset();
  
  public void addEmail(EmailTyp email);
  
  public List<String> getAllMailUIDS();
  
  public String getMailUidByID(int id);
  
  public void addEmailToDelete(int id); 
}
