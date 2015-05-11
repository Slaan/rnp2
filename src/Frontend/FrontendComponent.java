package Frontend;

import java.io.FileNotFoundException;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import entity.EmailTyp;
import persistenceComponent.IPersistenceService;
import pop3AccountComponent.IPOP3Account;
import pop3AccountComponent.IPOP3AccountService;

public class FrontendComponent implements IFrontend {

  
  private IPOP3AccountService   accountService = null;
  private IPersistenceService   filehandler = null;

  private IPOP3Account          currentAccount = null;
  private POP3FrontendParser    parser = null;
  private String                response = "";
  private boolean               authenticated = false;
  private boolean               done = false;
  
  public FrontendComponent(IPOP3AccountService accountService, IPersistenceService persistence) {
    this.parser = new POP3FrontendParser();
    this.accountService = accountService;
    this.filehandler = persistence;
  }
  
  public boolean isDone() {
    return done;
  }
  
  @Override
  public String handle(String message) {
    // CASE 1: PASS USER
    if (!authenticated
        && currentAccount == null
        && matchuser(message)) {
      return response;
    }
    // CASE 2: PASS PASS
    if (!authenticated
        && currentAccount != null
        && matchpass(message)) {
      return response;
    }
    // CASE 3: PASS CMDS
    if (authenticated
       && !matchnoop(message)
       && !matchrset(message)
       && !matchstat(message)
       && !matchlist(message)
       && !matchuidl(message)
       && !matchretr(message)
       && !matchdele(message)
       && !matchquit(message)){
      this.response = "-ERR NO MATCHING COMMAND";
    }
    return response;
  }
  
  /**
   * @return found POP3Account, when found. Else null.
   */
  private IPOP3Account findAccountWithUsername(String name) {
    for (IPOP3Account account : accountService.getAllPOP3Accounts()) {
      if (account.getUsername().equals(name)) {
        return account;
      }
    }
    return null;
  }
  
  private boolean matchuser(String str) {
    try {
      String name = parser.user(str);
      IPOP3Account account = findAccountWithUsername(name);
      if (account == null) {
        this.response = "-ERR";
        return true;
      }
      currentAccount = account;
      this.response = "+OK SEND PASS";
      return true;
    } catch (InvalidAttributesException e) {
      return false;
    }
  }
  
  private boolean matchpass(String str) {
    try {
      String pass = parser.pass(str);
      if (currentAccount.getPassword().equals(pass)) {
        this.authenticated = true;
        this.response = "+OK AUTHENTICATED";
        System.out.println("FRONTEND: " + currentAccount.getUsername() + " authenticated.");
        return true; 
      }
      this.done = true;
      this.response = "-ERR PASS: INCORRECT";
      return true;
    } catch (InvalidAttributesException e) {
      return false; 
    }
  }
  
  private boolean matchnoop(String str) {
    try {
      parser.noop(str);
      this.response = "+OK NOOP";
      return true;
    } catch(InvalidAttributesException e) {
      return false;
    }
  }
  
  private boolean matchlist(String str) {
    try {
      String numberAsString = parser.list(str);
      if (numberAsString.equals("")) {
        // return all mails
        List<String> uids = currentAccount.getAllMailUIDS();
        this.response = "+OK " + uids.size() + " messages (" 
        + currentAccount.getMailDropsize() + " octets)\n";
        int i = 1;
        for (String uid : uids) {
          EmailTyp email = filehandler.load(currentAccount, uid);
          this.response += i + " " + email.get_bytesize() + "\n"; 
          i++;
        }
        this.response += ".";
        return true;
      }
      int mailnumber = Integer.parseInt(numberAsString);
      String uid = currentAccount.getMailUidByID(mailnumber);
      EmailTyp email = filehandler.load(this.currentAccount, uid);
      this.response += "+OK " + mailnumber + " " + email.get_bytesize();
      return true;
    } catch (InvalidAttributesException e) {
      return false;
    } catch (FileNotFoundException e) {
      this.response = "-ERR INTERNAL SERVER ERROR!";
      return true;
    }
  }
 
  private boolean matchuidl(String str) {
    try {
      String idAsString = parser.uidl(str);
      if (idAsString.equals("")) {
        this.response = "+OK \n";
        int counter = 1;
        for (String uid : currentAccount.getAllMailUIDS()) {
          this.response += counter + " " + uid + "\n";
        }
        this.response += ".";
        return true;
      }
      int id = Integer.parseInt(idAsString); 
      String uid = currentAccount.getMailUidByID(id);
      this.response = "+OK " + id + " " + uid;
      return true;
    } catch (NumberFormatException e) {
      this.response = "-ERR COULD NOT READ ID";
      return true;
    } catch (InvalidAttributesException e) {
      return false;
    }
  }
  
  private boolean matchrset(String str) {
    try {
      parser.retr(str);
      currentAccount.handleRset();
      Integer mailDropSize = currentAccount.getAllMailUIDS().size();
      this.response = "+OK maildrop has " + mailDropSize + " messages"
          + " (" + currentAccount.getMailDropsize() + " octets)";
    } catch (InvalidAttributesException e) {
      return false;      
    }
    return true;
  }
  
  private boolean matchdele(String str) {
    try {
      String idAsString = parser.dele(str);
      int id = Integer.parseInt(idAsString);
      currentAccount.addEmailToDelete(id);
      this.response = "+OK MESSAGE " + id + " MARKED TO DELETE";
    } catch (NumberFormatException e) {
      this.response = "-ERR COULD NOT READ ID";
    } catch (InvalidAttributesException e) {      
      return false;
    }
    return true;
  }
  
  private boolean matchretr(String str) {
    try {
      String idAsString = parser.retr(str);
      int id = Integer.parseInt(idAsString);
      String uid = currentAccount.getMailUidByID(id);
      EmailTyp email = filehandler.load(currentAccount, uid);
      this.response  = "+OK " + email.get_bytesize() + " octets\n";
      this.response += email.get_content();
    } catch (FileNotFoundException e) {
      this.response = "-ERR INTERNAL SERVER ERROR";
    } catch (NumberFormatException e) {
      this.response = "-ERR COULD NOT READ ID";
    } catch (InvalidAttributesException e) {
      return false;
    }
    return true;
  }
  
  private boolean matchstat(String str) {
    try {
      parser.stat(str);
      List<String> uids = currentAccount.getAllMailUIDS();
      this.response = "+OK " + uids.size() + " messages (" 
          + currentAccount.getMailDropsize() + " octets)";
      return true;
    } catch (InvalidAttributesException e) {
      return false;
    }
  }

  private boolean matchquit(String str) {
    try {
      parser.quit(str);
      System.out.println("FRONTEND: " + currentAccount.getUsername() + " quit.");
      this.response = "+OK BYE BYE :)";
      currentAccount.handleQuit();
      this.done = true;
      return true;
    } catch (InvalidAttributesException e) {
      return false;
    }
  }
}
