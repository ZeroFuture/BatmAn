package trader.controller.commands;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * BuyStockWithCommission class to represent buy with commission command. BuyStockWithCommission is
 * the options that users have in our trading system. It will look up the API to find the
 * stock information, then creating a real stock data based on the user input of the stock
 * information including tickerSymbol, the time you want to buy the stock to the portfolio,
 * the volume you choose to buy in the portfolio and the name of the portfolio you want
 * to put your stock in.
 */
public class BuyStockWithCommission implements CommandInterface {
  private final String tickerSymbol;
  private final String time;
  private final String volume;
  private final String portfolio;
  private final String commission;

  /**
   * The constructor of BuyStockWithCommission.
   *
   * @param tickerSymbol The symbol of the stocks to be added
   * @param time         The time you want to buy the stock to the portfolio
   * @param volume       The volume you want to buy and put in to the portfolio
   * @param portfolio    The name of the portfolio you want to use
   */
  public BuyStockWithCommission(String commission,
                                String tickerSymbol,
                                String time,
                                String volume,
                                String portfolio) {
    this.tickerSymbol = tickerSymbol;
    this.time = time;
    this.volume = volume;
    this.portfolio = portfolio;
    this.commission = commission;
  }

  /**
   * Call buyEquity to realize the execute of ITrader.
   *
   * @param m the ITraderModel
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      m.buyEquityWithCommission(tickerSymbol, volume, time,
              portfolio, commission);
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_BUY_STOCK_WITH_COMMISSION_FAIL.getMsg());
    }
  }
}