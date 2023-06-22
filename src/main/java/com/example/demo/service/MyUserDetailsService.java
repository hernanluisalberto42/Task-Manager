package com.example.demo.service;

import com.example.demo.config.CustomNotFoundException;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(student->new User(student.getUsername(),student.getPassword(),student.getAuthorities()))
                .orElseThrow(()->new CustomNotFoundException("Not Found!!.."));
    }
}
