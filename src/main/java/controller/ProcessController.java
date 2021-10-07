package controller;

import model.Database;
import model.Model;
import model.UserInfo;
import model.facade.input.InputAPI;
import model.facade.output.OutputAPI;
import org.json.JSONArray;
import org.json.JSONObject;
import view.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class ProcessController implements Controller {

  static boolean clicked = false;
  static String name = "id";
  static String baseCurrency = "1";
  static String quoteCurrency = "1";
  static String amount = "1";
  static Statement stmt = null;
  static Connection conn = null;
  private final Model model;
  private final View view;
  private final PrintGUI printGUI = new PrintGUI();
  private final Database database = new Database();
  private UserInfo userInfo;
  private InputAPI inputAPI;
  private OutputAPI outputAPI;
  private ProcessView processView = null;
  private final View view1 = new ExtensionView();

  public ProcessController(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void initController() throws SQLException {
    conn = database.DBConnection();
    stmt = database.createTable(conn);
    JOptionPane.showMessageDialog(null, "DATABASE IS PROCESSING...");
    inputAPI = model.getInputAPIModel();
    outputAPI = model.getOutputAPIModel();
    userInfo = model.getUser();
    processView = (ProcessView) view;
    processView.run();
    processView
        .getViewCrpytoList()
        .addActionListener(
            e -> {
              try {
                getCryptoList();
              } catch (IOException | URISyntaxException | SQLException ioException) {
                ioException.printStackTrace();
              }
            });
    processView
        .getViewCrpytoInfo()
        .addActionListener(
            e -> {
              try {
                getCryptoInfo();
              } catch (IOException | URISyntaxException ioException) {
                ioException.printStackTrace();
              }
            });
    processView.getViewCrytoCurrncy().addActionListener(e -> getCryptoCurrency());
  }

  public void getCryptoList() throws IOException, URISyntaxException, SQLException {

    String data = inputAPI.viewCryptoList();
    JSONObject json = printGUI.convertToJson(data);
    if (data.contains("data")) {
      JOptionPane.showMessageDialog(null, "DATA IS STORED IN DB WITH PERSISTENCE");
      database.InsertDB(conn, json.toString(4));
      database.getResult(stmt);
    }
    processView.choice();

    processView
        .getRunButton()
        .addActionListener(
            e -> {
              Thread t =
                  new Thread(
                      () -> {
                        if (processView.getGUI_display().isSelected()) {
                          clicked = true;
                        } else if (processView.getPastebin_display().isSelected()) {
                          clicked = false;
                        }
                        if (clicked) {
                          pagination(json);
                        } else {
                          try {
                            printGUI.printInputTest(json);
                            String outputResult = outputAPI.paste(json.toString(4));
                            printGUI.printOutputTest(outputResult);
                            printGUI.printOutput(outputResult);
                          } catch (URISyntaxException | IOException uriSyntaxException) {
                            uriSyntaxException.printStackTrace();
                          }
                        }
                        try {
                          Thread.sleep(10);
                        } catch (InterruptedException interruptedException) {
                          interruptedException.printStackTrace();
                        }
                      });
              t.start();
            });
  }

  public void getCryptoInfo() throws IOException, URISyntaxException {

    CryptoInfoView cryptoInfoView = new CryptoInfoView(this.model, false);
    cryptoInfoView.run();
    cryptoInfoView.getTextAreaValue().setText("");

    cryptoInfoView
        .getSearchButton()
        .addActionListener(
            e -> {
              baseCurrency = cryptoInfoView.getTextAreaValue().getText();

              if (baseCurrency == null || baseCurrency.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a value");
              } else {
                if (cryptoInfoView.getSearchById().isSelected()) {
                  name = "id";
                } else if (cryptoInfoView.getSearchBySlug().isSelected()) {
                  name = "slug";
                } else if (cryptoInfoView.getSearchBySymbol().isSelected()) {
                  name = "symbol";
                }
                try {
                  String data = inputAPI.viewCryptoInfo(name, baseCurrency);
                  JSONObject json = printGUI.convertToJson(data);
                  if (data.contains("data")) {
                    JOptionPane.showMessageDialog(null, "DATA IS STORED IN DB WITH PERSISTENCE");
                    database.InsertDB(conn, json.toString(4));
                    database.getResult(stmt);
                  }
                  printGUI.printInputTest(json);
                  String outputResult = outputAPI.paste(json.toString(4));
                  printGUI.printOutputTest(outputResult);
                  printGUI.printOutput(outputResult);
                } catch (URISyntaxException | IOException | SQLException uriSyntaxException) {
                  uriSyntaxException.printStackTrace();
                }
              }
            });
  }

  public void getCryptoCurrency() {
    CryptoInfoView cryptoInfoView = new CryptoInfoView(this.model, true);
    cryptoInfoView.run();
    cryptoInfoView.getTextAreaValue().setText("");

    cryptoInfoView
        .getSearchButton()
        .addActionListener(
            e -> {
              baseCurrency = cryptoInfoView.getTextAreaValue().getText();
              quoteCurrency = cryptoInfoView.getTextQuoteValue().getText();
              amount = userInfo.getMax_amount();

              if (baseCurrency == null || baseCurrency.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a value");
              } else if (quoteCurrency == null || quoteCurrency.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a value");
              } else {
                if (amount == null || amount.equals("")) {
                  JOptionPane.showMessageDialog(null, "Please enter an amount");
                } else {
                  if (cryptoInfoView.getSearchById().isSelected()) {
                    name = "id";
                  } else if (cryptoInfoView.getSearchBySymbol().isSelected()) {
                    name = "symbol";
                  }
                  try {
                    String data =
                        inputAPI.viewCryptoCurrency(name, baseCurrency, quoteCurrency, amount);
                    JSONObject json = printGUI.convertToJson(data);
                    JSONObject extensionJSON = (JSONObject) json.get("data");
                    JSONObject quote = (JSONObject) extensionJSON.get("quote");
                    Iterator<String> keys = quote.keys();
                    String iter = keys.next();
                    JSONObject value = (JSONObject) quote.get(iter);
                    String price = value.get("price").toString();
                    String strName = extensionJSON.get("name").toString();
                    String strQuote = quote.keySet().toString();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder
                        .append("[")
                        .append(strName)
                        .append("]")
                        .append(" -> ")
                        .append("(Id/Symbol)")
                        .append(strQuote)
                        .append(" is converted to the price of ")
                        .append(price);

                    json.put("Extension Output: ", stringBuilder);

                    if (data.contains("data")) {
                      JOptionPane.showMessageDialog(null, "DATA IS STORED IN DB WITH PERSISTENCE");
                      database.InsertDB(conn, json.toString(4));
                      database.getResult(stmt);
                    }
                    ExtensionView extensionView = (ExtensionView) view1;
                    extensionView.choice();

                    extensionView
                        .getRunButton()
                        .addActionListener(
                            e1 -> {
                              if (extensionView.getGUI_display().isSelected()) {
                                printGUI.printInput(json.toString(4));
                                printGUI.printInputTest(json);
                              } else if (extensionView.getPastebin_display().isSelected()) {
                                try {
                                  printGUI.printInputTest(json);
                                  String outputResult = outputAPI.paste(json.toString(4));
                                  printGUI.printOutputTest(outputResult);
                                  printGUI.printOutput(outputResult);
                                } catch (URISyntaxException | IOException uriSyntaxException) {
                                  uriSyntaxException.printStackTrace();
                                }
                              } else {
                                JOptionPane.showMessageDialog(
                                    null, "Please select at least one of the two options");
                              }
                            });

                  } catch (SQLException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                  }
                }
              }
            });
  }

  private void pagination(JSONObject jsonObj) {
    JSONArray jsonArr = new JSONArray(jsonObj.get("data").toString());
    Pagination pagination = new Pagination(jsonArr);
    pagination.run();
  }
}
