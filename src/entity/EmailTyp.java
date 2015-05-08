package entity;

public class EmailTyp {
  
  private UIDTyp uid;
  private String content;
  private int bytesize;
  
  public EmailTyp(String content, UIDTyp uid,int bytesize) {
    this.content = content;
    this.uid = uid;
    this.bytesize = bytesize;
  }
  
  private UIDTyp get_uid() {
    return uid;
  }
  
  private String get_content() {
    return content;
  }
  
  private int get_bytesize() {
    return bytesize;
  }

}
