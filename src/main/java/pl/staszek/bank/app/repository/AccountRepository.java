package pl.staszek.bank.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.staszek.bank.app.domain.client.model.Account;
import pl.staszek.bank.app.domain.client.model.Client;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByClient(Client client);

}
