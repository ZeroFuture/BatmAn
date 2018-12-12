package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * The EvaluateAccount class represent the EvaluateAccount class. EvaluateAccount is one
 * the options that users have in our trading system. It will help users to evaluate the account
 * and the total cost basis of all the portfolios so that the users can have a quick view of how
 * well their account perform. The user need to enter the time they decide to evaluate the their
 * account.
 */
public class EvaluateAccount implements CommandInterface {
  String timeStamp;

  /**
   * The EvaluateAccount constructor.
   *
   * @param timeStamp the time you choose to evaluate the the value of your Account.
   */
  public EvaluateAccount(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  /**
   * Call the evaluateAccountAndCostBasis to realize the execute of ITraderModel.
   *
   * @param m ITraderModel
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      System.out.println(m.evaluateAccount(timeStamp));
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_EVALUATE_ACCOUNT_FAIL.getMsg());
    }
  }
}
