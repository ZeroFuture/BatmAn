package trader.util;

import java.time.DateTimeException;
import java.time.LocalDate;

import trader.ErrorMsg;

/**
 * This class represents a general timestamp.
 */
public class TradeTimeStamp implements TimeStamp {
  private final LocalDate localDate;

  /**
   * TradeTimeStamp constructor.
   *
   * @param year       year in int.
   * @param month      month in int.
   * @param dayOfMonth dayOfMonth in int.
   * @throws IllegalArgumentException if invalid input.
   */
  public TradeTimeStamp(int year, int month, int dayOfMonth)
          throws IllegalArgumentException {
    try {
      this.localDate = LocalDate.of(year, month, dayOfMonth);
    } catch (DateTimeException e) {
      throw new IllegalArgumentException(ErrorMsg.FAIL_TIME_STAMP_INIT_INVALID_DATETIME.getMsg());
    }
  }

  /**
   * TradeTimeStamp constructor with given localDateTime.
   *
   * @param date LocalDate to be initialized.
   * @throws IllegalArgumentException if invalid input.
   */
  public TradeTimeStamp(LocalDate date) throws IllegalArgumentException {
    if (date == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    this.localDate = date;
  }

  /**
   * Copy constructor of TradeTimeStamp.
   *
   * @param timeStamp timeStamp to be copied.
   * @throws IllegalArgumentException if invalid input.
   */
  public TradeTimeStamp(TradeTimeStamp timeStamp) throws IllegalArgumentException {
    if (timeStamp == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    this.localDate = timeStamp.getLocalDate();
  }

  /**
   * Get current localDate.
   *
   * @return localDate.
   */
  @Override
  public LocalDate getLocalDate() {
    return LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
  }

  /**
   * compareTo with another timestamp.
   *
   * @param toBeCompared timestamp to be compared.
   * @return 0 if two timestamp are identical, positive if this is greater and negative otherwise.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public int compareTo(TimeStamp toBeCompared) throws IllegalArgumentException {
    if (toBeCompared == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    return this.localDate.compareTo(toBeCompared.getLocalDate());
  }

  /**
   * Override toString for TradeTimeStamp.
   *
   * @return string of the timestamp.
   */
  @Override
  public String toString() {
    return localDate.toString();
  }
}
