package trader.controller.commands;

import trader.ErrorMsg;
import trader.controller.Interactivity;
import trader.model.IRetrievableStrategyTraderModel;

/**
 * This class represent save portfolio command.
 */
public class SavePortfolio implements CommandInterface {
  private final String portfolioName;

  /**
   * Constructor for SavePortfolio.
   * @param portfolioName portfolioName.
   */
  public SavePortfolio(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  /**
   * PLay the function with model.
   *
   * @param m model to be played.
   */
  @Override
  public void execute(IRetrievableStrategyTraderModel m) {
    try {
      m.savePortfolio(portfolioName);
      System.out.println(Interactivity.SAVE_SUCCESS.getMsg());
    }
    catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
      }
      System.out.println(ErrorMsg.CONTROLLER_SAVE_PORTFOLIO.getMsg());
    }
  }
}
