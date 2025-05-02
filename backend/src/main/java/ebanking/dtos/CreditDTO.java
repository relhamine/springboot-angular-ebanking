package ebanking.dtos;

import lombok.Data;

public record CreditDTO(
        String accountId,
        double amount,
        String description) {
}
