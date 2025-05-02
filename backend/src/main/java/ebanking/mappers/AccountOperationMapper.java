package ebanking.mappers;

import ebanking.dtos.AccountOperationDTO;
import ebanking.entities.AccountOperation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountOperationMapper {

    AccountOperation toModel(AccountOperationDTO dto);

    AccountOperationDTO toDTO(AccountOperation model);

}
