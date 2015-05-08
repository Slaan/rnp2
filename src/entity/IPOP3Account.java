package entity;

public interface IPOP3Account {
  
  public String getUsername();
  
  public String getPassword();
  
  public int getMailDropsize();
  
  public int getAmountOfEmails();
  
  public String getServer();
  
  public int getPort();
  
  public void update();
  
  public int getAllMailUIDS();
  
  public long getMailUidByID(int id);
  
  public void addEmailToDelete(int id); 
}
