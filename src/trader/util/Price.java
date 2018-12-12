package trader.util;

import java.math.BigDecimal;

/**
 * This interface represents necessary methods for all the possible currencies.
 */
public interface Price extends Comparable<Price> {
  /**
   * Get price amount in BigDecimal.
   */
  BigDecimal getAmount();

  /**
   * Override toString for Price.
   */
  @Override
  String toString();

  /**
   * Override compareTo for Price.
   *
   * @param other the other price to compare
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  int compareTo(Price other) throws IllegalArgumentException;

  /**
   * Add two price amount and return the new price.
   *
   * @param increment price to buy.
   * @return new Price.
   * @throws IllegalArgumentException if invalid input.
   */
  Price add(Price increment) throws IllegalArgumentException;
}
