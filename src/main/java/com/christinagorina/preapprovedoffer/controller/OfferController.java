package com.christinagorina.preapprovedoffer.controller;

import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.model.to.OfferTo;
import com.christinagorina.preapprovedoffer.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class OfferController {
    private final OfferService offerService;

    @PostMapping("/api/offer")
    public OfferTo create(@RequestBody OfferTo offerTo) {
        log.info("Input offerTo is {}", offerTo);
        return offerService.create(offerTo);
    }

    @GetMapping("/api/offers")
    public List<Offer> getAll() {
        log.info("Get all offers");
        return offerService.getAll();
    }

    @PostMapping("/success")
    public String successPage() {
        return "success";
    }
}
