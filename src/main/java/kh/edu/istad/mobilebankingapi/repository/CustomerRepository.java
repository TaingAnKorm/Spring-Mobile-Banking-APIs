package kh.edu.istad.mobilebankingapi.repository;

import kh.edu.istad.mobilebankingapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // JPQL
    @Modifying
    @Query(value = """
       UPDATE Customer c SET c.isDeleted = TRUE
              WHERE c.phoneNumber = :phoneNumber
       """)
    void disableByPhoneNumber(String phoneNumber);

    @Query(value = """
        SELECT EXISTS (SELECT c.id
                FROM Customer c
                        WHERE c.phoneNumber = ?1)
        """)
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    Customer findByEmail(String email);

    Customer findByPhoneNumber(String phoneNumber);
}
