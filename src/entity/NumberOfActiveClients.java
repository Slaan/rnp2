package entity;

public class NumberOfActiveClients {
  int number_of_clients;
  public NumberOfActiveClients() {
    number_of_clients=0;
  }
  
  public void increase_clientnumber() {
    number_of_clients++;
  }
  
  public void decrease_clientnumber() {
    number_of_clients--;
  }
  public int get_number_of_clients() {
    return number_of_clients;
  }
}
