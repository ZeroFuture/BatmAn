package trader.util.strategy;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;

import trader.ErrorMsg;
import trader.util.TradeTimeStamp;
import trader.util.USDPrice;
import trader.util.portfolio.CommissionPortfolio;

/**
 * DACStockStrategy implement Strategy.
 */
public class DACStockStrategy implements RetrievableStrategy {
  private final String strategyName;
  private final Map<String, BigDecimal> weightedStocks;
  private final USDPrice capitalPerInvestment;
  private final USDPrice commission;
  private final TradeTimeStamp startTime;
  private final TradeTimeStamp endTime;
  private TradeTimeStamp investmentTime;
  private final FrequencyNode frequencyNode;

  /**
   * Constructor for DACStockStrategy.
   *
   * @param name                 strategy name.
   * @param weightedStocks       weighted stocks.
   * @param startTime            investment time.
   * @param endTime              end investment time.
   * @param frequencyNode        freqNode.
   * @param capitalPerInvestment capital to invest.
   * @param commission           commission per transaction.
   * @throws IllegalArgumentException invalid input.
   */
  public DACStockStrategy(String name, Map<String, BigDecimal> weightedStocks,
                          TradeTimeStamp startTime, TradeTimeStamp endTime,
                          FrequencyNode frequencyNode, USDPrice capitalPerInvestment,
                          USDPrice commission)
          throws IllegalArgumentException {
    if (weightedStocks == null || startTime == null
            || capitalPerInvestment == null || commission == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    this.strategyName = name;
    this.weightedStocks = weightedStocks;
    this.capitalPerInvestment = capitalPerInvestment;
    this.commission = commission;
    this.startTime = startTime;
    investmentTime = startTime;
    if (endTime == null) {
      this.endTime = new TradeTimeStamp(LocalDate.MAX);
    } else {
      this.endTime = endTime;
    }
    this.frequencyNode = frequencyNode;
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
    WeightedStockStrategy weightedStockStrategy = new WeightedStockStrategy(strategyName,
            weightedStocks, investmentTime, capitalPerInvestment, commission);
    TradeTimeStamp timeStamp = new TradeTimeStamp(LocalDate.now().minusDays(1));
    if (endTime.compareTo(timeStamp) < 0) {
      timeStamp = endTime;
    }
    while (investmentTime.compareTo(timeStamp) <= 0) {
      try {
        weightedStockStrategy.executeStrategy(portfolio);
      } catch (IllegalArgumentException e) {
        if (e.getMessage().equals(ErrorMsg.FAIL_CRAW_DATA_HOLIDAY_TIME_STAMP.getMsg())) {
          LocalDate nextInvestmentTime = investmentTime.getLocalDate().plusDays(1);
          investmentTime = new TradeTimeStamp(nextInvestmentTime);
          weightedStockStrategy = new WeightedStockStrategy(strategyName, weightedStocks,
                  investmentTime, capitalPerInvestment, commission);
          continue;
        } else {
          throw new IllegalArgumentException(e.getMessage());
        }
      }
      investmentTimeIncrementer(frequencyNode.getFreqType(), frequencyNode.getFrequency());
      weightedStockStrategy = new WeightedStockStrategy(strategyName, weightedStocks,
              investmentTime, capitalPerInvestment, commission);
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
    sb.append("DACStrategy ").append(strategyName).append(": ").append("\n");
    sb.append("Stock Weights: ").append("\n");
    DecimalFormat df = new DecimalFormat("##.00");
    for (String stock : weightedStocks.keySet()) {
      sb.append("TickerSymbol: ").append(stock);
      BigDecimal weightInPercentage = weightedStocks.get(stock).multiply(new BigDecimal("100"));
      sb.append(" Stock Weight: ").append(df.format(weightInPercentage)).append("%").append("\n");
    }
    sb.append("Capital per Investment: ").append(capitalPerInvestment.toString()).append("\n");
    sb.append("Commission per Transaction: ").append(commission.toString()).append("\n");
    sb.append("Start Time: ").append(startTime.toString()).append("\n");
    if (endTime.getLocalDate().equals(LocalDate.MAX)) {
      sb.append("End Time: ").append("NA").append("\n");
    }
    else {
      sb.append("End Time: ").append(endTime.toString()).append("\n");
    }
    sb.append("Frequency Type: ").append(frequencyNode.getFreqType()).append("\n");
    sb.append("Frequency: ").append(frequencyNode.getFrequency());
    return sb.toString();
  }

  /**
   * Increment investmentTime.
   *
   * @param freqType  increment frequency type.
   * @param frequency increment frequency.
   * @throws IllegalArgumentException if invalid input.
   */
  private void investmentTimeIncrementer(char freqType, long frequency)
          throws IllegalArgumentException {
    LocalDate nextInvestmentTime;
    switch (freqType) {
      case 'D':
        nextInvestmentTime = investmentTime.getLocalDate().plusDays(frequency);
        break;
      case 'W':
        nextInvestmentTime = investmentTime.getLocalDate().plusWeeks(frequency);
        break;
      case 'M':
        nextInvestmentTime = investmentTime.getLocalDate().plusMonths(frequency);
        break;
      case 'Y':
        nextInvestmentTime = investmentTime.getLocalDate().plusYears(frequency);
        break;
      default:
        throw new IllegalArgumentException(ErrorMsg.INVALID_STRATEGY_FREQUENCY_TYPE.getMsg());
    }
    investmentTime = new TradeTimeStamp(nextInvestmentTime);
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
    String strategyFileHeader = "StrategyType,StockWeights,StartTime,EndTime," +
            "FrequencyType,Frequency,CapitalPerInvestment,Commission";
    FileWriter strategyFileWriter;
    try {
      strategyFileWriter = new FileWriter(strategyFilePath);
      strategyFileWriter.append(strategyFileHeader).append("\n");
      strategyFileWriter.append("DACInvestment").append(",");
      strategyFileWriter.append(weightedStocks.toString()
              .replace(",", "&")).append(",");
      strategyFileWriter.append(startTime.toString()).append(",");
      if (endTime.getLocalDate().equals(LocalDate.MAX)) {
        strategyFileWriter.append("NA").append(",");
      }
      else {
        strategyFileWriter.append(endTime.toString()).append(",");
      }
      strategyFileWriter.append(frequencyNode.getFreqType()).append(",");
      strategyFileWriter.append(Long.toString(frequencyNode.getFrequency())).append(",");
      strategyFileWriter.append(capitalPerInvestment.getAmount().toString()).append(",");
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
