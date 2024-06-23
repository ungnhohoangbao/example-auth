package com.hb.example.auth_grant.domain.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "users")
@Entity
@Getter
@Setter
public class UserEntity implements Serializable {

    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    @Column(name = "username")
    private String username;

    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "statusid")
    private Long status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleid", referencedColumnName = "roleid", nullable = false)
    private RoleEntity role;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "modifieddate")
    private Timestamp modifieddate;
}
