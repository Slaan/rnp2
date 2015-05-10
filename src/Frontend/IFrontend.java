package Frontend;

import java.util.Set;

import entity.POP3Account;

public interface IFrontend {

  /**
   * 
   * @param account
   */
  public void setAccounts(Set<POP3Account> accounts);
  
  public String handle(String message);
  
}
