/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger.trade;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author David
 */
public interface Trade {

    BigDecimal getAgreedForiegnExchangeRate();

    TradeCurrencies getCurrency();

    String getEntity();

    LocalDate getInstructionDate();

    BigDecimal getPricePerUnit();

    LocalDate getSettlementDate();

    TradeTypes getType();

    BigDecimal getUnits();

    void setSettledDate(LocalDate settledDate);

    /**
     * Calculates the total value of a trade in USD.  This is calculated by units * price per unit * agreed foreign exchange rate.
     * @return BigDecimal object containing the USD value of the trade
     */
    BigDecimal total();
    
}
