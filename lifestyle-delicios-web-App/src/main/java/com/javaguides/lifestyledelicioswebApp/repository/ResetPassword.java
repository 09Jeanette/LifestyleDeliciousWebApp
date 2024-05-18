package com.javaguides.lifestyledelicioswebApp.repository;

import com.javaguides.lifestyledelicioswebApp.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPassword extends JpaRepository<UsersModel, Long> {
    UsersModel findByEmail(String email);
}
