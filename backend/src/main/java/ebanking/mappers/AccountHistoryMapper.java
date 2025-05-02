package ebanking.mappers;

import ebanking.dtos.AccountOperationDTO;
import ebanking.entities.AccountOperation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountHistoryMapper {

    AccountOperation toModel(AccountOperationDTO dto);

    AccountOperationDTO toDTO(AccountOperation model);

}
