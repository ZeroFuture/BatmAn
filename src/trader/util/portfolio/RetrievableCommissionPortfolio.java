package trader.util.portfolio;

/**
 * Support write to current portfolios to a file.
 */
public interface RetrievableCommissionPortfolio extends CommissionPortfolio {
  /**
   * Write current portfolio to dest file.
   *
   * @param filePath filePath to write.
   * @throws IllegalArgumentException if write fails.
   */
  void write(String filePath) throws IllegalArgumentException;
}
