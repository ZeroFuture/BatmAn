package trader.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import trader.ErrorMsg;
import trader.util.account.StockTraderAccount;
import trader.util.equity.Equity;
import trader.util.StockGenerator;
import trader.util.TimeStamp;
import trader.util.TradeTimeStamp;
import trader.util.USDPrice;

/**
 * This is the main abstract trader model.
 */
public abstract class AbstractStockTraderModel implements ITraderModel {
  StockTraderAccount mainAccount;
  static final StockGenerator generator = new StockGenerator();

  /**
   * create a portfolio given name in model.
   *
   * @param name portfolio name.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void createPortfolio(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (name.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    mainAccount.addPortfolio(name);
  }

  /**
   * Add an equity to given portfolio.
   *
   * @param tickerSymbol  equity name to add.
   * @param portfolioName portfolio name to add.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void addEquity(String tickerSymbol, String portfolioName) throws IllegalArgumentException {
    if (tickerSymbol == null || portfolioName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (tickerSymbol.isEmpty() || portfolioName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    mainAccount.addEquity(tickerSymbol, portfolioName);
  }

  /**
   * Buy an equity to given portfolio.
   *
   * @param tickerSymbol  ticker symbol of the stock to buy.
   * @param volume        shares of the stock to buy.
   * @param timeStamp     time of the stock bought.
   * @param portfolioName portfolioName of the stock to buy.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void buyEquity(String tickerSymbol, String volume,
                        String timeStamp, String portfolioName)
          throws IllegalArgumentException {
    if (tickerSymbol == null || volume == null
            || timeStamp == null || portfolioName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (tickerSymbol.isEmpty() || volume.isEmpty()
            || timeStamp.isEmpty() || portfolioName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    BigInteger shares = sharesParser(volume);
    TradeTimeStamp stockTimeStamp = tradeTimeStampParser(timeStamp);
    Equity equity = generator.generateEquity(tickerSymbol, stockTimeStamp, shares);
    mainAccount.buyEquity(equity, portfolioName);
  }

  /**
   * View all the portfolio in the account.
   *
   * @return account view as String.
   */
  @Override
  public String viewAccount() {
    return "\n" + mainAccount.viewAccount();
  }

  /**
   * View portfolio information given portfolio name tag.
   *
   * @param portfolioName portfolio to view.
   * @return view.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public String viewPortfolio(String portfolioName) throws IllegalArgumentException {
    if (portfolioName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (portfolioName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    return "\n" + mainAccount.viewPortfolio(portfolioName);
  }

  /**
   * Display the cost basis of account.
   * @return cost as String.
   */
  @Override
  public String getAccountCostBasis() {
    String costBasis = "Cost Basis of Account : " + mainAccount.getAccountCost().toString();
    return "\n" + costBasis;
  }


  /**
   * Display the cost basis.
   *
   * @param portfolioName portfolio name to find.
   * @return cost as String.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public String getPortfolioCostBasis(String portfolioName) throws IllegalArgumentException {
    if (portfolioName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (portfolioName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    String costBasis = "Cost Basis of Portfolio " + portfolioName
            + " : " + mainAccount.getPortfolioCost(portfolioName).toString();
    return "\n" + costBasis;
  }

  /**
   * Get all the portfolio name tags.
   *
   * @return all the portfolio name tags as string.
   */
  @Override
  public String viewPortfolioNameTags() {
    return "\n" + mainAccount.viewAllPortfolioTag();
  }

  /**
   * Evaluate total worth given timestamp and portfolioName.
   *
   * @param timeStamp     timeStamp to evaluate.
   * @param portfolioName portfolio name to find.
   * @return total worth as String.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public String evaluatePortfolio(String timeStamp, String portfolioName)
          throws IllegalArgumentException {
    if (timeStamp == null || portfolioName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (timeStamp.isEmpty() || portfolioName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    TimeStamp stockTimeStamp = tradeTimeStampParser(timeStamp);
    String netWorth = "Net Worth of Portfolio " + portfolioName
            + " at time " + stockTimeStamp.toString()
            + " : " + mainAccount.evaluatePortfolio(stockTimeStamp, portfolioName).toString();
    return "\n" + netWorth;
  }

  /**
   * Evaluate total worth of account given timestamp and display the cost basis.
   *
   * @param timeStamp timeStamp to evaluate.
   * @return total worth as String.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public String evaluateAccount(String timeStamp) throws IllegalArgumentException {
    if (timeStamp == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (timeStamp.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    TimeStamp stockTimeStamp = tradeTimeStampParser(timeStamp);
    String netWorth = "Net Worth of Account at time " + stockTimeStamp.toString()
            + " : " + mainAccount.evaluateAccount(stockTimeStamp).toString();
    return "\n" + netWorth;
  }

  /**
   * Helper function to parse String to timeStamp.
   *
   * @param timeStamp timeStamp string to be parse.
   * @return timeStamp.
   */
  TradeTimeStamp tradeTimeStampParser(String timeStamp) {
    TradeTimeStamp stockTimeStamp;
    try {
      String[] date = timeStamp.split("/");
      int month = Integer.valueOf(date[0]);
      int day = Integer.valueOf(date[1]);
      int year = Integer.valueOf(date[2]);
      stockTimeStamp = new TradeTimeStamp(year, month, day);
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.FAIL_INIT_STOCK_INVALID_TIMESTAMP.getMsg());
    }
    return stockTimeStamp;
  }

  /**
   * Helper function to parse String to BigInteger.
   *
   * @param volume string to be parse.
   * @return BigInteger of the String.
   */
  BigInteger sharesParser(String volume) {
    BigInteger shares;
    try {
      shares = new BigInteger(volume);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(ErrorMsg.FAIL_INIT_STOCK_INVALID_VOLUME.getMsg());
    }
    return shares;
  }

  /**
   * Helper function to parse String to Price.
   *
   * @param stockPrice price string to be parse.
   * @return Price.
   */
  USDPrice usdPriceParser(String stockPrice) {
    BigDecimal price;
    try {
      price = new BigDecimal(stockPrice);
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.FAIL_INIT_STOCK_INVALID_PRICE.getMsg());
    }
    return new USDPrice(price);
  }
}
