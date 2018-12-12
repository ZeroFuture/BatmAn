package trader;

import java.io.InputStreamReader;

import trader.controller.GuiController;
import trader.controller.TraderController;
import trader.model.IRetrievableStrategyTraderModel;
import trader.model.StrategyStockTraderModel;
import trader.view.GuiView;
import trader.view.ITraderView;
import trader.view.TraderJFrameGuiView;
import trader.view.TraderViewImplement;

/**
 * Console runner.
 */
public class BatmAnRunner {
  /**
   * Main.
   * @param args args.
   */
  public static void main(String[] args) {
    String userArg = "console";
    if (args.length >= 2) {
      userArg = args[1];
    }
    if (userArg.equals("console")) {
      IRetrievableStrategyTraderModel model = StrategyStockTraderModel.getBuilder().build();
      ITraderView view = new TraderViewImplement(new InputStreamReader(System.in), System.out);
      TraderController tc = new TraderController(view, model);
      tc.play();
    }
    else if (userArg.equals("gui")) {
      IRetrievableStrategyTraderModel model = StrategyStockTraderModel.getBuilder().build();
      GuiController controller = new GuiController(model);
      GuiView view = new TraderJFrameGuiView("Welcome", controller);
      controller.setView(view);
    }
  }
}
