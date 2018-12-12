package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * This class represent ApplyStrategy command.
 */
public class ApplyStrategy implements CommandInterface {
  private final String portfolioName;
  private final String strategyName;

  /**
   * Constructor for ApplyStrategy.
   * @param portfolioName portfolioName.
   * @param strategyName strategyName.
   */
  public ApplyStrategy(String portfolioName, String strategyName) {
    this.portfolioName = portfolioName;
    this.strategyName = strategyName;
  }


  /**
   * PLay the function with model.
   *
   * @param m model to be played.
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      m.applyStrategy(strategyName, portfolioName);
    }
    catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_APPLY_STRATEGY_FAIL.getMsg());
    }
  }
}
