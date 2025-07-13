package kh.edu.istad.mobilebankingapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAccountRequest(
        @NotBlank(message = "Account number is required")
        String actNo,
        @NotBlank(message = "Account currency is required")
        String actCurrency,
        @NotNull(message = "Balance is required")
        BigDecimal balance,
        @NotNull(message = "Customer id is required")
        Integer customerId,
        @NotNull(message = "Account type id is required")
        Integer accountTypeId
) {
}
