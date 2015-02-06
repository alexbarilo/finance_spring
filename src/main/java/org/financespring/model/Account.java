package org.financespring.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * JavaBean domain object which represents bank account. It has Many-to-One relationship with client domain object.
 */
@Entity
@Table(name="account")
public class Account extends BaseEntity {

    @NotEmpty(message = "Please input account number")
    @Size(max = 20)
    @Column(name="account_number")
    private String accountNumber;

    //@NotEmpty(message = "Please input the amount")
    @Column(name="amount")
    private float amount;

    @NotEmpty(message = "Please choose a currency")
    @Column(name="currency")
    private String currency;

    @NotEmpty(message = "Please choose a date")
    @Column(name="reg_date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client clientId;

    @OneToMany(mappedBy = "accountId", fetch = FetchType.EAGER, orphanRemoval = true)
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
