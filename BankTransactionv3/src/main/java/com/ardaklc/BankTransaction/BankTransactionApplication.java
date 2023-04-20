package com.ardaklc.BankTransaction;

import com.ardaklc.BankTransaction.dto.TransactionDto;
import com.ardaklc.BankTransaction.model.Customer;
import com.ardaklc.BankTransaction.model.Transaction;
import com.ardaklc.BankTransaction.repository.CustomerRepository;
import com.ardaklc.BankTransaction.repository.TransactionRepository;
import jakarta.persistence.Convert;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class BankTransactionApplication implements CommandLineRunner{
	private final CustomerRepository customerRepository;
	private final TransactionRepository transactionRepository;


	public BankTransactionApplication(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
		this.customerRepository = customerRepository;
		this.transactionRepository = transactionRepository;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BankTransactionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
