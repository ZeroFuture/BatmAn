package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * The ViewPortfolio class to represent to ViewPortfolio command. ViewPortfolio is one
 * the options that users have in our trading system. It will help users to have a quick
 * review of what stocks they currently have in one of the specific portfolio.The user need to
 * enter the name of the portfolio to decide which porfolio they want to examine.
 */
public class ViewPortfolio implements CommandInterface {
  private final String portfolioName;

  /**
   * The Constructor of ViewPortfolio.
   *
   * @param portfolioName the name the portfolio you choose to view
   */
  public ViewPortfolio(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  /**
   * Call the viewPortfolio function realize the execute of the ITraderModel.
   *
   * @param m the ITraderModel
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      System.out.println(m.viewPortfolio(portfolioName));
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_VIEW_PORTFOLIO_FAIL.getMsg());
    }
  }
}
