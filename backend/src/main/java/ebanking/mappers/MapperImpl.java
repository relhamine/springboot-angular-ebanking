package ebanking.mappers;

import ebanking.dtos.AccountDTO;
import ebanking.dtos.AccountOperationDTO;
import ebanking.dtos.CustomerDTO;
import ebanking.entities.Account;
import ebanking.entities.AccountOperation;
import ebanking.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MapperImpl {
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public Account fromAccountDTO(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        return account;
    }

    public AccountDTO fromAccount(Account account) {
        AccountDTO blaBankAccountDTO = new AccountDTO();
        BeanUtils.copyProperties(account, blaBankAccountDTO);
        blaBankAccountDTO.setCustomerDTO(fromCustomer(account.getCustomer()));
        blaBankAccountDTO.setType(account.getClass().getSimpleName());
        return blaBankAccountDTO;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }

}
