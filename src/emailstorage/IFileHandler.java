package emailstorage;

import java.util.List;

import entity.EmailTyp;
import entity.POP3Account;
import entity.UIDTyp;

public interface IFileHandler {

  public EmailTyp load(POP3Account account, UIDTyp uid);
  
  public boolean store(POP3Account account, EmailTyp email);
  
  public List<Integer> getSizes(POP3Account account);
  
  public boolean delete(POP3Account account, UIDTyp uid);
}
