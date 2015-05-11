package entity;

public class NumberOfActiveClientsHandler {
  int number_of_clients;
  int max_number_of_clients=Integer.MAX_VALUE;
  
  public NumberOfActiveClientsHandler() {
    number_of_clients=0;
  }
  
  public void increase_clientnumber() {
    number_of_clients++;
  }
  
  public void decrease_clientnumber() {
    number_of_clients--;
  }
  
  public boolean max_clients_reached() {
    boolean result = true;
    if (number_of_clients < max_number_of_clients) {
      result=false;
    }    
    return result;
  }
  
  public void set_max_number_of_clients(int i) {
    this.max_number_of_clients = i;
  }
}
