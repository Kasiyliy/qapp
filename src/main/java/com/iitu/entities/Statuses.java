package com.iitu.entities;

import javax.persistence.*;

/**
 * @author Assylkhan
 * on 04.12.2018
 * @project qapp
 */
@Table
@Entity(name = "statuses")
@SequenceGenerator(
        name = "seq",
        sequenceName = "s_statuses",
        initialValue = 1,
        allocationSize=1
)
public class Statuses extends AuditModel {

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
