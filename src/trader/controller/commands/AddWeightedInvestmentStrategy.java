package trader.controller.commands;

import java.util.Map;

import trader.ErrorMsg;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * This class represent add Weighted investment strategy.
 */
public class AddWeightedInvestmentStrategy implements CommandInterface {
  private final String strategyName;
  private final String totalInvestment;
  private final Map<String, String> stocks;
  private final String time;
  private final String commission;

  /**
   * This is the constructor of AddWeightedInvestmentStrategy.
   *
   * @param strategyName    strategyName.
   * @param stocks          stocks.
   * @param totalInvestment totalInvestment.
   * @param commission      commission.
   * @param time            time.
   * @param numberOfStock   numberOfStock.
   * @param isEqualWeight   isEqualWeight.
   */
  public AddWeightedInvestmentStrategy(String strategyName,
                                       String numberOfStock,
                                       String isEqualWeight,
                                       Map<String, String> stocks,
                                       String totalInvestment,
                                       String commission,
                                       String time) {
    this.strategyName = strategyName;
    this.totalInvestment = totalInvestment;
    this.stocks = stocks;
    this.time = time;
    this.commission = commission;
  }

  /**
   * PLay the function with model.
   *
   * @param m model to be played.
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      m.addWeightedInvestmentStrategy(this.strategyName, this.stocks,
              this.totalInvestment, this.commission, this.time);
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_WEIGHTED_INVESTMENT_FAIL.getMsg());
    }
  }
}
