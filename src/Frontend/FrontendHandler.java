package Frontend;

import java.util.Set;

import javax.naming.directory.InvalidAttributesException;

import entity.POP3Account;

public class FrontendHandler implements IFrontend {

  // TODO: how do i get all accounts?
  private Set<POP3Account>      allAccounts;

  private POP3Account           currentAccount = null;
  private POP3FrontendParser    parser;
  private String                response;
  
  @Override
  public void setAccounts(Set<POP3Account> account) {
    this.allAccounts = account;
  }

  @Override
  public String handle(String message) {
    if (!matchuser(message)
       && !matchpass(message)
       && !matchnoop(message)) {
      this.response = "-ERR NO MATCHING COMMAND";
    }
    return response;
  }
  
  /**
   * @return found POP3Account, when found. Else null.
   */
  private POP3Account findAccountWithUsername(String name) {
    for (POP3Account account : allAccounts) {
      if (account.getUsername().equals(name)) {
        return account;
      }
    }
    return null;
  }
  
  private boolean matchuser(String str) {
    try {
      String name = parser.user(str);
      POP3Account account = findAccountWithUsername(name);
      if (account == null) {
        this.response = "-ERR\n";
        return true;
      }
      currentAccount = account;
      this.response = "+OK SEND PASS\n";
      return true;
    } catch (InvalidAttributesException e) {
      return false;
    }
  }
  
  private boolean matchpass(String str) {
    try {
      String pass = parser.pass(str);
      if (currentAccount.getPassword().equals(pass)) {
        this.response = "+OK AUTHENTICATED\n";
        return true; 
      }
      this.response = "-ERR PASS: INCORRECT \n";
      return true;
    } catch (InvalidAttributesException e) {
      return false; 
    }
   }
  
  private boolean matchnoop(String str) {
    try {
      parser.noop(str);
      this.response = "+OK NOOB\n";
      return true;
    } catch(InvalidAttributesException e) {
      return false;
    }
  }

}
