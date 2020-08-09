package pl.staszek.bank.app.domain.client.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.pl.PESEL;
import pl.staszek.bank.app.validator.PeselUnderage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;

    @Column(length = 100)
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;

    @NotBlank(message = "PESEL is mandatory")
    @Column(unique = true)
    @PESEL
    @PeselUnderage
    private String pesel;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Account> subAccounts = new HashSet<>();


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

    public Set<Account> getSubAccounts() {
        return subAccounts;
    }

    public void setSubAccounts(Set<Account> subAccounts) {
        this.subAccounts = subAccounts;
    }
}
