package trader.util;

import java.math.BigInteger;

import trader.util.equity.Equity;

/**
 * This interface support functions to craw equity data and generate equity.
 */
public interface EquityGenerator {

  /**
   * Generate equity given tickerSymbol, timeStamp and time.
   *
   * @param tickerSymbol equity tickerSymbol.
   * @param timeStamp    past timestamp.
   * @param shares       shares to buy.
   * @return Equity generated.
   * @throws RuntimeException if generation failed.
   */
  Equity generateEquity(String tickerSymbol, TimeStamp timeStamp, BigInteger shares)
          throws RuntimeException;
}
