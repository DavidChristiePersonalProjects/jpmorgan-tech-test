/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger.tradesmanager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import tradeslogger.trade.Trade;
import tradeslogger.trade.TradeTypes;

/**
 *
 * @author David
 */
public interface TradesManager {

    /**
     * Gets the total amount of transactions for a given date in USD.
     * @param date LocalDate object that contains the date to get the total for
     * @param type TransactionTypes object that contains the type of transactions to get the total for (BUY || SELL)
     * @return BigDecimal object containing the total amount
     */
    BigDecimal getDailyTotal(LocalDate date, TradeTypes type);

    /**
     * Gets the trades for a given date ranked highest to lowest value
     * @param date LocalDate object that contains the date to get the trades for
     * @param type TransactionTypes object that contains the type of transactions to get (BUY || SELL)
     * @return List object containing a sorted list of TradeImpl objects
     */
    List<Trade> getRankedDailyTrades(LocalDate date, TradeTypes type);

    List<Trade> getTrades();

    /**
     * Set the settlement date for a trade
     * @param trade the trade to set the settlement date for
     */
    void setSettlementDate(Trade trade);

    /**
     * Set the settlement date for all TradeImpl objects
     */
    void setSettlementDates();
    
}
