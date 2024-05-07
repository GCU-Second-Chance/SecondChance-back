package com.example.secondchanceback;

import com.example.secondchanceback.entity.DonationEntity;
import com.example.secondchanceback.repository.DonationRepository;
import com.example.secondchanceback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.secondchanceback.repository")
@RequiredArgsConstructor
public class SecondChanceBackApplication implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(SecondChanceBackApplication.class);
    private final DonationRepository donationRepository;
    public static void main(String[] args) {
        SpringApplication.run(SecondChanceBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DonationEntity donationEntity = new DonationEntity(1L, 0L);
        donationRepository.save(donationEntity);
        LOGGER.info("초기 데이터 생성 완료 : donation=0");
    }
}
