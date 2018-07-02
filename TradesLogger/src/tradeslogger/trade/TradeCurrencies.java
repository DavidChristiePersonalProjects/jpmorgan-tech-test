/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tradeslogger.trade;

/**
 *
 * @author David
 */
public enum TradeCurrencies {
    SGP("SGP"),
    AED("AED"),
    SAR("SAR"),
    USD("USD");
    
    final private String code;
    
    private TradeCurrencies(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
}
