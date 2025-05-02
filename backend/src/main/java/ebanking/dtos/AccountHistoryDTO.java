package ebanking.dtos;

import java.util.List;

public record AccountHistoryDTO(String accountId,
                                double balance,
                                int currentPage,
                                int totalPages,
                                int pageSize,
                                List<AccountOperationDTO> accountOperationDTOS) {
}
