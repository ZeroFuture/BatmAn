package trader.util.equity;

import java.math.BigInteger;

import trader.util.Price;
import trader.util.TimeStamp;

/**
 * This interface represents necessary methods.
 */
public interface Equity {
  /**
   * Get the equity symbol as String.
   *
   * @return the string of the equity symbol.
   */
  String getEquitySymbol();

  /**
   * Get the volume of the equity.
   *
   * @return the volume as big integer.
   */
  BigInteger getVolume();

  /**
   * Get the unit price value of the equity.
   *
   * @return the unit price value of the equity.
   */
  Price getUnitPrice();

  /**
   * Get the total value of the equity.
   *
   * @return total value of the equity.
   */
  Price getTotalValue();

  /**
   * Get the buy in timestamp of the equity.
   *
   * @return timestamp of the equity.
   */
  TimeStamp getTimeStamp();

  /**
   * Override the toString for the equity.
   *
   * @return toString of the equity contains all the information.
   */
  @Override
  String toString();
}
