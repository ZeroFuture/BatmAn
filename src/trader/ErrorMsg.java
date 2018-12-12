package trader;

public enum ErrorMsg {
  //Model ErrorMsgs
  NULL_INPUT("Invalid Input: NULL."),
  EMPTY_INPUT("Invalid Input: EMPTY."),
  INVALID_TYPE_INPUT("Invalid Input: type incompatible."),
  INVALID_PRICE_AMOUNT_NEGATIVE("Invalid Price Amount: negative amount is unacceptable."),
  INVALID_NEGATIVE_SHARE("Invalid Negative Share: negative share is unacceptable."),
  FAIL_TIME_STAMP_INIT_INVALID_DATETIME("Failed TimeStamp init: invalid date or time."),
  FAIL_CRAW_DATA_API_NOT_WORKING("Failed Get Stock Data: alpha vantage api no longer working."),
  FAIL_CRAW_DATA_NO_DATA_FOUND("Failed Get Stock Data: no data found."),
  FAIL_CRAW_DATA_INVALID_TICKER_SYMBOL("Failed Get Stock Data: invalid ticker symbol."),
  FAIL_CRAW_DATA_FUTURE_TIME_STAMP("Failed Get Stock Data: future time stamp."),
  FAIL_CRAW_DATA_INVALID_API_KEY("Failed Get Stock Data: api key limit reached."),
  FAIL_CRAW_DATA_HOLIDAY_TIME_STAMP("Failed Get Stock Data: holiday time stamp."),
  INVALID_ADD_PORTFOLIO_NAMETAG_NOT_FOUND("Invalid add to portfolio: invalid portfolio nametag."),
  INVALID_BUY_PORTFOLIO_NAMETAG_NOT_FOUND("Invalid buy to portfolio: invalid portfolio nametag."),
  INVALID_ADD_PORTFOLIO_DUPLICATE_NAMETAG("Invalid buy to portfolio: duplicate portfolio nametag."),
  FAIL_INIT_STOCK_INVALID_VOLUME("Failed Init Stock: Invalid volume."),
  FAIL_INIT_STOCK_INVALID_TIMESTAMP("Failed Init Stock: Invalid time stamp."),
  FAIL_INIT_STOCK_INVALID_PRICE("Failed Init Stock: Invalid price."),
  INVALID_VIEW_PORTFOLIO_NAMETAG_NOT_FOUND("Failed view portfolio: invalid portfolio nametag."),
  INVALID_EVAL_PORTFOLIO_NAMETAG_NOT_FOUND("Failed evaluate portfolio: invalid portfolio nametag."),
  INVALID_GET_PORTFOLIO_NAMETAG_NOT_FOUND("Failed get portfolio: invalid portfolio nametag."),
  INVALID_APPLY_STRATEGY_NAMETAG_NOT_FOUND("Failed apply strategy: invalid strategy nametag."),
  INVALID_VIEW_STRATEGY_NAMETAG_NOT_FOUND("Failed view strategy: invalid strategy nametag."),
  INVALID_SAVE_PORTFOLIO_NAMETAG_NOT_FOUND("Failed save portfolio: invalid portfolio nametag."),
  INVALID_SAVE_STRATEGY_NAMETAG_NOT_FOUND("Failed save strategy: invalid strategy nametag."),
  INVALID_STRATEGY_FREQUENCY_TYPE("Failed Execute Investment Strategy: invalid frequency type."),
  INVALID_WEIGHTED_STRATEGY_WEIGHT_MAP("Failed Execute Weighted Strategy: invalid weighted map."),
  INVALID_FREQUENCY_FORMAT("Invalid frequency format: format of frequency is invalid."),
  FAILED_WRITE_STRATEGY("Failed write Strategy to file."),
  FAILED_WRITE_PORTFOLIO("Failed write Portfolio to file."),
  FAILED_LOAD_PORTFOLIO("Failed load Portfolio from file."),
  FAILED_LOAD_STRATEGY_INVALID_MAP_STR("Failed load Strategy from file: wrong map string format."),

  //Controller ErrorMsgs
  CONTROLLER_ADD_EQUITY("Add Stock fails"),
  CONTROLLER_BUY_STOCK_FAIL("Buy Stock fails!"),
  CONTROLLER_BUY_STOCK_WITH_COMMISSION_FAIL("Buy Stock with commission fails!"),
  CONTROLLER_CREATE_PORTFOLIO_FAIL("Create portfolio fails!"),
  CONTROLLER_EVALUATE_ACCOUNT_FAIL("EvaluateAccount fails!"),
  CONTROLLER_EVALUATE_PORTFOLIO_FAIL("EvaluatePortfolio fails!"),
  CONTROLLER_GET_COST_ACCOUNT_FAIL("Get Cost Account fails!"),
  CONTROLLER_GET_COST_PORTFOLIO_FAIL("Get Cost Portfolio fails!"),
  CONTROLLER_VIEW_ACCOUNT_FAIL("View Account fails!"),
  CONTROLLER_VIEW_PORTFOLIO_FAIL("ViewPortfolio fails!"),

  CONTROLLER_WEIGHTED_INVESTMENT_FAIL("Execute weighted investment fails!"),
  CONTROLLER_DCA_FAIL("Execute DCA strategy fails!"),
  CONTROLLER_NUMBER_OF_STOCKS_FAIL("The number of stocks is not equal to the " +
          "number you assign to the portfolio," +
          "Duplicate stock may exist, please re-enter the stock name."),
  CONTROLLER_TOTAL_SHARE_FAIL("The total weight you enter is not equal" +
          " to 100.00%, please re-enter the weight for each stock."),
  CONTROLLER_APPLY_STRATEGY_FAIL("Apply strategy fails!"),
  CONTROLLER_VIEW_STRATEGY_FAIL("View strategy fails!"),
  CONTROLLER_SAVE_STRATEGY("Save strategy fails!"),
  CONTROLLER_RETRIEVE_STRATEGY("Retrieve strategy fails!"),
  CONTROLLER_SAVE_PORTFOLIO("Save portfolio fails!"),
  CONTROLLER_RETRIEVE_PORTFOLIO("Retrieve portfolio fails!");
  private final String msg;

  ErrorMsg(String description) {
    this.msg = description;
  }

  public String getMsg() {
    return msg;
  }
}
