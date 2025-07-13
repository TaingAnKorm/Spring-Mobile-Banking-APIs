package kh.edu.istad.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;
}
