package trader.model;

public interface IRetrievableStrategyTraderModel extends IStrategyTraderModel {
  /**
   * Save all portfolios.
   */
  void saveAllPortfolio();

  /**
   * Save the portfolio given name.
   * @param portfolioName portfolio to save.
   * @throws IllegalArgumentException if failed save.
   */
  void savePortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * Save all strategies.
   */
  void saveAllStrategy();

  /**
   * Save the strategy given name.
   * @param strategyName strategy to save.
   * @throws IllegalArgumentException if failed save.
   */
  void saveStrategy(String strategyName) throws IllegalArgumentException;

  /**
   * Retrieve all portfolio.
   */
  void retrieveAllPortfolio();

  /**
   * Retrieve the given portfolio.
   * @param portfolioName portfolio to retrieve.
   * @throws IllegalArgumentException if failed load.
   */
  void retrievePortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * Retrieve all strategies.
   */
  void retrieveAllStrategy();

  /**
   * Retrieve the given strategy.
   * @param strategyName strategy to retrieve.
   * @throws IllegalArgumentException if failed load.
   */
  void retrieveStrategy(String strategyName) throws IllegalArgumentException;
}
