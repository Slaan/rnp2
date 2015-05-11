package gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainWindowUI {
  
  private JFrame mainframe;
  private Container container;
  private JButton stopButton;
  private JButton saveButton;
  private JButton accountButton;
  private JLabel timeLabel;
  private JTextField timeTextField;
  private JLabel clientLabel;
  private JTextField clientTextField;
  
  public MainWindowUI() {
    mainframe = new JFrame("POP3Server");
    container = mainframe.getContentPane();
    stopButton = new JButton("Stop");
    saveButton = new JButton("Save");
    accountButton = new JButton("add User");
    timeLabel = new JLabel("Time in Sec");
    timeTextField = new JTextField(50);
    clientLabel = new JLabel("Max Clienits");
    clientTextField = new JTextField(10);
    
    mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    container.setLayout(new GridLayout(0, 2));
    container.add(timeLabel);
    container.add(timeTextField);
    container.add(clientLabel);
    container.add(clientTextField);
    container.add(new JLabel("Add Accounts"));
    container.add(accountButton);
    container.add(saveButton);
    container.add(stopButton);
  }
  
  public void showWindow() {
    mainframe.setSize(400, 150);
    mainframe.setVisible(true);
  }
  
  public void closeWindow() {
    mainframe.dispose();
  }
  
  public JButton getAccountButton() {
    return accountButton;
  }

  public void setAccountButton(JButton accountButton) {
    this.accountButton = accountButton;
  }

  public JButton getStopButton() {
    return stopButton;
  }

  public JButton getSaveButton() {
    return saveButton;
  }

  public JTextField getTimeTextField() {
    return timeTextField;
  }

  public JTextField getClientTextField() {
    return clientTextField;
  }
}
