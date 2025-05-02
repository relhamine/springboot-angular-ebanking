package ebanking.services.impl;

import ebanking.dtos.CustomerDTO;
import ebanking.entities.Customer;
import ebanking.exceptions.CustomerNotFoundException;
import ebanking.mappers.CustomerListMapper;
import ebanking.mappers.CustomerMapper;
import ebanking.repositories.CustomerRepository;
import ebanking.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerListMapper customerListMapper;
    private CustomerMapper customerMapper;


    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerResponse) {
        log.info("Saving new Customer");
        Customer customer = customerMapper.toModel(customerResponse);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customerListMapper.toDTOList(customers);
        return customerDTOS;
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found"));
        return customerMapper.toDTO(customer);
    }


    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerResponse) {
        log.info("Saving new Customer");
        Customer customer = customerMapper.toModel(customerResponse);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.searchCustomer(keyword);
        return customerListMapper.toDTOList(customers);
    }
}
