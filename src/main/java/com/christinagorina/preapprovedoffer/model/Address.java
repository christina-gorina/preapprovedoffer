package com.christinagorina.preapprovedoffer.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    @NonNull
    public String name;

    @ManyToMany(mappedBy = "addresses")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Client> clients = new HashSet<>();

}