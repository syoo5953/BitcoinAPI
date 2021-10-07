package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame implements View {
  Container container = getContentPane();
  JLabel tokenLabel = new JLabel("CoinMarketApp API TOKEN");
  JLabel tokenLabel2 = new JLabel("Pastebin API TOKEN");
  JPasswordField tokenField = new JPasswordField();
  JPasswordField tokenField2 = new JPasswordField();
  JButton loginButton = new JButton("LOGIN");
  JButton resetButton = new JButton("RESET");
  JButton registerButton = new JButton("REGISTER");
  JCheckBox showPassword = new JCheckBox("Show Token");
  JCheckBox showPassword2 = new JCheckBox("Show Token");
  JFrame registerPage = new JFrame("Register Page");
  Model model;
  JFrame tempFrame = new JFrame("API key");
  JFrame tempFrame2 = new JFrame("API key");
  JButton inputButton = new JButton("CoinMarketCap API key");
  JButton outputButton = new JButton("Pastebin API key");
  JLabel hyperlink = new JLabel("Register Link Page");

  public LoginView(Model model) {
    this.model = model;
    setLayoutManager();
    setLocationAndSize();
    addComponentsToContainer();
  }

  public void run() {
    this.setTitle("Login Form");
    this.setVisible(true);
    this.setBounds(10, 10, 460, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
  }

  public void setLayoutManager() {
    container.setLayout(null);
  }

  public void setLocationAndSize() {
    tokenLabel.setBounds(40, 130, 200, 30);
    tokenField.setBounds(220, 130, 150, 30);
    tokenLabel2.setBounds(40, 220, 200, 30);
    tokenField2.setBounds(220, 220, 150, 30);
    showPassword.setBounds(170, 160, 150, 30);
    showPassword2.setBounds(170, 250, 150, 30);
    loginButton.setBounds(95, 320, 100, 30);
    resetButton.setBounds(245, 320, 100, 30);
    registerButton.setBounds(170, 370, 100, 30);
    inputButton.setBounds(30, 30, 300, 30);
    outputButton.setBounds(30, 80, 300, 30);
  }

  public void addComponentsToContainer() {
    container.add(tokenLabel);
    container.add(tokenField);
    container.add(tokenLabel2);
    container.add(tokenField2);
    container.add(showPassword);
    container.add(showPassword2);
    container.add(loginButton);
    container.add(resetButton);
    container.add(registerButton);
  }

  public void reset() {
    tokenField.setText("");
    tokenField2.setText("");
  }

  public JPasswordField getTokenField() {
    return this.tokenField;
  }

  public JPasswordField getSecondTokenField() {
    return this.tokenField2;
  }

  public JButton getLoginButton() {
    return this.loginButton;
  }

  public JButton getResetButton() {
    return this.resetButton;
  }

  public JButton getRegisterButton() {
    return this.registerButton;
  }

  public JCheckBox getPasswordButton() {
    return this.showPassword;
  }

  public JCheckBox getSecondPasswordButton() {
    return this.showPassword2;
  }

  public JButton getInputButton() {
    return this.inputButton;
  }

  public JButton getOutputButton() {
    return this.outputButton;
  }

  public JFrame getRegisterPage() {
    return this.registerPage;
  }

  public void registerDisplay() {
    registerPage.setSize(375, 170);
    registerPage.setLayout(null);
    registerPage.setTitle("Register Page");
    registerPage.setVisible(true);
    registerPage.add(inputButton);
    registerPage.add(outputButton);
    registerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    registerPage.setResizable(false);
  }

  public JLabel registerOnline() {
    tempFrame.setSize(300, 150);
    tempFrame.setLayout(null);
    tempFrame.setVisible(true);
    hyperlink.setForeground(Color.BLUE.darker());
    hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
    hyperlink.setBounds(85, 5, 400, 100);
    tempFrame.add(hyperlink);
    tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    tempFrame.setResizable(false);
    return hyperlink;
  }

  public JButton registerOffline() {
    tempFrame2.setSize(500, 150);
    tempFrame2.setLayout(null);
    tempFrame2.setVisible(true);
    JLabel offlineLabel =
        new JLabel("Please use the following API key: 53240032-0bea-482c-9273-f346c3f17632");
    offlineLabel.setBounds(40, 0, 410, 100);
    JButton copy = new JButton("Copy the key");
    copy.setBounds(300, 70, 110, 30);
    tempFrame2.add(offlineLabel);
    tempFrame2.add(copy);
    tempFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    tempFrame2.setResizable(false);
    return copy;
  }

  public JFrame getSecondTempFrame() {
    return this.tempFrame2;
  }
}
