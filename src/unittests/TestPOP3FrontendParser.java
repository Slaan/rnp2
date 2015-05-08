package unittests;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.naming.directory.InvalidAttributesException;

import org.junit.Test;

import server.POP3FrontendParser;

public class TestPOP3FrontendParser {

  @Test
  public void testUserCMDSuccessfull_positive() {
    String validStr = "USER ADAM";
    String username = "";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      username = p.user(validStr);
    } catch (InvalidAttributesException e) {
      fail("Unexpected Exception thrown.");
    }
    assertEquals("ADAM", username);
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testUserCMDMoreThenOneSpace_negative() throws InvalidAttributesException {
    String invalidStr = "USER ADAM JOHAN";
    POP3FrontendParser p = new POP3FrontendParser();
    p.user(invalidStr);
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testUserCMDEmptyString_negative() throws InvalidAttributesException {
    String invalidStr = "USER ";
    POP3FrontendParser p = new POP3FrontendParser();
    p.user(invalidStr);
  }
  
  @Test
  public void testPassCMDSuccessfull_positive() {
    String validStr1 = "PASS ADAM";
    String validStr2 = "PASS ADAM ASasdf";
    String username = "";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      username = p.user(validStr1);
    } catch (InvalidAttributesException e) {
      fail("Unexpected Exception thrown.");
    }
    assertEquals("ADAM", username);
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testPassCMDEmptyString_negative() throws InvalidAttributesException {
    String invalidStr = "PASS ";
    POP3FrontendParser p = new POP3FrontendParser();
    p.user(invalidStr);
  }

  @Test
  public void testStatCMDSuccessfull() {
    String validStr1 = "STAT";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      p.stat(validStr1);
    } catch (InvalidAttributesException e) {
      fail("Unexpected Exception thrown");
    }
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testStatCMDUnsuccessfull() throws InvalidAttributesException {
    String validStr = "STATS ";
    POP3FrontendParser p = new POP3FrontendParser();
    p.stat(validStr);
  }
  
  @Test
  public void testListCMDSuccessfull() {
    String validStr1 = "LIST";
    String validStr2 = "LIST 1";
    String validStr3 = "LIST 1 69";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      String a = p.list(validStr1);
      assertEquals("", a);
      String b = p.list(validStr2);
      assertEquals("1", b);
      String c = p.list(validStr3);
      assertEquals("1", c);
    } catch (InvalidAttributesException e) {
      fail("Unexpected Exception thrown");
    }
  }
  
  @Test
  public void testListCMDNotSuccessfull() throws InvalidAttributesException {
    String validStr = "LIST as";
    POP3FrontendParser p = new POP3FrontendParser();
    String a = p.list(validStr);
  }
  
  @Test
  public void testRetrCMDSuccessfull() {
    
  }
}
