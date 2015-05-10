package pop3AccountComponent;

import java.util.List;

public interface IPOP3AccountService {
  
  public List<IPOP3Account> getAllPOP3Accounts();
  
  public boolean addPOP3Account(IPOP3Account account);
  
  public boolean deletePOP3Account(IPOP3Account account);
}
