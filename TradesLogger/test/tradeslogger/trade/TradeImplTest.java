/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger.trade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class TradeImplTest {
    
    public TradeImplTest() {
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
     * Test of create method, of class TradeImpl.
     */
    @Test
    public void testCreate_with_settled_date() {
        System.out.println("create_with _settled_date");
        String entity = "name";
        TradeTypes type = TradeTypes.BUY;
        BigDecimal agreedForiegnExchangeRate = BigDecimal.valueOf(0.5);
        TradeCurrencies currency = TradeCurrencies.AED;
        LocalDate instructionDate = LocalDate.of(2016, Month.JANUARY, 01);
        LocalDate settlementDate = LocalDate.of(2016, Month.JANUARY, 02);
        BigDecimal units = BigDecimal.valueOf(200);
        BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
        Optional<TradeImpl> result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, units, pricePerUnit);
        assertTrue(result.isPresent());
        // not entity
        result = TradeImpl.create(null, type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // entity is empty
        result = TradeImpl.create("", type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no type
        result = TradeImpl.create(entity, null, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no agreed foreign exchange rate
        result = TradeImpl.create(entity, type, null, currency, instructionDate, settlementDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // agreed foreign exchange rate is negative
        result = TradeImpl.create(entity, type, BigDecimal.valueOf(-1), currency, instructionDate, settlementDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no currency
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, null, instructionDate, settlementDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no instruction date
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, null, settlementDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no settlement date
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, null, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no units
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, null, pricePerUnit);
        assertFalse(result.isPresent());
        // 0 units
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, BigDecimal.valueOf(0), pricePerUnit);
        assertFalse(result.isPresent());
        // negative units
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, BigDecimal.valueOf(-1), pricePerUnit);
        assertFalse(result.isPresent());
        // no price per unit
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, units, null);
        assertFalse(result.isPresent());
        // 0 price per unit
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, units, BigDecimal.valueOf(0));
        assertFalse(result.isPresent());
        // negative price per unit
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, units, BigDecimal.valueOf(-1));
        assertFalse(result.isPresent());
    }

    /**
     * Test of create method, of class TradeImpl.
     */
    @Test
    public void testCreate_without_settled_date() {
        System.out.println("create_without _settled_date");
        String entity = "name";
        TradeTypes type = TradeTypes.BUY;
        BigDecimal agreedForiegnExchangeRate = BigDecimal.valueOf(0.5);
        TradeCurrencies currency = TradeCurrencies.AED;
        LocalDate instructionDate = LocalDate.of(2016, Month.JANUARY, 01);
        BigDecimal units = BigDecimal.valueOf(200);
        BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
        Optional<TradeImpl> result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, units, pricePerUnit);
        assertTrue(result.isPresent());
        // no entity
        result = TradeImpl.create(null, type, agreedForiegnExchangeRate, currency, instructionDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // empty entity
        result = TradeImpl.create("", type, agreedForiegnExchangeRate, currency, instructionDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no type
        result = TradeImpl.create(entity, null, agreedForiegnExchangeRate, currency, instructionDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no agreed foreign exchange rate
        result = TradeImpl.create(entity, type, null, currency, instructionDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // negative agreed foriegn exchange rate
        result = TradeImpl.create(entity, type, BigDecimal.valueOf(-1), currency, instructionDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no currency
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, null, instructionDate, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no instruction date
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, null, units, pricePerUnit);
        assertFalse(result.isPresent());
        // no units
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, null, pricePerUnit);
        assertFalse(result.isPresent());
        // 0 units
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, BigDecimal.valueOf(0), pricePerUnit);
        assertFalse(result.isPresent());
        // negative units
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, BigDecimal.valueOf(-1), pricePerUnit);
        assertFalse(result.isPresent());
        // no price per unit
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, units, null);
        assertFalse(result.isPresent());
        // 0 price per unit
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, units, BigDecimal.valueOf(0));
        assertFalse(result.isPresent());
        // negative price per unit
        result = TradeImpl.create(entity, type, agreedForiegnExchangeRate, currency, instructionDate, units, BigDecimal.valueOf(-1));
        assertFalse(result.isPresent());
    }

    /**
     * Test of total method, of class TradeImpl.
     */
    @Test
    public void testTotal() {
        System.out.println("total");
        TradeImpl instance = new TradeImpl(null, TradeTypes.BUY, BigDecimal.valueOf(0.5), TradeCurrencies.AED, LocalDate.MIN, BigDecimal.valueOf(200), BigDecimal.valueOf(100.25));
        // (200 * 100.25) * 0.5 = 10025
        BigDecimal expResult = BigDecimal.valueOf(10025);
        BigDecimal result = instance.total();
        assertEquals(expResult.compareTo(result), 0);
        
        instance= new TradeImpl(null, TradeTypes.BUY, BigDecimal.valueOf(0), TradeCurrencies.AED, LocalDate.MIN, BigDecimal.valueOf(200), BigDecimal.valueOf(100.25));
        // 200 * 100.25 = 20050
        expResult = BigDecimal.valueOf(20050);
        result = instance.total();
        assertEquals(expResult.compareTo(result), 0);
        
        instance= new TradeImpl(null, TradeTypes.BUY, BigDecimal.valueOf(2), TradeCurrencies.AED, LocalDate.MIN, BigDecimal.valueOf(200), BigDecimal.valueOf(100.25));
        // (200 * 100.25) * 2 = 40100
        expResult = BigDecimal.valueOf(40100);
        result = instance.total();
        assertEquals(expResult.compareTo(result), 0);
    }
    
}
