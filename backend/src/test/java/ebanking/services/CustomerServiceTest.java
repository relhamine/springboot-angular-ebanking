package ebanking.services;

import ebanking.entities.Customer;
import ebanking.repositories.CustomerRepository;
import ebanking.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerService;


    @BeforeEach
    void setUp() {
    }


    @Test
    void saveCustomer() {
    }

    @Test
    void listCustomers() {
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