package frontend;

import java.util.Set;

import pop3AccountComponent.POP3Account;

public interface IFrontend {

  /**
   * 
   * @param account
   */
  public void setAccounts(Set<POP3Account> accounts);
  
  public String handle(String message);
  
}
