package com.ardaklc.BankTransaction.service;
import com.ardaklc.BankTransaction.dto.TransactionDto;
import com.ardaklc.BankTransaction.model.Customer;
import com.ardaklc.BankTransaction.model.Transaction;
import com.ardaklc.BankTransaction.repository.CustomerRepository;
import com.ardaklc.BankTransaction.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public TransactionService(TransactionRepository transactionRepository, ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    protected Transaction findTransactionById(String id){
        return transactionRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public List<TransactionDto> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    public TransactionDto getTransaction(String id){
        Transaction transaction = findTransactionById(id);
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        return transactionDto;
    }


    public Transaction createTransaction(TransactionDto transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        return transactionRepository.save(transaction);
    }

    public TransactionDto putTransaction(String id, TransactionDto transactionDto){
        Transaction existingTransaction = findTransactionById(id);
        if (transactionDto.getAmount() != null){
            existingTransaction.setAmount(transactionDto.getAmount());
        }
        if (transactionDto.getCustomerId() != null){
           Customer customer = customerRepository.findById(transactionDto.getCustomerId().toString()).orElseThrow();
           existingTransaction.setCustomer(customer);
        }
        Transaction updatedTransaction = transactionRepository.save(existingTransaction);
        return modelMapper.map(updatedTransaction, TransactionDto.class);
    }

    public void deleteTransaction(String id){
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id " + id));
        transactionRepository.delete(existingTransaction);
    }
}
