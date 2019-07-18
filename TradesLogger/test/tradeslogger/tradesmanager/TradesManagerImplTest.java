/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger.tradesmanager;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tradeslogger.trade.Trade;
import tradeslogger.trade.TradeImpl;
import tradeslogger.trade.TradeCurrencies;
import tradeslogger.trade.TradeTypes;

/**
 *
 * @author David
 */
public class TradesManagerImplTest {
    
    public TradesManagerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDailyTotal method, of class TradesManagerImpl.
     */
    @Test
    public void testGetDailyTotal_incomings() {
        System.out.println("getDailyTotal_incomings");
        LocalDate date = LocalDate.of(2016, Month.JANUARY, 02);
        TradeTypes tradeInstruction = TradeTypes.SELL;       
        List<Trade> trades = new ArrayList();
        Optional<TradeImpl> optionalTrade = TradeImpl.create(
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
        
        optionalTrade = TradeImpl.create(
                "foo", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.6), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
                
        // won't be included as it has no settlement date
        optionalTrade = TradeImpl.create(
                "foobar", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        // won't be included as it is an instruction to buy
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        TradesManagerImpl instance = new TradesManagerImpl(trades);
        // ((200 * 100.25) * 0.5) + ((200 * 100.25) * 0.6) + ((300 * 100.25) * 0.5) + (300 * 100.25)= 10025 + 12030 + 15037.5 + 30075 = 67167.5
        BigDecimal expResult = (trades.get(0).total().add(trades.get(1).total())).add(trades.get(2).total()).add(trades.get(5).total());        
        BigDecimal result = instance.getDailyTotal(date, tradeInstruction);
        
        assertEquals(expResult, result);
    }
    /**
     * Test of getDailyTotal method, of class TradesManagerImpl.
     */
    @Test
    public void testGetDailyTotal_outgoingss() {
        System.out.println("getDailyTotal_outgoings");
        LocalDate date = LocalDate.of(2016, Month.JANUARY, 02);
        TradeTypes tradeInstruction = TradeTypes.BUY;       
        List<Trade> trades = new ArrayList();
        Optional<TradeImpl> optionalTrade = TradeImpl.create(
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
        
        optionalTrade = TradeImpl.create(
                "foo", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0.6), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
                
        // won't be included as it has no settlement date
        optionalTrade = TradeImpl.create(
                "foobar", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        // won't be included as it is an instruction to sell
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        TradesManagerImpl instance = new TradesManagerImpl(trades);
        // ((200 * 100.25) * 0.5) + ((200 * 100.25) * 0.6) + ((300 * 100.25) * 0.5) + (300 * 100.25)= 10025 + 12030 + 15037.5 + 30075 = 67167.5
        BigDecimal expResult = (trades.get(0).total().add(trades.get(1).total())).add(trades.get(2).total()).add(trades.get(5).total());
        BigDecimal result = instance.getDailyTotal(date, tradeInstruction);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getRankedDailyTrades method, of class TradesManagerImpl.
     */
    @Test
    public void testGetRankedDailyTrades_incomings() {
        System.out.println("getRankedDailyTrades_incomings");
        LocalDate date = LocalDate.of(2016, Month.JANUARY, 02);
        TradeTypes tradeInstruction = TradeTypes.SELL;       
        List<Trade> trades = new ArrayList();
        Optional<TradeImpl> optionalTrade = TradeImpl.create(
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
        
        optionalTrade = TradeImpl.create(
                "foo", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.6), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
                
        // won't be included as it has no settlement date
        optionalTrade = TradeImpl.create(
                "foobar", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        // won't be included as it is an instruction to buy
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        TradesManagerImpl instance = new TradesManagerImpl(trades);
        List<Trade> expResult = new ArrayList<>(4);
        expResult.add(trades.get(5));
        expResult.add(trades.get(2));
        expResult.add(trades.get(1));
        expResult.add(trades.get(0));
        List<Trade> result = instance.getRankedDailyTrades(date, tradeInstruction);
        
        assertEquals(expResult.size(), result.size());
        for(int i = 0; i < expResult.size(); i++) {
            assertTrue(expResult.get(i).getEntity().equals(result.get(i).getEntity()));
            assertTrue(expResult.get(i).total().equals(result.get(i).total()));
        }
    }
    /**
     * Test of getRankedDailyTrades method, of class TradesManagerImpl.
     */
    @Test
    public void testGetRankedDailyTrades_outgoings() {
        System.out.println("getRankedDailyTrades_outgoings");
        LocalDate date = LocalDate.of(2016, Month.JANUARY, 02);
        TradeTypes tradeInstruction = TradeTypes.BUY;       
        List<Trade> trades = new ArrayList();
        Optional<TradeImpl> optionalTrade = TradeImpl.create(
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
        
        optionalTrade = TradeImpl.create(
                "foo", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0.6), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
                
        // won't be included as it has no settlement date
        optionalTrade = TradeImpl.create(
                "foobar", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                BigDecimal.valueOf(200), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        // won't be included as it is an instruction to sell
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.SELL, 
                BigDecimal.valueOf(0.5), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = TradeImpl.create(
                "bar", 
                TradeTypes.BUY, 
                BigDecimal.valueOf(0), 
                TradeCurrencies.SGP, 
                LocalDate.of(2016, Month.JANUARY, 01), 
                LocalDate.of(2016, Month.JANUARY, 02), 
                BigDecimal.valueOf(300), 
                BigDecimal.valueOf(100.25)
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        TradesManagerImpl instance = new TradesManagerImpl(trades);
        List<Trade> expResult = new ArrayList<>(4);
        expResult.add(trades.get(5));
        expResult.add(trades.get(2));
        expResult.add(trades.get(1));
        expResult.add(trades.get(0));
        List<Trade> result = instance.getRankedDailyTrades(date, tradeInstruction);
        
        assertEquals(expResult.size(), result.size());
        for(int i = 0; i < expResult.size(); i++) {
            assertTrue(expResult.get(i).getEntity().equals(result.get(i).getEntity()));
            assertTrue(expResult.get(i).total().equals(result.get(i).total()));
        }
    }
    
    /**
     * Test of setSettlementDate method, of class TradesManagerImpl.
     */
    @Test
    public void testSetSettlementDate_SundayToThursdayWeek() {
        System.out.println("setSettlementDate_SundayToThursdayWeek");
        Optional<TradeImpl> optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.AED, 
                LocalDate.of(2018, Month.JULY, 1), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        TradeImpl trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        TradesManagerImpl instance = new TradesManagerImpl(null);
        instance.setSettlementDate(trade);
        
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.MONDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.AED, 
                LocalDate.of(2018, Month.JULY, 2), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.TUESDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.AED, 
                LocalDate.of(2018, Month.JULY, 3), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.WEDNESDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.AED, 
                LocalDate.of(2018, Month.JULY, 4), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.THURSDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.AED, 
                LocalDate.of(2018, Month.JULY, 5), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.SUNDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.AED, 
                LocalDate.of(2018, Month.JULY, 6), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.SUNDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.AED, 
                LocalDate.of(2018, Month.JULY, 7), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.SUNDAY);
    }
    
    /**
     * Test of setSettlementDate method, of class TradesManagerImpl.
     */
    @Test
    public void testSetSettlementDate_MondayToFridayWeek() {
        System.out.println("setSettlementDate_MondayToFridayWeek");
        Optional<TradeImpl> optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.SGP, 
                LocalDate.of(2018, Month.JULY, 1), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        TradeImpl trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        TradesManagerImpl instance = new TradesManagerImpl(null);
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.MONDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.SGP, 
                LocalDate.of(2018, Month.JULY, 2), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.TUESDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.SGP, 
                LocalDate.of(2018, Month.JULY, 3), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.WEDNESDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.SGP, 
                LocalDate.of(2018, Month.JULY, 4), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.THURSDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.SGP, 
                LocalDate.of(2018, Month.JULY, 5), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.FRIDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.SGP, 
                LocalDate.of(2018, Month.JULY, 6), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.MONDAY);
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.SGP, 
                LocalDate.of(2018, Month.JULY, 7), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        trade = optionalTrade.isPresent() ? optionalTrade.get() : null;
        instance.setSettlementDate(trade);
        assertEquals(trade.getSettlementDate().getDayOfWeek(), DayOfWeek.MONDAY);
    }

    /**
     * Test of setSettlementDates method, of class TradesManagerImpl.
     */
    @Test
    public void testSetSettlementDates() {
        System.out.println("setSettlementDates");
        List<Trade> trades = new ArrayList<>();
        Optional<TradeImpl> optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.SGP, 
                LocalDate.of(2018, Month.JULY, 1), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        optionalTrade = TradeImpl.create(
                "test", 
                TradeTypes.BUY, 
                BigDecimal.ONE, 
                TradeCurrencies.SGP, 
                LocalDate.of(2018, Month.JULY, 2), 
                BigDecimal.ONE, 
                BigDecimal.ONE
        );
        if(optionalTrade.isPresent()) trades.add(optionalTrade.get());
        
        TradesManagerImpl instance = new TradesManagerImpl(trades);
        instance.setSettlementDates();
        
        assertEquals(trades.get(0).getSettlementDate().getDayOfWeek(), DayOfWeek.MONDAY);
        assertEquals(trades.get(1).getSettlementDate().getDayOfWeek(), DayOfWeek.TUESDAY);
    }

}