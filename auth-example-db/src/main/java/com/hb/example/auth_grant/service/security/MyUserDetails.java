package com.hb.example.auth_grant.service.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Getter
@Setter
public class MyUserDetails extends User implements Serializable {

    private String userName;
    private Long userId;
    private String fullName;
    private String role;

    private Long statusId;

    private Timestamp createdDate;

    public MyUserDetails(String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
