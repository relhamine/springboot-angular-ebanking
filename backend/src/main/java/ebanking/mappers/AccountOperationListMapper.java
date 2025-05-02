package ebanking.mappers;

import ebanking.dtos.AccountOperationDTO;
import ebanking.entities.AccountOperation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AccountOperationMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountOperationListMapper {
    List<AccountOperationDTO> toDTOList(List<AccountOperation> models);

    List<AccountOperation> toModelList(List<AccountOperationDTO> dtos);
}
