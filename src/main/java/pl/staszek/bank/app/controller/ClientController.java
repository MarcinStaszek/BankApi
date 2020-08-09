package pl.staszek.bank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.staszek.bank.app.common.Currency;
import pl.staszek.bank.app.domain.client.dto.ClientDto;
import pl.staszek.bank.app.domain.client.dto.MoneyTransferDto;
import pl.staszek.bank.app.domain.client.model.Client;
import pl.staszek.bank.app.domain.client.service.ClientService;
import pl.staszek.bank.app.domain.client.service.exceptions.HasAccountInCurrencyException;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@RequestBody ClientDto clientDto){
        Client client = clientService.createClient(clientDto);
        clientDto.populate(client);
        return clientDto;
    }

    @PostMapping("/subaccounts/{currency}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto createSubAccount(@RequestBody ClientDto clientDto, @PathVariable String currency) throws HasAccountInCurrencyException {
        Currency selectedCurrency = Currency.valueOf(currency);
        Client client = clientService.getClientByPesel(clientDto.getPesel());
        clientService.addNewSubAccount(client, selectedCurrency);
        clientDto.populate(client);
        return clientDto;
    }


    @GetMapping("/{pesel}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto get(@PathVariable String pesel) {
        Client client = clientService.getClientByPesel(pesel);
        ClientDto clientDto = new ClientDto();
        clientDto.populate(client);
        return clientDto;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void edit(@RequestBody ClientDto clientDto) {
        clientService.editClient(clientDto);
    }

    @PutMapping("/{pesel}/account/transfer")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto transfer(@RequestBody MoneyTransferDto moneyTransferDto, @PathVariable String pesel){
        Client client = clientService.getClientByPesel(pesel);
        client = clientService.transferMoney(client, moneyTransferDto.getBaseCurrency(), moneyTransferDto.getTargetCurrency(), moneyTransferDto.getAmount());
        ClientDto clientDto = new ClientDto();
        clientDto.populate(client);
        return clientDto;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody ClientDto clientDto){
        clientService.deleteClient(clientDto);
    }
}
