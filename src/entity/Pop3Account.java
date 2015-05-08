package entity;

public class Pop3Account {

  private String _username;
  private String _password;
  private int _serveraddr;
  private int _portnumber;
  
  public Pop3Account(String name, String password, int serveraddr, int portnumber) {
    set_username(name);
    set_password(password);
    set_serveraddr(serveraddr);
    set_portnumber(portnumber);
  }

  public String get_username() {
    return _username;
  }

  public void set_username(String _username) {
    this._username = _username;
  }

  public String get_password() {
    return _password;
  }

  public void set_password(String _password) {
    this._password = _password;
  }

  public int get_serveraddr() {
    return _serveraddr;
  }

  public void set_serveraddr(int _serveraddr) {
    this._serveraddr = _serveraddr;
  }

  public int get_portnumber() {
    return _portnumber;
  }

  public void set_portnumber(int _portnumber) {
    this._portnumber = _portnumber;
  }
  
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_password == null) ? 0 : _password.hashCode());
    result = prime * result + _portnumber;
    result = prime * result + _serveraddr;
    result = prime * result + ((_username == null) ? 0 : _username.hashCode());
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
    if (_password == null) {
      if (other._password != null)
        return false;
    } else if (!_password.equals(other._password))
      return false;
    if (_portnumber != other._portnumber)
      return false;
    if (_serveraddr != other._serveraddr)
      return false;
    if (_username == null) {
      if (other._username != null)
        return false;
    } else if (!_username.equals(other._username))
      return false;
    return true;
  }

  
  
}
