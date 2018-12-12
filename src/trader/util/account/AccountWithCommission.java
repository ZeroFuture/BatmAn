package trader.util.account;

import trader.util.equity.Equity;
import trader.util.Price;

public interface AccountWithCommission extends Account {
  /**
   * Get account commission cost.
   *
   * @return account commission cost as Price.
   */
  Price getAccountCommissionCost();

  /**
   * Get account total cost.
   *
   * @return account total cost as Price.
   */
  Price getAccountTotalCost();

  /**
   * Get Portfolio total cost given portfolio name tag.
   *
   * @param portfolioNameTag portfolio to evaluate.
   * @return Portfolio total cost as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  Price getPortfolioTotalCost(String portfolioNameTag) throws IllegalArgumentException;

  /**
   * Get Portfolio commission cost given portfolio name tag.
   *
   * @param portfolioNameTag portfolio to evaluate.
   * @return Portfolio commission cost as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  Price getPortfolioCommissionCost(String portfolioNameTag) throws IllegalArgumentException;

  /**
   * Buy Equity with commission cost.
   * @param equity           equity to be added.
   * @param commission       commission in this transaction.
   * @param portfolioNameTag portfolio to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  void buyEquityWithCommission(Equity equity, Price commission, String portfolioNameTag)
          throws IllegalArgumentException;
}
