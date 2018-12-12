package trader.view.subframe;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

/**
 * Form class implements JPanel.
 */
public class Form extends JPanel {
  private JTextField[] fields;

  /**
   * Constructor for Form class.
   */
  public Form(String[] fieldLabel) {
    super(new BorderLayout());
    JPanel labelPanel = new JPanel(new GridLayout(fieldLabel.length, 1));
    JPanel fieldPanel = new JPanel(new GridLayout(fieldLabel.length, 1));
    this.add(labelPanel, BorderLayout.WEST);
    this.add(fieldPanel, BorderLayout.EAST);
    this.fields = new JTextField[fieldLabel.length];
    for (int i = 0; i < fieldLabel.length; i++) {
      fields[i] = new JTextField();
      fields[i].setColumns(15);
      JLabel label = new JLabel(fieldLabel[i], JLabel.LEFT);
      label.setLabelFor(fields[i]);
      labelPanel.add(label);
      JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
      setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      p.add(fields[i]);
      fieldPanel.add(p);
    }
  }

  /**
   * Get field.
   *
   * @return test field.
   */
  public JTextField[] getField() {
    return this.fields;
  }
}