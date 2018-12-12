package trader.util.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import trader.ErrorMsg;
import trader.util.equity.Equity;
import trader.util.equity.USStock;
import trader.util.Price;
import trader.util.TimeStamp;
import trader.util.TradeTimeStamp;
import trader.util.USDPrice;
import trader.util.portfolio.StockPortfolio;

/**
 * This class represents a stock trader account.
 */
public class StockTraderAccount implements Account {
  List<StockPortfolio> traderAccount;
  Set<String> portfolioNameSet;
  static String defaultNameTag = "Investment";

  /**
   * Constructor for StockTraderAccount.
   */
  public StockTraderAccount() {
    traderAccount = new ArrayList<>();
    portfolioNameSet = new HashSet<>();
    traderAccount.add(new StockPortfolio(defaultNameTag));
    portfolioNameSet.add(defaultNameTag);
  }

  /**
   * Add equity to the portfolio.
   *
   * @param tickerSymbol     equity to be added.
   * @param portfolioNameTag portfolio to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void addEquity(String tickerSymbol, String portfolioNameTag)
          throws IllegalArgumentException {
    if (tickerSymbol == null || portfolioNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      if (portfolio.getNameTag().equals(portfolioNameTag)) {
        portfolio.add(tickerSymbol);
        return;
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_ADD_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * Buy equity to the portfolio.
   *
   * @param equity           equity to be bought.
   * @param portfolioNameTag portfolio to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void buyEquity(Equity equity, String portfolioNameTag) throws IllegalArgumentException {
    if (equity == null || portfolioNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!(equity instanceof USStock)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_TYPE_INPUT.getMsg());
    }
    if (!portfolioNameSet.contains(portfolioNameTag)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_BUY_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      if (portfolio.getNameTag().equals(portfolioNameTag)) {
        portfolio.buy(equity);
        return;
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_BUY_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
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
    traderAccount.add(new StockPortfolio(portfolioNameTag));
    portfolioNameSet.add(portfolioNameTag);
  }

  /**
   * View all the portfolio information in the account.
   *
   * @return view as String.
   */
  @Override
  public String viewAccount() {
    StringBuilder sb = new StringBuilder();
    sb.append("Account Portfolios:");
    for (StockPortfolio portfolio : traderAccount) {
      sb.append("\n").append(portfolio.view());
    }
    return sb.toString();
  }

  /**
   * View the portfolio information in the account given name tag.
   *
   * @param portfolioNameTag portfolio to view.
   * @return View all the portfolio information in the account.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public String viewPortfolio(String portfolioNameTag) throws IllegalArgumentException {
    if (portfolioNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!portfolioNameSet.contains(portfolioNameTag)) {
      throw new IllegalArgumentException(
              ErrorMsg.INVALID_VIEW_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      if (portfolio.getNameTag().equals(portfolioNameTag)) {
        return portfolio.view();
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_VIEW_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * Evaluate all the portfolio in the account.
   *
   * @param timeStamp time to be evaluated.
   * @return totalValue as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public Price evaluateAccount(TimeStamp timeStamp) throws IllegalArgumentException {
    if (timeStamp == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!(timeStamp instanceof TradeTimeStamp)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_TYPE_INPUT.getMsg());
    }
    BigDecimal totalValue = new BigDecimal("0");
    for (StockPortfolio portfolio : traderAccount) {
      USDPrice currentPortfolioValue = (USDPrice) portfolio.evaluate(timeStamp);
      totalValue = totalValue.add(currentPortfolioValue.getAmount());
    }
    return new USDPrice(totalValue);
  }

  /**
   * Evaluate the portfolio.
   *
   * @param timeStamp        time to be evaluated.
   * @param portfolioNameTag portfolio to be added.
   * @return totalValue as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public Price evaluatePortfolio(TimeStamp timeStamp, String portfolioNameTag)
          throws IllegalArgumentException {
    if (portfolioNameTag == null || timeStamp == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!(timeStamp instanceof TradeTimeStamp)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_TYPE_INPUT.getMsg());
    }
    if (!portfolioNameSet.contains(portfolioNameTag)) {
      throw new IllegalArgumentException(
              ErrorMsg.INVALID_EVAL_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      if (portfolio.getNameTag().equals(portfolioNameTag)) {
        return portfolio.evaluate(timeStamp);
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_EVAL_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * Get all the cost in the account.
   *
   * @return account cost as Price.
   */
  @Override
  public Price getAccountCost() {
    BigDecimal totalValue = new BigDecimal("0");
    for (StockPortfolio portfolio : traderAccount) {
      Price currentPortfolioValue = portfolio.getCost();
      totalValue = totalValue.add(currentPortfolioValue.getAmount());
    }
    return new USDPrice(totalValue);
  }

  /**
   * Get portfolio cost.
   *
   * @param portfolioNameTag portfolio name.
   * @return portfolio cost as Price.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public Price getPortfolioCost(String portfolioNameTag) throws IllegalArgumentException {
    if (portfolioNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!portfolioNameSet.contains(portfolioNameTag)) {
      throw new IllegalArgumentException(
              ErrorMsg.INVALID_EVAL_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      if (portfolio.getNameTag().equals(portfolioNameTag)) {
        return portfolio.getCost();
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_EVAL_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * View all the portfolio name tag in the account.
   *
   * @return all name tag as string.
   */
  @Override
  public String viewAllPortfolioTag() {
    StringBuilder sb = new StringBuilder();
    sb.append("Portfolio NameTags:");
    for (String nameTag : portfolioNameSet) {
      sb.append("\n").append(nameTag);
    }
    return sb.toString();
  }

  /**
   * Get portfolio given portfolioNameTag.
   * @param portfolioNameTag portfolio to get.
   * @return StockPortfolio.
   */
  StockPortfolio getPortfolio(String portfolioNameTag) {
    if (portfolioNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!portfolioNameSet.contains(portfolioNameTag)) {
      throw new IllegalArgumentException(
              ErrorMsg.INVALID_GET_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      if (portfolio.getNameTag().equals(portfolioNameTag)) {
        return portfolio;
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_GET_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
  }
}
