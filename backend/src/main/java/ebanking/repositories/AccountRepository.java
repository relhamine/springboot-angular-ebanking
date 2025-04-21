package ebanking.repositories;

import ebanking.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,String> {

    List<Account> getAccountsByCustomerId(Long customerId);
}
