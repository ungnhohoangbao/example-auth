package com.hb.example.auth_grant.domain.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "role")
@Entity
@Getter
@Setter
public class RoleEntity implements Serializable {

    @Column(name = "roleid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long roleId;

    @Column(name = "role")
    private String role;

    @Column(name = "description")
    private String description;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "modifieddate")
    private Timestamp modifiedDate;
}
