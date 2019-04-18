package com.busyqa.crm.service;

import com.busyqa.crm.auth.AdminUserPrincipal;
import com.busyqa.crm.auth.AuthGroup;
import com.busyqa.crm.auth.User;
import com.busyqa.crm.repository.AuthGroupRepository;
import com.busyqa.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDetailService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AuthGroupRepository authGroupRepository;

    public AdminDetailService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
        if(null==user) {
            throw new UsernameNotFoundException("cannot find username " + username);

        }
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
        return new AdminUserPrincipal(user, authGroups);
    }
}
