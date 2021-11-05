package com.christinagorina.preapprovedoffer.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client_status")
public class ClientStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "level")
    public String level;

    @Column(name = "regular")
    public Boolean regular;

    @OneToOne
    @JoinColumn(name="client_id")
    public Client client;

}
