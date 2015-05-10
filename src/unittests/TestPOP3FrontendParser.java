package unittests;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.naming.directory.InvalidAttributesException;

import org.junit.Test;

import Frontend.POP3FrontendParser;

public class TestPOP3FrontendParser {

  final String UNEXPECTED_EXCEPTION = "Unexpected Exception thrown"; 
  
  @Test
  public void testGetSecondWord() {
    String str = "LIST 5";
    POP3FrontendParser p = new POP3FrontendParser();
    String res = p.getSecondWord(str);
    assertEquals("5", res);
  }
  
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
      username = p.pass(validStr1);
      assertEquals("ADAM", username);
      username = p.pass(validStr2);
      assertEquals("ADAM ASasdf", username);
    } catch (InvalidAttributesException e) {
      fail("Unexpected Exception thrown.");
    }
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
//      String c = p.list(validStr3);
//      assertEquals("1", c);
    } catch (InvalidAttributesException e) {
      fail("Unexpected Exception thrown");
    }
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testListCMDNotSuccessfull() throws InvalidAttributesException {
    String validStr = "LIST as";
    POP3FrontendParser p = new POP3FrontendParser();
    String a = p.list(validStr);
  }
  
  @Test
  public void testRetrCMDSuccessfull() {
    String validStr1 = "RETR 1";
    String validStr2 = "RETR 1 69";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      String b = p.retr(validStr1);
      assertEquals("1", b);
      String c = p.retr(validStr2);
      assertEquals("1", c);
    } catch (InvalidAttributesException e) {
      fail("Unexpected Exception thrown");
    }
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testRetrCMDCharsAsNumberNotSuccessfull() throws InvalidAttributesException {
    String validStr = "RETR as";
    POP3FrontendParser p = new POP3FrontendParser();
    String a = p.retr(validStr);
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testRetrCMDMissingNumber() throws InvalidAttributesException {
    String validStr = "RETR ";
    POP3FrontendParser p = new POP3FrontendParser();
    String a = p.retr(validStr);
  }
  
  @Test
  public void testDeleCMDSuccessfull() {
    String validStr1 = "DELE 1";
    String validStr2 = "DELE 1 69";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      String b = p.dele(validStr1);
      assertEquals("1", b);
      String c = p.dele(validStr2);
      assertEquals("1", c);
    } catch (InvalidAttributesException e) {
      fail(UNEXPECTED_EXCEPTION);
    }
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testDeleCMDCharsAsNumberNotSuccessfull() throws InvalidAttributesException {
    String validStr = "DELE as";
    POP3FrontendParser p = new POP3FrontendParser();
    p.dele(validStr);
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testDeleCMDMissingNumber() throws InvalidAttributesException {
    String validStr = "DELE ";
    POP3FrontendParser p = new POP3FrontendParser();
    p.dele(validStr);
  }
  
  @Test
  public void testNoopCMDSuccessfull() {
    String validStr1 = "NOOP";
    String validStr2 = "NOOP ";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      p.noop(validStr1);
      p.noop(validStr2);
    } catch (InvalidAttributesException e) {
      fail(UNEXPECTED_EXCEPTION);
    }
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testNoopCMDNotMatchingNotSuccessfull() throws InvalidAttributesException {
    String invalidStr = "BOOB";
    POP3FrontendParser p = new POP3FrontendParser();
    p.noop(invalidStr);
  }
  
  @Test
  public void testRsetCMDSuccessfull() {
    String validStr1 = "RSET";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      p.rset(validStr1);
    } catch (InvalidAttributesException e) {
      fail(UNEXPECTED_EXCEPTION);
    }
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testRsetCMDNotSuccessfull() throws InvalidAttributesException {
    String invalidStr = "RZET";
    POP3FrontendParser p = new POP3FrontendParser();
    p.rset(invalidStr);
  }
  
  @Test
  public void testUidlCMDWithoutNumberSuccessfull() {
    String validStr1 = "UIDL";
    String validStr2 = "UIDL ";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      String a = p.uidl(validStr1);
      assertEquals("", a);
      String b = p.uidl(validStr2);
      assertEquals("", b);      
    } catch (InvalidAttributesException e) {
      fail(UNEXPECTED_EXCEPTION);
    }
  }
  
  @Test
  public void testUidlCMDWithNumberSuccessfull() {
    String validStr1 = "UIDL 1";
    String validStr2 = "UIDL 23 54";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      String a = p.uidl(validStr1);
      assertEquals("1", a);
      String b = p.uidl(validStr2);
      assertEquals("23", b);      
    } catch (InvalidAttributesException e) {
      fail(UNEXPECTED_EXCEPTION);
    }
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testUidlCMDWithCharacterNotSuccessfull() throws InvalidAttributesException {
    String invalidStr = "UIDL asdf";
    POP3FrontendParser p = new POP3FrontendParser();
    p.uidl(invalidStr);
  }
  
  @Test
  public void testQuitCMDSuccessfull() {
    String validStr = "QUIT";
    POP3FrontendParser p = new POP3FrontendParser();
    try {
      p.quit(validStr);
    } catch (InvalidAttributesException e) {
      fail(UNEXPECTED_EXCEPTION);
    }
  }
  
  @Test(expected=InvalidAttributesException.class)
  public void testQuitCMDNotMatching() throws InvalidAttributesException {
    String invalidStr = "TIUQ";
    POP3FrontendParser p = new POP3FrontendParser();
    p.quit(invalidStr);
  }
}
