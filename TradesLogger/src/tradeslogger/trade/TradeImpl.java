/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger.trade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 *
 * @author David
 */
public class TradeImpl implements Trade {
    final private String entity;
    final private TradeTypes type;
    final private BigDecimal agreedForiegnExchangeRate;
    final private TradeCurrencies currency;
    final private LocalDate instructionDate;
    private LocalDate settlementDate;
    final private BigDecimal units;
    final private BigDecimal pricePerUnit;

    @Override
    public String getEntity() {
        return entity;
    }

    @Override
    public TradeTypes getType() {
        return type;
    }

    @Override
    public BigDecimal getAgreedForiegnExchangeRate() {
        return agreedForiegnExchangeRate;
    }

    @Override
    public TradeCurrencies getCurrency() {
        return currency;
    }

    @Override
    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    @Override
    public LocalDate getSettlementDate() {
        return settlementDate;
    }
    
    @Override
    public void setSettledDate(LocalDate settledDate) {
        this.settlementDate = settledDate;
    }

    @Override
    public BigDecimal getUnits() {
        return units;
    }

    @Override
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    protected TradeImpl(String entity, TradeTypes type, BigDecimal agreedForiegnExchangeRate, TradeCurrencies currency, LocalDate instructionDate, LocalDate settlementDate, BigDecimal units, BigDecimal pricePerUnit) {
        this.entity = entity;
        this.type = type;
        this.agreedForiegnExchangeRate = agreedForiegnExchangeRate;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    protected TradeImpl(String entity, TradeTypes type, BigDecimal agreedForiegnExchangeRate, TradeCurrencies currency, LocalDate instructionDate, BigDecimal units, BigDecimal pricePerUnit) {
        this.entity = entity;
        this.type = type;
        this.agreedForiegnExchangeRate = agreedForiegnExchangeRate;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }
    
    /**
     * Validates the parameters and creates a TradeImpl object if the parameters are valid.
     * @param entity String object containing the name of the entity that performed the trade
     * @param type TradeTypes object containing the type of the trade (BUY || SELL)
     * @param agreedForiegnExchangeRate BigDecimal object containing the agreed foreign exchange rate
     * @param currency TradeCurrencies object containing the currency of the trade
     * @param instructionDate LocalDate object containing the date the trade was instructed
     * @param settlementDate LocalDate object containing the date the trade was settled
     * @param units BigDecimal object containing the number of units to be traded
     * @param pricePerUnit BigDecimal object containing the price per unit
     * @return Optional object that will contain a TradeImpl object if the parameters where valid or will be empty if not
     */
    static public Optional<TradeImpl> create (String entity, TradeTypes type, BigDecimal agreedForiegnExchangeRate, TradeCurrencies currency, LocalDate instructionDate, LocalDate settlementDate, BigDecimal units, BigDecimal pricePerUnit) {
        Optional<TradeImpl> optionalTrade = Optional.empty();

        // validate trade
        if(
                entity != null && !entity.isEmpty() &&
                type != null &&
                agreedForiegnExchangeRate != null && (agreedForiegnExchangeRate.compareTo(BigDecimal.valueOf(0)) == 0 || agreedForiegnExchangeRate.compareTo(BigDecimal.valueOf(0)) == 1) &&
                currency != null &&
                instructionDate != null &&
                settlementDate != null &&
                units != null && (units.compareTo(BigDecimal.valueOf(1)) == 0 || units.compareTo(BigDecimal.valueOf(1)) == 1) &&
                pricePerUnit != null && pricePerUnit.compareTo(BigDecimal.valueOf(0)) == 1) {
            optionalTrade = Optional.of(new TradeImpl(entity, type, agreedForiegnExchangeRate, currency, instructionDate, settlementDate, units, pricePerUnit));
        }
        
        return optionalTrade;
    }
    
    /**
     * Validates the parameters and creates a TradeImpl object if the parameters are valid.
     * @param entity String object containing the name of the entity that performed the trade
     * @param type TradeTypes object containing the type of the trade (BUY || SELL)
     * @param agreedForiegnExchangeRate BigDecimal object containing the agreed foreign exchange rate
     * @param currency TradeCurrencies object containing the currency of the trade
     * @param instructionDate LocalDate object containing the date the trade was instructed
     * @param units BigDecimal object containing the number of units to be traded
     * @param pricePerUnit BigDecimal object containing the price per unit
     * @return Optional object that will contain a TradeImpl object if the parameters where valid or will be empty if not
     */
    static public Optional<TradeImpl> create (String entity, TradeTypes type, BigDecimal agreedForiegnExchangeRate, TradeCurrencies currency, LocalDate instructionDate, BigDecimal units, BigDecimal pricePerUnit) {
        Optional<TradeImpl> optionalTrade = Optional.empty();

        // validate trade
        if(
                entity != null && !entity.isEmpty() &&
                type != null &&
                agreedForiegnExchangeRate != null && (agreedForiegnExchangeRate.compareTo(BigDecimal.valueOf(0)) == 0 || agreedForiegnExchangeRate.compareTo(BigDecimal.valueOf(0)) == 1) &&
                currency != null &&
                instructionDate != null &&
                units != null && (units.compareTo(BigDecimal.valueOf(1)) == 0 || units.compareTo(BigDecimal.valueOf(1)) == 1) &&
                pricePerUnit != null && pricePerUnit.compareTo(BigDecimal.valueOf(0)) == 1) {
            optionalTrade = Optional.of(new TradeImpl(entity, type, agreedForiegnExchangeRate, currency, instructionDate, units, pricePerUnit));
        }
        
        return optionalTrade;
    }
    
    /**
     * Calculates the total value of a trade in USD.  This is calculated by units * price per unit * agreed foreign exchange rate.
     * @return BigDecimal object containing the USD value of the trade
     */
    @Override
    public BigDecimal total() {
        BigDecimal unadjustedTotal = pricePerUnit.multiply(units);
        
        if(agreedForiegnExchangeRate == BigDecimal.valueOf(0)) {
            return unadjustedTotal;
        }
        
        return unadjustedTotal.multiply(agreedForiegnExchangeRate);
    }

    
}
