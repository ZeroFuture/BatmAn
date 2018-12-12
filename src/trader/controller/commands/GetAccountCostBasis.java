package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * This class represent get account cost basis command.
 */
public class GetAccountCostBasis implements CommandInterface {
  /**
   * PLay the function with model.
   *
   * @param m model to be played.
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      System.out.println(m.getAccountCostBasis());
    }
    catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_GET_COST_ACCOUNT_FAIL.getMsg());
    }
  }
}
