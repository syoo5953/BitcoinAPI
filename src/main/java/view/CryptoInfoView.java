package view;

import model.Model;
import model.UserInfo;

import javax.swing.*;
import java.awt.*;

public class CryptoInfoView extends JFrame implements View {

  private final JFrame jFrame = new JFrame();
  private final JRadioButton searchById = new JRadioButton("Id");
  private final JRadioButton searchBySlug = new JRadioButton("Slug");
  private final JRadioButton searchBySymbol = new JRadioButton("Symbol");
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final JButton search = new JButton("Search Info");
  private final JTextField textAreaValue = new JTextField();
  private final JLabel baseCurrencyLabel = new JLabel("Enter the base currency: ");
  private final JLabel quoteCurrencyLabel = new JLabel("Enter the quote currency:  ");
  private final JTextField textQuoteValue = new JTextField();
  private final JLabel amountLabel = new JLabel();
  private final JLabel searchLabel = new JLabel("Search By: ");
  Container container = getContentPane();
  boolean isAmount;
  UserInfo userInfo;

  public CryptoInfoView(Model model, boolean amount) {
    userInfo = model.getUser();
    this.isAmount = amount;
    setLayoutManager();
    setLocationAndSize();
  }

  @Override
  public void run() {
    if (!this.isAmount) {
      jFrame.setTitle("CrpytoInfo Panel");
      jFrame.setSize(500, 200);
    } else {
      jFrame.setTitle("CryptoCurrency Panel");
      jFrame.setSize(500, 290);
    }
    buttonGroup.add(searchById);
    buttonGroup.add(searchBySymbol);
    container.add(searchLabel);
    container.add(searchById);
    container.add(searchBySymbol);

    if (this.isAmount) {
      amountLabel.setText("The amount you set is:  " + userInfo.getMax_amount());
      jFrame.add(amountLabel);
      jFrame.add(quoteCurrencyLabel);
      jFrame.add(textQuoteValue);
    } else {
      buttonGroup.add(searchBySlug);
      container.add(searchBySlug);
    }

    jFrame.add(search);
    jFrame.add(baseCurrencyLabel);
    jFrame.add(textAreaValue);
    jFrame.add(container);
    jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    jFrame.setResizable(false);
    jFrame.setVisible(true);
  }

  public void setLayoutManager() {
    container.setLayout(new FlowLayout());
  }

  public void setLocationAndSize() {
    if (!isAmount) {
      searchById.setBounds(100, 30, 150, 50);
      searchBySymbol.setBounds(190, 30, 150, 50);

      search.setBounds(300, 100, 130, 30);

      textAreaValue.setBounds(150, 100, 130, 30);

      searchLabel.setBounds(30, 30, 200, 50);
      baseCurrencyLabel.setBounds(30, 100, 130, 30);
    } else {
      searchById.setBounds(90, 30, 150, 50);
      searchBySymbol.setBounds(190, 30, 150, 50);
      searchBySlug.setBounds(140, 30, 150, 50);

      search.setBounds(300, 200, 130, 30);

      textAreaValue.setBounds(180, 100, 50, 30);
      textQuoteValue.setBounds(180, 130, 50, 30);
      searchLabel.setBounds(30, 30, 200, 50);
      baseCurrencyLabel.setBounds(30, 100, 170, 30);
      quoteCurrencyLabel.setBounds(30, 130, 170, 30);
      amountLabel.setBounds(30, 170, 200, 30);
    }
  }

  public JButton getSearchButton() {
    return this.search;
  }

  public JRadioButton getSearchById() {
    return this.searchById;
  }

  public JRadioButton getSearchBySlug() {
    return this.searchBySlug;
  }

  public JRadioButton getSearchBySymbol() {
    return this.searchBySymbol;
  }

  public JTextField getTextAreaValue() {
    return this.textAreaValue;
  }

  public JTextField getTextQuoteValue() {
    return this.textQuoteValue;
  }
}
