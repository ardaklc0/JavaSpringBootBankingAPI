package com.ardaklc.BankTransaction.repository;

import com.ardaklc.BankTransaction.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String > {
}
