package org.financespring.model;

import javax.persistence.*;

/**
 * JavaBean domain object with id-property. Used as a base class for entities needing this property.
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
