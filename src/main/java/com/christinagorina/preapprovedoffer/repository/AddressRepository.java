package com.christinagorina.preapprovedoffer.repository;

import com.christinagorina.preapprovedoffer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
