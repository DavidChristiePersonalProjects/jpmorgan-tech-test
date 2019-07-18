/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger.logger;

import tradeslogger.trade.Trade;

/**
 *
 * @author David
 */
public interface Logger {

    /**
     * Logs a report for all settled trades
     */
    void logSettledReport();

    /**
     * Logs the details of a trade
     * @param trade the value of trade
     */
    void logTrade(Trade trade);
    
}
