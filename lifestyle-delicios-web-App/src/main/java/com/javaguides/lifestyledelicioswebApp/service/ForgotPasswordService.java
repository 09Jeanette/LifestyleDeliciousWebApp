package com.javaguides.lifestyledelicioswebApp.service;
import com.javaguides.lifestyledelicioswebApp.model.UsersModel;
import com.javaguides.lifestyledelicioswebApp.repository.ResetPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {

    @Autowired
    private ResetPassword usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void resetPassword(String email, String newPassword) {
        UsersModel user = usersRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            usersRepository.save(user);
        }
    }
}
