package com.example.smartcontactmanager.config;

import com.example.smartcontactmanager.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


// UserDetails is Spring Security interface
public class CustomUserDetails implements UserDetails {
    // Constructor to initialize the User object from the database
    public CustomUserDetails(User user) {
        this.user = user;
    }

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming role is stored in the database as a column named 'role' in the 'User' entity ADMIN या USER
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        // Assuming password is stored in the database as a column named 'password' in the 'User' entity
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


    // return true all other methods account हमेशा चालू है, लॉक नहीं है, और उसकी credentials (जैसे password) valid हैं।
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
