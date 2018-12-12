package trader.view;

/**
 * Implements ITraderView.
 */
public class TraderViewImplement implements ITraderView {
  private Readable in;
  private Appendable out;

  /**
   * Constructor for TraderViewImplement.
   * @param in input.
   * @param out output.
   */
  public TraderViewImplement(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  /**
   * Get output as appendable.
   * @return appendable.
   */
  @Override
  public Appendable getOutput() {
    return this.out;
  }

  /**
   * Get input as readable.
   * @return readable.
   */
  @Override
  public Readable getInput() {
    return this.in;
  }
}
