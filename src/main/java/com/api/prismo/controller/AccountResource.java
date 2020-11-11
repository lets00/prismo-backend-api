package com.api.prismo.controller;

import com.api.prismo.models.Account;
import com.api.prismo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="accounts")
public class AccountResource {

    @Autowired
    private AccountRepository ar;

    @PostMapping("/")
    public ResponseEntity<Account> createAccount(@RequestBody AccountPost accountPost) {
        Account foundAccount = ar.findByDocumentNumber(accountPost.getDocumentNumber());
        if (foundAccount != null) {
            return ResponseEntity.badRequest().build();
        }
        Account newAccount = new Account();
        newAccount.setAvailableCreditLimit(100.0);
        newAccount.setDocumentNumber(accountPost.getDocumentNumber());
        ar.save(newAccount);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") long id) {
        Account foundAccount = ar.findById(id);
        if (foundAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundAccount);
    }
}
