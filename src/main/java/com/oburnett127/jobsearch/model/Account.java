package com.oburnett127.jobsearch.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Basic(optional = false)
    private int id;
    @Basic(optional = false)
    private String email;
    @Basic(optional = false)
    private String password;
    @Basic(optional = false)
    private boolean isAdmin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) {
            Hibernate.getClass(this);
            Hibernate.getClass(o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
