package com.example.MyProject11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MyProject11.details.CustomUserDetails;
import com.example.MyProject11.model.User;
import com.example.MyProject11.reposetory.UserRepository;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = repo.findByUsername(username)

                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User Not Found"));

        return new CustomUserDetails(user);

    }

}