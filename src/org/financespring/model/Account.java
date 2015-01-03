package org.financespring.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="account")
public class Account extends BaseEntity {

    @Column(name="account_number")
    private String accountNumber;

    @Column(name="amount")
    private float amount;

    @Column(name="currency")
    private String currency;

    @Column(name="reg_date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client clientId;

    @OneToMany(mappedBy = "accountId")
    private Set<BankTransaction> setOfTransactions;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Set<BankTransaction> getSetOfTransactions() {
        return setOfTransactions;
    }

    public void setSetOfTransactions(Set<BankTransaction> setOfTransactions) {
        this.setOfTransactions = setOfTransactions;
    }
}
