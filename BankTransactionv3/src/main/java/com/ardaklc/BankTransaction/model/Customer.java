package com.ardaklc.BankTransaction.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDateTime creationDate = LocalDateTime.now();
    private String location;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Transaction> transactions;


    public Customer(String name, String surname, Set<Transaction> transactions) {
        this.name = name;
        this.surname = surname;
        this.transactions = transactions;
    }

    public Customer(){

    }

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

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
               Objects.equals(name, customer.name) &&
               Objects.equals(surname, customer.surname) &&
               Objects.equals(creationDate, customer.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, creationDate);
    }
}
