package com.api.prismo.controller;

import com.api.prismo.models.Account;
import com.api.prismo.repository.AccountRepository;
import com.api.prismo.repository.OperationTypeRepository;
import com.api.prismo.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionResourceTest {

    @Autowired
    private TransactionResource transactionResource;

    @MockBean
    TransactionRepository tr;

    @MockBean
    AccountRepository ar;

    @MockBean
    OperationTypeRepository otr;

    @Test
    public void testDebitFunction() {
        TransactionPost transactionPost = new TransactionPost();
        transactionPost.setAccountId(16);
        transactionPost.setAmount(10);
        transactionPost.setOperationTypeId(1);

        Account mockedAccount = new Account();
        mockedAccount.setAvailableCreditLimit(1);

        Mockito.when(ar.findById(1)).thenReturn(mockedAccount);
        ResponseEntity re = transactionResource.createNewTransaction(transactionPost);
        assertEquals(re.getStatusCode(), 422);
    }

}