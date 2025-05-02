package ebanking.dtos;

public record DebitDTO(String accountId,
                       double amount,
                       String description) {
}
