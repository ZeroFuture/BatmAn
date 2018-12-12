package trader.util.portfolio;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import trader.ErrorMsg;
import trader.util.Price;
import trader.util.StockGenerator;
import trader.util.TimeStamp;
import trader.util.TradeTimeStamp;
import trader.util.USDPrice;
import trader.util.equity.Equity;
import trader.util.equity.USStock;

public class StockPortfolio implements Portfolio {
  final String portfolioTag;
  final Map<String, List<USStock>> portfolio;

  /**
   * Constructor for a stock portfolio.
   *
   * @param portfolioTag name of the portfolio.
   * @throws IllegalArgumentException if invalid input.
   */
  public StockPortfolio(String portfolioTag) throws IllegalArgumentException {
    if (portfolioTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    this.portfolioTag = portfolioTag;
    portfolio = new HashMap<>();
  }

  /**
   * Add stock to portfolio.
   *
   * @param tickerSymbol stock ticker symbol to add.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void add(String tickerSymbol) throws IllegalArgumentException {
    if (tickerSymbol == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    portfolio.putIfAbsent(tickerSymbol, new LinkedList<>());
  }

  /**
   * Buy stock to portfolio.
   *
   * @param target stock to buy.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void buy(Equity target) throws IllegalArgumentException {
    if (target == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!(target instanceof USStock)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_TYPE_INPUT.getMsg());
    }
    String tickerSymbol = target.getEquitySymbol();
    List<USStock> list = portfolio.getOrDefault(tickerSymbol, new LinkedList<>());
    list.add((USStock) target);
    portfolio.put(tickerSymbol, list);
  }

  /**
   * View the current portfolio of the account.
   *
   * @return account as String.
   */
  @Override
  public String view() {
    StringBuilder sb = new StringBuilder();
    sb.append("Portfolio Name: ");
    sb.append(portfolioTag);
    for (String tickerSymbol : portfolio.keySet()) {
      List<USStock> list = portfolio.get(tickerSymbol);
      sb.append("\n").append("TickerSymbol: ").append(tickerSymbol);
      for (int i = 0; i < list.size(); i++) {
        sb.append("\n").append(i).append(": ").append(list.get(i).toString());
      }
    }
    sb.append("\n").append("Current Cost Basis: ").append(getCost().toString());
    return sb.toString();
  }

  /**
   * Evaluate the value of portfolio in USD given a timestamp.
   *
   * @param timeStamp timeStamp want to be evaluated.
   * @return value of the portfolio in price.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public Price evaluate(TimeStamp timeStamp) throws IllegalArgumentException {
    if (timeStamp == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!(timeStamp instanceof TradeTimeStamp)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_TYPE_INPUT.getMsg());
    }
    BigDecimal totalAmount = new BigDecimal("0");
    TradeTimeStamp tradeTimeStamp = (TradeTimeStamp) timeStamp;
    StockGenerator stockGenerator = new StockGenerator();
    for (String tickerSymbol : portfolio.keySet()) {
      List<USStock> list = portfolio.get(tickerSymbol);
      for (USStock stock : list) {
        if (stock.getTimeStamp().compareTo(tradeTimeStamp) <= 0) {
          USStock currentStock = (USStock) stockGenerator.generateEquity(stock.getEquitySymbol(),
                  timeStamp, stock.getVolume());
          BigDecimal currentAmount = currentStock.getTotalValue().getAmount();
          totalAmount = totalAmount.add(currentAmount);
        }
      }
    }
    return new USDPrice(totalAmount);
  }

  /**
   * Get the total cost basis of the portfolio.
   *
   * @return cost basis as Price.
   */
  @Override
  public Price getCost() {
    BigDecimal totalAmount = new BigDecimal("0");
    for (String tickerSymbol : portfolio.keySet()) {
      List<USStock> list = portfolio.get(tickerSymbol);
      for (USStock stock : list) {
        totalAmount = totalAmount.add(stock.getTotalValue().getAmount());
      }
    }
    return new USDPrice(totalAmount);
  }

  /**
   * Get the name tag of the portfolio.
   *
   * @return name tag as string.
   */
  @Override
  public String getNameTag() {
    return portfolioTag;
  }

  /**
   * Get all the name of the equity in the portfolio.
   *
   * @return name of equities in the portfolio.
   */
  @Override
  public String getEquityNameTags() {
    StringBuilder sb = new StringBuilder();
    sb.append("Current Stocks in Portfolio ").append(portfolioTag).append(": ");
    for (String tickerSymbol : portfolio.keySet()) {
      sb.append("\n").append(tickerSymbol);
    }
    return sb.toString();
  }
}
