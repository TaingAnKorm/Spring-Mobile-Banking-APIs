package kh.edu.istad.mobilebankingapi.mapper;

import kh.edu.istad.mobilebankingapi.domain.Customer;
import kh.edu.istad.mobilebankingapi.dto.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper{
    
    CustomerResponse mapFromCustomerToCustomerResponse(Customer customer);
}
