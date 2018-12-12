package trader.util.account;

import trader.util.strategy.Strategy;

/**
 * StrategyAccount interface which support investment strategies.
 */
public interface StrategyAccount extends AccountWithCommission {

  /**
   * Add a Strategy to the account.
   * @param strategy strategy to add.
   * @throws IllegalArgumentException If invalid input.
   */
  void addStrategy(Strategy strategy) throws IllegalArgumentException;

  /**
   * Apply the strategy to a portfolio.
   * @param portfolioNameTag portfolio to apply.
   * @param strategyNameTag strategy to apply.
   * @throws IllegalArgumentException if invalid input.
   */
  void applyStrategy(String portfolioNameTag, String strategyNameTag)
          throws IllegalArgumentException;

  /**
   * View the strategy given strategy name.
   * @param strategyNameTag strategy to view.
   * @return strategy view.
   */
  String viewStrategy(String strategyNameTag) throws IllegalArgumentException;

  /**
   * View all strategies in the account.
   * @return all strategies.
   */
  String viewAllStrategies();

  /**
   * Get all strategy name tags.
   * @return all strategy name tags.
   */
  String viewStrategyNameTags();
}
