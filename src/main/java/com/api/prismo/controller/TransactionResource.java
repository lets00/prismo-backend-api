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

    private boolean canDebit(long id, double value, long operationType) {
        if (operationType != 4){
            Account user = ar.findById(id);
            if (user.getAvailableCreditLimit() - value >=0 ){
                return true;
            }
            return false;
        }
        return true;
    }

    @PostMapping("/")
    public ResponseEntity createNewTransaction(@RequestBody TransactionPost transaction){
        if (isValidAccountId(transaction.getAccountId()) && isValidOperationType(transaction.getOperationTypeId())) {
            if (canDebit(transaction.getAccountId(), transaction.getAmount(), transaction.getOperationTypeId())) {
                Transaction newTransaction = new Transaction();
                newTransaction.setAmount(transaction.getAmount());
                newTransaction.setAccount(ar.findById(transaction.getAccountId()));
                newTransaction.setOperationType(otr.findById(transaction.getOperationTypeId()));
                newTransaction.setLastUpdate(LocalDateTime.now());
                tr.save(newTransaction);

                Account user = ar.findById(transaction.getAccountId());
                double newCredit;
                if (transaction.getOperationTypeId() != 4) {
                    newCredit = user.getAvailableCreditLimit() - transaction.getAmount();
                } else {
                    newCredit = user.getAvailableCreditLimit() + transaction.getAmount();
                }
                user.setAvailableCreditLimit(newCredit);
                ar.save(user);

                return ResponseEntity.created(null).build();
            }
            else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
