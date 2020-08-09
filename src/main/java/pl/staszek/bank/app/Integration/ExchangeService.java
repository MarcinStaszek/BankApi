package pl.staszek.bank.app.Integration;

import org.springframework.stereotype.Service;
import pl.staszek.bank.app.common.Currency;

import java.math.BigDecimal;


public interface ExchangeService {
    BigDecimal getRate(Currency currency);

    BigDecimal changeMoney(Currency baseCurrency, Currency targetCurrency, BigDecimal amount);
}
