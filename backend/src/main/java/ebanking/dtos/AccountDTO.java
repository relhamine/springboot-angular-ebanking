package ebanking.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {
    private String type;
    private String id;
    private double balance;
    private double plafond;
    private double overDraft;
    private Date createdAt;
    private CustomerDTO customerDTO;
}
