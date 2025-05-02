package ebanking.dtos;

import ebanking.enums.OperationType;

import java.util.Date;

public record AccountOperationDTO(Long id,
                                  Date operationDate,
                                  double amount,
                                  OperationType type,
                                  String description) {
}
