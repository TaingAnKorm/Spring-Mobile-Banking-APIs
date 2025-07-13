package kh.edu.istad.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kycs")
// Know Your Customer
public class KYC {
    @Id
    private Integer id;

    private String nationalCardId;

    private Boolean isVerified;

    private Boolean isDeleted;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
