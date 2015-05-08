package entity;

public class Pop3Account {

  private String username;
  private String password;
  private String serveraddr;
  private int portnumber;
  
  public Pop3Account(String name, String password, String serveraddr, int portnumber) {
    set_username(name);
    set_password(password);
    set_serveraddr(serveraddr);
    set_portnumber(portnumber);
  }

  public String get_username() {
    return username;
  }

  public void set_username(String username) {
    this.username = username;
  }

  public String get_password() {
    return password;  
  }

  public void set_password(String password) {
    this.password = password;
  }

  public String get_serveraddr() {
    return serveraddr;
  }

  public void set_serveraddr(String serveraddr) {
    this.serveraddr = serveraddr;
  }

  public int get_portnumber() {
    return portnumber;
  }

  public void set_portnumber(int portnumber) {
    this.portnumber = portnumber;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + portnumber;
    result = prime * result + ((serveraddr == null) ? 0 : serveraddr.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
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
    Pop3Account other = (Pop3Account) obj;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (portnumber != other.portnumber)
      return false;
    if (serveraddr == null) {
      if (other.serveraddr != null)
        return false;
    } else if (!serveraddr.equals(other.serveraddr))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }
  
  
}
