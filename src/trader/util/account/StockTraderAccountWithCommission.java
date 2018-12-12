package trader.util.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

import trader.ErrorMsg;
import trader.util.equity.Equity;
import trader.util.equity.USStock;
import trader.util.portfolio.CommissionPortfolio;
import trader.util.portfolio.StockCommissionPortfolio;
import trader.util.Price;
import trader.util.USDPrice;
import trader.util.portfolio.StockPortfolio;

/**
 * Trader account with commission.
 */
public class StockTraderAccountWithCommission extends StockTraderAccount
        implements AccountWithCommission {

  /**
   * Constructor for trader account with commission.
   */
  public StockTraderAccountWithCommission() {
    traderAccount = new ArrayList<>();
    portfolioNameSet = new HashSet<>();
    traderAccount.add(new StockCommissionPortfolio(defaultNameTag));
    portfolioNameSet.add(defaultNameTag);
  }

  /**
   * Buy Equity with commission cost.
   * @param equity           equity to be added.
   * @param commission       commission in this transaction.
   * @param portfolioNameTag portfolio to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void buyEquityWithCommission(Equity equity, Price commission, String portfolioNameTag)
          throws IllegalArgumentException {
    if (equity == null || portfolioNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!(commission instanceof USDPrice) || !(equity instanceof USStock)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_TYPE_INPUT.getMsg());
    }
    if (!portfolioNameSet.contains(portfolioNameTag)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_ADD_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      CommissionPortfolio commissionPortfolio = (CommissionPortfolio) portfolio;
      if (commissionPortfolio.getNameTag().equals(portfolioNameTag)) {
        commissionPortfolio.buyWithCommission(equity, commission);
        return;
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_ADD_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * Add an empty portfolio with the portfolioNameTag.
   *
   * @param portfolioNameTag portfolio to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void addPortfolio(String portfolioNameTag) throws IllegalArgumentException {
    if (portfolioNameSet.contains(portfolioNameTag)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_ADD_PORTFOLIO_DUPLICATE_NAMETAG.getMsg());
    }
    traderAccount.add(new StockCommissionPortfolio(portfolioNameTag));
    portfolioNameSet.add(portfolioNameTag);
  }

  /**
   * Get account commission cost.
   *
   * @return account commission cost as Price.
   */
  @Override
  public Price getAccountCommissionCost() {
    BigDecimal totalValue = new BigDecimal("0");
    for (StockPortfolio portfolio : traderAccount) {
      CommissionPortfolio commissionPortfolio = ((CommissionPortfolio) portfolio);
      USDPrice currentPortfolioValue = (USDPrice) commissionPortfolio.getCommissionCost();
      totalValue = totalValue.add(currentPortfolioValue.getAmount());
    }
    return new USDPrice(totalValue);
  }

  /**
   * Get account total cost.
   *
   * @return account total cost as Price.
   */
  @Override
  public Price getAccountTotalCost() {
    BigDecimal totalValue = new BigDecimal("0");
    for (StockPortfolio portfolio : traderAccount) {
      CommissionPortfolio commissionPortfolio = ((CommissionPortfolio) portfolio);
      USDPrice currentPortfolioValue = (USDPrice) commissionPortfolio.getTotalCost();
      totalValue = totalValue.add(currentPortfolioValue.getAmount());
    }
    return new USDPrice(totalValue);
  }

  /**
   * Get Portfolio total cost given portfolio name tag.
   *
   * @param portfolioNameTag portfolio to evaluate.
   * @return Portfolio total cost as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public Price getPortfolioTotalCost(String portfolioNameTag) throws IllegalArgumentException {
    if (portfolioNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!portfolioNameSet.contains(portfolioNameTag)) {
      throw new IllegalArgumentException(
              ErrorMsg.INVALID_EVAL_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      CommissionPortfolio commissionPortfolio = ((CommissionPortfolio) portfolio);
      if (commissionPortfolio.getNameTag().equals(portfolioNameTag)) {
        return commissionPortfolio.getTotalCost();
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_EVAL_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * Get Portfolio commission cost given portfolio name tag.
   * @param portfolioNameTag portfolio to evaluate.
   * @return Portfolio commission cost as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public Price getPortfolioCommissionCost(String portfolioNameTag) throws IllegalArgumentException {
    if (portfolioNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!portfolioNameSet.contains(portfolioNameTag)) {
      throw new IllegalArgumentException(
              ErrorMsg.INVALID_EVAL_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      CommissionPortfolio commissionPortfolio = ((CommissionPortfolio) portfolio);
      if (commissionPortfolio.getNameTag().equals(portfolioNameTag)) {
        return commissionPortfolio.getCommissionCost();
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_EVAL_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
  }
}
