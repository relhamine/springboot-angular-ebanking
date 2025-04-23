package ebanking.repositories;

import ebanking.entities.Account;
import ebanking.entities.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

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
    }

    @Test
    void shouldGetAllCustomer() {
        //Act
        List<Customer> customers = customerRepository.searchCustomer("dupont");

        //Assert
        assertEquals(1, customers.size());
    }
}