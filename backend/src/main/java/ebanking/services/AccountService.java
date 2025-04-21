package ebanking.services;

import ebanking.dtos.AccountDTO;
import ebanking.dtos.AccountHistoryDTO;
import ebanking.dtos.AccountOperationDTO;
import ebanking.dtos.CustomerDTO;
import ebanking.exceptions.BalanceExceedException;
import ebanking.exceptions.BalanceNotSufficientException;
import ebanking.exceptions.AccountNotFoundException;
import ebanking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface AccountService {

    AccountDTO saveAccount(AccountDTO accountDTO);

    AccountDTO saveAccount(double initialBalance, double plafond, double overDraft, Long customerId) throws CustomerNotFoundException;

    Object getAccount(String accountId) throws AccountNotFoundException;

    void debit(String accountId, double amount, String description) throws AccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws AccountNotFoundException, BalanceExceedException;

    List<AccountDTO> getAccounts();

    List<AccountDTO> getAccountsByCustomerId(Long customerId);

    List<AccountDTO> getAccounts(Long customerId) throws AccountNotFoundException;

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws AccountNotFoundException;

}
