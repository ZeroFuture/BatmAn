package trader.util.account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import trader.ErrorMsg;
import trader.util.TradeTimeStamp;
import trader.util.USDPrice;
import trader.util.equity.USStock;
import trader.util.portfolio.CommissionPortfolio;
import trader.util.portfolio.RetrievableCommissionPortfolio;
import trader.util.portfolio.StockPortfolio;
import trader.util.strategy.DACStockStrategy;
import trader.util.strategy.FrequencyNode;
import trader.util.strategy.RetrievableStrategy;
import trader.util.strategy.Strategy;
import trader.util.strategy.WeightedStockStrategy;

/**
 * StrategyStockTraderAccount implements StrategyAccount.
 */
public class StrategyStockTraderAccount extends StockTraderAccountWithCommission
        implements RetrievableStrategyAccount {
  private final List<Strategy> strategies;
  private final Set<String> strategyNameSet;
  private static final String portfolioFilePath = "res/Portfolios";
  private static final String strategyFilePath = "res/Strategies";
  private static final String equityNamePath = "equityNames.csv";
  private static final String equitiesPath = "equities.csv";

  /**
   * Constructor for StrategyStockTraderAccount.
   */
  public StrategyStockTraderAccount() {
    strategies = new LinkedList<>();
    strategyNameSet = new HashSet<>();
    File res = new File("res");
    res.mkdir();
    File portfolioDir = new File(portfolioFilePath);
    portfolioDir.mkdir();
    File strategyDir = new File(strategyFilePath);
    strategyDir.mkdir();
  }

  /**
   * Add a Strategy to the account.
   *
   * @param strategy strategy to add.
   * @throws IllegalArgumentException If invalid input.
   */
  @Override
  public void addStrategy(Strategy strategy) throws IllegalArgumentException {
    if (strategy == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    strategies.add(strategy);
    strategyNameSet.add(strategy.getNameTag());
  }

  /**
   * Apply the strategy to a portfolio.
   *
   * @param portfolioNameTag portfolio to apply.
   * @param strategyNameTag  strategy to apply.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void applyStrategy(String portfolioNameTag, String strategyNameTag)
          throws IllegalArgumentException {
    if (portfolioNameTag == null || strategyNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!strategyNameSet.contains(strategyNameTag)) {
      throw new IllegalArgumentException(
              ErrorMsg.INVALID_APPLY_STRATEGY_NAMETAG_NOT_FOUND.getMsg());
    }
    if (!portfolioNameSet.contains(portfolioNameTag)) {
      addPortfolio(portfolioNameTag);
    }
    CommissionPortfolio portfolio = (CommissionPortfolio) getPortfolio(portfolioNameTag);
    for (Strategy strategy : strategies) {
      String strategyName = strategy.getNameTag();
      if (strategyName.equals(strategyNameTag)) {
        strategy.executeStrategy(portfolio);
        return;
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_APPLY_STRATEGY_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * View the strategy given strategy name.
   *
   * @param strategyNameTag strategy to view.
   * @return strategy view.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public String viewStrategy(String strategyNameTag) throws IllegalArgumentException {
    if (strategyNameTag == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!strategyNameSet.contains(strategyNameTag)) {
      throw new IllegalArgumentException(
              ErrorMsg.INVALID_VIEW_STRATEGY_NAMETAG_NOT_FOUND.getMsg());
    }
    for (Strategy strategy : strategies) {
      String strategyName = strategy.getNameTag();
      if (strategyName.equals(strategyNameTag)) {
        return strategy.view();
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_VIEW_STRATEGY_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * View all strategies in the account.
   *
   * @return all strategies.
   */
  @Override
  public String viewAllStrategies() {
    StringBuilder sb = new StringBuilder();
    sb.append("All Strategies in the Account: ");
    for (Strategy strategy : strategies) {
      sb.append("\n").append(strategy.view()).append("\n");
    }
    return sb.toString();
  }

  /**
   * Get all strategy name tags.
   *
   * @return all strategy name tags.
   */
  @Override
  public String viewStrategyNameTags() {
    StringBuilder sb = new StringBuilder();
    sb.append("All Strategies in the Account: ");
    for (Strategy strategy : strategies) {
      sb.append("\n").append(strategy.getNameTag());
    }
    return sb.toString();
  }

  /**
   * Save all portfolios.
   */
  @Override
  public void saveAllPortfolio() {
    for (StockPortfolio portfolio : traderAccount) {
      RetrievableCommissionPortfolio commissionPortfolio =
              (RetrievableCommissionPortfolio) portfolio;
      commissionPortfolio.write(portfolioFilePath);
    }
  }

  /**
   * Save the portfolio given name.
   *
   * @param portfolioName portfolio to save.
   * @throws IllegalArgumentException if failed save.
   */
  @Override
  public void savePortfolio(String portfolioName) throws IllegalArgumentException {
    if (!portfolioNameSet.contains(portfolioName)) {
      throw new IllegalArgumentException(
              ErrorMsg.INVALID_SAVE_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
    }
    for (StockPortfolio portfolio : traderAccount) {
      RetrievableCommissionPortfolio commissionPortfolio =
              (RetrievableCommissionPortfolio) portfolio;
      if (commissionPortfolio.getNameTag().equals(portfolioName)) {
        commissionPortfolio.write(portfolioFilePath);
        return;
      }
    }
    throw new IllegalArgumentException(
            ErrorMsg.INVALID_SAVE_PORTFOLIO_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * Save all strategies.
   */
  @Override
  public void saveAllStrategy() {
    for (Strategy strategy : strategies) {
      RetrievableStrategy retrievableStrategy = (RetrievableStrategy) strategy;
      retrievableStrategy.write(strategyFilePath);
    }
  }

  /**
   * Save the strategy given name.
   *
   * @param strategyName strategy to save.
   * @throws IllegalArgumentException if failed save.
   */
  @Override
  public void saveStrategy(String strategyName) throws IllegalArgumentException {
    if (!strategyNameSet.contains(strategyName)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_SAVE_STRATEGY_NAMETAG_NOT_FOUND.getMsg());
    }
    for (Strategy strategy : strategies) {
      RetrievableStrategy retrievableStrategy = (RetrievableStrategy) strategy;
      if (retrievableStrategy.getNameTag().equals(strategyName)) {
        retrievableStrategy.write(strategyFilePath);
        return;
      }
    }
    throw new IllegalArgumentException(ErrorMsg.INVALID_SAVE_STRATEGY_NAMETAG_NOT_FOUND.getMsg());
  }

  /**
   * Retrieve all portfolio.
   */
  @Override
  public void retrieveAllPortfolio() {
    File portfolioFile = new File(portfolioFilePath);
    File[] portfolioFiles = portfolioFile.listFiles();
    if (portfolioFiles == null) {
      return;
    }
    for (File file : portfolioFiles) {
      String portfolioName = file.getPath().split("[\\\\|/]")[2];
      try {
        addPortfolio(portfolioName);
      }
      catch (IllegalArgumentException e) {
        if (!e.getMessage().equals(ErrorMsg.INVALID_ADD_PORTFOLIO_DUPLICATE_NAMETAG.getMsg())) {
          throw e;
        }
      }
      retrieveEquityNames(portfolioName);
      retrieveEquities(portfolioName);
    }
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
    try {
      addPortfolio(portfolioName);
    }
    catch (IllegalArgumentException e) {
      if (!e.getMessage().equals(ErrorMsg.INVALID_ADD_PORTFOLIO_DUPLICATE_NAMETAG.getMsg())) {
        throw e;
      }
    }
    retrieveEquityNames(portfolioName);
    retrieveEquities(portfolioName);
  }

  /**
   * private helper to retrieve equity name file.
   * @param portfolioName portfolio name.
   */
  private void retrieveEquityNames(String portfolioName) {
    String filePath = portfolioFilePath + "/" + portfolioName + "/" + equityNamePath;
    BufferedReader fileReader;
    try {
      String line;
      fileReader = new BufferedReader(new FileReader(filePath));
      fileReader.readLine();
      while ((line = fileReader.readLine()) != null) {
        addEquity(line, portfolioName);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.FAILED_LOAD_PORTFOLIO.getMsg());
    }
    try {
      fileReader.close();
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.FAILED_LOAD_PORTFOLIO.getMsg());
    }
  }

  /**
   * private helper to retrieve equities file.
   * @param portfolioName portfolio name.
   */
  private void retrieveEquities(String portfolioName) {
    String filePath = portfolioFilePath + "/" + portfolioName + "/" + equitiesPath;
    BufferedReader fileReader;
    try {
      String line;
      fileReader = new BufferedReader(new FileReader(filePath));
      fileReader.readLine();
      while ((line = fileReader.readLine()) != null) {
        String[] stockStr = line.split(",");
        String tickerSymbol = stockStr[0];
        TradeTimeStamp tradeTimeStamp = new TradeTimeStamp(LocalDate.parse(stockStr[1]));
        USDPrice price = new USDPrice(new BigDecimal(stockStr[2]));
        BigInteger shares = new BigInteger(stockStr[3]);
        USDPrice commission = new USDPrice(new BigDecimal(stockStr[4]));
        USStock stock = new USStock(tickerSymbol, price, tradeTimeStamp, shares);
        buyEquityWithCommission(stock, commission, portfolioName);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new IllegalArgumentException(ErrorMsg.FAILED_LOAD_PORTFOLIO.getMsg());
    }
    try {
      fileReader.close();
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.FAILED_LOAD_PORTFOLIO.getMsg());
    }
  }

  /**
   * Retrieve all strategies.
   */
  @Override
  public void retrieveAllStrategy() {
    File portfolioFile = new File(strategyFilePath);
    File[] portfolioFiles = portfolioFile.listFiles();
    if (portfolioFiles == null) {
      return;
    }
    for (File file : portfolioFiles) {
      String strategyName = file.getPath().split("[\\\\|/]")[2]
              .replace(".csv", "");
      retrieveStrategy(strategyName);
    }
  }

  /**
   * Retrieve the given strategy.
   *
   * @param strategyName strategy to retrieve.
   * @throws IllegalArgumentException if failed load.
   */
  @Override
  public void retrieveStrategy(String strategyName) {
    String filePath = strategyFilePath + "/" + strategyName + ".csv";
    BufferedReader fileReader;
    try {
      String line;
      fileReader = new BufferedReader(new FileReader(filePath));
      fileReader.readLine();
      while ((line = fileReader.readLine()) != null) {
        String[] strategyStr = line.split(",");
        String strategyType = strategyStr[0];
        if (strategyType.equals("WeightedInvestment")) {
          addStrategy(parseWeightInvestmentStrategy(strategyName, strategyStr));
        } else if (strategyType.equals("DACInvestment")) {
          addStrategy(parseDACInvestmentStrategy(strategyName, strategyStr));
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new IllegalArgumentException(ErrorMsg.FAILED_LOAD_PORTFOLIO.getMsg());
    }
    try {
      fileReader.close();
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.FAILED_LOAD_PORTFOLIO.getMsg());
    }
  }

  /**
   * Private helper to parse weighted investment strategy.
   * @param strategyName strategy name.
   * @param strategyStr strategy string.
   * @return Strategy.
   */
  private Strategy parseWeightInvestmentStrategy(String strategyName, String[] strategyStr) {
    Map<String, BigDecimal> stockWeights = parseStockMapStr(strategyStr[1]);
    TradeTimeStamp investmentTime = new TradeTimeStamp(LocalDate.parse(strategyStr[2]));
    USDPrice capital = new USDPrice(new BigDecimal(strategyStr[3]));
    USDPrice commission = new USDPrice(new BigDecimal(strategyStr[4]));
    return new WeightedStockStrategy(strategyName, stockWeights,
            investmentTime, capital, commission);
  }

  /**
   * Private helper to parse DAC investment strategy.
   * @param strategyName strategy name.
   * @param strategyStr strategy string.
   * @return Strategy.
   */
  private Strategy parseDACInvestmentStrategy(String strategyName, String[] strategyStr) {
    Map<String, BigDecimal> stockWeights = parseStockMapStr(strategyStr[1]);
    TradeTimeStamp startTime = new TradeTimeStamp(LocalDate.parse(strategyStr[2]));
    TradeTimeStamp endTime = null;
    if (!strategyStr[3].equals("NA")) {
      endTime = new TradeTimeStamp(LocalDate.parse(strategyStr[3]));
    }
    FrequencyNode frequencyNode = new FrequencyNode(strategyStr[4].charAt(0),
            Long.parseLong(strategyStr[5]));
    USDPrice capitalPerInvestment = new USDPrice(new BigDecimal(strategyStr[6]));
    USDPrice commission = new USDPrice(new BigDecimal(strategyStr[7]));
    return new DACStockStrategy(strategyName, stockWeights, startTime, endTime, frequencyNode,
            capitalPerInvestment, commission);
  }

  /**
   * Parse stock Map.
   * @param mapStr map Str.
   * @return stock map.
   * @throws IllegalArgumentException if parse failed.
   */
  private Map<String, BigDecimal> parseStockMapStr(String mapStr) throws IllegalArgumentException {
    Properties props = new Properties();
    try {
      props.load(new StringReader(mapStr.substring(1, mapStr.length() - 1)
              .replace("& ", "\n")));
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.FAILED_LOAD_STRATEGY_INVALID_MAP_STR.getMsg());
    }
    Map<String, BigDecimal> stockWeightMap = new HashMap<>();
    for (Map.Entry<Object, Object> entry : props.entrySet()) {
      stockWeightMap.put((String) entry.getKey(), new BigDecimal((String) entry.getValue()));
    }
    return stockWeightMap;
  }
}
