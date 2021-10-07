package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

public class ProcessView extends JFrame implements View {

  Model model;
  Container container = getContentPane();
  JButton viewListButton = new JButton("View CryptoCurrency Lists");
  JButton viewCrpytoInfo = new JButton("View CryptoCurrency Info");
  JButton viewCrytoCurrncy = new JButton("View CryptoCurrency Rates");
  JFrame choice_frame = new JFrame("Choose one");
  JRadioButton GUI_display = new JRadioButton("GUI Result Display");
  JRadioButton Pastebin_display = new JRadioButton("Pastebin_URL Result Display");
  JButton click = new JButton("Run");
  ButtonGroup buttonGroup = new ButtonGroup();
  Boolean onOrOff;

  public ProcessView(Model model) {
    this.model = model;
    setLayoutManager();
    setLocationAndSize();
    addComponentsToContainer();
    this.onOrOff = model.getInputAPIModel().getIsOnline();
  }

  public void run() {
    this.setTitle("Process form");
    this.setVisible(true);
    this.setBounds(10, 10, 350, 250);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
  }

  public void setLayoutManager() {
    container.setLayout(null);
  }

  public void setLocationAndSize() {
    viewListButton.setBounds(70, 20, 200, 30);
    viewCrpytoInfo.setBounds(70, 80, 200, 30);
    viewCrytoCurrncy.setBounds(70, 140, 200, 30);

    GUI_display.setBounds(30, 30, 150, 50);
    Pastebin_display.setBounds(160, 30, 210, 50);
    click.setBounds(125, 130, 80, 30);
  }

  public void addComponentsToContainer() {
    container.add(viewListButton);
    container.add(viewCrpytoInfo);
    container.add(viewCrytoCurrncy);
  }

  public JButton getViewCrpytoList() {
    return this.viewListButton;
  }

  public JButton getViewCrpytoInfo() {
    return this.viewCrpytoInfo;
  }

  public JButton getViewCrytoCurrncy() {
    return this.viewCrytoCurrncy;
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
