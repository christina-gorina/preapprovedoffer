package com.christinagorina.preapprovedoffer.repository;

import com.christinagorina.preapprovedoffer.model.CheckResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckResultRepository extends JpaRepository<CheckResult, Long> {
    CheckResult getByOfferId(Long id);
}
