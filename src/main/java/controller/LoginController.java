package controller;

import model.Model;
import model.UserInfo;
import model.facade.input.InputAPI;
import model.facade.output.OutputAPI;
import view.ExtensionView;
import view.LoginView;
import view.ProcessView;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class LoginController extends JFrame implements Controller {
  static String coinTempVal;
  static String temp;
  private final Model model;
  private final View view;
  private InputAPI inputAPI;
  private OutputAPI outputAPI;
  private LoginView loginView;
  private UserInfo userInfo;
  private final View view1 = new ExtensionView();

  public LoginController(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  public void initController() {
    inputAPI = model.getInputAPIModel();
    outputAPI = model.getOutputAPIModel();
    userInfo = model.getUser();

    ExtensionView extensionView = (ExtensionView) view1;
    extensionView.run();
    extensionView
        .getApplyButton()
        .addActionListener(
            e -> {
              try {
                userInfo.setMax_amount(extensionView.getTextField().getText());
                extensionView.dispose();
                loginView = (LoginView) view;
                loginView.run();
                loginView
                    .getLoginButton()
                    .addActionListener(
                        e1 -> {
                          try {
                            login();
                          } catch (IOException | URISyntaxException | SQLException ioException) {
                            ioException.printStackTrace();
                          }
                        });
                loginView.getResetButton().addActionListener(e1 -> loginView.reset());
                loginView.getRegisterButton().addActionListener(e1 -> loginView.registerDisplay());
                loginView.getPasswordButton().addActionListener(e1 -> showToken());
                loginView.getSecondPasswordButton().addActionListener(e1 -> showToken());
                loginView.getInputButton().addActionListener(e1 -> inputDisplay());
                loginView.getOutputButton().addActionListener(e1 -> outputDisplay());
              } catch (IllegalArgumentException error) {
                JOptionPane.showMessageDialog(null, "Amount must be in between 1 and 1000");
              }
            });
  }

  public void login() throws IOException, URISyntaxException, SQLException {
    String token = String.valueOf(loginView.getTokenField().getPassword());
    String token2 = String.valueOf(loginView.getSecondTokenField().getPassword());
    int status = inputAPI.loggingIn(token);
    int second_status = outputAPI.loggingIn(token2);

    if (inputAPI.getIsOnline() && outputAPI.getIsOnline()) {
      if (status == 200 && second_status == 200) {
        JOptionPane.showMessageDialog(this, "Login Successful");
        loginView.dispose();
        userInfo.setCoinToken(token);
        userInfo.setPasteToken(token2);

        ProcessView processView = new ProcessView(model);
        ProcessController processController = new ProcessController(model, processView);
        processController.initController();
        loginView.getRegisterPage().dispose();

      } else {
        JOptionPane.showMessageDialog(this, "Invalid Username or Authentication");
      }
    } else if (!inputAPI.getIsOnline() && !outputAPI.getIsOnline()) {
      if (coinTempVal != null && temp != null) {
        if (status == 200 && second_status == 200) {
          JOptionPane.showMessageDialog(this, "Login Successful");
          loginView.dispose();
          userInfo.setCoinToken(token);
          userInfo.setPasteToken(token2);

          ProcessView processView = new ProcessView(model);
          ProcessController processController = new ProcessController(model, processView);
          processController.initController();
          loginView.getRegisterPage().dispose();
        }
      } else {
        JOptionPane.showMessageDialog(this, "Invalid Username or Authentication");
      }
    } else if (inputAPI.getIsOnline() && !outputAPI.getIsOnline()) {
      if (status == 200) {
        if (temp != null) {
          if (second_status == 200) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            loginView.dispose();
            userInfo.setCoinToken(token);
            userInfo.setPasteToken(token2);

            ProcessView processView = new ProcessView(model);
            ProcessController processController = new ProcessController(model, processView);
            processController.initController();
            loginView.getRegisterPage().dispose();
          } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Authentication");
          }
        } else {
          JOptionPane.showMessageDialog(this, "Invalid Username or Authentication");
        }
      } else {
        JOptionPane.showMessageDialog(this, "Invalid Username or Authentication");
      }
    } else if (!inputAPI.getIsOnline() && outputAPI.getIsOnline()) {
      if (coinTempVal != null) {
        if (status == 200 && second_status == 200) {
          JOptionPane.showMessageDialog(this, "Login Successful");
          loginView.dispose();
          userInfo.setCoinToken(token);
          userInfo.setPasteToken(token2);

          ProcessView processView = new ProcessView(model);
          ProcessController processController = new ProcessController(model, processView);
          processController.initController();
          loginView.getRegisterPage().dispose();
        } else {
          JOptionPane.showMessageDialog(this, "Invalid Username or Authentication");
        }
      } else {
        JOptionPane.showMessageDialog(this, "Invalid Username or Authentication");
      }
    }
  }

  public void inputDisplay() {
    if (model.getInputAPIModel().getIsOnline()) {
      JLabel hyperLink = loginView.registerOnline();

      hyperLink.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              try {
                Desktop.getDesktop().browse(new URI("https://pro.coinmarketcap.com/"));
              } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
              }
            }
          });
    } else {
      coinTempVal = "";
      JButton copy = loginView.registerOffline();
      copy.addActionListener(
          e -> {
            CoinCtrlC();
            JOptionPane.showMessageDialog(null, "Please paste the copy using Ctrl+V");
            loginView.getSecondTempFrame().dispose();
          });
    }
  }

  public void outputDisplay() {

    if (model.getOutputAPIModel().getIsOnline()) {
      JLabel hyperLink = loginView.registerOnline();

      hyperLink.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              try {
                Desktop.getDesktop().browse(new URI("https://pastebin.com/login"));
              } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
              }
            }
          });
    } else {
      temp = "";
      JButton copy = loginView.registerOffline();
      copy.addActionListener(
          e -> {
            PasteCtrlC();
            JOptionPane.showMessageDialog(null, "Please paste the copy using Ctrl+V");
            loginView.getSecondTempFrame().dispose();
          });
    }
  }

  public void showToken() {
    if (loginView.getPasswordButton().isSelected()) {
      loginView.getTokenField().setEchoChar((char) 0);
    } else {
      loginView.getTokenField().setEchoChar('*');
    }

    if (loginView.getSecondPasswordButton().isSelected()) {
      loginView.getSecondTokenField().setEchoChar((char) 0);
    } else {
      loginView.getSecondTokenField().setEchoChar('*');
    }
  }

  private void CoinCtrlC() {
    coinTempVal = "53240032-0bea-482c-9273-f346c3f17632";
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();
    StringSelection strSel = new StringSelection(coinTempVal);
    clipboard.setContents(strSel, null);
  }

  private void PasteCtrlC() {
    temp = "53240032-0bea-482c-9273-f346c3f17632";
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();
    StringSelection strSel = new StringSelection(temp);
    clipboard.setContents(strSel, null);
  }
}
