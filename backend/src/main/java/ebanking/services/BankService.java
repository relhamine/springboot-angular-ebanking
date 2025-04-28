package ebanking.services;

import ebanking.entities.Account;
import ebanking.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BankService {
    private static final Logger LOGGER =  LogManager.getLogger( BankService.class );
    private AccountRepository AccountRepository;
    public void consulter(){
        Account bankAccount=
                AccountRepository.findById("0b36be78-8d5d-446b-9f20-37eadc9d3c3b").orElse(null);
        if(bankAccount!=null) {
            LOGGER.debug("*****************************");
            LOGGER.debug(bankAccount.getId());
            LOGGER.debug(bankAccount.getBalance());
            LOGGER.debug(bankAccount.getCreatedAt());
            LOGGER.debug(bankAccount.getCustomer().getName());
            LOGGER.debug(bankAccount.getClass().getSimpleName());
            LOGGER.debug("Over Draft=>" + bankAccount.getOverDraft());

            bankAccount.getAccountOperations().forEach(op -> {
                LOGGER.debug(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
            });
        }
    }
}
