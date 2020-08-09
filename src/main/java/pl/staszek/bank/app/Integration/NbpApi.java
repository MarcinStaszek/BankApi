package pl.staszek.bank.app.Integration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.staszek.bank.app.Integration.dto.RateDtoWrapper;
import pl.staszek.bank.app.common.Currency;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class NbpApi {

    private WebClient client = WebClient.create("http://api.nbp.pl/api/exchangerates");

    public Mono<RateDtoWrapper> getMeanRateForCurrency(Currency currency) {
        return client
                .get()
                .uri("/rates/a/" + currency.getCode() + "/?format=json")
                .retrieve()
                .bodyToMono(RateDtoWrapper.class)
                .timeout(Duration.ofSeconds(2));
    }
}
