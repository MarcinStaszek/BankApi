package pl.staszek.bank.app.domain.client.service;

import pl.staszek.bank.app.common.Currency;
import pl.staszek.bank.app.domain.client.dto.ClientDto;
import pl.staszek.bank.app.domain.client.model.Client;
import pl.staszek.bank.app.domain.client.service.exceptions.HasAccountInCurrencyException;

import java.math.BigDecimal;


public interface ClientService {

    Client createClient(ClientDto clientDto);

    Client createClientWithBalance(ClientDto clientDto, BigDecimal balance, Currency currency);

    Client editClient(ClientDto clientDto);

    void deleteClient(ClientDto clientDto);

    void addSubAccountWithBalance(Client client, BigDecimal balance, Currency currency);

    Client getClientByPesel(String pesel);

    Client addNewSubAccount(Client client, Currency selectedCurrency) throws HasAccountInCurrencyException;

    Client transferMoney(Client client, Currency baseCurrency, Currency targetCurrency, BigDecimal amount);
}
