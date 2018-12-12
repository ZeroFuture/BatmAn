package trader.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import trader.ErrorMsg;
import trader.util.account.RetrievableStrategyAccount;
import trader.util.account.StrategyStockTraderAccount;
import trader.util.equity.USStock;
import trader.util.TradeTimeStamp;
import trader.util.USDPrice;
import trader.util.strategy.DACStockStrategy;
import trader.util.strategy.FrequencyNode;
import trader.util.strategy.WeightedStockStrategy;

/**
 * Strategy model that supports investment strategies and commission fee.
 */
public class StrategyStockTraderModel extends AbstractStockTraderModel
        implements IRetrievableStrategyTraderModel {
  private final RetrievableStrategyAccount strategyAccount;

  /**
   * Constructor for StrategyStockTraderModel.
   */
  private StrategyStockTraderModel() {
    this.mainAccount = new StrategyStockTraderAccount();
    strategyAccount = (StrategyStockTraderAccount) mainAccount;
  }

  /**
   * get builder.
   *
   * @return builder.
   */
  public static StrategyStockTraderModelBuilder getBuilder() {
    return new StrategyStockTraderModelBuilder();
  }

  /**
   * builder class.
   */
  public static class StrategyStockTraderModelBuilder implements
          StrategyStockTraderOperationBuilder {

    /**
     * constructor.
     */
    private StrategyStockTraderModelBuilder() {
      new StrategyStockTraderModel();
    }

    /**
     * build.
     *
     * @return model.
     */
    @Override
    public IRetrievableStrategyTraderModel build() {
      return new StrategyStockTraderModel();
    }
  }

  /**
   * Add a real equity to given portfolio with commission.
   *
   * @param tickerSymbol  ticker symbol of the stock to buy.
   * @param volume        shares of the stock to buy.
   * @param timeStamp     time of the stock bought.
   * @param portfolioName portfolioName of the stock to buy.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void buyEquityWithCommission(String tickerSymbol, String volume, String timeStamp,
                                      String portfolioName, String commission)
          throws IllegalArgumentException {
    if (tickerSymbol == null || volume == null || commission == null
            || timeStamp == null || portfolioName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (tickerSymbol.isEmpty() || volume.isEmpty()
            || timeStamp.isEmpty() || portfolioName.isEmpty() || commission.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    BigInteger shares = sharesParser(volume);
    TradeTimeStamp stockTimeStamp = tradeTimeStampParser(timeStamp);
    USStock equity = (USStock) generator.generateEquity(tickerSymbol, stockTimeStamp, shares);
    USDPrice commissionFee = usdPriceParser(commission);
    strategyAccount.buyEquityWithCommission(equity, commissionFee, portfolioName);
  }

  /**
   * Add a weighted investment strategy to the account.
   *
   * @param strategyName   strategyName.
   * @param weightedStocks weightedStocks.
   * @param capital        capital.
   * @param commission     commission.
   * @param investmentTime investmentTime.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void addWeightedInvestmentStrategy(String strategyName,
                                            Map<String, String> weightedStocks,
                                            String capital, String commission,
                                            String investmentTime) throws IllegalArgumentException {
    if (strategyName == null || weightedStocks == null || capital == null || commission == null
            || investmentTime == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (strategyName.isEmpty() || weightedStocks.isEmpty() || capital.isEmpty()
            || commission.isEmpty() || investmentTime.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    Map<String, BigDecimal> stockWeights = weightedStocksParser(weightedStocks);
    USDPrice investmentCapital = usdPriceParser(capital);
    USDPrice investmentCommission = usdPriceParser(commission);
    TradeTimeStamp timeStamp = tradeTimeStampParser(investmentTime);
    WeightedStockStrategy weightedStockStrategy = new WeightedStockStrategy(strategyName,
            stockWeights,  timeStamp, investmentCapital, investmentCommission);
    strategyAccount.addStrategy(weightedStockStrategy);
  }

  /**
   * Add a dollar averaging cost strategy to the account.
   *
   * @param strategyName         strategyName.
   * @param weightedStocks       weightedStocks.
   * @param capitalPerInvestment capitalPerInvestment.
   * @param commission           commission.
   * @param startTime            startTime.
   * @param endTime              endTime.
   * @param frequency            frequency.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void addDollarAveragingCostStrategy(String strategyName,
                                             Map<String, String> weightedStocks,
                                             String capitalPerInvestment, String commission,
                                             String startTime, String endTime, String frequency)
          throws IllegalArgumentException {
    if (strategyName == null || weightedStocks == null || capitalPerInvestment == null
            || commission == null || startTime == null || endTime == null || frequency == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (strategyName.isEmpty() || weightedStocks.isEmpty() || capitalPerInvestment.isEmpty()
            || commission.isEmpty() || startTime.isEmpty() || endTime.isEmpty()
            || frequency.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    Map<String, BigDecimal> stockWeights = weightedStocksParser(weightedStocks);
    USDPrice investmentCapital = usdPriceParser(capitalPerInvestment);
    USDPrice investmentCommission = usdPriceParser(commission);
    TradeTimeStamp startTimeStamp = tradeTimeStampParser(startTime);
    TradeTimeStamp endTimeStamp = null;
    if (!endTime.equals("NA")) {
      endTimeStamp = tradeTimeStampParser(endTime);
    }
    FrequencyNode frequencyNode = FrequencyNode.parse(frequency);
    DACStockStrategy dacStockStrategy = new DACStockStrategy(strategyName, stockWeights,
            startTimeStamp, endTimeStamp, frequencyNode, investmentCapital,
            investmentCommission);
    strategyAccount.addStrategy(dacStockStrategy);
  }

  /**
   * Apply a Strategy in the account to a specific portfolio.
   *
   * @param strategyName  strategyName.
   * @param portfolioName portfolioName.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void applyStrategy(String strategyName, String portfolioName)
          throws IllegalArgumentException {
    if (strategyName == null || portfolioName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (strategyName.isEmpty() || portfolioName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    strategyAccount.applyStrategy(portfolioName, strategyName);
  }

  /**
   * View all the Strategies in the the Account.
   *
   * @return all strategies view.
   */
  @Override
  public String viewAllStrategies() {
    return "\n" + strategyAccount.viewAllStrategies();
  }

  /**
   * View all strategy name tags.
   *
   * @return all strategy names.
   */
  @Override
  public String viewStrategyNameTags() {
    return "\n" + strategyAccount.viewStrategyNameTags();
  }

  /**
   * Stock weight map parser.
   *
   * @param weightStocks map to parse.
   * @return parsed map.
   */
  private Map<String, BigDecimal> weightedStocksParser(Map<String, String> weightStocks) {
    Map<String, BigDecimal> res = new HashMap<>();
    for (String tickerSymbol : weightStocks.keySet()) {
      String weightStr = weightStocks.get(tickerSymbol);
      String percentStr = weightStr.substring(0, weightStr.length() - 1);
      BigDecimal percent = new BigDecimal(percentStr);
      res.put(tickerSymbol, percent.multiply(new BigDecimal("0.01")));
    }
    return res;
  }

  /**
   * Display the cost basis of account.
   * @return cost as String.
   */
  @Override
  public String getAccountCostBasis() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.getAccountCostBasis()).append("\n");
    sb.append("Commission Cost : ").append(strategyAccount.getAccountCommissionCost().toString())
            .append("\n");
    sb.append("Total Cost Basis : ").append(strategyAccount.getAccountTotalCost().toString());
    return sb.toString();
  }

  /**
   * Display the cost basis of portfolio.
   * @param portfolioName portfolio name to find.
   * @return cost as String.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public String getPortfolioCostBasis(String portfolioName) throws IllegalArgumentException {
    StringBuilder sb = new StringBuilder();
    sb.append(super.getPortfolioCostBasis(portfolioName)).append("\n");
    sb.append("Commission Cost : ").append(strategyAccount
            .getPortfolioCommissionCost(portfolioName).toString()).append("\n");
    sb.append("Total Cost Basis : ").append(strategyAccount
            .getPortfolioTotalCost(portfolioName).toString());
    return sb.toString();
  }


  /**
   * Save all portfolios.
   */
  @Override
  public void saveAllPortfolio() {
    strategyAccount.saveAllPortfolio();
  }

  /**
   * Save the portfolio given name.
   *
   * @param portfolioName portfolio to save.
   * @throws IllegalArgumentException if failed save.
   */
  @Override
  public void savePortfolio(String portfolioName) throws IllegalArgumentException {
    if (portfolioName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (portfolioName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    strategyAccount.savePortfolio(portfolioName);
  }

  /**
   * Save all strategies.
   */
  @Override
  public void saveAllStrategy() {
    strategyAccount.saveAllStrategy();
  }

  /**
   * Save the strategy given name.
   *
   * @param strategyName strategy to save.
   * @throws IllegalArgumentException if failed save.
   */
  @Override
  public void saveStrategy(String strategyName) throws IllegalArgumentException {
    if (strategyName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (strategyName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    strategyAccount.saveStrategy(strategyName);
  }

  /**
   * Retrieve all portfolio.
   */
  @Override
  public void retrieveAllPortfolio() {
    strategyAccount.retrieveAllPortfolio();
  }

  /**
   * Retrieve the given portfolio.
   *
   * @param portfolioName portfolio to retrieve.
   * @throws IllegalArgumentException if failed load.
   */
  @Override
  public void retrievePortfolio(String portfolioName) throws IllegalArgumentException {
    if (portfolioName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (portfolioName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    strategyAccount.retrievePortfolio(portfolioName);
  }

  /**
   * Retrieve all strategies.
   */
  @Override
  public void retrieveAllStrategy() {
    strategyAccount.retrieveAllStrategy();
  }

  /**
   * Retrieve the given strategy.
   *
   * @param strategyName strategy to retrieve.
   * @throws IllegalArgumentException if failed load.
   */
  @Override
  public void retrieveStrategy(String strategyName) throws IllegalArgumentException {
    if (strategyName == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (strategyName.isEmpty()) {
      throw new IllegalArgumentException(ErrorMsg.EMPTY_INPUT.getMsg());
    }
    strategyAccount.retrieveStrategy(strategyName);
  }
}
