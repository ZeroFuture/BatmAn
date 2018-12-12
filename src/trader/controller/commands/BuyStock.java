package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * The BuyStock class to represent buy without commission.
 */
public class BuyStock implements CommandInterface {
  private final String tickerSymbol;
  private final String time;
  private final String volume;
  private final String portfolio;

  /**
   * The constructor of BuyStock.
   *
   * @param tickerSymbol The symbol of the stocks to be added
   * @param time         The time you want to buy the stock to the portfolio
   * @param volume       The volume you want to buy and put in to the portfolio
   * @param portfolio    The name of the portfolio you want to use
   */
  public BuyStock(String tickerSymbol,
                                String time,
                                String volume,
                                String portfolio) {
    this.tickerSymbol = tickerSymbol;
    this.time = time;
    this.volume = volume;
    this.portfolio = portfolio;
  }

  /**
   * Call buyEquity to realize the execute of IRetrievableStrategyTraderModel.
   *
   * @param m the ITraderModel
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      m.buyEquity(tickerSymbol, volume, time,
              portfolio);
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_BUY_STOCK_FAIL.getMsg());
    }
  }
}