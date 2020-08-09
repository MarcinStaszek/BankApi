package pl.staszek.bank.app.domain.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.staszek.bank.app.common.Currency;
import pl.staszek.bank.app.domain.client.model.Account;
import pl.staszek.bank.app.domain.client.model.Client;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    private int id;
    private BigDecimal balance;
    private Currency currency;

    public void populate(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.currency = account.getCurrency();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
