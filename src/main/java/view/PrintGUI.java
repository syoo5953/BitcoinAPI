package view;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class PrintGUI {

  public JSONObject convertToJson(String data) {
    JSONObject json = new JSONObject(data);
    return json;
  }

  public void printInput(String data) {
    JSONObject jsonObject = new JSONObject(data);
    final JFrame frame = new JFrame("Scroll Pane Example");
    frame.setSize(500, 500);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(new FlowLayout());
    JTextArea textArea = new JTextArea(20, 20);
    textArea.setText(jsonObject.toString(4));
    JScrollPane scrollableTextArea = new JScrollPane(textArea);
    scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    frame.getContentPane().add(scrollableTextArea);
  }

  public void printOutput(String data) {
    final JFrame frame = new JFrame("Scroll Pane Example");
    frame.setSize(500, 500);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(new FlowLayout());
    JTextArea textArea = new JTextArea(20, 20);
    textArea.setText(data);
    JScrollPane scrollableTextArea = new JScrollPane(textArea);
    scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    frame.getContentPane().add(scrollableTextArea);
  }

  public void printInputTest(JSONObject json) {
    System.out.println(json.toString(4));
  }

  public void printOutputTest(String str) {
    System.out.println(str);
  }
}
