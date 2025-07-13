package kh.edu.istad.mobilebankingapi.mapper;

import kh.edu.istad.mobilebankingapi.domain.Account;
import kh.edu.istad.mobilebankingapi.dto.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "accountType.id", target = "accountTypeId")
    AccountResponse mapFromAccountToAccountResponse(Account account);
}
