package trader.util.strategy;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Map;

import trader.ErrorMsg;
import trader.util.equity.USStock;
import trader.util.StockGenerator;
import trader.util.TradeTimeStamp;
import trader.util.USDPrice;
import trader.util.portfolio.CommissionPortfolio;

/**
 * WeightedStockStrategy implement Strategy.
 */
public class WeightedStockStrategy implements RetrievableStrategy {
  private static final BigDecimal minTotalWeight = new BigDecimal("0.99");
  private static final BigDecimal maxTotalWeight = new BigDecimal("1.01");
  private static final StockGenerator generator = new StockGenerator();
  private final String strategyName;
  private final Map<String, BigDecimal> weightedStocks;
  private final TradeTimeStamp investmentTime;
  private final USDPrice capital;
  private final USDPrice commission;

  /**
   * Constructor for WeightedStockStrategy.
   *
   * @param name           strategy name.
   * @param weightedStocks weighted stocks.
   * @param timeStamp      investment time.
   * @param capital        capital to invest.
   * @param commission     commission per transaction.
   * @throws IllegalArgumentException invalid input.
   */
  public WeightedStockStrategy(String name, Map<String, BigDecimal> weightedStocks,
                               TradeTimeStamp timeStamp, USDPrice capital, USDPrice commission)
          throws IllegalArgumentException {
    if (weightedStocks == null || timeStamp == null || capital == null || commission == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    this.strategyName = name;
    this.investmentTime = timeStamp;
    this.weightedStocks = weightedStocks;
    this.capital = capital;
    this.commission = commission;
    BigDecimal totalWeight = new BigDecimal("0");
    for (BigDecimal weight : weightedStocks.values()) {
      totalWeight = totalWeight.add(weight);
    }
    if (totalWeight.compareTo(minTotalWeight) < 0 || totalWeight.compareTo(maxTotalWeight) > 0) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_WEIGHTED_STRATEGY_WEIGHT_MAP.getMsg());
    }
  }

  /**
   * Execute Strategy in the given portfolio.
   *
   * @throws IllegalArgumentException if Invalid input.
   */
  public void executeStrategy(CommissionPortfolio portfolio) throws IllegalArgumentException {
    if (portfolio == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    for (String tickerSymbol : weightedStocks.keySet()) {
      USStock stock = (USStock) generator.generateEquity(tickerSymbol,
              investmentTime, new BigInteger("1"));
      USDPrice unitPrice = (USDPrice) stock.getUnitPrice();
      BigDecimal weight = weightedStocks.get(tickerSymbol);
      BigDecimal cost = capital.getAmount().multiply(weight);
      BigInteger shares = cost.divide(unitPrice.getAmount(),
              BigDecimal.ROUND_FLOOR).toBigInteger();
      USStock toInvest = new USStock(tickerSymbol, unitPrice, investmentTime, shares);
      portfolio.buyWithCommission(toInvest, commission);
    }
  }

  /**
   * Get Strategy Name Tag.
   *
   * @return Strategy name.
   */
  @Override
  public String getNameTag() {
    return strategyName;
  }

  /**
   * View Strategy.
   *
   * @return view as String.
   */
  @Override
  public String view() {
    StringBuilder sb = new StringBuilder();
    sb.append("WeightedStrategy ").append(strategyName).append(": ").append("\n");
    sb.append("Stock Weights: ").append("\n");
    DecimalFormat df = new DecimalFormat("##.00");
    for (String stock : weightedStocks.keySet()) {
      sb.append("TickerSymbol: ").append(stock);
      BigDecimal weightInPercentage = weightedStocks.get(stock).multiply(new BigDecimal("100"));
      sb.append(" Stock Weight: ").append(df.format(weightInPercentage)).append("%").append("\n");
    }
    sb.append("Capital: ").append(capital.toString()).append("\n");
    sb.append("Commission: ").append(commission.toString()).append("\n");
    sb.append("Investment Time: ").append(investmentTime.toString());
    return sb.toString();
  }

  /**
   * Write current strategy to dest file.
   *
   * @param filePath filePath to write.
   * @throws IllegalArgumentException if write fails.
   */
  @Override
  public void write(String filePath) {
    String strategyFilePath = filePath + "/" + strategyName + ".csv";
    String strategyFileHeader = "StrategyType,StockWeights,InvestmentTime,Capital,Commission";
    FileWriter strategyFileWriter;
    try {
      strategyFileWriter = new FileWriter(strategyFilePath);
      strategyFileWriter.append(strategyFileHeader).append("\n");
      strategyFileWriter.append("WeightedInvestment").append(",");
      strategyFileWriter.append(weightedStocks.toString()
              .replace(",", "&")).append(",");
      strategyFileWriter.append(investmentTime.toString()).append(",");
      strategyFileWriter.append(capital.getAmount().toString()).append(",");
      strategyFileWriter.append(commission.getAmount().toString()).append("\n");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new IllegalArgumentException(ErrorMsg.FAILED_WRITE_STRATEGY.getMsg());
    }
    try {
      strategyFileWriter.flush();
      strategyFileWriter.close();
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.FAILED_WRITE_STRATEGY.getMsg());
    }
  }
}
