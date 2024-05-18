package com.javaguides.lifestyledelicioswebApp.repository;

import com.javaguides.lifestyledelicioswebApp.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UsersModel,Integer> {


    Optional<UsersModel> findByLoginAndPassword(String login, String password);

    Optional<UsersModel> findByLogin(String login);

    Optional<UsersModel> findById(Integer id);

}
