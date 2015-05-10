package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pop3AccountComponent.IPOP3AccountService;
import entity.NumberOfActiveClientsHandler;
import backend.POP3Backend;

public class MainWindow {

  private MainWindowUI mainui;
  private POP3Backend backend;
  private IPOP3AccountService accountServices;
  private NumberOfActiveClientsHandler clientHandler;

  
  public MainWindow(POP3Backend back, IPOP3AccountService as, NumberOfActiveClientsHandler ch) {
    backend = back;
    accountServices = as;    
    clientHandler = ch;
    mainui = new MainWindowUI();
    registerUIActions();
    
    mainui.showWindow();
  }

  private void registerUIActions() {
    mainui.getSaveButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        saveChanges();        
      }
    });
    mainui.getStopButton().addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        stopServer();        
      }
    });
    mainui.getAccountButton().addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        new AccountWindow(accountServices);
      }
    });
    

  }

  protected void stopServer() {
    // TODO Auto-generated method stub
  }

  protected void saveChanges() {
    String max_cli = mainui.getClientTextField().getText();
    if (!max_cli.isEmpty()) {
      clientHandler.set_max_number_of_clients(Integer.valueOf
        (max_cli));
    }
    String time = mainui.getTimeTextField().getText();
    if (!time.isEmpty()) {
      long newtime = Long.valueOf(time);
      backend.setSleepMilis(newtime*1000);
    }
  }
}
