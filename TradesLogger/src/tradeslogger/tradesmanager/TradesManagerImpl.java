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
import tradeslogger.trade.TradeCurrencies;
import tradeslogger.trade.TradeTypes;

/**
 *
 * @author David
 */
public class TradesManagerImpl implements TradesManager {
    
    final private List<Trade> trades;

    public TradesManagerImpl(List<Trade> trades) {
        this.trades = trades;
    }

    @Override
    public List<Trade> getTrades() {
        return trades;
    }
    
    /**
     * Gets the total amount of transactions for a given date in USD.
     * @param date LocalDate object that contains the date to get the total for
     * @param type TransactionTypes object that contains the type of transactions to get the total for (BUY || SELL)
     * @return BigDecimal object containing the total amount
     */
    @Override
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
     * @return List object containing a sorted list of TradeImpl objects
     */
    @Override
    public List<Trade> getRankedDailyTrades(LocalDate date, TradeTypes type) {
        return trades.stream()
                    .filter(trade -> trade.getSettlementDate() != null && trade.getSettlementDate().equals(date))
                    .filter(trade -> trade.getType() == type)
                    .sorted((o1, o2) -> o2.total().compareTo(o1.total()))
                    .collect(Collectors.toList());
    }
    
    /**
     * Set the settlement date for a trade
     * @param trade the trade to set the settlement date for
     */
    @Override
    public void setSettlementDate(Trade trade) {
        if(trade.getCurrency() == TradeCurrencies.AED || trade.getCurrency() == TradeCurrencies.SAR) {
            adjustForSundayToThursdayWeek(trade);
        }
        else {
            adjustForMondayToFridayWeek(trade);
        }
    }
    
    /**
     * Set the settlement date for all TradeImpl objects 
     */
    @Override
    public void setSettlementDates() {
        trades.forEach(this::setSettlementDate);
    }

    /**
     * Adjusts the settlement date based on a Monday to Friday week
     * @param trade the TradeImpl object to have its settlement date adjusted
     */
    private void adjustForMondayToFridayWeek(Trade trade) {
        switch (trade.getInstructionDate().getDayOfWeek()) {
            case FRIDAY:
                trade.setSettledDate(trade.getInstructionDate().plusDays(3));
                break;
            case SATURDAY:
                trade.setSettledDate(trade.getInstructionDate().plusDays(2));
                break;
            default:
                trade.setSettledDate(trade.getInstructionDate().plusDays(1));
                break;
        }
    }
    
    /**
     * Adjusts the settlement date based on a Sunday to Thursday week
     * @param trade the TradeImpl object to have its settlement date adjusted
     */
    private void adjustForSundayToThursdayWeek(Trade trade) {
        switch (trade.getInstructionDate().getDayOfWeek()) {
            case THURSDAY:
                trade.setSettledDate(trade.getInstructionDate().plusDays(3));
                break;
            case FRIDAY:
                trade.setSettledDate(trade.getInstructionDate().plusDays(2));
                break;
            default:
                trade.setSettledDate(trade.getInstructionDate().plusDays(1));
                break;
        }
    }

}
