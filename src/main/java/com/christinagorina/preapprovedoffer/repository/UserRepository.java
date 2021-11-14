package com.christinagorina.preapprovedoffer.repository;


import com.christinagorina.preapprovedoffer.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SystemUser, Long> {

    SystemUser findByName(String username);

}