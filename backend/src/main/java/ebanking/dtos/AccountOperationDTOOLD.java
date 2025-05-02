package ebanking.dtos;

import ebanking.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data
public class AccountOperationDTOOLD {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}

