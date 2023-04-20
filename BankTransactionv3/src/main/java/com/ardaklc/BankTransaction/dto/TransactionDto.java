package com.ardaklc.BankTransaction.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime creationDate = LocalDateTime.now();
    private CustomerDto customerDto;
    private Long customerId;

    public TransactionDto(Long id, BigDecimal amount, LocalDateTime creationDate, CustomerDto customerDto) {
        this.id = id;
        this.amount = amount;
        this.creationDate = creationDate;
        this.customerId = customerDto.getId();
    }

    public TransactionDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
