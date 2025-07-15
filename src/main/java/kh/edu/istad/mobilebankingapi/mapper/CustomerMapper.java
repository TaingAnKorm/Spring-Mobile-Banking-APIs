package kh.edu.istad.mobilebankingapi.mapper;

import kh.edu.istad.mobilebankingapi.domain.Customer;
import kh.edu.istad.mobilebankingapi.dto.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper{
    @Mapping(source = "customer.customerSegment.name", target = "segmentName")
    CustomerResponse mapFromCustomerToCustomerResponse(Customer customer);
}
