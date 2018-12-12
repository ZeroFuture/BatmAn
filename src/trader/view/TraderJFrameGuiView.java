package trader.view;


import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import trader.controller.ITraderController;

/**
 * This is the implementation of the ITraderGuiView interface.
 */
public class TraderJFrameGuiView extends JFrame implements GuiView {
  private JButton buyStockNoCommission;
  private JButton buyStockWithCommission;
  private JButton createPortfolio;
  private JButton viewPortfolio;
  private JButton evaluatePortfolio;
  private JButton addStock;
  private JButton getPortfolioCostBasis;
  private JButton addWeightedInvestmentStrategy;
  private JButton dollarAverageCostingStrategy;
  private JButton applyStrategy;
  private JButton savePortfolio;
  private JButton saveStrategy;
  private JButton retrievePortfolio;
  private JButton retrieveStrategy;
  private JButton evaluateAccount;
  private JButton saveAllPortfolio;
  private JButton saveAllStrategy;
  private JButton retrieveAllPortfolio;
  private JButton retrieveAllStrategy;
  private JButton viewAccount;
  private JButton viewStrategy;

  /**
   * The constructor of TraderJFrameGuiView, which is the constructor of main view.
   * @param caption view title
   * @param controller the Controller
   */
  public TraderJFrameGuiView(String caption, ITraderController controller) {
    super(caption);
    this.setLocation(200,400);
    GridLayout experimentLayout = new GridLayout(0, 1 ,5, 5);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel welcomePannel = new JPanel();
    JLabel welcomeDisplay = new JLabel(FieldName.PROGRAM_START.getMsg());
    welcomePannel.add(welcomeDisplay);

    // main pannel
    JPanel mainPanel = new JPanel();
    mainPanel.setPreferredSize(new Dimension(800,800));
    JLabel StockOperation = new JLabel(FieldName.STOCK_OPERATION.getMsg(), SwingConstants.CENTER);
    JLabel portfolioOperation = new JLabel(FieldName.PORTFOLIO_OPERATION.getMsg(),
            SwingConstants.CENTER);
    JLabel strategyOperation = new JLabel(FieldName.STRATEGY_OPERATION.getMsg(), SwingConstants
            .CENTER);
    JLabel accountOperation = new JLabel(FieldName.ACCOUNT_OPERATION.getMsg(),
            SwingConstants.CENTER);
    StockOperation.setFont(new Font("Courier New", Font.BOLD, 20));
    StockOperation.setForeground(Color.GRAY);
    portfolioOperation.setFont(new Font("Courier New", Font.BOLD, 20));
    portfolioOperation.setForeground(Color.GRAY);
    strategyOperation.setFont(new Font("Courier New", Font.BOLD, 20));
    strategyOperation.setForeground(Color.GRAY);
    accountOperation.setFont(new Font("Courier New", Font.BOLD, 20));
    accountOperation.setForeground(Color.GRAY);
    buyStockNoCommission = new JButton(FieldName.BUY_STOCK_NO_COMMISSION_ENTER.getMsg());
    buyStockNoCommission.setActionCommand(FieldName.BUY_STOCK_NO_COMMISSION_ENTER.getMsg());
    buyStockWithCommission = new JButton(FieldName.BUY_STOCK_WITH_COMMISSION_ENTER.getMsg());
    buyStockWithCommission.setActionCommand(FieldName.BUY_STOCK_WITH_COMMISSION_ENTER.getMsg());
    addStock = new JButton(FieldName.ADD_STOCK_ENTER.getMsg());
    addStock.setActionCommand(FieldName.ADD_STOCK_ENTER.getMsg());
    createPortfolio = new JButton(FieldName.CREATE_PORTFOLIO_ENTER.getMsg());
    createPortfolio.setActionCommand(FieldName.CREATE_PORTFOLIO_ENTER.getMsg());
    viewPortfolio = new JButton(FieldName.VIEW_PORTFOLIO_ENTER.getMsg());
    viewPortfolio.setActionCommand(FieldName.VIEW_PORTFOLIO_ENTER.getMsg());
    evaluatePortfolio = new JButton(FieldName.EVALUATE_PORTFOLIO_ENTER.getMsg());
    evaluatePortfolio.setActionCommand(FieldName.EVALUATE_PORTFOLIO_ENTER.getMsg());
    getPortfolioCostBasis = new JButton(FieldName.GET_PORTFOLIO_COST_BASIS_ENTER.getMsg());
    getPortfolioCostBasis.setActionCommand(FieldName.GET_PORTFOLIO_COST_BASIS_ENTER.getMsg());
    addWeightedInvestmentStrategy = new JButton(FieldName.WEIGHTED_INVESTMENT_ENTER.getMsg());
    addWeightedInvestmentStrategy.setActionCommand(FieldName.WEIGHTED_INVESTMENT_ENTER.getMsg());
    dollarAverageCostingStrategy = new JButton(FieldName.DAC_STRATEGY_ENTER.getMsg());
    dollarAverageCostingStrategy.setActionCommand(FieldName.DAC_STRATEGY_ENTER.getMsg());
    applyStrategy = new JButton(FieldName.APPLY_STRATEGY_ENTER.getMsg());
    applyStrategy.setActionCommand(FieldName.APPLY_STRATEGY_ENTER.getMsg());
    savePortfolio = new JButton(FieldName.SAVE_PORTFOLIO_ENTER.getMsg());
    savePortfolio.setActionCommand(FieldName.SAVE_PORTFOLIO_ENTER.getMsg());
    saveStrategy = new JButton(FieldName.SAVE_STRATEGY_ENTER.getMsg());
    saveStrategy.setActionCommand(FieldName.SAVE_STRATEGY_ENTER.getMsg());
    retrievePortfolio = new JButton(FieldName.RETRIEVE_PORTFOLIO_ENTER.getMsg());
    retrievePortfolio.setActionCommand(FieldName.RETRIEVE_PORTFOLIO_ENTER.getMsg());
    retrieveStrategy = new JButton(FieldName.RETRIEVE_STRATEGY_ENTER.getMsg());
    retrieveStrategy.setActionCommand(FieldName.RETRIEVE_STRATEGY_ENTER.getMsg());
    evaluateAccount = new JButton(FieldName.EVALUATE_ACCOUNT_ENTER.getMsg());
    evaluateAccount.setActionCommand(FieldName.EVALUATE_ACCOUNT_ENTER.getMsg());
    saveAllPortfolio = new JButton(FieldName.SAVE_ALL_PORTFOLIO_ENTER.getMsg());
    saveAllPortfolio.setActionCommand(FieldName.SAVE_ALL_PORTFOLIO_ENTER.getMsg());
    saveAllStrategy = new JButton(FieldName.SAVE_ALL_STRATEGY_ENTER.getMsg());
    saveAllStrategy.setActionCommand(FieldName.SAVE_ALL_STRATEGY_ENTER.getMsg());
    retrieveAllPortfolio = new JButton(FieldName.RETRIEVE_ALL_PORTFOLIO_ENTER.getMsg());
    retrieveAllPortfolio.setActionCommand(FieldName.RETRIEVE_ALL_PORTFOLIO_ENTER.getMsg());
    retrieveAllStrategy = new JButton(FieldName.RETRIEVE_ALL_STRATEGY_ENTER.getMsg());
    retrieveAllStrategy.setActionCommand(FieldName.RETRIEVE_ALL_STRATEGY_ENTER.getMsg());
    viewAccount = new JButton(FieldName.VIEW_ACCOUNT.getMsg());
    viewAccount.setActionCommand(FieldName.VIEW_ACCOUNT.getMsg());
    viewStrategy = new JButton(FieldName.VIEW_STRATEGY.getMsg());
    viewStrategy.setActionCommand(FieldName.VIEW_STRATEGY.getMsg());
    mainPanel.setLayout(experimentLayout);
    mainPanel.add(StockOperation);
    mainPanel.add(addStock);
    mainPanel.add(buyStockNoCommission);
    mainPanel.add(buyStockWithCommission);
    mainPanel.add(portfolioOperation);
    mainPanel.add(createPortfolio);
    mainPanel.add(viewPortfolio);
    mainPanel.add(getPortfolioCostBasis);
    mainPanel.add(evaluatePortfolio);
    mainPanel.add(savePortfolio);
    mainPanel.add(saveAllPortfolio);
    mainPanel.add(retrievePortfolio);
    mainPanel.add(retrieveAllPortfolio);
    mainPanel.add(strategyOperation);
    mainPanel.add(viewStrategy);
    mainPanel.add(addWeightedInvestmentStrategy);
    mainPanel.add(dollarAverageCostingStrategy);
    mainPanel.add(saveStrategy);
    mainPanel.add(saveAllStrategy);
    mainPanel.add(retrieveStrategy);
    mainPanel.add(retrieveAllStrategy);
    mainPanel.add(retrieveAllStrategy);
    mainPanel.add(applyStrategy);
    mainPanel.add(accountOperation);
    mainPanel.add(viewAccount);
    mainPanel.add(evaluateAccount);
    // bot pannel
    JPanel botPannel = new JPanel();
    JLabel endDiplay = new JLabel(FieldName.PROGRAM_END.getMsg());
    botPannel.add(endDiplay);
    this.getContentPane().add(mainPanel, BorderLayout.CENTER);
    this.getContentPane().add(welcomePannel, BorderLayout.BEFORE_FIRST_LINE);
    this.getContentPane().add(botPannel, BorderLayout.AFTER_LAST_LINE);
    pack();
    setVisible(true);
  }

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
    buyStockWithCommission.addActionListener(listener);
    buyStockNoCommission.addActionListener(listener);
    createPortfolio.addActionListener(listener);
    viewPortfolio.addActionListener(listener);
    evaluatePortfolio.addActionListener(listener);
    addStock.addActionListener(listener);
    getPortfolioCostBasis.addActionListener(listener);
    addWeightedInvestmentStrategy.addActionListener(listener);
    dollarAverageCostingStrategy.addActionListener(listener);
    applyStrategy.addActionListener(listener);
    savePortfolio.addActionListener(listener);
    saveStrategy.addActionListener(listener);
    retrievePortfolio.addActionListener(listener);
    retrieveStrategy.addActionListener(listener);
    evaluateAccount.addActionListener(listener);
    saveAllPortfolio.addActionListener(listener);
    saveAllStrategy.addActionListener(listener);
    retrieveAllPortfolio.addActionListener(listener);
    retrieveAllStrategy.addActionListener(listener);
    viewAccount.addActionListener(listener);
    viewStrategy.addActionListener(listener);
  }
}
