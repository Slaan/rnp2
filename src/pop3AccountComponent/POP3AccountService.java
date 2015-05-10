package pop3AccountComponent;

import java.util.ArrayList;
import java.util.List;

public class POP3AccountService implements IPOP3AccountService {

  private List<IPOP3Account> pop3accounts;
  
  public POP3AccountService() {
    //TODO check if accounts.txt exists
    pop3accounts = new ArrayList<>();
  }
  
  @Override
  public List<IPOP3Account> getAllPOP3Accounts() {
    return pop3accounts;
  }

  @Override
  public boolean addPOP3Account(IPOP3Account account) {
    pop3accounts.add(account);
    return true;
  }

  @Override
  public boolean deletePOP3Account(IPOP3Account account) {
    pop3accounts.remove(account);
    return true;
  }
  
}
