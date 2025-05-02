package ebanking.mappers;

import ebanking.dtos.AccountDTO;
import ebanking.entities.Account;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface AccountMapper {

    @Mapping(source = "customerDTO", target = "customer")
    Account toModel(AccountDTO dto);

    @Mapping(source = "customer", target = "customerDTO")
    AccountDTO toDTO(Account model);

}
