package com.christinagorina.preapprovedoffer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    public Client client;

}
