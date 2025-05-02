package ebanking.dtos;

import java.util.Date;

public record AccountDTO(String type,
                         String id,
                         double balance,
                         double plafond,
                         double overDraft,
                         Date createdAt,
                         CustomerDTO customerDTO) {

    public AccountDTO() {
        this(null,null,0,0,0,null, null);
    }

}
