package pl.staszek.bank.app.domain.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.staszek.bank.app.Integration.ExchangeService;
import pl.staszek.bank.app.domain.client.model.Account;
import pl.staszek.bank.app.repository.AccountRepository;
import pl.staszek.bank.app.common.Currency;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ExchangeService exchangeService;

    @Override
    public Account createSubAccount(BigDecimal balance, Currency currency) {
        return createSubAccountWithBalance(balance, currency);
    }

    @Override
    public Account createSubAccountWithBalance(BigDecimal balance, Currency currency) {
        Account acc = new Account();
        acc.setCurrency(currency);
        acc.setBalance(balance);
        return accountRepository.save(acc);
    }

    @Override
    public void transferMoney(Account baseAccount, Account targetAccount, BigDecimal amount) {
        BigDecimal amountInTargetCurrency = exchangeService.changeMoney(baseAccount.getCurrency(),targetAccount.getCurrency(),amount);
        BigDecimal baseAccountNewBalance = baseAccount.getBalance().subtract(amount);
        BigDecimal targetAccountNewBalance = targetAccount.getBalance().add(amountInTargetCurrency);
        baseAccount.setBalance(baseAccountNewBalance);
        targetAccount.setBalance(targetAccountNewBalance);
        accountRepository.save(baseAccount);
        accountRepository.save(targetAccount);
    }
}