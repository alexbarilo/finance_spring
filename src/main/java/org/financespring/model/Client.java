package org.financespring.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @OneToMany(mappedBy = "clientId", fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade({CascadeType.DELETE})
    private Set<Account> setOfAccounts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Set<Account> getSetOfAccounts() {
        return setOfAccounts;
    }

    public void setSetOfAccounts(Set<Account> setOfAccounts) {
        this.setOfAccounts = setOfAccounts;
    }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }
}
