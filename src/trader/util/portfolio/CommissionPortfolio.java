package trader.util.portfolio;

import trader.util.equity.Equity;
import trader.util.Price;

/**
 * This CommissionPortfolio represent a portfolio that can buy with commission or weight of stocks.
 */
public interface CommissionPortfolio extends Portfolio {
  /**
   * Add equity with commission fee.
   *
   * @param target     target equity.
   * @param commission commission fee to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  void buyWithCommission(Equity target, Price commission) throws IllegalArgumentException;

  /**
   * Get commission cost of the portfolio.
   *
   * @return commission cost as Price.
   */
  Price getCommissionCost();

  /**
   * Get total cost equals to portfolio cost plus commission cost.
   *
   * @return totalCost as Price.
   */
  Price getTotalCost();
}
