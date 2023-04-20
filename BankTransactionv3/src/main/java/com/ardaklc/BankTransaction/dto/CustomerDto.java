package com.ardaklc.BankTransaction.dto;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Set;


public class CustomerDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDateTime creationDate = LocalDateTime.now();
    private Set<TransactionDto> transactions;

    public CustomerDto(Long id, String name, String surname, LocalDateTime creationDate, Set<TransactionDto> transactions) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.creationDate = creationDate;
        this.transactions = transactions;
    }

    public CustomerDto(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}
