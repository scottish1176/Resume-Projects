package com.techelevator;

import java.math.BigDecimal;

public class Funds {

    private BigDecimal balance;

    @Override
    public String toString() {
        return "balance=" + balance;
    }

    public Funds() {
        this.balance = new BigDecimal("0.00");
    }

    public void addToBalance(BigDecimal newFunds) {
        this.balance = this.balance.add(newFunds);
    }

    public void subtractFromBalance(BigDecimal amountToSubtract) {
        this.balance = this.balance.subtract(amountToSubtract);
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
