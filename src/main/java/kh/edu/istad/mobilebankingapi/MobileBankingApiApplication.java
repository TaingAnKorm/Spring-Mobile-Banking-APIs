package kh.edu.istad.mobilebankingapi;

import kh.edu.istad.mobilebankingapi.repository.AccountTypeRepository;
import kh.edu.istad.mobilebankingapi.repository.CustomerRepository;
import kh.edu.istad.mobilebankingapi.repository.SegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MobileBankingApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MobileBankingApiApplication.class, args);
    }

    private final CustomerRepository customerRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final SegmentRepository  segmentRepository;
    @Override
    public void run(String... args) throws Exception {

    }
}