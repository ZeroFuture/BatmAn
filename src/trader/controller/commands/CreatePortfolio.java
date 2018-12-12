package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * The CreatePortfolio class to represent the CreatePortfolio command. CreatePortfolio is one
 * the options that users have in our trading system. It will help users to create the portfolio
 * named by the user input. The user can buy any stock no matter it's real or fake to the portfolio.
 */
public class CreatePortfolio implements CommandInterface {
  private final String name;

  /**
   * The constructor of the CreatePortfolio.
   *
   * @param name the name the portfolio you want to create
   */
  public CreatePortfolio(String name) {
    this.name = name;
  }

  /**
   * call createPortfolio to realize the execute of ITrade.
   *
   * @param m the ITradeModel
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      m.createPortfolio(name);
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_CREATE_PORTFOLIO_FAIL.getMsg());
    }
  }
}
