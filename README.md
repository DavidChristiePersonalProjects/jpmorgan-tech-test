# jpmorgan-tech-test
Technical Test for JP Morgan

Created in NetBeans 8.2

Built using JDK 1.8

Tested using JUnit 4.12 and Hamcrest 1.3

# Description
Outputs a trades report to the console

# Requirements
* Amount in USD settled incoming everyday
* Amount in USD settled outgoing everyday
* Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest
amount for a buy instruction, then foo is rank 1 for outgoing

# Problem Space
* No database or UI is required
* You can assume the code will only ever be executed in a single threaded environment
* Minimise the number of external jar dependencies your code has. We would expect a maximum of
one or two would be required.
* All data to be in memory.
* Output format to be plain text, printed out to the console.
* Create more sample data as needed.
* We would expect you to spend somewhere in the region of about 3 hours on this exercise.

# Assumptions
* The ranking of the entities should be outputed after the amount settled for the day is outputted.
* A trade that does not have an entity is invlaid
* A trade that does not have an instruction date is invalid
* A trade that does not have a currency is invalid
* A trade that does not have Buy/Sell flag is invalid
* A trade that has 0, negative, or no units is invalid
* A trade which has 0, negative, or no price per unit is invalid
* A trade that has a negative agreed or no foriegn exchange rate is invalid
* Precision is necessary
 
# Design
Need 3 classes in order to seperate concerns. One class to represent a trade, one class to manage trades, and one class to log the report.

In addition create an interface to represent trade types (BUY or SELL) to simplify comparrisons in code and and interface for currencies to make it easier to extend the code.

# Methodology
Used test driven development to implement the functionality.

# Implementation
Created the TradeTypes and TradeCurrencies interface, created theTrade, TradeManager, and Logger classes, and generatated unit tests for Trade and TradeManager (not for the Logger class as it only outputs to the console).  
After this I implemented unit test functionality and then the class functionality to get the units test to pass.
I then iteratively expanded the unit test functionality and refactored the class functionality until I felt I was happy to call it production ready.

# Running
To run this application from the command line without Ant, try:

java -jar "TradesLogger\dist\TradesLogger.jar"

# Documentation
The documentation can be launched from TradesLogger\dist\javadoc\index.html