package com.christinagorina.preapprovedoffer.repository;

import com.christinagorina.preapprovedoffer.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}

