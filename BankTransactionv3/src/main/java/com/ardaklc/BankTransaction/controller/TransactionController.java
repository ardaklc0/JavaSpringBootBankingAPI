package com.ardaklc.BankTransaction.controller;
import com.ardaklc.BankTransaction.dto.TransactionDto;
import com.ardaklc.BankTransaction.model.Transaction;
import com.ardaklc.BankTransaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getTransaction(){
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable String id){
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(transactionService.createTransaction(transactionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> putTransaction(@PathVariable String id, @RequestBody TransactionDto transactionDto){
        transactionService.putTransaction(id, transactionDto);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable String id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
