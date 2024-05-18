package com.javaguides.lifestyledelicioswebApp.repository;

import com.javaguides.lifestyledelicioswebApp.model.UsersModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeactivateRepository extends CrudRepository<UsersModel, Long> {
    UsersModel findByLogin(String username);
    // Other methods can be omitted as they are inherited from CrudRepository
}