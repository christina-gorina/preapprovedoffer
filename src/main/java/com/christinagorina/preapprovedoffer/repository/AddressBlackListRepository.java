package com.christinagorina.preapprovedoffer.repository;

import com.christinagorina.preapprovedoffer.model.AddressBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AddressBlackListRepository extends JpaRepository<AddressBlackList, Long> {
    List<AddressBlackList> getByNameIn(Set<String> names);
}
