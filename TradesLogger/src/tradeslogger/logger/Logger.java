/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger.logger;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import tradeslogger.trade.Trade;
import tradeslogger.trade.TradeCurrencies;
import tradeslogger.trade.TradeTypes;
import tradeslogger.tradesmanager.TradesManager;

/**
 *
 * @author David
 */
public class Logger {
    
    private final TradesManager tradesManager;

    public Logger(TradesManager tradesManager) {
        this.tradesManager = tradesManager;
    }
    
    /**
     * Logs a report for all settled trades
     */
    public void logSettledReport() {
        HashSet<LocalDate> settledDates = new HashSet<>();
        for(int i = 0; i < tradesManager.getTrades().size(); i++) {
            settledDates.add(tradesManager.getTrades().get(i).getSettlementDate());
        }
        
        settledDates.forEach((LocalDate date) -> {
            // Amount in USD settled incoming everday
            System.out.println("Total incomings for " + date +  " - " + tradesManager.getDailyTotal(date, TradeTypes.SELL) + " " + TradeCurrencies.USD);
            List<Trade> dailyRankedTrades = tradesManager.getRankedDailyTrades(date, TradeTypes.SELL);
            dailyRankedTrades.forEach(trade -> {
                System.out.println("Enitity: " + trade.getEntity() + ", Total: " + trade.total() + " " + TradeCurrencies.USD);
            });
            // Amount in USD settled outgoing everday
            System.out.println("Total outgoings for " + date +  " - " + tradesManager.getDailyTotal(date, TradeTypes.BUY) + " " + TradeCurrencies.USD);
            dailyRankedTrades = tradesManager.getRankedDailyTrades(date, TradeTypes.BUY);
            dailyRankedTrades.forEach(trade -> {
                System.out.println("Enitity: " + trade.getEntity() + ", Total: " + trade.total() + " " + TradeCurrencies.USD);
            });
            
            System.out.println("\n");
        });
    }
    
}
