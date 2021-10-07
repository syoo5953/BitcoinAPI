package view;

import org.json.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Pagination extends JFrame implements View {

  private final int pageSize;
  private final JSONArray jsonArray;
  private final int lastPageNum;
  private final JLabel countLabel;
  JTextArea textArea = new JTextArea(20, 20);
  private int currPageNum;
  private JButton first, prev, next, last;

  public Pagination(JSONArray jsonArray) {
    this.pageSize = 100;
    this.jsonArray = jsonArray;

    this.lastPageNum = jsonArray.length() / pageSize + (jsonArray.length() % pageSize != 0 ? 1 : 0);
    this.currPageNum = lastPageNum > 0 ? 1 : 0;
    countLabel = new JLabel();
  }

  @Override
  public void run() {
    this.setSize(350, 500);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.getContentPane().setLayout(new FlowLayout());
    this.add(countLabel, BorderLayout.NORTH);
    this.add(createControls(), BorderLayout.SOUTH);
    updatePage();
    JScrollPane scrollableTextArea = new JScrollPane(textArea);
    scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.getContentPane().add(scrollableTextArea);

    this.setVisible(true);
  }

  private JPanel createControls() {
    first =
        new JButton(
            new AbstractAction("<<") {
              public void actionPerformed(ActionEvent e) {
                currPageNum = 1;
                updatePage();
              }
            });

    prev =
        new JButton(
            new AbstractAction("<") {
              public void actionPerformed(ActionEvent e) {
                if (--currPageNum <= 0) currPageNum = 1;
                updatePage();
              }
            });

    next =
        new JButton(
            new AbstractAction(">") {
              public void actionPerformed(ActionEvent e) {
                if (++currPageNum > lastPageNum) currPageNum = lastPageNum;
                updatePage();
              }
            });

    last =
        new JButton(
            new AbstractAction(">>") {
              public void actionPerformed(ActionEvent e) {
                currPageNum = lastPageNum;
                updatePage();
              }
            });

    JPanel bar = new JPanel(new GridLayout(1, 4));
    bar.add(first);
    bar.add(prev);
    bar.add(next);
    bar.add(last);
    return bar;
  }

  private void updatePage() {

    if (jsonArray.length() != 0) {
      final int start = (currPageNum - 1) * pageSize;
      int end = start + pageSize;
      if (end >= jsonArray.length()) {
        end = jsonArray.length();
      }
      JSONArray jsonArray1 = new JSONArray();
      for (int i = start; i < end; i++) {
        jsonArray1.put(jsonArray.getJSONObject(i));
      }
      textArea.setText(jsonArray1.toString(4));
    }

    countLabel.setText("Page " + currPageNum + "/" + lastPageNum);

    final boolean canGoBack = currPageNum > 1;
    final boolean canGoFwd = currPageNum != lastPageNum;
    first.setEnabled(canGoBack);
    prev.setEnabled(canGoBack);
    next.setEnabled(canGoFwd);
    last.setEnabled(canGoFwd);
  }
}
