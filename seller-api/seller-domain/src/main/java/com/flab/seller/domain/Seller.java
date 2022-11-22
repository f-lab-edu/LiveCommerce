package com.flab.seller.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String businessNo;
    private String email;

    protected Seller() {
    }

    public Seller(String name, String businessNo, String email) {
        this.name = name;
        this.businessNo = businessNo;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public String getEmail() {
        return email;
    }

    public Seller setId(Long id) {
        this.id = id;
        return this;
    }
}
