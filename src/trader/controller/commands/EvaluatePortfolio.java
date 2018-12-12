package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * The EvaluatePortfolio to represent the EvaluatePortfolio command. EvaluateAccount is one
 * the options that users have in our trading system. It will help users to evaluate the account
 * and the total cost basis of one of the specific portfolios so that the users can have a quick
 * view how well that specific portfolio performs. The user need to enter the time and the the name
 * of the portfolio they want to examine.
 */
public class EvaluatePortfolio implements CommandInterface {
  private final String portfolioName;
  private final String time;

  /**
   * The EvaluatePortfolio constructor.
   *
   * @param portfolioName the name of your portfolio you want to examine
   * @param time          the time you choose to examine your portfolio
   */
  public EvaluatePortfolio(String portfolioName, String time) {
    this.portfolioName = portfolioName;
    this.time = time;
  }

  /**
   * Called evaluatePortfolioAndCostBasis to realize the execute of the ITraderModel.
   *
   * @param m the ItraderModel.
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      System.out.println(m.evaluatePortfolio(this.time, this.portfolioName));
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_EVALUATE_PORTFOLIO_FAIL.getMsg());
    }
  }
}
