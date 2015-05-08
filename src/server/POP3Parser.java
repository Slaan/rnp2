package server;

import java.util.Optional;

import javax.naming.directory.InvalidAttributesException;

public class POP3Parser {
  
  private final String UserCMD = "USER";
  private final String PassCMD = "PASS";
  private final String StatCMD = "STAT";
  private final String ListCMD = "LIST";
  
  
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
    if (!rawStr.matches(PassCMD + " (w)+")) {
      throw new InvalidAttributesException();
    }
    return rawStr.substring(5);
  }
  
  public String stat(String stat) {
    // TODO: implement me
    return null;
  }
  
}
