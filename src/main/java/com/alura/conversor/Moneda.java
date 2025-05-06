package com.alura.conversor;

import java.util.Map;

public class Moneda {
    
    private String base_code;
    private Map<String,Double> conversion_rates;

    public String getBase_code() {
        return base_code;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    @Override
    public String toString() {
        return "Moneda{" + "base_code=" + base_code + ", conversion_rates=" + conversion_rates + '}';
    }
    
}
