package ebanking.repositories;

import ebanking.entities.Account;
import ebanking.entities.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;


    @Autowired
    CustomerRepository customerRepository;

    @BeforeAll
    void setUp() {
        //Save customer
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setName("dupont");
        customer.setEmail("name@gmaill.com");
        customerRepository.save(customer);

        //Save Account
        Account account = new Account();
        account.setId("1");
        account.setPlafond(50000);
        account.setBalance(1000);
        account.setOverDraft(0);
        account.setCreatedAt(new Date());
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    @Test
    void shouldGetAllAccount() {
        //Act
        List<Account> accounts = accountRepository.findAll();

        //Assert
        assertEquals(1, accounts.size());
        assertEquals("dupont" , accounts.get(0).getCustomer().getName());

    }
}