package com.christinagorina.preapprovedoffer.repository;

import com.christinagorina.preapprovedoffer.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client getByPassport(String passport);
}
