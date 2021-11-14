package com.christinagorina.preapprovedoffer.service;

import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.model.to.OfferTo;

import java.util.List;

public interface OfferService {
    OfferTo create(OfferTo offerTo);

    List<Offer> getAll();
}
