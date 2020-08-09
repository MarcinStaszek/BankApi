package pl.staszek.bank.app.domain.client.dto;

import pl.staszek.bank.app.common.Currency;

import java.math.BigDecimal;

public class MoneyTransferDto {
    private BigDecimal amount;
    private Currency baseCurrency;
    private Currency targetCurrency;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
