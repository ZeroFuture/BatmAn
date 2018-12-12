package trader.util.strategy;

import trader.util.portfolio.CommissionPortfolio;

/**
 * This interface offers investment strategies given desired portfolio and capital.
 */
public interface Strategy {
  /**
   * Execute Strategy in the given portfolio.
   * @throws IllegalArgumentException if Invalid input.
   */
  void executeStrategy(CommissionPortfolio portfolio) throws IllegalArgumentException;

  /**
   * Get Strategy Name Tag.
   * @return Strategy name.
   */
  String getNameTag();

  /**
   * View Strategy.
   *
   * @return view as String.
   */
  String view();
}
