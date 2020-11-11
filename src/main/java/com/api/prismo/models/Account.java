package com.api.prismo.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long accountId;

    private String documentNumber;

    private double availableCreditLimit;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long id) {
        this.accountId = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public double getAvailableCreditLimit() {
        return availableCreditLimit;
    }

    public void setAvailableCreditLimit(double availableCreditLimit) {
        this.availableCreditLimit = availableCreditLimit;
    }
}
