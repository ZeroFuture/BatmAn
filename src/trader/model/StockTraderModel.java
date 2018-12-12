package trader.model;

import trader.util.account.StockTraderAccount;

/**
 * This is the main trader model.
 */
public class StockTraderModel extends AbstractStockTraderModel implements ITraderModel {
  /**
   * model constructor.
   */
  private StockTraderModel() {
    this.mainAccount = new StockTraderAccount();
  }

  /**
   * model getBuilder.
   * @return builder.
   */
  public static StockTraderModelBuilder getBuilder() {
    return new StockTraderModelBuilder();
  }

  /**
   * modelBuilder class.
   */
  public static class StockTraderModelBuilder implements StockTraderOperationBuilder {

    /**
     * constructor of model builder.
     */
    private StockTraderModelBuilder() {
    }

    /**
     * build.
     * @return model.
     */
    @Override
    public ITraderModel build() {
      return new StockTraderModel();
    }
  }
}
