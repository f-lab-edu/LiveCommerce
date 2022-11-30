package com.flab.seller.domain;

import com.flab.common.auth.AuthenticatedSeller;
import com.flab.common.auth.Role;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    protected Seller() {
    }

    public Seller(String name, String businessNo, String email, String password) {
        this.name = name;
        this.businessNo = businessNo;
        this.email = email;
        this.password = password;
        this.role = Role.SELLER;
    }

    public Long getId() {
        return id;
    }

    public Seller setId(Long id) {
        this.id = id;
        return this;
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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public AuthenticatedSeller toLoginInfo() {
        return new AuthenticatedSeller(id, email, role);
    }
}
