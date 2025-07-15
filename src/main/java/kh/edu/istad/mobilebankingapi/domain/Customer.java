package kh.edu.istad.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, length = 15)
    private String gender;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String remark;

    private String nationalCardId;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "customer_segment_id")
    private CustomerSegment customerSegment;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private KYC kyc;
}
