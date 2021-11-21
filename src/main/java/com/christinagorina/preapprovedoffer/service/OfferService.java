package com.christinagorina.preapprovedoffer.service;

import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.model.to.OfferTo;

import java.util.List;

public interface OfferService {
    Offer create(OfferTo offerTo);

    List<Offer> getAll();

    void deleteById(Long id);

    Offer getById(Long id);
}
