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
 * The ApplyStrategyView for the GUI view.
 */
public class ApplyStrategyView extends JFrame implements GuiView {
  private JButton submit;
  private JButton back;
  private JButton hintPortfolio;
  private JButton hintStrategy;
  private final String[] fieldLabel = {FieldName.APPLY_STRATEGY_NAME.getMsg(),
          FieldName.APPLY_PORTFOLIO_NAME.getMsg()};
  private final Form form = new Form(fieldLabel);

  /**
   * The constructor of ApplyStrategyView.
   *
   * @param caption the title
   */
  public ApplyStrategyView(String caption) {
    super(caption);
    this.setLocation(400, 200);
    this.setPreferredSize(new Dimension(600, 300));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().add(form, BorderLayout.NORTH);
    submit = new JButton(FieldName.SUBMIT.getMsg());
    submit.setActionCommand(FieldName.APPLY_STRATEGY_SUBMIT.getMsg());
    back = new JButton(FieldName.BACK.getMsg());
    back.setActionCommand(FieldName.APPLY_STRATEGY_BACK.getMsg());
    hintPortfolio = new JButton(FieldName.CHECK_CURRENT_PORTFOLIO_ENTER.getMsg());
    hintPortfolio.setActionCommand(FieldName.CHECK_CURRENT_PORTFOLIO_ENTER.getMsg());
    hintStrategy = new JButton(FieldName.CHECK_CURRENT_STRATEGY_ENTER.getMsg());
    hintStrategy.setActionCommand(FieldName.CHECK_CURRENT_STRATEGY_ENTER.getMsg());
    JPanel mainPanel = new JPanel();
    mainPanel.add(hintPortfolio);
    mainPanel.add(hintStrategy);
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
    hintPortfolio.addActionListener(listener);
    hintStrategy.addActionListener(listener);
    submit.addActionListener(listener);
    back.addActionListener(listener);
  }
}
