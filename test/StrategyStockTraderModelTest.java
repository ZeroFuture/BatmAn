import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import trader.model.IRetrievableStrategyTraderModel;
import trader.model.StrategyStockTraderModel;

import static junit.framework.TestCase.fail;

/**
 * Model tests.
 */
public class StrategyStockTraderModelTest {
  private IRetrievableStrategyTraderModel model = StrategyStockTraderModel.getBuilder().build();

  @Test
  public void testAdd() {
    try {
      model.addEquity("GOOG", "Investment");
      System.out.println(model.viewAccount());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void testBuy() {
    try {
      model.buyEquity("FB", "500",
              "11/13/2018", "Investment");
      System.out.println(model.viewAccount());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void testBuyWithCommission() {
    try {
      model.buyEquityWithCommission("GOOG", "1000",
              "11/13/2018", "Investment", "3.00");
      System.out.println(model.viewAccount());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void testEvaluateGetCostBasisPortfolio() {
    try {
      model.createPortfolio("EvaluateTest");
      model.buyEquityWithCommission("FB", "500",
              "11/12/2018", "EvaluateTest", "3.00");
      model.buyEquityWithCommission("GOOG", "250",
              "10/24/2018", "EvaluateTest", "2.00");
      System.out.println(model.evaluatePortfolio("12/04/2018", "EvaluateTest"));
      System.out.println(model.getPortfolioCostBasis("EvaluateTest"));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void testEvaluateGetCostBasisAccount() {
    try {
      model.buyEquityWithCommission("FB", "100",
              "09/19/2018", "Investment", "3.00");
      model.buyEquityWithCommission("GOOG", "20",
              "11/07/2018", "Investment", "2.00");
      System.out.println(model.evaluateAccount("12/03/2018"));
      System.out.println(model.getAccountCostBasis());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void testAddAndApplyWeightedInvestmentStrategy() {
    try {
      Map<String, String> stocks = new HashMap<>();
      stocks.put("AAPL", "50.00%");
      stocks.put("FB", "50.00%");
      model.addWeightedInvestmentStrategy("WeightedInvestmentCommission", stocks,
              "20000.00", "3.00", "11/12/2018");
      System.out.println(model.viewAllStrategies());
      model.applyStrategy("WeightedInvestmentCommission", "AddApplyWeighted");
      System.out.println(model.viewPortfolio("AddApplyWeighted"));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void testAddAndApplyDACInvestmentStrategy() {
    try {
      Map<String, String> stocks = new HashMap<>();
      stocks.put("AAPL", "50.00%");
      stocks.put("FB", "50.00%");
      model.addDollarAveragingCostStrategy("DACInvestmentCommission", stocks,
              "50000.00", "2.00",
              "11/12/2018", "01/10/2019", "1W");
      System.out.println(model.viewAllStrategies());
      model.applyStrategy("DACInvestmentCommission", "AddApplyDAC");
      System.out.println(model.viewPortfolio("AddApplyDAC"));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void testSaveRetrieveAllStrategies() {
    try {
      Map<String, String> stocks1 = new HashMap<>();
      stocks1.put("AAPL", "50.00%");
      stocks1.put("FB", "50.00%");
      model.addWeightedInvestmentStrategy("WeightedInvestmentCommission", stocks1,
              "20000.00", "3.00", "11/12/2018");
      Map<String, String> stocks2 = new HashMap<>();
      stocks2.put("AAPL", "50.00%");
      stocks2.put("FB", "50.00%");
      model.addDollarAveragingCostStrategy("DACInvestmentCommission", stocks2,
              "50000.00", "2.00",
              "11/12/2018", "NA", "1W");
      System.out.println(model.viewAllStrategies());
      model.saveAllStrategy();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
    try {
      model.retrieveAllStrategy();
      System.out.println(model.viewAllStrategies());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void testSaveRetrieveAllPortfolios() {
    try {
      model.buyEquityWithCommission("GOOG", "1000",
              "11/13/2018", "Investment", "3.00");
      model.retrieveAllStrategy();
      model.createPortfolio("Portfolio1");
      model.createPortfolio("Portfolio2");
      model.applyStrategy("WeightedInvestmentCommission", "Portfolio1");
      model.applyStrategy("DACInvestmentCommission", "Portfolio2");
      model.saveAllPortfolio();
      System.out.println(model.viewAccount());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
    try {
      model.retrieveAllPortfolio();
      System.out.println(model.viewAccount());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void testSaveRetrieveSinglePortfolio() {
    try {
      model.createPortfolio("SaveSingle");
      model.buyEquityWithCommission("FB", "1000",
              "11/08/2018", "SaveSingle", "3.00");
      model.buyEquityWithCommission("GOOG", "500",
              "11/14/2018", "SaveSingle", "3.00");
      model.savePortfolio("SaveSingle");
      model.createPortfolio("DoesNotSaveSingle");
      model.buyEquityWithCommission("FB", "250",
              "11/07/2018", "SaveSingle", "2.00");
      model.buyEquityWithCommission("GOOG", "100",
              "11/13/2018", "SaveSingle", "2.00");
      System.out.println(model.viewPortfolio("SaveSingle"));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
    try {
      model.retrievePortfolio("SaveSingle");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
    try {
      model.retrievePortfolio("DoesNotSaveSingle");
      fail();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println(model.viewPortfolio("SaveSingle"));
  }

  @Test
  public void testSaveRetrieveSingleStrategy() {
    try {
      Map<String, String> stocks = new HashMap<>();
      stocks.put("AAPL", "70.00%");
      stocks.put("GOOG", "30.00%");
      model.addWeightedInvestmentStrategy("WeightedInvestmentSingle", stocks,
              "20000.00", "3.00", "11/12/2018");
      model.saveStrategy("WeightedInvestmentSingle");
      System.out.println(model.viewAllStrategies());
      Map<String, String> stocks2 = new HashMap<>();
      stocks2.put("GOOG", "50.00%");
      stocks2.put("FB", "50.00%");
      model.addDollarAveragingCostStrategy("DACInvestmentSingle", stocks2,
              "100000.00", "2.00",
              "11/12/2018", "NA", "1W");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
    try {
      model.retrieveStrategy("WeightedInvestmentSingle");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
    try {
      model.retrieveStrategy("DACInvestmentSingle");
      fail();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}