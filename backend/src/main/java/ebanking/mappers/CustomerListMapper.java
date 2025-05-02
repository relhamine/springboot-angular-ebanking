package ebanking.mappers;

import ebanking.dtos.CustomerDTO;
import ebanking.entities.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CustomerMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerListMapper {
    List<CustomerDTO> toDTOList(List<Customer> models);

    List<Customer> toModelList(List<CustomerDTO> dtos);
}
