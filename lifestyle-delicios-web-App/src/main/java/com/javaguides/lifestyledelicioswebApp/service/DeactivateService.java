package com.javaguides.lifestyledelicioswebApp.service;

import com.javaguides.lifestyledelicioswebApp.model.UsersModel;
import com.javaguides.lifestyledelicioswebApp.repository.DeactivateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DeactivateService {

    @Autowired
    private DeactivateRepository userRepository;

    public boolean deactivateAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName(); // Get the username of the authenticated user
        UsersModel user = userRepository.findByLogin(currentUserName);

        if (user != null) {
            user.setActive(false); // Assuming there is a field to mark user as inactive
            userRepository.save(user); // Update the user
            return true;
        }
        return false;
    }
}