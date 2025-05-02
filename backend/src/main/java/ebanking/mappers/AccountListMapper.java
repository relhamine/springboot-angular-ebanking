package ebanking.mappers;

import ebanking.dtos.AccountDTO;
import ebanking.entities.Account;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AccountMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountListMapper {
    List<AccountDTO> toDTOList(List<Account> models);

    List<Account> toModelList(List<AccountDTO> dtos);
}
