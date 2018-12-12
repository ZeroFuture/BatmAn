CS5010 Assignment 10 GStocks
Team 20904 Zhidong Qu, Yuxuan Xie
1. Version History:

Assignment 8:
Basic Functions:
Provide a basic Equity trading platform that allows the user to add Equity, 
evaluate portfolio, evaluate account, view portfolio and view account.
More specifically, the user is also able to choose whether the Equity desired to add 
is "REAL" (existed in the market) or "FAKE" (as just for demonstration or assumption).

Assignment 9:
Advanced Functions:
Provide Strategies such as weighted investment and dollar averaging cost frequent investment that allows the user to buy stocks in "REAL" market with a investment plan.
Added Commission fees with each transaction.

Assignment 10:
Model TOTALLY REFACTORED, and now support GUI view.

Due to the fact that all new features and functions required totally mismatch the original design,
the model is totally refactored.

DELETED add "FAKE" stock feature.
Provide add Equity function without buying.
Provide buy Equity function with or without commission fee.
Support add high level strategy in separate functions.
Support apply existing strategies to desired portfolio (or a new one);
Support save and retrieve portfolios or strategies from local memory.

Brand new GUI for the trading system.

Support apply high level strategies with future end date.

2. Design Features:
The program uses AlphaVantage to get real time equity information in order to allow user invest in real equities.
Implemented Cache to prevent excessive API calls.