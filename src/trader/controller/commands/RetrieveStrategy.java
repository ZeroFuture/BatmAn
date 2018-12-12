package trader.controller.commands;

import trader.ErrorMsg;
import trader.controller.Interactivity;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * This class represent retrieve strategy command.
 */
public class RetrieveStrategy implements CommandInterface {
  private final String strategyName;

  /**
   * Constructor for RetrieveStrategy.
   * @param strategyName strategyName.
   */
  public RetrieveStrategy(String strategyName) {
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
      m.retrieveStrategy(strategyName);
      System.out.println(Interactivity.RETRIEVE_SUCCESS.getMsg());
    }
    catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_RETRIEVE_STRATEGY.getMsg());
    }
  }
}
