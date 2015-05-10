package entity;

public class EmailTyp {
  
  private String uid;
  private String content;
  private int bytesize;
  
  public EmailTyp(String content, String uid, int bytesize) {
    this.content = content;
    this.uid = uid;
    this.bytesize = bytesize;
  }
  
  public String get_uid() {
    return uid;
  }
  
  public String get_content() {
    return content;
  }
  
  public int get_bytesize() {
    return bytesize;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + bytesize;
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + ((uid == null) ? 0 : uid.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    EmailTyp other = (EmailTyp) obj;
    if (bytesize != other.bytesize)
      return false;
    if (content == null) {
      if (other.content != null)
        return false;
    } else if (!content.equals(other.content))
      return false;
    if (uid == null) {
      if (other.uid != null)
        return false;
    } else if (!uid.equals(other.uid))
      return false;
    return true;
  }

}
