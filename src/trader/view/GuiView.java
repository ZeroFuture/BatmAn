package trader.view;

import java.awt.event.ActionListener;

public interface GuiView {

  /**
   * Get the string from the text field and return it.
   * @return the user input
   */
  String getInputString();

  /**
   * This is to force the view to have a method to set up actions for buttons. All the buttons must
   * be given this action listener. Thus our Swing-based implementation of this interface will
   * already have such a method.
   *
   * @param listener the ActionListener
   */
  void addActionListener(ActionListener listener);
}
