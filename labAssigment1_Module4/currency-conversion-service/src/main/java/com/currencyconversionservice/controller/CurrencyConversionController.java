package com.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currencyconversionservice.model.CurrencyConversion;
import com.currencyconversionservice.proxy.CurrencyExchangeProxy;
import com.currencyexchangeservice.model.ExchangeValue;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        ExchangeValue exchangeValue =
                proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(
                exchangeValue.getId(),
                from,
                to,
                quantity,
                exchangeValue.getConversionMultiple(),
                quantity.multiply(exchangeValue.getConversionMultiple()),
                exchangeValue.getEnvironment()
        );
    }
}

//@RestController
//public class CurrencyConversionController {
//
//    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
//    public CurrencyConversion calculateConversion(
//            @PathVariable String from,
//            @PathVariable String to,
//            @PathVariable BigDecimal quantity) {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<ExchangeValue> response =
//                restTemplate.getForEntity(
//                        "http://localhost:8080/currency-exchange/from/{from}/to/{to}",
//                        ExchangeValue.class,
//                        from,
//                        to
//                );
//
//        ExchangeValue exchangeValue = response.getBody();
//
//        return new CurrencyConversion(
//                exchangeValue.getId(),
//                from,
//                to,
//                quantity,
//                exchangeValue.getConversionMultiple(),
//                quantity.multiply(exchangeValue.getConversionMultiple()),
//                exchangeValue.getEnvironment()
//        );
//    }
//}
