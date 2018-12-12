package trader.util.equity;

import java.math.BigDecimal;
import java.math.BigInteger;

import trader.ErrorMsg;
import trader.util.Price;
import trader.util.TimeStamp;
import trader.util.TradeTimeStamp;
import trader.util.USDPrice;

/**
 * USStock class implements Equity.
 */
public class USStock implements Equity {
  private final String tickerSymbol;
  private final USDPrice stockPrice;
  private final BigInteger shares;
  private final TradeTimeStamp buyInTime;

  /**
   * Constructor for a USStock.
   *
   * @param tickerSymbol tickerSymbol of the stock.
   * @param price        unit price of the stock.
   * @param time         buy in time of the stock.
   * @param volume       volume bought.
   * @throws IllegalArgumentException if invalid input.
   */
  public USStock(String tickerSymbol, USDPrice price, TradeTimeStamp time,
                 BigInteger volume) throws IllegalArgumentException {
    if (tickerSymbol == null || price == null || time == null || volume == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (volume.compareTo(new BigInteger("0")) < 0) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_NEGATIVE_SHARE.getMsg());
    }
    this.tickerSymbol = tickerSymbol;
    this.stockPrice = price;
    this.buyInTime = time;
    this.shares = volume;
  }

  /**
   * Copy constructor of USStock.
   * @param copy  stock to be copied.
   * @throws IllegalArgumentException if invalid input.
   */
  public USStock(USStock copy) throws IllegalArgumentException {
    if (copy == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    this.tickerSymbol = copy.getEquitySymbol();
    this.stockPrice = (USDPrice) copy.getUnitPrice();
    this.buyInTime = (TradeTimeStamp) copy.getTimeStamp();
    this.shares = copy.getVolume();
  }

  /**
   * Get the equity symbol as String.
   *
   * @return the string of the equity symbol.
   */
  @Override
  public String getEquitySymbol() {
    return tickerSymbol;
  }

  /**
   * Get the volume of the equity.
   *
   * @return the volume as big integer.
   */
  @Override
  public BigInteger getVolume() {
    return new BigInteger(shares.toString());
  }

  /**
   * Get the unit price value of the equity.
   *
   * @return the unit price value of the equity.
   */
  @Override
  public Price getUnitPrice() {
    return new USDPrice(stockPrice);
  }

  /**
   * Get the total value of the equity.
   *
   * @return total value of the equity.
   */
  @Override
  public Price getTotalValue() {
    BigDecimal priceAmount = stockPrice.getAmount().multiply(new BigDecimal(shares));
    return new USDPrice(priceAmount);
  }

  /**
   * Get the buy in timestamp of the equity.
   *
   * @return timestamp of the equity.
   */
  @Override
  public TimeStamp getTimeStamp() {
    return new TradeTimeStamp(buyInTime);
  }

  /**
   * Override toString for USStock.
   *
   * @return toString for USStock.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(" | TickerSymbol: ").append(tickerSymbol);
    sb.append(" | Trade Time: ").append(buyInTime.toString());
    sb.append(" | Price per Share: ").append(stockPrice.toString());
    sb.append(" | Shares: ").append(shares.toString());
    sb.append(" | Total Value: ").append(getTotalValue().toString());
    return sb.toString();
  }
}
