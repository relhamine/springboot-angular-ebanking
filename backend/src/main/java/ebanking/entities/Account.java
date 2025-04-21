package ebanking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data @NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id
    private String id;
    private double balance;
    private double plafond;
    private Date createdAt;
    private double overDraft;
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;
    }
