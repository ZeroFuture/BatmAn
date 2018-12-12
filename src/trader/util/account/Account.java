package trader.util.account;

import trader.util.equity.Equity;
import trader.util.Price;
import trader.util.TimeStamp;

/**
 * This interface represents a trader account.
 */
public interface Account {

  /**
   * Add equity to the portfolio.
   *
   * @param tickerSymbol     equity to be added.
   * @param portfolioNameTag portfolio to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  void addEquity(String tickerSymbol, String portfolioNameTag) throws IllegalArgumentException;

  /**
   * Buy equity to the portfolio.
   *
   * @param equity           equity to be bought.
   * @param portfolioNameTag portfolio to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  void buyEquity(Equity equity, String portfolioNameTag) throws IllegalArgumentException;

  /**
   * Add an empty portfolio with the portfolioNameTag.
   *
   * @param portfolioNameTag portfolio to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  void addPortfolio(String portfolioNameTag) throws IllegalArgumentException;

  /**
   * View all the portfolio information in the account.
   *
   * @return view as String.
   */
  String viewAccount();

  /**
   * View the portfolio information in the account given name tag.
   *
   * @param portfolioNameTag portfolio to view.
   * @return View all the portfolio information in the account.
   * @throws IllegalArgumentException if invalid input.
   */
  String viewPortfolio(String portfolioNameTag) throws IllegalArgumentException;

  /**
   * Evaluate all the portfolio in the account.
   *
   * @param timeStamp time to be evaluated.
   * @return totalValue as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  Price evaluateAccount(TimeStamp timeStamp) throws IllegalArgumentException;

  /**
   * Evaluate the portfolio.
   *
   * @param timeStamp        time to be evaluated.
   * @param portfolioNameTag portfolio to be added.
   * @return totalValue as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  Price evaluatePortfolio(TimeStamp timeStamp, String portfolioNameTag)
          throws IllegalArgumentException;

  /**
   * Get all the cost in the account.
   *
   * @return account cost as Price.
   */
  Price getAccountCost();

  /**
   * Get portfolio cost.
   *
   * @param portfolioNameTag portfolio to get cost.
   * @return portfolio cost as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  Price getPortfolioCost(String portfolioNameTag) throws IllegalArgumentException;

  /**
   * View all the portfolio name tag in the account.
   *
   * @return all name tag as string.
   */
  String viewAllPortfolioTag();
}
