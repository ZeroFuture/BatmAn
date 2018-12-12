package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * This class represent get portfolio cost basis command.
 */
public class GetPortfolioCostBasis implements CommandInterface {
  private final String portfolioName;

  /**
   * Constructor for get portfolio cost basis.
   * @param portfolioName portfolioName.
   */
  public GetPortfolioCostBasis(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  /**
   * PLay the function with model.
   *
   * @param m model to be played.
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      System.out.println(m.getPortfolioCostBasis(portfolioName));
    }
    catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_GET_COST_PORTFOLIO_FAIL.getMsg());
    }
  }
}
