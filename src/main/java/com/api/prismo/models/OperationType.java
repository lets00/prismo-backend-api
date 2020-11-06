package com.api.prismo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="OPERATIONTYPE")
public class OperationType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="operationtype_id")
    private long id;

    private String description;

    @OneToMany(mappedBy="OPERATIONTYPE",cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
