package ebanking.services;

import ebanking.entities.Account;
import ebanking.entities.Customer;
import ebanking.repositories.AccountRepository;
import ebanking.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;


    void setUp() {
    }

    @Test
    void shouldSaveAccount() {

        //Save customerDTO
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setName("dupont");
        customer.setEmail("name@gmaill.com");

        //Save Account
        Account account = new Account();
        account.setId("1");
        account.setPlafond(50000);
        account.setBalance(1000);
        account.setOverDraft(0);
        account.setCreatedAt(new Date());
        account.setCustomer(customer);

        /**
        AccountDTO accountDTO = mapper.fromAccount(account);


        when(accountRepository.save(account)).thenReturn(account);

        AccountDTO accountDTO1 = accountService.saveAccount(accountDTO);
        Account account1 = mapper.fromAccountDTO(accountDTO1);


        assertThat(account1).isEqualTo(account);
         */

    }

    @Test
    void testSaveAccount() {
    }

    @Test
    void getAccount() {
    }
}