package ebanking.mappers;

import ebanking.dtos.CustomerDTO;
import ebanking.entities.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {
    Customer toModel(CustomerDTO dto);
    CustomerDTO toDTO(Customer model);

}
