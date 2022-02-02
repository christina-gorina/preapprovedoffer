package com.christinagorina.preapprovedoffer.repository;

import com.christinagorina.preapprovedoffer.model.PassportBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportBlackListRepository extends JpaRepository<PassportBlackList, Long> {
    PassportBlackList getByNumber(String number);
}
