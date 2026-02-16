package com.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currencyexchangeservice.model.ExchangeValue;

@RestController
public class CurrencyExchangeController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to) {

        if (from.equals("USD") && to.equals("INR")) {
            return new ExchangeValue(
                    1001L,
                    from,
                    to,
                    new BigDecimal(82),
                    "Exchange Service running on port " + port
            );
        }

        return new ExchangeValue(
                1002L,
                from,
                to,
                new BigDecimal(1),
                "Default conversion"
        );
    }
}
