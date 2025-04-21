package ebanking.controller;

import ebanking.dtos.*;
import ebanking.exceptions.AccountNotFoundException;
import ebanking.exceptions.BalanceExceedException;
import ebanking.exceptions.BalanceNotSufficientException;
import ebanking.services.impl.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Tag(name = "Account", description = "The Account API. Contains operations like get, add, list ...")
public class AccountController {
    private AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{accountId}")
    @Operation(description = "Get Account by ID")
    public AccountDTO getAccount(@Valid @PathVariable String accountId) throws AccountNotFoundException {
        return accountService.getAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<AccountDTO> listAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return accountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws AccountNotFoundException {
        return accountService.getAccountHistory(accountId, page, size);
    }

    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws AccountNotFoundException, BalanceNotSufficientException {
        this.accountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
        return debitDTO;
    }
    @GetMapping("/customers/accounts/{id}")
    public List<AccountDTO> getAccountsByCustomerId(@PathVariable(name = "id") Long id) throws AccountNotFoundException {
        return accountService.getAccountsByCustomerId(id);
    }


    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws AccountNotFoundException, BalanceExceedException {
        this.accountService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping("/customers/accounts/add")
    public void add(@RequestBody AccountDTO accountDTO) throws AccountNotFoundException, BalanceNotSufficientException {
        this.accountService.saveAccount(accountDTO);
    }
}
