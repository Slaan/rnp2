package gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;

public class AccountWindowUI {

  private JFrame accountFrame;
  private Container container;
  private JLabel nameLabel;
  private JTextField nameTextField;
  private JLabel passLabel;
  private JTextField passTextField;
  private JLabel serverLabel;
  private JTextField serverTextField;
  private JLabel portLabel;
  private JTextField portTextField;
  private JButton saveButton;
  private JButton cancleButton;
  
  public AccountWindowUI() {
    accountFrame = new JFrame("Neuer Account");
    container = accountFrame.getContentPane();
    nameLabel = new JLabel("Username");
    nameTextField = new JTextField(100);
    passLabel = new JLabel("Passwort");
    passTextField = new JTextField(100);
    serverLabel = new JLabel("Server IP");
    serverTextField = new JTextField(100);
    portLabel = new JLabel("Portnummer");
    portTextField = new JTextField(100);
    saveButton = new JButton("Save");
    cancleButton = new JButton("Cancle");
    
    accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    container.setLayout(new GridLayout(0, 2));
    
    container.add(nameLabel);
    container.add(nameTextField);
    container.add(passLabel);
    container.add(passTextField);
    container.add(serverLabel);
    container.add(serverTextField);
    container.add(portLabel);
    container.add(portTextField);
    container.add(cancleButton);
    container.add(saveButton);
  }
  
  public void showWindow() {
    accountFrame.setSize(400, 150);
    accountFrame.setVisible(true);
  }
  
  public void closeWindow() {
    accountFrame.dispose();
  }

  public JTextField getNameTextField() {
    return nameTextField;
  }

  public JTextField getPassTextField() {
    return passTextField;
  }

  public JLabel getServerLabel() {
    return serverLabel;
  }

  public JTextField getServerTextField() {
    return serverTextField;
  }

  public JTextField getPortTextField() {
    return portTextField;
  }

  public JButton getSaveButton() {
    return saveButton;
  }

  public JButton getCancleButton() {
    return cancleButton;
  }
  
  
}
