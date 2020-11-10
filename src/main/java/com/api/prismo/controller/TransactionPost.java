package com.api.prismo.controller;

public class TransactionPost {
    private long accountId;
    private long operationTypeId;
    private double amount;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(long operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
