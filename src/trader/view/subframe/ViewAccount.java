package trader.view.subframe;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import trader.view.GuiView;

/**
 * View Account view.
 */
public class ViewAccount extends JFrame implements GuiView {
  // to do

  /**
   * Get the string from the text field and return it.
   *
   * @return the user input
   */
  @Override
  public String getInputString() {
    return null;
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
    //to do
  }
}
