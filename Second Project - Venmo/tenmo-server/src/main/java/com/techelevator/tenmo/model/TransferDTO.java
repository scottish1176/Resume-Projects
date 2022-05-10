package com.techelevator.tenmo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class TransferDTO {
    @NotBlank
    Long transferId;
    @NotBlank
    Long accountFromId;
    @NotBlank
    Long accountToId;
    @NotBlank
    String transferType;
    @NotBlank
    String transferStatus;
    @NotBlank
    String userName;
    @NotBlank
    String userName2;
    @DecimalMin(".01")
    BigDecimal amount;
    Boolean isFromCurrentUser = false;

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public Boolean getFromCurrentUser() {
        return isFromCurrentUser;
    }

    public void setFromCurrentUser(Boolean fromCurrentUser) {
        isFromCurrentUser = fromCurrentUser;
    }

    public Long getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(Long accountFromId) {
        this.accountFromId = accountFromId;
    }

    public Long getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(Long accountToId) {
        this.accountToId = accountToId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "transferId=" + transferId +
                ", userName='" + userName + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String toString2() {
        return userName;
    }



}
