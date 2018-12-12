package trader.controller.commands;

import trader.ErrorMsg;
import trader.controller.Interactivity;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * The ViewAccount Class to represents the ViewAccount command. ViewAccount is one
 * the options that users have in our trading system. It will help users to have a quick
 * review of what stocks they currently have in every portfolio they have.
 */
public class ViewAccount implements CommandInterface {
  /**
   * Call the viewAccount method realize execute of the ITraderModel.
   *
   * @param m the ITrader
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      System.out.println(Interactivity.SHOW_ACCOUNT.getMsg());
      System.out.println(m.viewAccount());
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_VIEW_ACCOUNT_FAIL.getMsg());
    }
  }
}
