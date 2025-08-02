package kh.edu.istad.mobilebankingapi.dto;

//Patch no validation meaning user can update any data
public record UpdateCustomerRequest(
        String fullName,
        String gender,
        String remark
) {
}
