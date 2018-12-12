package trader.controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import trader.model.IRetrievableStrategyTraderModel;
import trader.view.FieldName;
import trader.view.GuiView;
import trader.view.TraderJFrameGuiView;
import trader.view.subframe.AddStockView;
import trader.view.subframe.ApplyStrategyView;
import trader.view.subframe.AskNumberOfStockViewForDAC;
import trader.view.subframe.AskNumberOfStockViewForWeighted;
import trader.view.subframe.AskWeightedOption;
import trader.view.subframe.BuyStockNoCommissionView;
import trader.view.subframe.BuyStockWithCommissionView;
import trader.view.subframe.CreatePortfolioView;
import trader.view.subframe.DACStrategyDifferentWeightView;
import trader.view.subframe.DollarAveragingCostStrategyView;
import trader.view.subframe.EvaluateAccountView;
import trader.view.subframe.EvaluatePortfolioView;
import trader.view.subframe.GetPortfolioCostBasisView;
import trader.view.subframe.RetrievePortfolioView;
import trader.view.subframe.RetrieveStrategyView;
import trader.view.subframe.SavePortfolioView;
import trader.view.subframe.SaveStrategyView;
import trader.view.subframe.ViewPortfolioView;
import trader.view.subframe.WeightedStrategyView;

/**
 * GuiController class.
 */
public class GuiController implements ITraderController {
  private IRetrievableStrategyTraderModel model;
  private GuiView view;
  private GuiView buyStockWithCommissionView;
  private GuiView buyStockNoCommissionView;
  private GuiView createPortfolioView;
  private GuiView viewPortfolioView;
  private GuiView evaluatePortfolioView;
  private GuiView addStockView;
  private GuiView getPortfolioCostBasisView;
  private GuiView askNumberOfStockViewForWeighted;
  private GuiView weightedStrategyView;
  private GuiView dollarAveragingCostStrategyView;
  private GuiView askNumberOfStockViewForDAC;
  private GuiView applyStrategyView;
  private GuiView savePortfolioView;
  private GuiView saveStrategyView;
  private GuiView retrievePortfolioView;
  private GuiView retrieveStrategyView;
  private GuiView evaluateAccountView;
  private GuiView fail;
  private GuiView askWeightedOptionView;
  private Map<String, String> stocks = new HashMap<>();
  private boolean isEquallyWeight;
  private int sizeOfStocks;

  /**
   * take model.
   *
   * @param m model.
   */
  public GuiController(IRetrievableStrategyTraderModel m) {
    this.model = m;
  }

  /**
   * generate view.
   *
   * @param v view.
   */
  public void setView(GuiView v) {
    this.view = v;
    configureButtonListener();
  }

  /**
   * Start the trading system.
   */
  @Override
  public void play() {
    //do nothing.
  }


  /**
   * Helper function to create Button Listener.
   */
  private void configureButtonListener() {
    Map<String, Runnable> buttonClickMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    //buy stock no commission enter
    buttonClickMap.put(FieldName.BUY_STOCK_NO_COMMISSION_ENTER.getMsg(), () -> {
      this.buyStockNoCommissionView = new BuyStockNoCommissionView(
              FieldName.BUY_STOCK_NO_COMMISSION_ENTER.getMsg());
      buyStockNoCommissionView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // buy stock with commission enter
    buttonClickMap.put(FieldName.BUY_STOCK_WITH_COMMISSION_ENTER.getMsg(), () -> {
      this.buyStockWithCommissionView = new BuyStockWithCommissionView(
              FieldName.BUY_STOCK_WITH_COMMISSION_ENTER.getMsg());
      buyStockWithCommissionView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // create portfolio enter
    buttonClickMap.put(FieldName.CREATE_PORTFOLIO_ENTER.getMsg(), () -> {
      this.createPortfolioView = new CreatePortfolioView(FieldName.CREATE_PORTFOLIO_ENTER.getMsg());
      createPortfolioView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // view portfolio enter
    buttonClickMap.put(FieldName.VIEW_PORTFOLIO_ENTER.getMsg(), () -> {
      this.viewPortfolioView = new ViewPortfolioView(FieldName.VIEW_PORTFOLIO_ENTER.getMsg());
      viewPortfolioView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // evaluate portfolio enter
    buttonClickMap.put(FieldName.EVALUATE_PORTFOLIO_ENTER.getMsg(), () -> {
      this.evaluatePortfolioView = new EvaluatePortfolioView(
              FieldName.EVALUATE_PORTFOLIO_ENTER.getMsg());
      evaluatePortfolioView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // AddStock enter
    buttonClickMap.put(FieldName.ADD_STOCK_ENTER.getMsg(), () -> {
      this.addStockView = new AddStockView(FieldName.ADD_STOCK_ENTER.getMsg());
      addStockView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // getCostCasis enter
    buttonClickMap.put(FieldName.GET_PORTFOLIO_COST_BASIS_ENTER.getMsg(), () -> {
      this.getPortfolioCostBasisView = new GetPortfolioCostBasisView(
              FieldName.GET_PORTFOLIO_COST_BASIS_ENTER.getMsg());
      getPortfolioCostBasisView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // weighted enter
    buttonClickMap.put(FieldName.WEIGHTED_INVESTMENT_ENTER.getMsg(), () -> {
      this.askNumberOfStockViewForWeighted = new AskNumberOfStockViewForWeighted(
              FieldName.ASK_NUMBER_OF_STOCK_ENTER.getMsg());
      askNumberOfStockViewForWeighted.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // DAC enter
    buttonClickMap.put(FieldName.DAC_STRATEGY_ENTER.getMsg(), () -> {
      this.askNumberOfStockViewForDAC = new AskNumberOfStockViewForDAC(
              FieldName.ASK_NUMBER_OF_STOCK_ENTER.getMsg());
      askNumberOfStockViewForDAC.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // Apply strategy enter
    buttonClickMap.put(FieldName.APPLY_STRATEGY_ENTER.getMsg(), () -> {
      this.applyStrategyView = new ApplyStrategyView(FieldName.APPLY_STRATEGY_ENTER.getMsg());
      applyStrategyView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // Save portfolio enter
    buttonClickMap.put(FieldName.SAVE_PORTFOLIO_ENTER.getMsg(), () -> {
      this.savePortfolioView = new SavePortfolioView(FieldName.SAVE_PORTFOLIO_ENTER.getMsg());
      savePortfolioView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // Save Strategy enter
    buttonClickMap.put(FieldName.SAVE_STRATEGY_ENTER.getMsg(), () -> {
      this.saveStrategyView = new SaveStrategyView(FieldName.SAVE_STRATEGY_ENTER.getMsg());
      saveStrategyView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // Retrieve portfolio enter
    buttonClickMap.put(FieldName.RETRIEVE_PORTFOLIO_ENTER.getMsg(), () -> {
      this.retrievePortfolioView = new RetrievePortfolioView(
              FieldName.RETRIEVE_PORTFOLIO_ENTER.getMsg());
      retrievePortfolioView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // Retrieve Strategy enter
    buttonClickMap.put(FieldName.RETRIEVE_STRATEGY_ENTER.getMsg(), () -> {
      this.retrieveStrategyView =
              new RetrieveStrategyView(FieldName.RETRIEVE_STRATEGY_ENTER.getMsg());
      retrieveStrategyView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // Evaluate Account enter
    buttonClickMap.put(FieldName.EVALUATE_ACCOUNT_ENTER.getMsg(), () -> {
      this.evaluateAccountView = new EvaluateAccountView(
              FieldName.EVALUATE_ACCOUNT_ENTER.getMsg());
      evaluateAccountView.addActionListener(buttonListener);
      ((JFrame) view).dispose();
    });

    // back for BuyWithCommission
    buttonClickMap.put(FieldName.BUY_STOCK_COMMISSION_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) buyStockWithCommissionView).dispose();
    });

    // back for BuyNoCommission
    buttonClickMap.put(FieldName.BUY_STOCK_NO_COMMISSION_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) buyStockNoCommissionView).dispose();
    });

    //back for CreatePortfolio
    buttonClickMap.put(FieldName.CREATE_PORTFOLIO_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) createPortfolioView).dispose();
    });

    //back for viewPortfolio
    buttonClickMap.put(FieldName.VIEW_PORTFOLIO_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) viewPortfolioView).dispose();
    });

    // back for evaluatePortfolio
    buttonClickMap.put(FieldName.EVALUATE_PORTFOLIO_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) evaluatePortfolioView).dispose();
    });

    // back for addStock
    buttonClickMap.put(FieldName.ADD_STOCK_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) addStockView).dispose();
    });

    // back for getPortfolioCost
    buttonClickMap.put(FieldName.GET_PORTFOLIO_COST_BASIS_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) getPortfolioCostBasisView).dispose();
    });

    // back for AskForNumberforWeighted
    buttonClickMap.put(FieldName.WEIGHTED_ASK_NUMBER_OF_STOCK_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) askNumberOfStockViewForWeighted).dispose();
    });

    // back for AskForNumberForWeighted
    buttonClickMap.put(FieldName.DAC_ASK_NUMBER_OF_STOCK_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) askNumberOfStockViewForDAC).dispose();
    });

    // back for weightedStrategy
    buttonClickMap.put(FieldName.WEIGHTED_INVESTMENT_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) weightedStrategyView).dispose();
    });

    //back for DAC strategy
    buttonClickMap.put(FieldName.DAC_STRATEGY_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) dollarAveragingCostStrategyView).dispose();
    });

    //back for Apply strategy
    buttonClickMap.put(FieldName.APPLY_STRATEGY_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) applyStrategyView).dispose();
    });

    //back for save portfolio
    buttonClickMap.put(FieldName.SAVE_PORTFOLIO_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) savePortfolioView).dispose();
    });

    //back for save Strategy
    buttonClickMap.put(FieldName.SAVE_STRATEGY__BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) saveStrategyView).dispose();
    });

    //back for retrieve portfolio
    buttonClickMap.put(FieldName.RETRIEVE_PORTFOLIO_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) retrievePortfolioView).dispose();
    });

    //back for retrieve strategy
    buttonClickMap.put(FieldName.RETRIEVE_STRATEGY_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) retrieveStrategyView).dispose();
    });

    // back for evaluate Account
    buttonClickMap.put(FieldName.EVALUATE_ACCOUNT_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) evaluateAccountView).dispose();
    });

    //back for DAC_diff
    buttonClickMap.put(FieldName.DAC_STRATEGY_DIFF_BACK.getMsg(), () -> {
      this.view = new TraderJFrameGuiView(FieldName.PROGRAM_START.getMsg(), this);
      this.view.addActionListener(buttonListener);
      ((JFrame) dollarAveragingCostStrategyView).dispose();
    });

    // buy stock commission submit
    buttonClickMap.put(FieldName.BUY_STOCK_WITH_COMMISSION_SUBMIT.getMsg(), () -> {
      String text = buyStockWithCommissionView.getInputString();
      String[] parseText = text.split("\n");
      try {
        model.buyEquityWithCommission(parseText[1], parseText[3], parseText[2], parseText[4],
                parseText[0]);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // buy stock no commission submit
    buttonClickMap.put(FieldName.BUY_STOCK_WITH_NO_COMMISSION_SUBMIT.getMsg(), () -> {
      String text = buyStockNoCommissionView.getInputString();
      String[] parseText = text.split("\n");
      try {
        model.buyEquity(parseText[0], parseText[2], parseText[1], parseText[3]);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // create portfolio submit
    buttonClickMap.put(FieldName.CREATE_PORTFOLIO_SUBMIT.getMsg(), () -> {
      String text = createPortfolioView.getInputString();
      try {
        model.createPortfolio(text);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // view portfolio submit
    buttonClickMap.put(FieldName.VIEW_PORTFOLIO_SUBMIT.getMsg(), () -> {
      String text = viewPortfolioView.getInputString();
      try {
        JOptionPane.showMessageDialog(null, model.viewPortfolio(text));
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // evaluate portfolio submit
    buttonClickMap.put(FieldName.EVALUATE_PORTFOLIO_SUBMIT.getMsg(), () -> {
      String text = evaluatePortfolioView.getInputString();
      String[] parseText = text.split("\n");
      try {
        JOptionPane.showMessageDialog(null,
                model.evaluatePortfolio(parseText[1], parseText[0]));
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // add stock submit
    buttonClickMap.put(FieldName.ADD_STOCK_SUBMIT.getMsg(), () -> {
      String text = addStockView.getInputString();
      String[] parseText = text.split("\n");
      try {
        model.addEquity(parseText[0], parseText[1]);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    //getportfoliocost submit
    buttonClickMap.put(FieldName.GET_PORTFOLIO_COST_BASIS_SUBMIT.getMsg(), () -> {
      String text = getPortfolioCostBasisView.getInputString();
      try {
        JOptionPane.showMessageDialog(null, model.getPortfolioCostBasis(text));
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    //ask_number_stock_weight submit
    buttonClickMap.put(FieldName.WEIGHTED_ASK_NUMBER_OF_STOCK_SUBMIT.getMsg(), () -> {
      String number = askNumberOfStockViewForWeighted.getInputString();
      try {
        this.sizeOfStocks = Integer.parseInt(number);
        this.weightedStrategyView = new WeightedStrategyView(sizeOfStocks,
                FieldName.WEIGHTED_INVESTMENT_ENTER.getMsg());
        weightedStrategyView.addActionListener(buttonListener);
        ((JFrame) askNumberOfStockViewForWeighted).dispose();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    //add_weighted_invest submit
    buttonClickMap.put(FieldName.WEIGHTED_INVESTMENT_SUBMIT.getMsg(), () -> {
      String result = this.weightedStrategyView.getInputString();
      this.stocks.clear();
      String[] parsetext = result.split("\n");
      int totalSize = parsetext.length;
      String sameFormatWeight = String.format("%.2f", 100.00 / (parsetext.length - 4)) + "%";
      for (int i = 0; i < sizeOfStocks; i++) {
        this.stocks.put(parsetext[i], sameFormatWeight);
      }
      try {
        model.addWeightedInvestmentStrategy(parsetext[totalSize - 1], this.stocks,
                parsetext[totalSize - 4], parsetext[totalSize - 3], parsetext[totalSize - 2]);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    //ask_number_stock_dac
    buttonClickMap.put(FieldName.DAC_ASK_NUMBER_OF_STOCK_SUBMIT.getMsg(), () -> {
      String number = askNumberOfStockViewForDAC.getInputString();
      try {
        this.sizeOfStocks = Integer.parseInt(number);
        this.askWeightedOptionView = new
                AskWeightedOption(FieldName.IS_EQUAL_WEIGHT_TITLE.getMsg());
        askWeightedOptionView.addActionListener(buttonListener);
        ((JFrame) askNumberOfStockViewForDAC).dispose();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    //dac_strategy submit
    buttonClickMap.put(FieldName.DAC_STRATEGY_SUBMIT.getMsg(), () -> {
      String result = this.dollarAveragingCostStrategyView.getInputString();
      String[] parseText = result.split("\n");
      try {
        String sameFormatWeight = String.format("%.2f", 100.00 / this.sizeOfStocks) + "%";
        this.stocks.clear();
        for (int i = 0; i < sizeOfStocks; i++) {
          this.stocks.put(parseText[i], sameFormatWeight);
        }
        model.addDollarAveragingCostStrategy(parseText[parseText.length - 1], this.stocks,
                parseText[parseText.length - 6], parseText[parseText.length - 5],
                parseText[parseText.length - 4], parseText[parseText.length - 3],
                parseText[parseText.length - 2]);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // dac_diff_submit
    buttonClickMap.put(FieldName.DAC_STRATEGY_DIFF_SUBMIT.getMsg(), () -> {
      String result = this.dollarAveragingCostStrategyView.getInputString();
      String[] parseText = result.split("\n");
      try {
        this.stocks.clear();
        for (int i = 0; i < this.sizeOfStocks * 2; i += 2) {
          this.stocks.put(parseText[i], parseText[i + 1]);
        }
        model.addDollarAveragingCostStrategy(parseText[parseText.length - 1], this.stocks,
                parseText[parseText.length - 6], parseText[parseText.length - 5],
                parseText[parseText.length - 4], parseText[parseText.length - 3],
                parseText[parseText.length - 2]);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    //apply strategy submit
    buttonClickMap.put(FieldName.APPLY_STRATEGY_SUBMIT.getMsg(), () -> {
      String result = this.applyStrategyView.getInputString();
      String[] parseText = result.split("\n");
      try {
        model.applyStrategy(parseText[0], parseText[1]);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // save portfolio submit
    buttonClickMap.put(FieldName.SAVE_PORTFOLIO_SUBMIT.getMsg(), () -> {
      String result = this.savePortfolioView.getInputString();
      try {
        model.savePortfolio(result);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // save strategy submit
    buttonClickMap.put(FieldName.SAVE_STRATEGY_SUBMIT.getMsg(), () -> {
      String result = this.saveStrategyView.getInputString();
      try {
        model.saveStrategy(result);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // retrieve portfolio submit
    buttonClickMap.put(FieldName.RETRIEVE_PORTFOLIO_SUBMIT.getMsg(), () -> {
      String result = this.retrievePortfolioView.getInputString();
      try {
        model.retrievePortfolio(result);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // retrieve strategy submit
    buttonClickMap.put(FieldName.RETRIEVE_STRATEGY_SUBMIT.getMsg(), () -> {
      String result = this.retrieveStrategyView.getInputString();
      try {
        model.retrieveStrategy(result);
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // evaluate account submit
    buttonClickMap.put(FieldName.EVALUATE_ACCOUNT_SUBMIT.getMsg(), () -> {
      String result = this.evaluateAccountView.getInputString();
      try {
        JOptionPane.showMessageDialog(null, model.evaluateAccount(result));
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // save all portfolio submit
    buttonClickMap.put(FieldName.SAVE_ALL_PORTFOLIO_ENTER.getMsg(), () -> {
      // call model
      try {
        model.saveAllPortfolio();
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // save all strategy submit
    buttonClickMap.put(FieldName.SAVE_ALL_STRATEGY_ENTER.getMsg(), () -> {
      try {
        model.saveAllStrategy();
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });
    // retrieve all portfolio
    buttonClickMap.put(FieldName.RETRIEVE_ALL_PORTFOLIO_ENTER.getMsg(), () -> {
      try {
        model.retrieveAllPortfolio();
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });
    // retrieve all strategy
    buttonClickMap.put(FieldName.RETRIEVE_ALL_STRATEGY_ENTER.getMsg(), () -> {
      try {
        model.retrieveAllStrategy();
        JOptionPane.showMessageDialog(null, FieldName.SUCCESS.getMsg());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // submit weightOption
    buttonClickMap.put(FieldName.IS_EQUAL_WEIGHT_SUBMIT.getMsg(), () -> {
      String text = askWeightedOptionView.getInputString();
      try {
        int judge = Integer.parseInt(text);
        if (judge != 1 && judge != 2) {
          throw new Exception();
        } else {
          this.isEquallyWeight = judge == 1;
          if (this.isEquallyWeight) {
            this.dollarAveragingCostStrategyView = new DollarAveragingCostStrategyView(
                    this.sizeOfStocks, FieldName.DAC_STRATEGY_ENTER.getMsg());
            dollarAveragingCostStrategyView.addActionListener(buttonListener);
            ((JFrame) askWeightedOptionView).dispose();
          } else {
            this.dollarAveragingCostStrategyView = new DACStrategyDifferentWeightView(this
                    .sizeOfStocks, FieldName.DAC_STRATEGY_ENTER.getMsg());
            dollarAveragingCostStrategyView.addActionListener(buttonListener);
            ((JFrame) askWeightedOptionView).dispose();
          }
        }
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, FieldName.FAIL_LINE.getMsg());
      }
    });

    // check all existing portfolio
    buttonClickMap.put(FieldName.CHECK_CURRENT_PORTFOLIO_ENTER.getMsg(), () -> {
      JOptionPane.showMessageDialog(null, model.viewPortfolioNameTags());
    });
    // check all strategy
    buttonClickMap.put(FieldName.CHECK_CURRENT_STRATEGY_ENTER.getMsg(), () -> {
      JOptionPane.showMessageDialog(null, model.viewStrategyNameTags());
    });

    buttonClickMap.put(FieldName.VIEW_ACCOUNT.getMsg(), () -> {
      JOptionPane.showMessageDialog(null, model.viewAccount());
    });

    buttonClickMap.put(FieldName.VIEW_STRATEGY.getMsg(), () -> {
      JOptionPane.showMessageDialog(null, model.viewAllStrategies());
    });

    buttonListener.setButtonClickedActionMap(buttonClickMap);
    view.addActionListener(buttonListener);
  }
}
