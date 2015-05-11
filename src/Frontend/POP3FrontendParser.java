package Frontend;

import javax.naming.directory.InvalidAttributesException;

public class POP3FrontendParser {
  
  public final String UserCMD = "USER";
  public final String PassCMD = "PASS";
  public final String StatCMD = "STAT";
  public final String ListCMD = "LIST";
  public final String RetrCMD = "RETR";
  public final String DeleCMD = "DELE";
  public final String NoopCMD = "NOOP";
  public final String RsetCMD = "RSET";
  public final String UidlCMD = "UIDL";
  public final String QuitCMD = "QUIT";
  
  public String getSecondWord(String rawStr) {
    int firstCharPos = 5;
    // find first digit
    while (firstCharPos < rawStr.length() && rawStr.charAt(firstCharPos) == ' ') {
      firstCharPos++;
    }
    // find end of space;
    int lastCharPos = firstCharPos;
    while (lastCharPos < rawStr.length() && rawStr.charAt(lastCharPos) != ' ') {
      lastCharPos++;
    }
    return rawStr.substring(firstCharPos, lastCharPos);
  }
  
  public String handleWithSecondWord(String cmd, String rawStr) throws InvalidAttributesException {
    if (rawStr.matches(cmd + "(\\s(\\d)+)+")) {
        String substring = getSecondWord(rawStr);
        // ensure parsed string is integer
        Integer.parseInt(substring);
        return substring;
    }
    throw new InvalidAttributesException();
  }
  
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
    if (!rawStr.matches(UserCMD + "\\s(\\w)+")) {
      throw new InvalidAttributesException();
    }
    return rawStr.substring(5);
  }
  
  public String pass(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (rawStr.length() < 7) {
      // just check the minimum length as one sign
      throw new InvalidAttributesException();
    }
    return rawStr.substring(5);
  }
  
  public void stat(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (!rawStr.matches(StatCMD + "\\s*")) {
      throw new InvalidAttributesException();
    }
  }
  
  public String list(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (rawStr.matches(ListCMD + "(\\s)*")) {
      return "";
    }
    return handleWithSecondWord(ListCMD, rawStr);
  }
  
  public String retr(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    return handleWithSecondWord(RetrCMD, rawStr);
  }
  
  public String dele(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    return handleWithSecondWord(DeleCMD, rawStr);
  }
  
  public void noop(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (!rawStr.matches(NoopCMD + "(\\s)*")) {
      throw new InvalidAttributesException();
    }
  }
  
  public void rset(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (!rawStr.matches(RsetCMD + "(\\s)*")) {
      throw new InvalidAttributesException();
    }
  }
  
  public String uidl(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (rawStr.matches(UidlCMD + "(\\s)*")) {
      return "";
    }
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    return handleWithSecondWord(UidlCMD, rawStr);
  }
  
  public void quit(String rawStr) throws InvalidAttributesException {
    if (rawStr == null || rawStr.isEmpty()) {
      throw new InvalidAttributesException();
    }
    if (!rawStr.matches(QuitCMD + "(\\s)*")) {
      throw new InvalidAttributesException();
    }
  }
}
