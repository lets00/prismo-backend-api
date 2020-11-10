package com.api.prismo.controller;

import com.api.prismo.models.Account;
import com.api.prismo.models.OperationType;
import com.api.prismo.models.Transaction;
import com.api.prismo.repository.AccountRepository;
import com.api.prismo.repository.OperationTypeRepository;
import com.api.prismo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value="transactions")
public class TransactionResource {
    
    @Autowired
    TransactionRepository tr;

    @Autowired
    AccountRepository ar;

    @Autowired
    OperationTypeRepository otr;

    private boolean isValidAccountId(long id){
        Account foundAccount = ar.findById(id);
        return foundAccount != null;
    }

    private boolean isValidOperationType(long id) {
        OperationType foundOperationType = otr.findById(id);
        return foundOperationType != null;
    }

    @PostMapping("/")
    public ResponseEntity createNewTransaction(@RequestBody TransactionPost transaction){
        if (isValidAccountId(transaction.getAccountId()) && isValidOperationType(transaction.getOperationTypeId())) {
            Transaction newTransaction = new Transaction();
            newTransaction.setAmount(transaction.getAmount());
            newTransaction.setAccount(ar.findById(transaction.getAccountId()));
            newTransaction.setOperationType(otr.findById(transaction.getOperationTypeId()));
            newTransaction.setLastUpdate(LocalDateTime.now());
            tr.save(newTransaction);

            return ResponseEntity.created(null).build();
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
