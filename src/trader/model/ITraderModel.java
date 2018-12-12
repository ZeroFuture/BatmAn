package trader.model;

/**
 * This is the interface of the ITrader model.
 */
public interface ITraderModel {
  /**
   * create a portfolio given name in model.
   *
   * @param name portfolio name.
   * @throws IllegalArgumentException if invalid input.
   */
  void createPortfolio(String name) throws IllegalArgumentException;

  /**
   * Add an equity to given portfolio.
   *
   * @param tickerSymbol  equity name to add.
   * @param portfolioName portfolio name to add.
   * @throws IllegalArgumentException if invalid input.
   */
  void addEquity(String tickerSymbol, String portfolioName) throws IllegalArgumentException;

  /**
   * Buy an equity to given portfolio.
   *
   * @param tickerSymbol  ticker symbol of the stock to buy.
   * @param volume        shares of the stock to buy.
   * @param timeStamp     time of the stock bought.
   * @param portfolioName portfolioName of the stock to buy.
   * @throws IllegalArgumentException if invalid input.
   */
  void buyEquity(String tickerSymbol, String volume, String timeStamp, String portfolioName)
          throws IllegalArgumentException;

  /**
   * View all the portfolio in the account.
   *
   * @return account view as String.
   */
  String viewAccount();

  /**
   * View portfolio information given portfolio name tag.
   * @param portfolioName portfolio to view.
   * @return view.
   * @throws IllegalArgumentException if invalid input.
   */
  String viewPortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * Evaluate total worth given timestamp and portfolioName.
   *
   * @param timeStamp     timeStamp to evaluate.
   * @param portfolioName portfolio name to find.
   * @return total worth as String.
   * @throws IllegalArgumentException if invalid input.
   */
  String evaluatePortfolio(String timeStamp, String portfolioName) throws IllegalArgumentException;

  /**
   * Display the cost basis of portfolio.
   * @param portfolioName portfolio name to find.
   * @return cost as String.
   * @throws IllegalArgumentException if invalid input.
   */
  String getPortfolioCostBasis(String portfolioName) throws IllegalArgumentException;

  /**
   * Evaluate total worth of account given timestamp and display the cost basis.
   *
   * @param timeStamp timeStamp to evaluate.
   * @return total worth as String.
   * @throws IllegalArgumentException if invalid input.
   */
  String evaluateAccount(String timeStamp) throws IllegalArgumentException;


  /**
   * Display the cost basis of account.
   * @return cost as String.
   */
  String getAccountCostBasis();

  /**
   * Get all the portfolio name tags.
   *
   * @return all the portfolio name tags as string.
   */
  String viewPortfolioNameTags();

}
