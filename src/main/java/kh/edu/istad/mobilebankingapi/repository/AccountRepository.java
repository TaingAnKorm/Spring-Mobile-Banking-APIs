package kh.edu.istad.mobilebankingapi.repository;

import kh.edu.istad.mobilebankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByActNo(String actNo);

    boolean existsByActNo(String actNo);

    List<Account> findAllByCustomer_Id(Integer customerId);

}
