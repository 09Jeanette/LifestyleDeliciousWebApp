package com.javaguides.lifestyledelicioswebApp.service;

import com.javaguides.lifestyledelicioswebApp.model.UsersModel;
import com.javaguides.lifestyledelicioswebApp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {

    private final  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    public UsersService(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }
    public UsersModel registerUser(String login, String password, String email, String name, String surname, String phoneNumber) {
        if (login != null && password != null) {
            if (userRepository.findByLogin(login).isPresent()) {
                logger.info("User already exists");
                return null;
            } else {
                UsersModel newUser = new UsersModel();
                newUser.setLogin(login);
                // Encode password using BCrypt before storing
                newUser.setPassword(passwordEncoder.encode(password));
                // Set additional fields
                newUser.setEmail(email);
                newUser.setName(name);
                newUser.setSurname(surname);
                newUser.setPhoneNumber(phoneNumber);
                return userRepository.save(newUser);
            }
        } else {
            return null;
        }
    }

    public UsersModel aunthenticate(String login, String password) {
        Optional<UsersModel> userOptional = userRepository.findByLogin(login);
        if (userOptional.isPresent()) {
            UsersModel user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getLogin())
                .password(user.getPassword())
                .authorities("ROLE_USER") // You can set the roles or authorities accordingly
                .build();
    }


    }



