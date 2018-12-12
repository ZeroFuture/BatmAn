package trader.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import trader.ErrorMsg;
import trader.controller.commands.AddDACInvestmentStrategy;
import trader.controller.commands.AddStock;
import trader.controller.commands.AddWeightedInvestmentStrategy;
import trader.controller.commands.ApplyStrategy;
import trader.controller.commands.BuyStock;
import trader.controller.commands.BuyStockWithCommission;
import trader.controller.commands.CommandInterface;
import trader.controller.commands.CreatePortfolio;
import trader.controller.commands.EvaluateAccount;
import trader.controller.commands.EvaluatePortfolio;
import trader.controller.commands.GetAccountCostBasis;
import trader.controller.commands.GetPortfolioCostBasis;
import trader.controller.commands.RetrieveAllPortfolio;
import trader.controller.commands.RetrieveAllStrategy;
import trader.controller.commands.RetrievePortfolio;
import trader.controller.commands.RetrieveStrategy;
import trader.controller.commands.SaveAllPortfolio;
import trader.controller.commands.SaveAllStrategy;
import trader.controller.commands.SavePortfolio;
import trader.controller.commands.SaveStrategy;
import trader.controller.commands.ViewAccount;
import trader.controller.commands.ViewPortfolio;
import trader.controller.commands.ViewStrategy;
import trader.model.IRetrievableStrategyTraderModel;
import trader.view.ITraderView;

/**
 * This class implements necessary functions for a ITraderController Interface.
 */
public class TraderController implements ITraderController {
  private final ITraderView view;
  private IRetrievableStrategyTraderModel model;
  private HashMap<String, String> stocks = new HashMap<>();

  /**
   * Constructor for TraderController class.
   *
   * @param v view.
   * @param m model.
   */
  public TraderController(ITraderView v, IRetrievableStrategyTraderModel m) {
    this.view = v;
    this.model = m;
  }

  /**
   * Translate input String to a valid output.
   */
  private void transToOutPut(String s) throws IllegalStateException {
    try {
      this.view.getOutput().append(s);
      this.view.getOutput().append("\n");
    } catch (IOException e) {
      throw new IllegalStateException(Interactivity.FAIL_APPENDING.getMsg());
    }
  }

  /**
   * Helper function to ask for the commission that user want to set up for per transaction.
   *
   * @param s scanner s to be used to read the user input
   * @return the commission fee that user entered.
   */
  private String askForCommission(Scanner s) {
    this.transToOutPut(Interactivity.ASK_COMMISSION_FEE.getMsg());
    String commission = s.next();
    this.transToOutPut(Interactivity.COMMISSION_FEE_CHECK.getMsg() + commission);
    return commission;
  }

  /**
   * Helper function to get the real stock symbol user entered.
   *
   * @param s scanner s to be used to read the user input
   * @return the stock symbol user entered
   */
  private String getRealSymbol(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_SYMBOL.getMsg());
    String symbol = s.next();
    this.transToOutPut(Interactivity.CURRENT_SYMBOL.getMsg() + symbol);
    return symbol;
  }

  /**
   * Helper function to get time user entered.
   *
   * @param s scanner s to be used to read the user input
   * @return time user entered
   */
  private String getTime(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_TIME.getMsg());
    String time = s.next();
    this.transToOutPut(Interactivity.CURRENT_TIME.getMsg() + time);
    return time;
  }

  /**
   * Helper function to get volume user entered.
   *
   * @param s scanner s to be used to read the user input
   * @return the volume user entered
   */
  private String getVolume(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_VOLUME.getMsg());
    String volume = s.next();
    this.transToOutPut(Interactivity.CURRENT_VOLUME.getMsg() + volume);
    return volume;
  }

  /**
   * Helper function to get portfolio name user entered.
   *
   * @param s scanner s to be used to read the user input
   * @return the portfolio name user entered
   */
  private String getPortfolioName(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_PORTFOLIO_NAME.getMsg());
    //show the portfolio to be added;
    this.transToOutPut(Interactivity.VIEW_CURRENT_PORTFOLIO.getMsg()
            + model.viewPortfolioNameTags());
    String portfolio = s.next();
    this.transToOutPut(Interactivity.CURRENT_PORTFOLIO.getMsg() + portfolio);
    return portfolio;
  }

  /**
   * Helper function to get strategy name user entered.
   *
   * @param s scanner s to be used to read the user input
   * @return the strategy name user entered
   */
  private String getStrategyName(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_STRATEGY_NAME.getMsg());
    this.transToOutPut(Interactivity.VIEW_CURRENT_STRATEGY.getMsg()
            + model.viewStrategyNameTags());
    String strategy = s.next();
    this.transToOutPut(Interactivity.CURRENT_STRATEGY.getMsg() + strategy);
    return strategy;
  }

  /**
   * Helper function to create a portfolio based on the name user entered.
   *
   * @param s scanner s to be used to read the user input
   * @return the portfolio name that user entered
   */
  private String createPortfolioHelper(Scanner s) {
    this.transToOutPut(Interactivity.CREATE_PORTFOLIO.getMsg());
    String name = s.next();
    this.transToOutPut(Interactivity.CREATE_PORTFOLIO_SUCCESS.getMsg());
    return name;
  }

  /**
   * Helper function to view the portfolio which user choose to view.
   *
   * @param s scanner s to be used to read the user input
   * @return the portfolio name that user choose
   */
  private String viewPortfolioHelper(Scanner s) {
    this.transToOutPut(Interactivity.SHOW_PORTFOLIO.getMsg() + model.viewPortfolioNameTags());
    return s.next();
  }

  /**
   * Helper function to evaluate the portfolio that user choose.
   *
   * @param s scanner s to be used to read the user input
   * @return the name of portfolio that user choose
   */
  private String evaluatePortfolioNameHelper(Scanner s) {
    this.transToOutPut(Interactivity.EVALUATE_PORTFOLIO_NAME.getMsg()
            + model.viewPortfolioNameTags());
    return s.next();
  }

  /**
   * Helper function to evaluate the portfolio based on the time that user choose.
   *
   * @param s scanner s to be used to read the user input
   * @return the time that user decide to evaluate the portfolio
   */
  private String evaluatePortfolioTimeHelper(Scanner s) {
    this.transToOutPut(Interactivity.EVALUATE_TIME.getMsg());
    return s.next();
  }

  /**
   * Helper function to evaluate the account based on the time that user choose.
   *
   * @param s scanner s to be used to read the user input
   * @return the time that user decide to evaluate the account
   */
  private String evaluateAccountHelper(Scanner s) {
    this.transToOutPut(Interactivity.EVALUATE_TIME.getMsg());
    String timeStamp = s.next();
    this.transToOutPut(Interactivity.EVALUATE_ACCOUNT.getMsg());
    return timeStamp;
  }

  /**
   * Helper function to get the name that user choose for the preset portfolio.
   *
   * @param s scanner s to be used to read the user input
   * @return the name of the preset portfolio
   */
  private String presetNameHelper(Scanner s) {
    this.transToOutPut(model.viewPortfolioNameTags());
    this.transToOutPut(Interactivity.PORTFOLIO_CHOOSE_OR_CREATE.getMsg());
    return s.next();
  }

  /**
   * Helper function to get the number of the stock in the preset portfolio that user choose. And
   * put all the stocks that user entered into the map.
   *
   * @param s scanner s to be used to read the user input
   * @return the number of stock in the preset portfolio
   */
  private String presetNumberStockHelper(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_NUMBER_OF_STOCK.getMsg());
    String numberOfStock = s.next();
    this.stocks.clear();
    while (true) {
      try {
        putStockToMap(stocks, Integer.parseInt(numberOfStock), s);
        if (stocks.keySet().size() == Integer.parseInt(numberOfStock)) {
          break;
        }
        this.transToOutPut(ErrorMsg.CONTROLLER_NUMBER_OF_STOCKS_FAIL.getMsg());
        this.stocks.clear();
      } catch (NumberFormatException e) {
        this.transToOutPut(Interactivity.NUMBER_STOCKS_MISMATCH.getMsg());
        presetNumberStockHelper(s);
        break;
      }
    }
    return numberOfStock;
  }

  /**
   * Helper function to get the time that user want to buy the stocks in the preset portfolio.
   *
   * @param s scanner s to be used to read the user input
   * @return the number of time that user want to buy the stocks
   */
  private String presetTimeStampHelper(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_TIME.getMsg());
    return s.next();
  }

  /**
   * Helper function to get the total investment the user want to put in this preset portfolio.
   *
   * @param s scanner s to be used to read the user input
   * @return the total investment that user want to invest.
   */
  private String presetInvestmentHelper(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_TOTAL_INVESTMENT.getMsg());
    return s.next();
  }

  /**
   * Helper function to return the portfolio map based on the weight that user choose.
   *
   * @param s scanner s to be used to read the user input
   * @return the portfolio map based on the weight that user choose
   */
  private String presetWeightHelper(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_EQUAL_WEIGHT_BOOL.getMsg());
    String isEqual = s.next();
    if (isEqual.equalsIgnoreCase("TRUE")) {
      assignEqualWeight(stocks);
      this.transToOutPut(Interactivity.BUILD_PRESET_PORTFOLIO_SUCCESS.getMsg());
    } else {
      while (true) {
        if (assignDiffWeight(stocks, s) == 100.00) {
          this.transToOutPut(Interactivity.BUILD_PRESET_PORTFOLIO_SUCCESS.getMsg());
          break;
        }
        this.transToOutPut(ErrorMsg.CONTROLLER_TOTAL_SHARE_FAIL.getMsg());
      }
    }
    return isEqual;
  }

  /**
   * Helper function to assign the equal weight to the stock.
   *
   * @param stocks the stocks to be assigned weight
   */
  private void assignEqualWeight(Map<String, String> stocks) {
    String sameFormatWeight = String.format("%.2f", 100.00 / stocks.size()) + "%";
    for (String key : stocks.keySet()) {
      stocks.put(key, sameFormatWeight);
    }
  }

  /**
   * Helper function to assign different weight to the stock based on the user input.
   *
   * @param stocks the stocks to be assigned weight
   * @param s      scanner s to be used to read the user input
   * @return the total weight user entered
   */
  private double assignDiffWeight(Map<String, String> stocks, Scanner s) {
    double totalWeight = 0;
    for (String key : stocks.keySet()) {
      this.transToOutPut(Interactivity.ENTER_DIFFERENT_WEIGHT.getMsg() + key);
      String differentWeight = s.next();
      try {
        totalWeight += Double.parseDouble(differentWeight);
      } catch (NumberFormatException e) {
        this.transToOutPut(Interactivity.INVALID_WEIGHT_FORMAT.getMsg());
        assignDiffWeight(stocks, s);
      }
      stocks.put(key, differentWeight + "%");
    }
    return totalWeight;
  }

  /**
   * helper function to transfer 1 to 1st, 2 to 2nd, 3 to 3rd etc.
   *
   * @param x number to be transferred
   * @return transferred number
   */
  private String convert(int x) {
    if (x == 11 || x == 12 || x == 13) {
      return String.valueOf(x) + "TH";
    }
    String number = String.valueOf(x);
    if (number.charAt(number.length() - 1) == '1') {
      return number + "ST";
    } else if (number.charAt(number.length() - 1) == '2') {
      return number + "ND";
    } else if (number.charAt(number.length() - 1) == '3') {
      return number + "RD";
    }
    return number + "TH";
  }

  /**
   * Put all the stock user entered into the map.
   *
   * @param map   the map to store the stock
   * @param total total number of stock user entered
   * @param s     scanner s to be used to read the user input
   */
  private void putStockToMap(Map<String, String> map, int total, Scanner s) {
    for (int i = 0; i < total; i++) {
      this.transToOutPut(convert(i + 1) + Interactivity.ENTER_NAME_OF_STOCK.getMsg());
      String symbol = s.next();
      map.putIfAbsent(symbol, null);
    }
  }

  /**
   * Helper function to get the start time of the strategy that user entered.
   *
   * @param s scanner s to be used to read the user input
   * @return the start time that user entered
   */
  private String startTimeHelper(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_STRATEGY_START_TIME.getMsg());
    return s.next();
  }

  /**
   * Helper function to get the end time of the strategy that user entered.
   *
   * @param s scanner s to be used to read the user input
   * @return the end time that user entered
   */
  private String endTimeHelper(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_STRATEGY_END_TIME.getMsg());
    return s.next();
  }

  private String frequencyHelper(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_FREQUENCY.getMsg());
    String freq = s.next();
    this.transToOutPut(Interactivity.STRATEGY_ALL_SET.getMsg());
    return freq;
  }

  private String strategyNameHelper(Scanner s) {
    this.transToOutPut(Interactivity.ENTER_STRATEGY_NAME.getMsg());
    return s.next();
  }


  /**
   * Start the trading system.
   */
  @Override
  public void play() {
    this.transToOutPut(Interactivity.WELCOME_LINE.getMsg());
    this.transToOutPut(Interactivity.OPTION_LINE.getMsg());
    Map<String, Function<Scanner, CommandInterface>> commands = new HashMap<>();
    commands.put("1", s -> new CreatePortfolio(createPortfolioHelper(s)));
    commands.put("CreatePortfolio", s -> new CreatePortfolio(createPortfolioHelper(s)));
    commands.put("2", s -> new AddStock(getRealSymbol(s), getPortfolioName(s)));
    commands.put("AddStock", s -> new AddStock(getRealSymbol(s), getPortfolioName(s)));
    commands.put("3", s -> new BuyStock(
            getRealSymbol(s), getTime(s), getVolume(s), getPortfolioName(s)));
    commands.put("BuyStock", s -> new BuyStock(getRealSymbol(s),
            getTime(s), getVolume(s), getPortfolioName(s)));
    commands.put("4", s -> new BuyStockWithCommission(askForCommission(s),
            getRealSymbol(s), getTime(s), getVolume(s), getPortfolioName(s)));
    commands.put("BuyStockWithCommission", s -> new BuyStockWithCommission(askForCommission(s),
            getRealSymbol(s), getTime(s), getVolume(s), getPortfolioName(s)));
    commands.put("5", s -> new ViewPortfolio(viewPortfolioHelper(s)));
    commands.put("ViewPortfolio", s -> new ViewPortfolio(viewPortfolioHelper(s)));
    commands.put("6", s -> new ViewAccount());
    commands.put("ViewAccount", s -> new ViewAccount());
    commands.put("7", s -> new EvaluatePortfolio(evaluatePortfolioNameHelper(s),
            evaluatePortfolioTimeHelper(s)));
    commands.put("EvaluatePortfolio", s -> new EvaluatePortfolio(evaluatePortfolioNameHelper(s),
            evaluatePortfolioTimeHelper(s)));
    commands.put("8", s -> new EvaluateAccount(evaluateAccountHelper(s)));
    commands.put("EvaluateAccount", s -> new EvaluateAccount(evaluateAccountHelper(s)));
    commands.put("9", s -> new GetPortfolioCostBasis(getPortfolioName(s)));
    commands.put("GetPortfolioCostBasis", s -> new GetPortfolioCostBasis(getPortfolioName(s)));
    commands.put("10", s -> new GetAccountCostBasis());
    commands.put("GetAccountCostBasis", s -> new GetAccountCostBasis());
    commands.put("11", s -> new AddWeightedInvestmentStrategy(strategyNameHelper(s),
            presetNumberStockHelper(s), presetWeightHelper(s), this.stocks,
            presetInvestmentHelper(s), askForCommission(s), presetTimeStampHelper(s)));
    commands.put("AddWeightedInvestmentStrategy", s -> new AddWeightedInvestmentStrategy(
            strategyNameHelper(s),
            presetNumberStockHelper(s),
            presetWeightHelper(s),
            this.stocks,
            presetInvestmentHelper(s),
            askForCommission(s),
            presetTimeStampHelper(s)));
    commands.put("12", s -> new AddDACInvestmentStrategy(
            strategyNameHelper(s),
            presetNumberStockHelper(s),
            presetWeightHelper(s),
            this.stocks,
            presetInvestmentHelper(s),
            askForCommission(s),
            startTimeHelper(s),
            endTimeHelper(s),
            frequencyHelper(s)
    ));
    commands.put("AddDollarAveragingCostInvestmentStrategy", s -> new AddDACInvestmentStrategy(
            strategyNameHelper(s),
            presetNumberStockHelper(s),
            presetWeightHelper(s),
            this.stocks,
            presetInvestmentHelper(s),
            askForCommission(s),
            startTimeHelper(s),
            endTimeHelper(s),
            frequencyHelper(s)
    ));
    commands.put("13", s -> new ViewStrategy());
    commands.put("ViewStrategy", s -> new ViewStrategy());
    commands.put("14", s -> new ApplyStrategy(presetNameHelper(s), getStrategyName(s)));
    commands.put("ApplyStrategy", s -> new ApplyStrategy(presetNameHelper(s), getStrategyName(s)));
    commands.put("15", s -> new SaveAllStrategy());
    commands.put("SaveAllStrategy", s -> new SaveAllStrategy());
    commands.put("16", s -> new SaveAllPortfolio());
    commands.put("SaveAllPortfolio", s -> new SaveAllPortfolio());
    commands.put("17", s -> new SaveStrategy(getStrategyName(s)));
    commands.put("SaveStrategy", s -> new SaveStrategy(getStrategyName(s)));
    commands.put("18", s -> new SavePortfolio(getPortfolioName(s)));
    commands.put("SavePortfolio", s -> new SavePortfolio(getPortfolioName(s)));
    commands.put("19", s -> new RetrieveAllStrategy());
    commands.put("RetrieveAllStrategy", s -> new RetrieveAllStrategy());
    commands.put("20", s -> new RetrieveAllPortfolio());
    commands.put("RetrieveAllPortfolio", s -> new RetrieveAllPortfolio());
    commands.put("21", s -> new RetrieveStrategy(getStrategyName(s)));
    commands.put("RetrieveStrategy", s -> new RetrieveStrategy(getStrategyName(s)));
    commands.put("22", s -> new RetrievePortfolio(getPortfolioName(s)));
    commands.put("RetrievePortfolio", s -> new RetrievePortfolio(getPortfolioName(s)));
    Scanner scan = new Scanner(this.view.getInput());
    while (scan.hasNext()) {
      CommandInterface c;
      String in = scan.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        System.exit(0);
      }
      Function<Scanner, CommandInterface> cmd = commands.getOrDefault(in, null);
      if (cmd != null) {
        c = cmd.apply(scan);
        c.execute(model);
        this.transToOutPut(Interactivity.WELCOME_BACK_LINE.getMsg());
      } else {
        this.transToOutPut(Interactivity.BAD_INPUT.getMsg());
        this.transToOutPut(Interactivity.OPTION_LINE.getMsg());
      }
    }
  }
}

