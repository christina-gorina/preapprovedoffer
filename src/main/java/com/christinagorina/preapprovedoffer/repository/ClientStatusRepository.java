package com.christinagorina.preapprovedoffer.repository;

import com.christinagorina.preapprovedoffer.model.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientStatusRepository extends JpaRepository<ClientStatus, Long> {
}
