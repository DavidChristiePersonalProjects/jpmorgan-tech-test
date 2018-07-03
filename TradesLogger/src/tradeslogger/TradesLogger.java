/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import tradeslogger.logger.Logger;
import tradeslogger.trade.Trade;
import tradeslogger.trade.TradeCurrencies;
import tradeslogger.trade.TradeTypes;
import tradeslogger.tradesmanager.TradesManager;

/**
 *
 * @author David
 */
public class TradesLogger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Trade> trades = new ArrayList();
        Optional<Trade> optionalTrade = Trade.create(
                "foo", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "bar",
                TradeTypes.SELL,
                BigDecimal.valueOf(0.22),
                TradeCurrencies.AED,
                LocalDate.of(2016, Month.JANUARY, 01),
                LocalDate.of(2016, Month.JANUARY, 02),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "test",
                TradeTypes.SELL,
                BigDecimal.valueOf(0.22),
                TradeCurrencies.AED,
                LocalDate.of(2016, Month.JANUARY, 01),
                LocalDate.of(2016, Month.JANUARY, 02),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(110.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "foo", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "bar",
                TradeTypes.SELL,
                BigDecimal.valueOf(0.22),
                TradeCurrencies.AED,
                LocalDate.of(2016, Month.JANUARY, 05),
                LocalDate.of(2016, Month.JANUARY, 07),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "bar",
                TradeTypes.SELL,
                BigDecimal.valueOf(0.22),
                TradeCurrencies.AED,
                LocalDate.of(2016, Month.JANUARY, 05),
                LocalDate.of(2016, Month.JANUARY, 07),
                BigDecimal.valueOf(300),
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "foo", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 02), 
                LocalDate.of(2016, Month.JANUARY, 03), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "test",
                TradeTypes.SELL,
                BigDecimal.valueOf(0.21),
                TradeCurrencies.AED,
                LocalDate.of(2016, Month.JANUARY, 05),
                LocalDate.of(2016, Month.JANUARY, 07),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "test",
                TradeTypes.SELL,
                BigDecimal.valueOf(0.22),
                TradeCurrencies.AED,
                LocalDate.of(2016, Month.JANUARY, 02),
                LocalDate.of(2016, Month.JANUARY, 03),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "foo",
                TradeTypes.BUY,
                BigDecimal.valueOf(0.22),
                TradeCurrencies.AED,
                LocalDate.of(2016, Month.JANUARY, 02),
                LocalDate.of(2016, Month.JANUARY, 03),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(100.21)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "bar",
                TradeTypes.BUY,
                BigDecimal.valueOf(0.21),
                TradeCurrencies.AED,
                LocalDate.of(2018, Month.JULY, 8),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(100.21)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "foobar",
                TradeTypes.BUY,
                BigDecimal.valueOf(0.22),
                TradeCurrencies.AED,
                LocalDate.of(2018, Month.JULY, 7),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(100.21)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "test",
                TradeTypes.BUY,
                BigDecimal.valueOf(0.23),
                TradeCurrencies.SGP,
                LocalDate.of(2018, Month.JULY, 8),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(100.21)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "foo",
                TradeTypes.BUY,
                BigDecimal.valueOf(0.22),
                TradeCurrencies.SGP,
                LocalDate.of(2018, Month.JULY, 9),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(100.2)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = Trade.create(
                "bar",
                TradeTypes.BUY,
                BigDecimal.valueOf(0.22),
                TradeCurrencies.SGP,
                LocalDate.of(2018, Month.JULY, 7),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        
        TradesManager tradesManager = new TradesManager(trades);
        tradesManager.setSettlementDates();
        Logger logger = new Logger(tradesManager);
        logger.logSettledReport();
    }
    
}
