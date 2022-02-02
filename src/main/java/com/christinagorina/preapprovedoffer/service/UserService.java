package com.christinagorina.preapprovedoffer.service;

import com.christinagorina.preapprovedoffer.model.Role;
import com.christinagorina.preapprovedoffer.model.SystemUser;
import com.christinagorina.preapprovedoffer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = userRepository.findByName(username);

        if (systemUser == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }

        return User.builder()
                .username(systemUser.getName())
                .password(systemUser.getPassword())
                .roles(systemUser.getRoles().stream().map(Role::getAuthority).collect(Collectors.joining()))
                .build();

    }
}