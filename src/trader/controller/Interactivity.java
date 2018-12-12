package trader.controller;

/**
 * The enum class to record all the possibilities between user and the trading system.
 */
public enum Interactivity {
  WELCOME_LINE("Welcome to the BatmAn."),
  WELCOME_BACK_LINE("You still have these options: " +
          "\n1. CreatePortfolio" +
          "\n2. AddStock" +
          "\n3. BuyStock" +
          "\n4. BuyStockWithCommission" +
          "\n5. ViewPortfolio" +
          "\n6. ViewAccount" +
          "\n7. EvaluatePortfolio" +
          "\n8. EvaluateAccount" +
          "\n9. GetPortfolioCostBasis" +
          "\n10. GetAccountCostBasis" +
          "\n11. AddWeightedInvestmentStrategy" +
          "\n12. AddDollarAveragingCostInvestmentStrategy" +
          "\n13. ViewStrategy" +
          "\n14. ApplyStrategy" +
          "\n15. SaveAllStrategy" +
          "\n16. SaveAllPortfolio" +
          "\n17. SaveStrategy" +
          "\n18. SavePortfolio" +
          "\n19. RetrieveAllStrategy" +
          "\n20. RetrieveAllPortfolio" +
          "\n21. RetrieveStrategy" +
          "\n22. RetrievePortfolio" +
          "\nQuit"),
  OPTION_LINE("You have these options:" +
          "\n1. CreatePortfolio" +
          "\n2. AddStock" +
          "\n3. BuyStock" +
          "\n4. BuyStockWithCommission" +
          "\n5. ViewPortfolio" +
          "\n6. ViewAccount" +
          "\n7. EvaluatePortfolio" +
          "\n8. EvaluateAccount" +
          "\n9. GetPortfolioCostBasis" +
          "\n10. GetAccountCostBasis" +
          "\n11. AddWeightedInvestmentStrategy" +
          "\n12. AddDollarAveragingCostInvestmentStrategy" +
          "\n13. ViewStrategy" +
          "\n14. ApplyStrategy" +
          "\n15. SaveAllStrategy" +
          "\n16. SaveAllPortfolio" +
          "\n17. SaveStrategy" +
          "\n18. SavePortfolio" +
          "\n19. RetrieveAllStrategy" +
          "\n20. RetrieveAllPortfolio" +
          "\n21. RetrieveStrategy" +
          "\n22. RetrievePortfolio" +
          "\nQuit"),
  ENTER_SYMBOL("Please enter the ticketSymbol you want to buy."),
  CURRENT_SYMBOL("The symbol you enter is: "),
  ENTER_TIME("Please enter the time you want to buy the stock, " +
          "the format is MM/DD/YYYY"),
  CURRENT_TIME("Your time you enter is: "),
  ENTER_VOLUME("Please enter the volume you want to buy: "),
  CURRENT_VOLUME("The volume you enter is: "),
  CURRENT_PORTFOLIO("The portfolio you choose is: "),
  VIEW_CURRENT_PORTFOLIO("Currently you have following portfolio: "),
  CREATE_PORTFOLIO("Please type the name you want to name your portfolio: "),
  CREATE_PORTFOLIO_SUCCESS("You create portfolio successfully."),
  EVALUATE_PORTFOLIO_NAME("Please enter the name of your portfolio you want to evaluate: "),
  EVALUATE_TIME("Please enter the time that you want to evaluate, " +
          "the format is MM/DD/YYYY: "),
  SHOW_ACCOUNT("All Portfolio Information: "),
  SHOW_PORTFOLIO("Please enter the name of your portfolio you want to view: "),
  EVALUATE_ACCOUNT("Total Net Worth and Cost Basis of the Account: "),
  FAIL_APPENDING("Fail appending to the output"),


  ASK_COMMISSION_FEE("Please enter the commission fee you want to pay for each transaction: "),
  COMMISSION_FEE_CHECK("The commission fee you entered is $"),
  ENTER_NUMBER_OF_STOCK("Please enter how many stocks you want to put in this portfolio, " +
          "only integer will be accepted:"),
  NUMBER_STOCKS_MISMATCH("Please notice only the number will be accepted, please check you input."),
  ENTER_NAME_OF_STOCK(" stock to be added:"),
  ENTER_TOTAL_INVESTMENT("Please enter the total investment you want to put in this portfolio: "),
  ENTER_EQUAL_WEIGHT_BOOL("Do you want your stock to be equally weighted?" +
          "\n" + "TRUE" +
          "\n" + "FALSE"),
  ENTER_DIFFERENT_WEIGHT("Please enter the weight in percentage (format: XX.XX) " +
          "you want to assign to the stock "),
  BUILD_PRESET_PORTFOLIO_SUCCESS("You successfully build the preset portfolio."),
  PORTFOLIO_CHOOSE_OR_CREATE("You can either choose one of the existing portfolio showing above, " +
          "or enter a new portfolio: "),
  ENTER_STRATEGY_START_TIME("Please enter the time you want to start your strategy, " +
          "the format is MM/DD/YYYY"),
  ENTER_STRATEGY_END_TIME("Please enter the time you want to end your strategy, " +
          "the format is MM/DD/YYYY (Please enter NA if not applicable)"),
  ENTER_FREQUENCY("Please enter the frequency you want to invest this portfolio, " +
          "the format is: " +
          "\nxxD(per xx days) " +
          "\nxxW(per xx weeks) " +
          "\nxxM(per xx months) " +
          "\nxxY(per xx years))" +
          "\nYou can only choose one of these four frequency options"),
  STRATEGY_ALL_SET("You strategy is all set!"),
  BAD_INPUT("Please enter either the number or the name of the commands list below."),
  INVALID_WEIGHT_FORMAT("Weight format mismatch, please check the weight."),
  ENTER_PORTFOLIO_NAME("Please enter the portfolio name: "),
  ENTER_STRATEGY_NAME("Please enter the strategy name: "),
  VIEW_CURRENT_STRATEGY("Currently you have following strategies: "),
  CURRENT_STRATEGY("The strategy you choose is: "),
  SAVE_SUCCESS("Successfully Saved"),
  RETRIEVE_SUCCESS("Successfully Retrieved");

  private final String msg;

  Interactivity(String description) {
    this.msg = description;
  }

  public String getMsg() {
    return this.msg;
  }
}
