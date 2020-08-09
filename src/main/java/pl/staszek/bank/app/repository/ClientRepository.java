package pl.staszek.bank.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.staszek.bank.app.domain.client.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByPesel(String pesel);
}
