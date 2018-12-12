package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * AddStock command represents adding stock to the portfolio.
 */
public class AddStock implements CommandInterface {
  private final String ticketSymbol;
  private final String portfolioName;

  public AddStock(String ticketSymbol, String portfolioName) {
    this.ticketSymbol = ticketSymbol;
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
      m.addEquity(this.ticketSymbol, this.portfolioName);
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_ADD_EQUITY.getMsg());
    }
  }
}
