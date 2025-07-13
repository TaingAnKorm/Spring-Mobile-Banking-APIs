package kh.edu.istad.mobilebankingapi.dto;

import java.math.BigDecimal;

public record AccountResponse(
        String actNo,
        String actCurrency,
        BigDecimal balance,
        Boolean isDeleted,
        Integer customerId,
        Integer accountTypeId
) {
}
