package trader.util.portfolio;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import trader.ErrorMsg;
import trader.util.equity.Equity;
import trader.util.Price;
import trader.util.USDPrice;

/**
 * Strategy Stock Portfolio.
 */
public class StockCommissionPortfolio extends StockPortfolio
        implements RetrievableCommissionPortfolio {
  private USDPrice totalCommissionFee;
  private Map<Equity, Price> commissionMap;

  /**
   * Constructor for a stock portfolio.
   *
   * @param portfolioTag name of the portfolio.
   */
  public StockCommissionPortfolio(String portfolioTag) throws IllegalArgumentException {
    super(portfolioTag);
    totalCommissionFee = new USDPrice(new BigDecimal("0"));
    commissionMap = new HashMap<>();
  }

  /**
   * View the current portfolio of the account.
   *
   * @return view.
   */
  @Override
  public String view() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.view());
    sb.append("\n").append("Commission Cost: ").append(getCommissionCost().toString());
    sb.append("\n").append("Total Cost Basis: ").append(getTotalCost().toString());
    return sb.toString();
  }

  /**
   * Add equity with commission fee.
   *
   * @param target     target equity.
   * @param commission commission fee to be added.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void buyWithCommission(Equity target, Price commission) throws IllegalArgumentException {
    if (target == null || commission == null) {
      throw new IllegalArgumentException(ErrorMsg.NULL_INPUT.getMsg());
    }
    if (!(commission instanceof USDPrice)) {
      throw new IllegalArgumentException(ErrorMsg.INVALID_TYPE_INPUT.getMsg());
    }
    super.buy(target);
    totalCommissionFee = (USDPrice) totalCommissionFee.add(commission);
    commissionMap.put(target, commission);
  }

  /**
   * Buy stock to portfolio.
   *
   * @param target stock to buy.
   * @throws IllegalArgumentException if invalid input.
   */
  @Override
  public void buy(Equity target) throws IllegalArgumentException {
    super.buy(target);
    commissionMap.put(target, new USDPrice(new BigDecimal("0.0")));
  }

  /**
   * Get commission cost of the portfolio.
   *
   * @return commission cost as Price.
   */
  @Override
  public Price getCommissionCost() {
    return new USDPrice(totalCommissionFee);
  }

  /**
   * Get total cost equals to portfolio cost plus commission cost.
   */
  @Override
  public Price getTotalCost() {
    return new USDPrice(super.getCost().add(totalCommissionFee).getAmount());
  }

  /**
   * Write current portfolio to dest file.
   *
   * @param filePath filePath to write.
   * @throws IllegalArgumentException if write fails.
   */
  @Override
  public void write(String filePath) throws IllegalArgumentException {
    filePath = filePath + "/" + portfolioTag;
    File file = new File(filePath);
    file.mkdir();
    writeEquityName(filePath);
    writeEquities(filePath);
  }

  /**
   * private helper to write equity name file.
   *
   * @param filePath filePath to write.
   * @throws IllegalArgumentException if write fails.
   */
  private void writeEquityName(String filePath) throws IllegalArgumentException {
    String equityNamesFile = filePath + "/equityNames.csv";
    String equityNameFileHeader = "TickerSymbol";
    FileWriter equityNamesFileWriter;
    try {
      equityNamesFileWriter = new FileWriter(equityNamesFile);
      equityNamesFileWriter.append(equityNameFileHeader).append("\n");
      for (String tickerSymbol : portfolio.keySet()) {
        equityNamesFileWriter.append(tickerSymbol).append("\n");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new IllegalArgumentException(ErrorMsg.FAILED_WRITE_PORTFOLIO.getMsg());
    }
    flushCloseWriter(equityNamesFileWriter);
  }

  /**
   * private helper to write equities file.
   *
   * @param filePath filePath to write.
   * @throws IllegalArgumentException if write fails.
   */
  private void writeEquities(String filePath) {
    String equitiesFile = filePath + "/equities.csv";

    String equitiesFileHeader = "TickerSymbol,TradeTime,UnitPrice,Shares,Commission";

    FileWriter equitiesFileWriter;
    try {
      equitiesFileWriter = new FileWriter(equitiesFile);
      equitiesFileWriter.append(equitiesFileHeader).append("\n");
      for (Equity equity : commissionMap.keySet()) {
        equitiesFileWriter.append(equity.getEquitySymbol()).append(",");
        equitiesFileWriter.append(equity.getTimeStamp().toString()).append(",");
        equitiesFileWriter.append(equity.getUnitPrice().getAmount().toString()).append(",");
        equitiesFileWriter.append(equity.getVolume().toString()).append(",");
        equitiesFileWriter.append(commissionMap.get(equity).getAmount().toString()).append("\n");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new IllegalArgumentException(ErrorMsg.FAILED_WRITE_PORTFOLIO.getMsg());
    }
    flushCloseWriter(equitiesFileWriter);
  }

  /**
   * private helper to close flush writer.
   *
   * @param writer writer to flush close.
   */
  private void flushCloseWriter(FileWriter writer) {
    try {
      writer.flush();
      writer.close();
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMsg.FAILED_WRITE_PORTFOLIO.getMsg());
    }
  }
}
