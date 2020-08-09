package pl.staszek.bank.app.domain.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.staszek.bank.app.domain.client.model.Account;
import pl.staszek.bank.app.domain.client.model.Client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String pesel;
    private List<AccountDto> subAccounts = new ArrayList<>();
    private BigDecimal initialBalance;

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void populate(Client client) {
        this.id = client.getId();
        this.firstname = client.getFirstname();
        this.lastname = client.getLastname();
        this.pesel = client.getPesel();
        client.getSubAccounts().forEach(subAccount -> {
            AccountDto accountDto = new AccountDto();
            accountDto.populate(subAccount);
            subAccounts.add(accountDto);
        });
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public List<AccountDto> getSubAccounts() {
        return subAccounts;
    }

    public void setSubAccounts(List<AccountDto> subAccounts) {
        this.subAccounts = subAccounts;
    }
}
