package view;

import javax.swing.*;
import java.awt.*;

public class ExtensionView extends JFrame implements View {
  private final Container container = getContentPane();
  private final JLabel label =
      new JLabel("Please enter the maximum amount of the currency to convert: ");
  private final JTextField textField = new JTextField();
  private final JButton applyButton = new JButton("Apply");
  private final JFrame choice_frame = new JFrame("Choose one");
  private final JRadioButton GUI_display = new JRadioButton("GUI Result Display");
  private final JRadioButton Pastebin_display = new JRadioButton("Pastebin_URL Result Display");
  private final JButton click = new JButton("Run");
  private final ButtonGroup buttonGroup = new ButtonGroup();

  public ExtensionView() {
    setLayoutManager();
    setLocationAndSize();
    addComponentsToContainer();
  }

  public void run() {
    this.setTitle("Extension Form");
    this.setSize(500, 160);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);
  }

  public void setLayoutManager() {
    container.setLayout(null);
  }

  public void setLocationAndSize() {
    label.setBounds(20, 20, 400, 30);
    textField.setBounds(390, 25, 50, 30);
    applyButton.setBounds(190, 70, 80, 30);
    GUI_display.setBounds(30, 30, 150, 50);
    Pastebin_display.setBounds(160, 30, 210, 50);
    click.setBounds(125, 130, 80, 30);
  }

  public void addComponentsToContainer() {
    container.add(label);
    container.add(textField);
    container.add(applyButton);
  }

  public JButton getApplyButton() {
    return this.applyButton;
  }

  public JTextField getTextField() {
    return this.textField;
  }

  public JRadioButton getGUI_display() {
    return this.GUI_display;
  }

  public JRadioButton getPastebin_display() {
    return this.Pastebin_display;
  }

  public JButton getRunButton() {
    return this.click;
  }

  public void choice() {
    choice_frame.setSize(375, 130);
    choice_frame.setLayout(new FlowLayout());
    choice_frame.setTitle("Choose Option");
    buttonGroup.add(GUI_display);
    buttonGroup.add(Pastebin_display);
    choice_frame.add(GUI_display);
    choice_frame.add(Pastebin_display);
    choice_frame.add(click);
    choice_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    choice_frame.setResizable(false);
    choice_frame.setVisible(true);
  }
}
