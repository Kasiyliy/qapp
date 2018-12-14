package com.iitu.entities;

import javax.persistence.*;

/**
 * @author Assylkhan
 * on 04.12.2018
 * @project qapp
 */

@Entity
@Table(name="weeks")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_weeks",
        initialValue = 1,
        allocationSize=1
)
public class Weeks extends AuditModel{

    @Column(name = "value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
