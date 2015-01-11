package org.financespring.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "bank_transaction")
public class BankTransaction extends BaseEntity {

    @NotEmpty(message = "Please input beneficiary's bank name")
    @Size(max = 40)
    @Column(name = "ben_bank_name")
    private String benBankName;

    @NotEmpty(message = "Please input beneficiary's account number")
    @Size(max = 20)
    @Column(name = "ben_account_number")
    private String benAccountNum;

    //@NotEmpty(message = "Please input amount to remit")
    @Column(name = "ben_amount")
    private float benAmount;

    @NotEmpty(message = "Please choose date of transaction")
    @Column(name = "transaction_date")
    private String transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account accountId;

    public String getBenBankName() {
        return benBankName;
    }

    public void setBenBankName(String benBankName) {
        this.benBankName = benBankName;
    }

    public String getBenAccountNum() {
        return benAccountNum;
    }

    public void setBenAccountNum(String benAccountNum) {
        this.benAccountNum = benAccountNum;
    }

    public float getBenAmount() {
        return benAmount;
    }

    public void setBenAmount(float benAmount) {
        this.benAmount = benAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }
}
