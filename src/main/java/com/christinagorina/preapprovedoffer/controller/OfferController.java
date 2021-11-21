package com.christinagorina.preapprovedoffer.controller;

import com.christinagorina.preapprovedoffer.model.Offer;
import com.christinagorina.preapprovedoffer.model.to.OfferTo;
import com.christinagorina.preapprovedoffer.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class OfferController {
    private final OfferService offerService;

    @PostMapping("/api/offers")
    public OfferTo create(@RequestBody OfferTo offerTo) {
        return offerService.create(offerTo);
    }

    @GetMapping("/api/offers")
    public List<Offer> getAll() {
        return offerService.getAll();
    }

    @GetMapping("/api/offers/{id}")
    public Offer getById(@PathVariable("id") Long id) {
        return offerService.getById(id);
    }

    @DeleteMapping("/api/offers/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        offerService.deleteById(id);
        return "success";
    }

    @PostMapping("/success")
    public String successPage() {
        return "success";
    }

}
