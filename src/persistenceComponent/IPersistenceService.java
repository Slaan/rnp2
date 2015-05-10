package persistenceComponent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import pop3AccountComponent.IPOP3Account;
import entity.EmailTyp;

public interface IPersistenceService {

  public EmailTyp load(IPOP3Account account, String uid) throws FileNotFoundException;
  
  public boolean store(IPOP3Account account, EmailTyp email) throws IOException;
  
  public List<Integer> getSizes(IPOP3Account account) throws FileNotFoundException;
  
  public void delete(IPOP3Account account, String uid) throws FileNotFoundException;
}
