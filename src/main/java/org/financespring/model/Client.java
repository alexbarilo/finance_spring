package org.financespring.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client extends BaseEntity {

    @NotEmpty(message = "Please input first name")
    @Size(max = 30)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Please input last name")
    @Size(max = 30)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "Please input address")
    @Size(max = 40)
    @Column(name = "address", nullable = false)
    private String address;

    @NotEmpty(message = "Please input city")
    @Size(max = 20)
    @Column(name = "city", nullable = false)
    private String city;

    @NotEmpty(message = "Please input postal code")
    @Size(max = 10)
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
