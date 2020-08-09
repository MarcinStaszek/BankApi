package pl.staszek.bank.app.domain.client.service;

import pl.staszek.bank.app.common.Currency;
import pl.staszek.bank.app.domain.client.model.Account;
import java.math.BigDecimal;


public interface AccountService {

    Account createSubAccount(BigDecimal balance, Currency currency);

    Account createSubAccountWithBalance(BigDecimal balance, Currency currency);

    void transferMoney(Account from, Account to, BigDecimal amount);
}
