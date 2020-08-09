package pl.staszek.bank.app.domain.client.service.exceptions;

public class HasAccountInCurrencyException extends Throwable {

    public HasAccountInCurrencyException() {
        super("Subaccount in selected currency already exist");
    }
}
