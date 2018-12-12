package trader.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

import trader.ErrorMsg;

/**
 * This class represent price in USD format.
 */
public class USDPrice implements Price {
  private final BigDecimal priceAmount;
  private static final NumberFormat format = NumberFormat.getCurrencyInstance();

  /**
   * Constructor of USDPrice given priceAmount.
   *
   * @param priceAmount tbe price to be passed to the constructor
   * @throws IllegalArgumentException if invalid input.
   */
  public USDPrice(BigDecimal priceAmount) throws IllegalArgumentException {
    if (priceAmount == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (priceAmount.compareTo(new BigDecimal("0")) < 0) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_PRICE_AMOUNT_NEGATIVE.getMsg());
    }
    this.priceAmount = priceAmount;
  }

  /**
   * Copy constructor for USDPrice.
   *
   * @param price price to be copied.
   * @throws IllegalArgumentException if invalid input.
   */
  public USDPrice(USDPrice price) throws IllegalArgumentException {
    if (price == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (price.getAmount().compareTo(new BigDecimal("0")) < 0) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_PRICE_AMOUNT_NEGATIVE.getMsg());
    }
    this.priceAmount = new BigDecimal(price.getAmount().toString());
  }

  /**
   * Get price amount in BigDecimal.
   */
  @Override
  public BigDecimal getAmount() {
    return new BigDecimal(priceAmount.toString());
  }

  /**
   * Override toString for USDPrice.
   *
   * @return the format string
   */
  @Override
  public String toString() {
    return format.format(priceAmount);
  }

  /**
   * Override compareTo for USDPrice.
   *
   * @param other the other Price to compare with
   * @return 0 if equals, -1 if this is less than other, 1 if this is greater than other
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public int compareTo(Price other) throws IllegalArgumentException {
    if (other == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    return priceAmount.compareTo(other.getAmount());
  }

  /**
   * Add two price amount and return the new price.
   *
   * @param increment price to buy.
   * @return new Price.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public Price add(Price increment) throws IllegalArgumentException {
    if (increment == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!(increment instanceof USDPrice)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_TYPE_INPUT.getMsg());
    }
    BigDecimal newAmount = priceAmount.add(increment.getAmount());
    return new USDPrice(newAmount);
  }
}
