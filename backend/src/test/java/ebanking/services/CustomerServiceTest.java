package ebanking.services;

import ebanking.entities.Customer;
import ebanking.repositories.CustomerRepository;
import ebanking.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;


    void setUp() {
    }

    @Test
    void shouldReturnListCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setName("dupont");
        customer1.setEmail("dupont@gmaill.com");

        Customer customer2 = new Customer();
        customer2.setId(1l);
        customer2.setName("dupont2");
        customer2.setEmail("dupont2@gmaill.com");

        when(customerRepository.findAll()).thenReturn(List.of(customer1, customer2));

        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(2).containsExactly(customer1, customer2);
    }

    @Test
    void saveCustomer() {
    }

    @Test
    void getCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void searchCustomers() {
    }
}