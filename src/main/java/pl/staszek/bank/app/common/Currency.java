package pl.staszek.bank.app.common;

public enum Currency {

    USD("USD"),
    PLN("PLN");

    private String code;

    Currency(String currencyCode) {
        this.code = currencyCode;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getCode();
    }

}
