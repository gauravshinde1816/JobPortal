package com.ideas.jobportal.services;

import com.ideas.jobportal.models.User;
import com.ideas.jobportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("load user");
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found with username " + username));
        return UserDetailsImplementation.build(user);
    }
}
