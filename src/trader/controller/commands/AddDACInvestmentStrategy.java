package trader.controller.commands;

import java.util.Map;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * This class represent add DAC investment strategy.
 */
public class AddDACInvestmentStrategy implements CommandInterface {
  private final String StrategyName;
  private final String investmentPerTime;
  private final Map<String, String> stocks;
  private final String startTime;
  private final String endTime;
  private final String freq;
  private final String commission;

  /**
   * Constructor for AddDACInvestmentStrategy.
   *
   * @param strategyName      StrategyName.
   * @param numberOfStock     numberOfStock.
   * @param isEqualWeight     isEqualWeight.
   * @param stocks            stocks.
   * @param investmentPerTime investmentPerTime.
   * @param commission        commission.
   * @param startTime         startTime.
   * @param endTime           endTime.
   * @param freq              freq.
   */
  public AddDACInvestmentStrategy(String strategyName,
                                  String numberOfStock,
                                  String isEqualWeight,
                                  Map<String, String> stocks,
                                  String investmentPerTime,
                                  String commission,
                                  String startTime,
                                  String endTime,
                                  String freq) {
    this.commission = commission;
    this.StrategyName = strategyName;
    this.investmentPerTime = investmentPerTime;
    this.stocks = stocks;
    this.startTime = startTime;
    this.endTime = endTime;
    this.freq = freq;
  }

  /**
   * PLay the function with model.
   *
   * @param m model to be played.
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      m.addDollarAveragingCostStrategy(this.StrategyName, this.stocks,
              this.investmentPerTime, this.commission, this.startTime, this.endTime, this.freq);
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_DCA_FAIL.getMsg());
    }
  }
}
