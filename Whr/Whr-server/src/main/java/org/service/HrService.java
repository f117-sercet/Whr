package org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class HrService implements UserDetailsService {
    @Autowired
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
