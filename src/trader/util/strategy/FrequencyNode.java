package trader.util.strategy;

import trader.ErrorMsg;

/**
 * FrequencyNode class.
 */
public class FrequencyNode {
  private char freqType;
  private long frequency;
  private static final String freqRegex = "(?<=\\d)(?=\\D)";

  /**
   * Constructor of frequency node takes a frequency type and corresponding frequency.
   * @param freqType freqType.
   * @param frequency frequency.
   */
  public FrequencyNode(char freqType, long frequency) {
    this.freqType = freqType;
    this.frequency = frequency;
  }

  /**
   * Parse String to a FrequencyNode.
   * @param freqStr to be parsed.
   * @return FrequencyNode.
   * @throws IllegalArgumentException if parse failed.
   */
  public static FrequencyNode parse(String freqStr) throws IllegalArgumentException {
    long freq;
    char freqType;
    try {
      String[] frequencyArr = freqStr.split(freqRegex);
      freq = Long.parseLong(frequencyArr[0]);
      String freqTypeStr = frequencyArr[1];
      if (freqTypeStr.length() > 1 || freqTypeStr.length() == 0) {
        throw new IllegalArgumentException(ErrorMsg.INVALID_FREQUENCY_FORMAT.getMsg());
      } else if (freqTypeStr.charAt(0) != 'D' && freqTypeStr.charAt(0) != 'W'
              && freqTypeStr.charAt(0) != 'M' && freqTypeStr.charAt(0) != 'Y') {
        throw new IllegalArgumentException(ErrorMsg.INVALID_FREQUENCY_FORMAT.getMsg());
      }
      freqType = frequencyArr[1].charAt(0);
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_FREQUENCY_FORMAT.getMsg());
    }
    return new FrequencyNode(freqType, freq);
  }

  /**
   * Getter for freqType.
   * @return freqType.
   */
  public char getFreqType() {
    return freqType;
  }

  /**
   * Getter for frequency.
   * @return frequency.
   */
  public long getFrequency() {
    return frequency;
  }
}
