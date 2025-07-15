package kh.edu.istad.mobilebankingapi.dto;

public record CustomerResponse(
        String fullName,
        String email,
        String phoneNumber,
        String remark,
        String nationalCardId,
        String segmentName
) {
}
