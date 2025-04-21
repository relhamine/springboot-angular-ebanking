package ebanking.services;

import ebanking.dtos.AccountDTO;
import ebanking.dtos.AccountHistoryDTO;
import ebanking.dtos.AccountOperationDTO;
import ebanking.dtos.CustomerDTO;
import ebanking.exceptions.AccountNotFoundException;
import ebanking.exceptions.BalanceExceedException;
import ebanking.exceptions.BalanceNotSufficientException;
import ebanking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;


    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);


    List<CustomerDTO> searchCustomers(String keyword);
}
