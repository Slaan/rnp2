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
  
  public void update();
  
  public void addEmail(EmailTyp email);
  
  public List<String> getAllMailUIDS();
  
  public long getMailUidByID(int id);
  
  public void addEmailToDelete(int id); 
}
