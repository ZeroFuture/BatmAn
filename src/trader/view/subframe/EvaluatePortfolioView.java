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
 * This is the EvaluatePortfolioView class which provide the view for evaluating portfolio.
 */
public class EvaluatePortfolioView extends JFrame implements GuiView {
  private JButton submit;
  private JButton back;
  private JButton hint;
  private final String[] fieldLabel = {FieldName.EVALUATE_PORTFOLIO_NAME.getMsg(),
          FieldName.EVALUATE_PORTFOLIO_TIME.getMsg()};
  private final Form form = new Form(fieldLabel);

  /**
   * Constructor for EvaluatePortfolioView.
   *
   * @param caption caption.
   */
  public EvaluatePortfolioView(String caption) {
    super(caption);
    this.setLocation(400, 200);
    this.setPreferredSize(new Dimension(600, 300));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().add(form, BorderLayout.NORTH);

    submit = new JButton(FieldName.SUBMIT.getMsg());
    submit.setActionCommand(FieldName.EVALUATE_PORTFOLIO_SUBMIT.getMsg());

    back = new JButton(FieldName.BACK.getMsg());
    back.setActionCommand(FieldName.EVALUATE_PORTFOLIO_BACK.getMsg());

    hint = new JButton(FieldName.CHECK_CURRENT_PORTFOLIO_ENTER.getMsg());
    hint.setActionCommand(FieldName.CHECK_CURRENT_PORTFOLIO_ENTER.getMsg());

    JPanel mainPanel = new JPanel();
    mainPanel.add(hint);
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
    hint.addActionListener(listener);
  }
}
