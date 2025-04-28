package ebanking.services.impl;

import ebanking.dtos.AccountDTO;
import ebanking.dtos.AccountHistoryDTO;
import ebanking.dtos.AccountOperationDTO;
import ebanking.entities.Account;
import ebanking.entities.AccountOperation;
import ebanking.entities.Customer;
import ebanking.enums.OperationType;
import ebanking.exceptions.AccountNotFoundException;
import ebanking.exceptions.BalanceExceedException;
import ebanking.exceptions.BalanceNotSufficientException;
import ebanking.exceptions.CustomerNotFoundException;
import ebanking.mappers.MapperImpl;
import ebanking.repositories.AccountOperationRepository;
import ebanking.repositories.AccountRepository;
import ebanking.repositories.CustomerRepository;
import ebanking.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private static final Logger logger =  LogManager.getLogger( AccountServiceImpl.class );

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private AccountOperationRepository accountOperationRepository;
    private MapperImpl dtoMapper;

    @Override
    public AccountDTO saveAccount(AccountDTO accountDTO) {
        log.info("Saving new Account");
        accountDTO.setCreatedAt(new Date());

        accountDTO.setId(UUID.randomUUID().toString());
        Account account = dtoMapper.fromAccountDTO(accountDTO);
        account.setCustomer(dtoMapper.fromCustomerDTO(accountDTO.getCustomerDTO()));
        Account savedAccount = accountRepository.save(account);
        return dtoMapper.fromAccount(savedAccount);
    }

    @Override
    public AccountDTO saveAccount(double initialBalance, double plafond, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not found");
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setCreatedAt(new Date());
        account.setBalance(initialBalance);
        account.setPlafond(plafond);
        account.setOverDraft(overDraft);
        account.setCustomer(customer);
        Account saveAccount = accountRepository.save(account);
        return dtoMapper.fromAccount(saveAccount);
    }


    @Override
    public AccountDTO getAccount(String accountId) {
        try {
            Account account = accountRepository.findById(accountId)
                    .orElseThrow(() -> new AccountNotFoundException("Account not found"));
            return dtoMapper.fromAccount(account);
        } catch (AccountNotFoundException ex) {
            logger.debug("Account is not found");
            return new AccountDTO(); // or any other appropriate response
        }
    }


    @Override
    public void debit(String accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account.getBalance() + account.getOverDraft() < amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setAccount(account);
        accountOperationRepository.save(accountOperation);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws AccountNotFoundException, BalanceExceedException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account.getBalance() + amount > account.getPlafond())
            throw new BalanceExceedException("Balance exceed");
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setAccount(account);
        accountOperationRepository.save(accountOperation);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> getAccounts() {
        List<Account> bankAccounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = bankAccounts.stream().map(bankAccount -> {
            return dtoMapper.fromAccount(bankAccount);
        }).collect(Collectors.toList());
        return accountDTOS;
    }

    @Override
    public List<AccountDTO> getAccountsByCustomerId(Long customerId) {
        List<Account> bankAccounts = accountRepository.getAccountsByCustomerId(customerId);
        List<AccountDTO> bankAccountDTOS = bankAccounts.stream()
                .map(bankAccount -> dtoMapper.fromAccount(bankAccount))
                .collect(Collectors.toList());

        return bankAccountDTOS;
    }


    @Override
    public List<AccountDTO> getAccounts(Long customerId) throws AccountNotFoundException {
        List<Account> bankAccounts = accountRepository.findAll();
        List<AccountDTO> savingBankAccountDTOS = bankAccounts.stream()
                .map(bankAccount -> dtoMapper.fromAccount(bankAccount))
                .collect(Collectors.toList());

        return savingBankAccountDTOS;
    }

    @Override
    public List<AccountOperationDTO> accountHistory(String accountId) {
        List<AccountOperation> accountOperations = accountOperationRepository.findByAccountId(accountId);
        return accountOperations.stream().map(op -> dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws AccountNotFoundException {
        Account bankAccount = accountRepository.findById(accountId).orElse(null);
        if (bankAccount == null) throw new AccountNotFoundException("Account not Found");
        Page<AccountOperation> accountOperations = accountOperationRepository.findByAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<AccountOperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }

}
