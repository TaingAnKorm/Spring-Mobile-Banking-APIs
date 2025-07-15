package kh.edu.istad.mobilebankingapi.repository;

import kh.edu.istad.mobilebankingapi.domain.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment, Integer> {
}