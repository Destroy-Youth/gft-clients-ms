package dev.gft.example.clients.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7590920895537518760L;
    private String accountNumber;
    private BigDecimal balance;
    private String accountType;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
