package pl.staszek.bank.app.Integration;

import org.springframework.stereotype.Service;
import pl.staszek.bank.app.Integration.dto.RateDto;
import pl.staszek.bank.app.Integration.dto.RateDtoWrapper;
import pl.staszek.bank.app.common.Currency;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeServiceImpl implements ExchangeService{

    NbpApi api = new NbpApi();

    @Override
    public BigDecimal getRate(Currency currency) {
        RateDto rateDto = new RateDto();
        if (currency == Currency.PLN) {
            rateDto.setMid(1.0d);
        } else {
            Mono<RateDtoWrapper> mono = api.getMeanRateForCurrency(currency);
            RateDtoWrapper wrapper = mono.block();
            rateDto = wrapper.getRates().get(0);
        }
        return BigDecimal.valueOf(rateDto.getMid()).setScale(4, RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal changeMoney(Currency baseCurrency, Currency targetCurrency, BigDecimal amount) {
        BigDecimal result = amount.multiply(getRate(baseCurrency)).divide(getRate(targetCurrency), 2, RoundingMode.HALF_DOWN);
        return result;
    }
}
