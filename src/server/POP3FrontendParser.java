package server;

import javax.naming.directory.InvalidAttributesException;

public class POP3FrontendParser {
  
  private final String UserCMD = "USER";
  private final String PassCMD = "PASS";
  private final String StatCMD = "STAT";
  private final String ListCMD = "LIST";
  private final String RetrCMD = "RETR";
  private final String DeleCMD = "DELE";
  private final String NoopCMD = "NOOP";
  private final String RsetCMD = "RSET";
  private final String UidlCMD = "UIDL";
  private final String QuitCMD = "QUIT";
  
  
  /**
   * Parses username out of given rawstring
   * @param rawStr rawstring
   * @return username
   * @throws InvalidAttributesException when given string is null, empty,
   * doesn't match "USER" or contains more then one space.
   */
  public String user(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (!rawStr.matches(UserCMD + " (w)+")) {
      throw new InvalidAttributesException();
    }
    return rawStr.substring(5);
  }
  
  public String pass(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (!rawStr.matches(PassCMD + " ((w)+(W)*)+")) {
      throw new InvalidAttributesException();
    }
    return rawStr.substring(5);
  }
  
  public void stat(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (!rawStr.matches(StatCMD + " (w)+")) {
      throw new InvalidAttributesException();
    }
  }
  
  public String list(String rawStr) throws InvalidAttributesException {
    return null;
  }
  
  public String retr(String rawStr) throws InvalidAttributesException {
    return null;
  }
  
  public String dele(String rawStr) throws InvalidAttributesException {
    return null;
  }
  
  public String noop(String rawStr) throws InvalidAttributesException {
    return null;
  }
  
  public String reset(String rawStr) throws InvalidAttributesException {
    return null;
  }
  
  public String uidl(String rawStr) throws InvalidAttributesException {
    return null;
  }
  
  public String quit(String rawStr) throws InvalidAttributesException {
    return null;
  }
}
