package trader.view;


public enum FieldName {
  PROGRAM_START("Welcome to the BatmAn"),
  STOCK_OPERATION("Stock Operation"),
  PORTFOLIO_OPERATION("Portfolio Operation"),
  STRATEGY_OPERATION("Strategy Operation"),
  ACCOUNT_OPERATION("Account Operation"),
  PROGRAM_END("Thank you for using our program!"),
  SUBMIT("Submit"),
  BACK("Back"),
  SUCCESS("You Did it!!!!!!"),
  FAIL_LINE("Something wrong with your input, Please enter it again"),
  COMMISSION_FEE("Commission Fee"),
  TICKET_SYMBOL("Ticket Symbol"),
  DATE_OF_PURCHASE("Purchase Date (MM/DD/YYYY)"),
  VOLUME_NUMBER("Volume"),
  TOTAL_INVESTMENT("Total Investment"),
  PER_INVESTMENT("Capital Per Investment"),
  INVEST_TIME("Investment Time (MM/DD/YYYY)"),
  START_TIME("Investment Start Time (MM/DD/YYYY)"),
  END_TIME("Investment End Time (MM/DD/YYYY) NA if not applicable"),
  FREQUENCY("Frequency (\nxxD(per xx days)\n xxW(per xx weeks)\n xxM(per xx months)\n xxY(per xx "
          + "years))"),
  EVALUATE_TIME("Please Enter The Time You Want To Evaluate Your Account (MM/DD/YYYY)"),
  STRATEGY_NAME("Your Strategy Name"),
  PORTFOLIO_NAME("The Portfolio You Want To Put In"),
  PORTFOLIO_NAME_SAVE("The Portfolio You Want To Save"),
  STRATEGY_NAME_SAVE("The Strategy You Want To Save"),
  PORTFOLIO_NAME_RETRIEVE("The Portfolio You Want To Retrieve"),
  STRATEGY_NAME_RETRIEVE("The Strategy You Want To Retrieve"),
  CREATE_PORTFOLIO_NAME("Enter The Name of Portfolio You Want To Create"),
  VIEW_PORTFOLIO_NAME("Enter The Name of Portfolio You Want To View"),
  EVALUATE_PORTFOLIO_NAME("Enter The Name of The Portfolio You Want To Evaluate"),
  EVALUATE_PORTFOLIO_TIME("Enter The Time You Want to Evaluate (MM/DD/YYYY)"),
  GET_PORTFOLIO_COST("Enter The Portfolio Name"),
  NUMBER_STOCK_ASK("How Many Stocks You Want To Apply This Strategy"),
  APPLY_PORTFOLIO_NAME("Please Enter the Portfolio Name you want to apply " +
          "(or a new portfolio)"),
  APPLY_STRATEGY_NAME("Please Enter the Strategy Name you want to apply."),

  BUY_STOCK_WITH_COMMISSION_SUBMIT("Commission_Submit"),
  BUY_STOCK_WITH_NO_COMMISSION_SUBMIT("No_Commission_Submit"),

  BUY_STOCK_COMMISSION_BACK("Commission_Back"),
  BUY_STOCK_NO_COMMISSION_BACK("No_Commission_Back"),

  BUY_STOCK_WITH_COMMISSION_ENTER("BuyStockWithCommission"),
  BUY_STOCK_NO_COMMISSION_ENTER("BuyStockNoCommission"),

  CREATE_PORTFOLIO_ENTER("CreatePortfolio"),
  CREATE_PORTFOLIO_SUBMIT("Portfolio_Submit"),
  CREATE_PORTFOLIO_BACK("Portfolio_Back"),

  VIEW_PORTFOLIO_ENTER("ViewPortfolio"),
  VIEW_PORTFOLIO_SUBMIT("ViewPortfolio_Submit"),
  VIEW_PORTFOLIO_BACK("ViewPortfolio_Back"),

  EVALUATE_PORTFOLIO_ENTER("EvaluatePortfolio"),
  EVALUATE_PORTFOLIO_SUBMIT("EvaluatePortfolio_Submit"),
  EVALUATE_PORTFOLIO_BACK("EvaluatePortfolio_Back"),

  ADD_STOCK_ENTER("AddStock"),
  ADD_STOCK_SUBMIT("AddStock_Submit"),
  ADD_STOCK_BACK("AddStock_Back"),

  GET_PORTFOLIO_COST_BASIS_ENTER("GetPortfolioCostBasis"),
  GET_PORTFOLIO_COST_BASIS_SUBMIT("GetPortfolioCostBasis_Submit"),
  GET_PORTFOLIO_COST_BASIS_BACK("GetPortfolioCostBasis_Back"),

  WEIGHTED_INVESTMENT_ENTER("AddWeightedInvestmentStrategy"),
  WEIGHTED_INVESTMENT_SUBMIT("WeightedInvestment_Submit"),
  WEIGHTED_INVESTMENT_BACK("WeightedInvestment_Back"),

  ASK_NUMBER_OF_STOCK_ENTER("NumberOfStock"),
  WEIGHTED_ASK_NUMBER_OF_STOCK_SUBMIT("NumberOfStock_Submit"),
  WEIGHTED_ASK_NUMBER_OF_STOCK_BACK("NumberOfStock_Back"),

  DAC_STRATEGY_ENTER("DollarAverageCostingStrategy"),
  DAC_STRATEGY_SUBMIT("DollarAverageCostingStrategy_Submit"),
  DAC_STRATEGY_BACK("DollarAverageCostingStrategy_Back"),
  DAC_STRATEGY_DIFF_SUBMIT("DacDiffWeight_Submit"),
  DAC_STRATEGY_DIFF_BACK("DacDiffWeight_Back"),
  DAC_ASK_NUMBER_OF_STOCK_SUBMIT("DAC_NumberOfStock_Submit"),
  DAC_ASK_NUMBER_OF_STOCK_BACK("DAC_NumberOfStock_Back"),

  APPLY_STRATEGY_ENTER("ApplyStrategy"),
  APPLY_STRATEGY_SUBMIT("ApplyStrategy_Submit"),
  APPLY_STRATEGY_BACK("ApplyStrategy_Back"),

  SAVE_PORTFOLIO_ENTER("SavePortfolio"),
  SAVE_PORTFOLIO_SUBMIT("SavePortfolio_Submit"),
  SAVE_PORTFOLIO_BACK("SavePortfolio_Back"),

  SAVE_STRATEGY_ENTER("SaveStrategy"),
  SAVE_STRATEGY_SUBMIT("SaveStrategy_Submit"),
  SAVE_STRATEGY__BACK("SaveStrategy_Back"),

  RETRIEVE_PORTFOLIO_ENTER("RetrievePortfolio"),
  RETRIEVE_PORTFOLIO_SUBMIT("RetrievePortfolio_Submit"),
  RETRIEVE_PORTFOLIO_BACK("RetrievePortfolio_Back"),

  RETRIEVE_STRATEGY_ENTER("RetrieveStrategy"),
  RETRIEVE_STRATEGY_SUBMIT("RetrieveStrategy_Submit"),
  RETRIEVE_STRATEGY_BACK("RetrieveStrategy_Back"),

  EVALUATE_ACCOUNT_ENTER("EvaluateAccount"),
  EVALUATE_ACCOUNT_SUBMIT("EvaluateAccount_Submit"),
  EVALUATE_ACCOUNT_BACK("EvaluateAccount_Back"),

  SAVE_ALL_PORTFOLIO_ENTER("SaveAllPortfolio"),
  SAVE_ALL_STRATEGY_ENTER("SaveAllStrategy"),
  RETRIEVE_ALL_PORTFOLIO_ENTER("RetrieveAllPortfolio"),
  RETRIEVE_ALL_STRATEGY_ENTER("RetrieveAllStrategy"),

  CHECK_CURRENT_PORTFOLIO_ENTER("hintPortfolio"),
  CHECK_CURRENT_STRATEGY_ENTER("hintStrategy"),

  VIEW_ACCOUNT("ViewAccount"),
  VIEW_STRATEGY("ViewStrategy"),

  IS_EQUAL_WEIGHT_TITLE("Options"),
  IS_EQUAL_WEIGHT_ENTER("Want Your Share Equally Weighted? (1 for True, 2 for False)"),
  IS_EQUAL_WEIGHT_SUBMIT("WeightOption_Submit"),

  TYPE_WEIGHT_SUBMIT("TypeWeight_Submit"),

  ENTER_WEIGHT("Please Enter The Weight For The Stock in Percentage (xx.xx)");

  private final String msg;

  FieldName(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return this.msg;
  }
}
