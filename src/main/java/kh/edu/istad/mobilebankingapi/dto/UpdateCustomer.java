package kh.edu.istad.mobilebankingapi.dto;

public record UpdateCustomer(
        String fullName,
        String email,
        String phoneNumber,
        String remark,
        String nationalCardId,
        Integer customerSegmentId
) {
}