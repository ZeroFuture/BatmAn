package trader.util.strategy;

/**
 * Support write strategy to local memory.
 */
public interface RetrievableStrategy extends Strategy {

  /**
   * Write current strategy to dest file.
   *
   * @param filePath filePath to write.
   * @throws IllegalArgumentException if write fails.
   */
  void write(String filePath);
}
