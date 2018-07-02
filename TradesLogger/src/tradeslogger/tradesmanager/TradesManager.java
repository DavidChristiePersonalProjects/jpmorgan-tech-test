/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger.tradesmanager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import tradeslogger.trade.Trade;
import tradeslogger.trade.TradeTypes;

/**
 *
 * @author David
 */
public class TradesManager {
    
    final private List<Trade> trades;

    public TradesManager(List<Trade> trades) {
        this.trades = trades;
    }

    public List<Trade> getTrades() {
        return trades;
    }
    
    /**
     * Gets the total amount of transactions for a given date in USD.
     * @param date LocalDate object that contains the date to get the total for
     * @param type TransactionTypes object that contains the type of transactions to get the total for (BUY || SELL)
     * @return BigDecimal object containing the total amount
     */
    public BigDecimal getDailyTotal(LocalDate date, TradeTypes type) {
        return trades.stream()
                    .filter(trade -> trade.getSettlementDate()!= null && trade.getSettlementDate().equals(date))
                    .filter(trade -> trade.getType() == type)
                    .map(Trade::total)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    /**
     * Gets the trades for a given date ranked highest to lowest value
     * @param date LocalDate object that contains the date to get the trades for
     * @param type TransactionTypes object that contains the type of transactions to get (BUY || SELL)
     * @return List<Trade> object containing a sorted list of trades
     */
    public List<Trade> getRankedDailyTrades(LocalDate date, TradeTypes type) {
        return trades.stream()
                    .filter(trade -> trade.getSettlementDate() != null && trade.getSettlementDate().equals(date))
                    .filter(trade -> trade.getType() == type)
                    .sorted((o1, o2) -> o2.total().compareTo(o1.total()))
                    .collect(Collectors.toList());
    }
}
