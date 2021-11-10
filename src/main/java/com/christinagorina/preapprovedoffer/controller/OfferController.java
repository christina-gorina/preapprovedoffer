package com.christinagorina.preapprovedoffer.controller;

import com.christinagorina.preapprovedoffer.model.to.OfferTo;
import com.christinagorina.preapprovedoffer.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
