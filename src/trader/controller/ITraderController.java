package trader.controller;

/**
 * Interface for the ITrader trading system controller. An implementation will work with
 * the ITraderModel interface to provide the trading system.
 */
public interface ITraderController {

  /**
   * Start the trading system.
   */
  void play();

}
