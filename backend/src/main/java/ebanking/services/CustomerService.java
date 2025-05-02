package ebanking.services;

import ebanking.dtos.*;
import ebanking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerResponse);

    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;


    CustomerDTO updateCustomer(CustomerDTO customerResponse);

    void deleteCustomer(Long customerId);


    List<CustomerDTO> searchCustomers(String keyword);
}
