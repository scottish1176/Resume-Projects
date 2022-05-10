package com.techelevator.tenmo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class Transfer {

    private long transferId;
    private int transferTypeId;
    private int transferStatusId;
    private long accountFromId;
    private long accountToId;
    @DecimalMin(".01")
    private BigDecimal transferAmount;

    public long getTransferId() {
        return transferId;
    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public long getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(long accountFromId) {
        this.accountFromId = accountFromId;
    }

    public long getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(long accountToId) {
        this.accountToId = accountToId;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", transferTypeId=" + transferTypeId +
                ", transferStatusId=" + transferStatusId +
                ", accountFromId=" + accountFromId +
                ", accountToId=" + accountToId +
                ", transferAmount=" + transferAmount +
                '}';
    }
}
