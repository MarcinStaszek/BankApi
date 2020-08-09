package pl.staszek.bank.app.domain.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.staszek.bank.app.common.Currency;
import pl.staszek.bank.app.domain.client.dto.ClientDto;
import pl.staszek.bank.app.domain.client.model.Account;
import pl.staszek.bank.app.domain.client.model.Client;
import pl.staszek.bank.app.domain.client.service.exceptions.HasAccountInCurrencyException;
import pl.staszek.bank.app.repository.ClientRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountService accountService;

    @Override
    public Client createClient(ClientDto clientDto) {
        Client client;
        if (clientDto.getInitialBalance() != null) {
            client = createClientWithBalance(clientDto, clientDto.getInitialBalance(), Currency.PLN);
        } else {
            client = createClientWithBalance(clientDto, BigDecimal.ZERO, Currency.PLN);
        }
        return client;
    }

    @Override
    public Client createClientWithBalance(ClientDto clientDto, BigDecimal balance, Currency currency) {
        Client client = new Client();
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setPesel(clientDto.getPesel());
        addSubAccountWithBalance(client, balance, currency);
        return clientRepository.save(client);
    }

    @Override
    public Client editClient(ClientDto clientDto) {
        Optional<Client> client = clientRepository.findById(clientDto.getId());
        if (client.isPresent()) {
            client.get().setFirstname(clientDto.getFirstname());
            client.get().setLastname(clientDto.getLastname());
            client.get().setPesel(clientDto.getPesel());
        }
        return clientRepository.save(client.get());
    }

    @Override
    public void deleteClient(ClientDto clientDto) {
        Client client = clientRepository.findByPesel(clientDto.getPesel());
        clientRepository.delete(client);
    }

    @Override
    public void addSubAccountWithBalance(Client client, BigDecimal balance, Currency currency) {
        Account account = accountService.createSubAccount(balance, currency);
        client.getSubAccounts().add(account);
        account.setClient(client);
    }

    @Override
    public Client getClientByPesel(String pesel) {
        return clientRepository.findByPesel(pesel);
    }

    @Override
    public Client addNewSubAccount(Client client, Currency selectedCurrency) throws HasAccountInCurrencyException {
        boolean hasAccountInSelectedCurrency = client.getSubAccounts().stream().anyMatch(subAccount -> subAccount.getCurrency() == selectedCurrency);
        if (hasAccountInSelectedCurrency) {
            throw new HasAccountInCurrencyException();
        }
        addSubAccountWithBalance(client, BigDecimal.ZERO , selectedCurrency);
        return clientRepository.save(client);
    }

    @Override
    public Client transferMoney(Client client, Currency baseCurrency, Currency targetCurrency, BigDecimal amount) {
        Map<Currency, Account> currencyAccuntMap = new HashMap<>();
        client.getSubAccounts().forEach(subAcc -> {
            currencyAccuntMap.put(subAcc.getCurrency(), subAcc);
        });
        Account baseAccount = currencyAccuntMap.get(baseCurrency);
        Account targetAccount = currencyAccuntMap.get(targetCurrency);
        accountService.transferMoney(baseAccount, targetAccount, amount);
        return clientRepository.save(client);
    }
}