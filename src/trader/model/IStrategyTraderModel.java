package trader.model;

import java.util.Map;

/**
 * This interface provide necessary functions to make the model compatible with commission
 * and investment strategies.
 */
public interface IStrategyTraderModel extends ITraderModel {

  /**
   * Add a real equity to given portfolio with commission.
   *
   * @param tickerSymbol  ticker symbol of the stock to buy.
   * @param volume        shares of the stock to buy.
   * @param timeStamp     time of the stock bought.
   * @param portfolioName portfolioName of the stock to buy.
   * @throws IllegalArgumentException if invalid input.
   */
  void buyEquityWithCommission(String tickerSymbol, String volume, String timeStamp,
                               String portfolioName, String commission)
          throws IllegalArgumentException;

  /**
   * Add a weighted investment strategy to the account.
   * @param strategyName strategyName.
   * @param weightedStocks weightedStocks.
   * @param capital capital.
   * @param commission commission.
   * @param investmentTime investmentTime.
   * @throws IllegalArgumentException if invalid input.
   */
  void addWeightedInvestmentStrategy(String strategyName, Map<String, String> weightedStocks,
                                     String capital, String commission, String investmentTime)
          throws IllegalArgumentException;

  /**
   * Add a dollar averaging cost strategy to the account.
   * @param strategyName strategyName.
   * @param weightedStocks weightedStocks.
   * @param capitalPerInvestment capitalPerInvestment.
   * @param commission commission.
   * @param startTime startTime.
   * @param endTime endTime.
   * @param frequency frequency.
   * @throws IllegalArgumentException if invalid input.
   */
  void addDollarAveragingCostStrategy(String strategyName, Map<String, String> weightedStocks,
                                      String capitalPerInvestment, String commission,
                                      String startTime, String endTime, String frequency)
          throws IllegalArgumentException;

  /**
   * Apply a Strategy in the account to a specific portfolio.
   * @param strategyName strategyName.
   * @param portfolioName portfolioName.
   * @throws IllegalArgumentException if invalid input.
   */
  void applyStrategy(String strategyName, String portfolioName) throws IllegalArgumentException;

  /**
   * View all the Strategies in the the Account.
   * @return all strategies view.
   */
  String viewAllStrategies();

  /**
   * View all strategy name tags.
   * @return all strategy names.
   */
  String viewStrategyNameTags();

  /**
   * Display the cost basis of account.
   * @return cost as String.
   */
  @Override
  String getAccountCostBasis();

  /**
   * Display the cost basis of portfolio.
   * @param portfolioName portfolio name to find.
   * @return cost as String.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  String getPortfolioCostBasis(String portfolioName) throws IllegalArgumentException;
}
