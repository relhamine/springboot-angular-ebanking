package ebanking.controller;

import ebanking.dtos.*;
import ebanking.exceptions.AccountNotFoundException;
import ebanking.exceptions.BalanceExceedException;
import ebanking.exceptions.BalanceNotSufficientException;
import ebanking.services.impl.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AccountDTO> getAccount(@Valid @PathVariable String accountId) {
        return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public List<AccountDTO> listAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public ResponseEntity<List<AccountOperationDTO>> getHistory(@PathVariable String accountId) {
        return new ResponseEntity<>(accountService.accountHistory(accountId), HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public ResponseEntity<AccountHistoryDTO> getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws AccountNotFoundException {
        return new ResponseEntity<>(accountService.getAccountHistory(accountId, page, size), HttpStatus.OK);
    }

    @PostMapping("/accounts/debit")
    public ResponseEntity<DebitDTO> debit(@RequestBody DebitDTO debitDTO) throws AccountNotFoundException, BalanceNotSufficientException {
        this.accountService.debit(debitDTO.accountId(), debitDTO.amount(), debitDTO.description());
        return new ResponseEntity<>(debitDTO, HttpStatus.OK);
    }

    @GetMapping("/customers/accounts/{id}")
    public ResponseEntity<List<AccountDTO>> getAccountsByCustomerId(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(accountService.getAccountsByCustomerId(id), HttpStatus.OK);
    }


    @PostMapping("/accounts/credit")
    public ResponseEntity<CreditDTO> credit(@RequestBody CreditDTO creditDTO) throws AccountNotFoundException, BalanceExceedException {
        this.accountService.credit(creditDTO.accountId(), creditDTO.amount(), creditDTO.description());
        return new ResponseEntity<>(creditDTO, HttpStatus.OK);
    }

    @PostMapping("/customers/accounts/add")
    public ResponseEntity<AccountDTO> add(@RequestBody AccountDTO accountResponse) {
        return new ResponseEntity<>(this.accountService.saveAccount(accountResponse), HttpStatus.CREATED);
    }
}
