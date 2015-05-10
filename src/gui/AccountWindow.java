package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pop3AccountComponent.IPOP3Account;
import pop3AccountComponent.IPOP3AccountService;
import pop3AccountComponent.POP3Account;

public class AccountWindow {

  private IPOP3AccountService accountServices;
  private AccountWindowUI accountUI;
  
  public AccountWindow(IPOP3AccountService as) {
    accountServices = as;
    accountUI = new AccountWindowUI();
    registerUIActions();
    accountUI.showWindow();
  }

  private void registerUIActions() {
    accountUI.getCancleButton().addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        accountUI.closeWindow();        
      }
    });
    accountUI.getSaveButton().addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        addNewAccount();
        accountUI.closeWindow();
      }
    });
  }

  protected void addNewAccount() {
    String username = accountUI.getNameTextField().getText();
    String passwort = accountUI.getPassTextField().getText();
    String server = accountUI.getServerTextField().getText();
    int port = Integer.valueOf(accountUI.getPortTextField().getText());
    IPOP3Account account = new POP3Account(username, passwort, server, port);
    accountServices.addPOP3Account(account);
  }
  
  
}
