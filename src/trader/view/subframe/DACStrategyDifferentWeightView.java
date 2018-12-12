package trader.view.subframe;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import trader.view.FieldName;
import trader.view.GuiView;

public class DACStrategyDifferentWeightView extends JFrame implements GuiView{
  private JButton submit;
  private JButton back;
  private Form form;

  public DACStrategyDifferentWeightView(int size, String caption) {
    super(caption);
    // size = 3  6
    // 0 1 2 3 4 5
    int totalSize = size * 2 + 6;
    int stockPartSize = size * 2;
    String[] fieldLabel = new String[totalSize];

    for (int i = 0; i < size * 2; i+=2) {
      fieldLabel[i] = FieldName.TICKET_SYMBOL.getMsg();
      fieldLabel[i + 1] = FieldName.ENTER_WEIGHT.getMsg();
    }
    fieldLabel[stockPartSize] = FieldName.PER_INVESTMENT.getMsg();
    fieldLabel[stockPartSize + 1] = FieldName.COMMISSION_FEE.getMsg();
    fieldLabel[stockPartSize + 2] = FieldName.START_TIME.getMsg();
    fieldLabel[stockPartSize + 3] = FieldName.END_TIME.getMsg();
    fieldLabel[stockPartSize + 4] = FieldName.FREQUENCY.getMsg();
    fieldLabel[stockPartSize + 5] = FieldName.STRATEGY_NAME.getMsg();

    this.form = new Form(fieldLabel);
    this.setLocation(400, 200);
    this.setPreferredSize(new Dimension(800, 800));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().add(form, BorderLayout.NORTH);

    submit = new JButton(FieldName.SUBMIT.getMsg());
    submit.setActionCommand(FieldName.DAC_STRATEGY_DIFF_SUBMIT.getMsg());
    back = new JButton(FieldName.BACK.getMsg());
    back.setActionCommand(FieldName.DAC_STRATEGY_DIFF_BACK.getMsg());

    JPanel mainPanel = new JPanel();
    mainPanel.add(submit);
    mainPanel.add(back);

    this.getContentPane().add(mainPanel, BorderLayout.SOUTH);
    this.pack();
    this.setVisible(true);
  }
  /**
   * Get the string from the text field and return it.
   *
   * @return the user input
   */
  @Override
  public String getInputString() {
    StringBuilder sb = new StringBuilder();
    for (JTextField text : form.getField()) {
      sb.append("\n");
      sb.append(text.getText());
    }
    return sb.toString().substring(1);
  }

  /**
   * This is to force the view to have a method to set up actions for buttons. All the buttons must
   * be given this action listener. Thus our Swing-based implementation of this interface will
   * already have such a method.
   *
   * @param listener the ActionListener
   */
  @Override
  public void addActionListener(ActionListener listener) {
    submit.addActionListener(listener);
    back.addActionListener(listener);
  }
}
