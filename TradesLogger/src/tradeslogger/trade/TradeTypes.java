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
public enum TradeTypes {
    BUY("BUY"), SELL("SELL");
    
    final private String type;
    
    private TradeTypes(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
}
