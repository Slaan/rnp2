package persistenceComponent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pop3AccountComponent.IPOP3Account;
import entity.EmailTyp;

public class PersistenceService implements IPersistenceService {

  private String foldername = "maildump/";
  
  @Override
  public EmailTyp load(IPOP3Account account, String uid) throws FileNotFoundException {
    String filename = createFilename(account, uid);
    File dir = new File(foldername);
    File emailfile = new File(dir, filename);
    if (!emailfile.exists()) {
      throw new FileNotFoundException("Email for UID " + uid + " not found");
    }
    String content;
    Scanner scanner = new Scanner(emailfile,"UTF-8");
    content = scanner.useDelimiter("\\Z").next();
    scanner.close();
    String[] data = content.split("\\:");
    String emailcontent = data[2];
    String emailuid = data[0];
    int emailsize = Integer.valueOf(data[1]);
    EmailTyp result = new EmailTyp(emailcontent, emailuid, emailsize);
    return result;
  }

  @Override
  public boolean store(IPOP3Account account, EmailTyp email) throws IOException {
    String filename = createFilename(account, email.get_uid());
    File dir = new File(foldername);
    dir.mkdirs();
    File emailfile = new File(dir, filename);
    emailfile.createNewFile();
    PrintWriter write = new PrintWriter(emailfile, "UTF-8");
    String to_write = "" + email.get_uid() + ":" + email.get_bytesize() + ":" 
        + email.get_content();
    write.print(to_write);
    write.close();
    return false;
  }

  @Override
  public List<Integer> getSizes(IPOP3Account account) throws FileNotFoundException {
    List<Integer> result = new ArrayList<>();
    for (String uid : account.getAllMailUIDS()) {
      System.out.println("uid");
      EmailTyp email = load(account, uid);
      System.out.println(""+email.get_bytesize());
      result.add(email.get_bytesize());
    }
    return result;
  }

  @Override
  public void delete(IPOP3Account account, String uid) throws FileNotFoundException{
    String filename = createFilename(account, uid);
    File dir = new File(foldername);
    File emailfile = new File(dir, filename);
    if (!emailfile.exists()) {
      throw new FileNotFoundException();
    }
    emailfile.delete();
  }
  
  private String createFilename(IPOP3Account account, String uid) {
    String filename = "" + account.getServer() + "." + account.getUsername() + 
        "." + uid;
    return filename;
  }

}
