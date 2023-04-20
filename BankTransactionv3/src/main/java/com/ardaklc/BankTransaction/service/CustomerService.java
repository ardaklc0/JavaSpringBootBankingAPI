package com.ardaklc.BankTransaction.service;
import com.ardaklc.BankTransaction.dto.CustomerDto;
import com.ardaklc.BankTransaction.dto.TransactionDto;
import com.ardaklc.BankTransaction.model.Customer;
import com.ardaklc.BankTransaction.model.Transaction;
import com.ardaklc.BankTransaction.repository.CustomerRepository;
import com.ardaklc.BankTransaction.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.transactionRepository = transactionRepository;
    }

    protected Customer findCustomerById(String id){
        return customerRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomer(String id){
        Customer customer = findCustomerById(id);
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        List<Transaction> transactions = transactionRepository.findByCustomerId(customer.getId().toString());
        Set<TransactionDto> transactionDtos = transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toSet());
        customerDto.setTransactions(transactionDtos);
        return customerDto;
    }

    public Customer createCustomer(CustomerDto customerDto){
        Customer customer = modelMapper.map(customerDto, Customer.class);
        return customerRepository.save(customer);
    }

    public CustomerDto putCustomer(String id, CustomerDto customerDto){
        Customer existingCustomer = findCustomerById(id);
        if (customerDto.getName() != null) {
            existingCustomer.setName(customerDto.getName());
        }
        if (customerDto.getSurname() != null) {
            existingCustomer.setSurname(customerDto.getSurname());
        }
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerDto.class);

    }

    public void deleteCustomer(String id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));
        customerRepository.delete(existingCustomer);
    }
}
