package blackboxtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistenceComponent.IPersistenceService;
import persistenceComponent.PersistenceService;
import pop3AccountComponent.IPOP3Account;
import pop3AccountComponent.POP3Account;
import entity.EmailTyp;

public class TestPersistenceService {

  private IPOP3Account account;
  private EmailTyp mail1;
  private EmailTyp mail2;
  private EmailTyp delmail;
  private IPersistenceService persi;
  
  @Before
  public void initialize() {
    account = new POP3Account("hans", "hi", "0.0.0.0", 1337);
    mail1 = new EmailTyp("hallo dies ist ein test l???", "123", 85);
    persi = new PersistenceService();
    mail2 = new EmailTyp("test1", "131", 15);
    delmail = new EmailTyp("to delete", "151", 24);
    account.addEmail(mail1);
    account.addEmail(mail2);
    try {
      persi.store(account, mail1);
      persi.store(account, mail2);
      persi.store(account, delmail);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test
  public void testStore() {
    String filename = "" + account.getServer() + "." + account.getUsername() + 
        "." + mail1.get_uid();
    File dir = new File("maildump/");
    File emailfile = new File(dir, filename);
    assertTrue(emailfile.exists());
  }
  
  @Test 
  public void testgetSizes() {
    List<Integer> sizes = null;
    int actual=0;
    try {
      sizes = persi.getSizes(account);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    for (int i : sizes) {
      System.out.println(actual);
      actual += i;
    }
    int expected = mail1.get_bytesize() + mail2.get_bytesize();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testLoad() {
    EmailTyp test = null;
    try {
      test = persi.load(account, mail1.get_uid());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals(mail1, test);
  }
  
  @Test 
  public void testDelete() {
    try {
      persi.delete(account, "151");
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String filename = "" + account.getServer() + "." + account.getUsername() + 
        "." + delmail.get_uid();
    File dir = new File("maildump/");
    File emailfile = new File(dir, filename);
    assertFalse(emailfile.exists());
  }
  
  @After
  public void cleanup() {
    try {
      persi.delete(account, mail1.get_uid());
      persi.delete(account, mail2.get_uid());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
