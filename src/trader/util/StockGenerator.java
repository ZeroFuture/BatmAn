package trader.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import trader.ErrorMsg;
import trader.util.equity.Equity;
import trader.util.equity.USStock;

/**
 * This class implements a stock generator implementing EquityGenerator.
 */
public class StockGenerator implements EquityGenerator {
  private static final String urlDailyPrefix =
          "https://www.alphavantage"
                  + ".co/query?function=TIME_SERIES_DAILY"
                  + "&outputsize=full";
  private static final String[] apiKeys = new String[]{"BF756129MBEV817Y", "NR2HJDUJC95IT1BH",
    "76EFTN30DULF7VOE", "GO9JIZFXN8MKNYC4", "YPQG08FAN5U46T5E"};
  private static final String errorIndicator = "Error";
  private static final String invalidApiKeyError = "\"Note\": \"Thank you for " +
          "using Alpha Vantage! " +
          "Our standard API call frequency is 5 calls per minute and 500 calls per day. " +
          "Please visit https://www.alphavantage.co/premium/ " +
          "if you would like to target a higher API call frequency.\"";
  private static int counter = 0;
  private static int realCounter = 0;
  private static final int maxRealCounter = apiKeys.length;
  private static final Map<String, Map<String, USDPrice>> dailyStockCache = new HashMap<>();
  private static final String timeRegex = "^(\n)*([0-9]+\\-[0-9]+\\-[0-9]+)(\n)*$";

  /**
   * Generate the past equity given tickerSymbol, timeStamp and time.
   *
   * @param tickerSymbol equity tickerSymbol.
   * @param timeStamp    past timestamp.
   * @param shares       shares to buy.
   * @return Equity generated.
   * @throws RuntimeException if generation failed.
   */
  @Override
  public Equity generateEquity(String tickerSymbol, TimeStamp timeStamp, BigInteger shares)
          throws RuntimeException {
    if (tickerSymbol == null || timeStamp == null || shares == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!(timeStamp instanceof TradeTimeStamp)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_TYPE_INPUT.getMsg());
    }
    if (realCounter > maxRealCounter) {
      throw new IllegalArgumentException(ErrorMsg.FAIL_CRAW_DATA_API_NOT_WORKING.getMsg());
    }
    String apiKey = apiKeys[counter];
    TradeTimeStamp tradeTimeStamp = (TradeTimeStamp) timeStamp;
    updateApiCounter();
    URL url = buildUrl(tickerSymbol, apiKey);
    try {
      Equity res = generateEquityHelper(url, tickerSymbol, tradeTimeStamp, shares);
      realCounter = 0;
      return res;
    } catch (IllegalArgumentException e) {
      if (e.getMessage().equals(ErrorMsg.FAIL_CRAW_DATA_INVALID_API_KEY.getMsg())) {
        try {
          TimeUnit.MINUTES.sleep(1);
          return generateEquity(tickerSymbol, timeStamp, shares);
        } catch (Exception err) {
          throw new IllegalArgumentException(err.getMessage());
        }
      } else {
        realCounter = 0;
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }

  /**
   * private helper function to generate equity.
   *
   * @param url          api url.
   * @param tickerSymbol equity tickerSymbol.
   * @param timeStamp    buy in timeStamp.
   * @param shares       shares to generate.
   * @return Equity generated.
   * @throws IllegalArgumentException if invalid input.
   */
  private Equity generateEquityHelper(URL url, String tickerSymbol,
                                      TradeTimeStamp timeStamp, BigInteger shares)
          throws IllegalArgumentException {
    if (dailyStockCache.containsKey(tickerSymbol)) {
      return extractStockCache(tickerSymbol, timeStamp, shares);
    }
    InputStream inputStream;
    StringBuilder output = new StringBuilder();
    LocalDate matchDate = LocalDate.parse(timeStamp.toString());
    Equity res = null;
    try {
      inputStream = url.openStream();
      int ch;
      while ((ch = inputStream.read()) != -1) {
        if ((char) ch == '\n') {
          String line = output.toString();
          if (line.contains(errorIndicator)) {
            throw new IllegalArgumentException(
                    ErrorMsg.FAIL_CRAW_DATA_INVALID_TICKER_SYMBOL.getMsg());
          }
          if (line.contains(invalidApiKeyError)) {
            throw new IllegalArgumentException(ErrorMsg.FAIL_CRAW_DATA_INVALID_API_KEY.getMsg());
          }
          String[] stockData = line.split(",");
          String toMatchTime = stockData[0].replace("\n", "").trim();
          if (toMatchTime.matches(timeRegex)) {
            LocalDate cacheDate = LocalDate.parse(toMatchTime);
            USDPrice price = generatePrice(stockData);
            updateStockCache(tickerSymbol, cacheDate.toString(), price);
            if (matchDate.compareTo(cacheDate) == 0) {
              res = new USStock(tickerSymbol, generatePrice(stockData), timeStamp, shares);
            } else if (matchDate.compareTo(cacheDate) > 0 && res == null) {
              throw new IllegalArgumentException(
                      ErrorMsg.FAIL_CRAW_DATA_HOLIDAY_TIME_STAMP.getMsg());
            }
          }
          output = new StringBuilder();
        }
        output.append((char) ch);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException(ErrorMsg.FAIL_CRAW_DATA_NO_DATA_FOUND.getMsg());
    }
    if (res != null) {
      return res;
    }
    throw new IllegalArgumentException(ErrorMsg.FAIL_CRAW_DATA_FUTURE_TIME_STAMP.getMsg());
  }

  /**
   * private helper function to update api counter.
   */
  private static void updateApiCounter() {
    counter++;
    realCounter++;
    if (counter == apiKeys.length) {
      counter = 0;
    }
  }

  /**
   * private helper function to build url.
   *
   * @param tickerSymbol tickerSymbol.
   * @param apiKey       apiKey.
   * @return URL.
   * @throws RuntimeException if MalformedURL.
   */
  private URL buildUrl(String tickerSymbol, String apiKey)
          throws RuntimeException {
    URL url;
    try {
      url = new URL(urlDailyPrefix + "&symbol=" + tickerSymbol +
              "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException(ErrorMsg.FAIL_CRAW_DATA_API_NOT_WORKING.getMsg());
    }
    return url;
  }

  /**
   * private helper to generatePrice.
   *
   * @param stockData stockData.
   * @return Price generated with given stockData.
   */
  private USDPrice generatePrice(String[] stockData) {
    BigDecimal close = new BigDecimal(stockData[4]);
    return new USDPrice(close);
  }

  /**
   * update StockCache to prevent excessive api calls.
   *
   * @param tickerSymbol   tickerSymbol of Stock.
   * @param cacheTimeStamp timeStamp to store.
   * @param unitPrice      price of stock.
   */
  private void updateStockCache(String tickerSymbol,
                                String cacheTimeStamp, USDPrice unitPrice) {
    dailyStockCache.putIfAbsent(tickerSymbol, new HashMap<>());
    Map<String, USDPrice> stockMap = dailyStockCache.get(tickerSymbol);
    stockMap.put(cacheTimeStamp, unitPrice);
  }

  /**
   * private helper function to extract stock from cache.
   *
   * @param tickerSymbol stock tickerSymbol.
   * @param timeStamp    time stamp to init.
   * @param shares       shares to init.
   * @return Equity generated from cache.
   * @throws IllegalArgumentException if stock queried on holiday.
   */
  private Equity extractStockCache(String tickerSymbol,
                                   TradeTimeStamp timeStamp, BigInteger shares)
          throws IllegalArgumentException {
    Map<String, USDPrice> stockMap = dailyStockCache.get(tickerSymbol);
    String matchingDate = timeStamp.toString();
    if (stockMap.containsKey(matchingDate)) {
      USDPrice unitPrice = stockMap.get(matchingDate);
      return new USStock(tickerSymbol, unitPrice, timeStamp, shares);
    }
    throw new IllegalArgumentException(ErrorMsg.FAIL_CRAW_DATA_HOLIDAY_TIME_STAMP.getMsg());
  }
}
