package trader.util.portfolio;

import trader.util.equity.Equity;
import trader.util.Price;
import trader.util.TimeStamp;

/**
 * Allow a user to create one or more portfolios and examine its composition. Buy shares of some
 * stock in a portfolio worth a certain amount at a certain date. Determine the total cost basis and
 * total value of a portfolio at a certain date.
 */
public interface Portfolio {
  /**
   * Add stock to portfolio.
   *
   * @param tickerSymbol stock ticker symbol to add.
   * @throws IllegalArgumentException if invalid input.
   */
  void add(String tickerSymbol) throws IllegalArgumentException;

  /**
   * Add equity to portfolio.
   *
   * @param target Equity to buy.
   * @throws IllegalArgumentException if invalid input.
   */
  void buy(Equity target) throws IllegalArgumentException;

  /**
   * View the current portfolio of the account.
   *
   * @return view.
   */
  String view();

  /**
   * Evaluate the value of portfolio in USD given a timestamp.
   *
   * @param timeStamp timeStamp want to be evaluated.
   * @return value of the portfolio in price.
   * @throws IllegalArgumentException if invalid input.
   */
  Price evaluate(TimeStamp timeStamp) throws IllegalArgumentException;

  /**
   * Get the total cost basis of the portfolio.
   *
   * @return cost basis as Price.
   */
  Price getCost();

  /**
   * Get the name tag of the portfolio.
   *
   * @return name tag as string.
   */
  String getNameTag();

  /**
   * Get all the name of the equity in the portfolio.
   *
   * @return name of equities in the portfolio.
   */
  String getEquityNameTags();
}
