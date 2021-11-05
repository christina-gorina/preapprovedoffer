package com.christinagorina.preapprovedoffer.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "date_of_offer")
    @NonNull
    public LocalDateTime dateOfOffer;

    @Column(name = "date_of_check")
    @NonNull
    public LocalDateTime dateOfCheck;

    @Column(name = "type")
    @NonNull
    public String type;

    @Column(name = "amount")
    @NonNull
    public BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client client;

}
