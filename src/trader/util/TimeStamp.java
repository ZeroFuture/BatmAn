package trader.util;

import java.time.LocalDate;

/**
 * This interface represents necessary functions for a timeStamp.
 */
public interface TimeStamp extends Comparable<TimeStamp> {

  /**
   * Get current localDate.
   *
   * @return localDate.
   */
  LocalDate getLocalDate();

  /**
   * compareTo with another timestamp.
   *
   * @param toBeCompared timestamp to be compared.
   * @return 0 if two timestamp are identical, positive if this is greater and negative otherwise.
   * @throws IllegalArgumentException if invalid input.
   */
  int compareTo(TimeStamp toBeCompared) throws IllegalArgumentException;

  /**
   * Override toString for timestamp.
   *
   * @return timestamp as String.
   */
  @Override
  String toString();
}
