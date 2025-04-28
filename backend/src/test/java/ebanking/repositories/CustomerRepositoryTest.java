package ebanking.repositories;

import ebanking.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void shouldGetAllCustomer() {
        //Save customer
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setName("dupont");
        customer.setEmail("name@gmaill.com");
        customerRepository.save(customer);

        //Act
        List<Customer> customers = customerRepository.searchCustomer("dupont");

        //Assert
        assertEquals(1, customers.size());
    }
}