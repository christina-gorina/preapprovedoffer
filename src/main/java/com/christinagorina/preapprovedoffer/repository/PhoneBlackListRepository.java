package com.christinagorina.preapprovedoffer.repository;

import com.christinagorina.preapprovedoffer.model.PhoneBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneBlackListRepository extends JpaRepository<PhoneBlackList, Long> {
    PhoneBlackList getByNumber(String number);
}
