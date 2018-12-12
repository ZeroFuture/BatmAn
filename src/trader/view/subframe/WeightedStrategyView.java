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

/**
 * WeightedStrategyView class.
 */
public class WeightedStrategyView extends JFrame implements GuiView {
  private JButton submit;
  private JButton back;
  private Form form;

  /**
   * Constructor for WeightedStrategyView.
   *
   * @param size    size.
   * @param caption caption.
   */
  public WeightedStrategyView(int size, String caption) {
    super(caption);

    String[] fieldLabel = new String[size + 4];
    for (int i = 0; i < size; i++) {
      fieldLabel[i] = FieldName.TICKET_SYMBOL.getMsg();
    }
    fieldLabel[size] = FieldName.TOTAL_INVESTMENT.getMsg();
    fieldLabel[size + 1] = FieldName.COMMISSION_FEE.getMsg();
    fieldLabel[size + 2] = FieldName.INVEST_TIME.getMsg();
    fieldLabel[size + 3] = FieldName.STRATEGY_NAME.getMsg();

    this.form = new Form(fieldLabel);

    this.setLocation(400, 200);
    this.setPreferredSize(new Dimension(800, 800));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().add(form, BorderLayout.NORTH);

    submit = new JButton(FieldName.SUBMIT.getMsg());
    submit.setActionCommand(FieldName.WEIGHTED_INVESTMENT_SUBMIT.getMsg());
    back = new JButton(FieldName.BACK.getMsg());
    back.setActionCommand(FieldName.WEIGHTED_INVESTMENT_BACK.getMsg());

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
