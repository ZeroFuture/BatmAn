package trader.view;

/**
 * ITraderView represent view interface.
 */
public interface ITraderView  {

  /**
   * Get output as appendable.
   * @return appendable.
   */
  Appendable getOutput();

  /**
   * Get input as readable.
   * @return readable.
   */
  Readable getInput();

}
