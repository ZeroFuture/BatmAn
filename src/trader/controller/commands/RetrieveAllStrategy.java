package trader.controller.commands;

import trader.ErrorMsg;
import trader.controller.Interactivity;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * This class represent retrieve all strategy command.
 */
public class RetrieveAllStrategy implements CommandInterface {
  /**
   * PLay the function with model.
   *
   * @param m model to be played.
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      m.retrieveAllStrategy();
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
