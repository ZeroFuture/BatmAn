package trader.controller.commands;

import trader.model.IRetrievableStrategyTraderModel;

/**
 * This this is the command interface contains the function that model can use.
 * It is used for model to realize very command of the controller.
 */
public interface CommandInterface {

  /**
   * PLay the function with model.
   *
   * @param m model to be played.
   */
  void execute(IRetrievableStrategyTraderModel m);
}
