package com.christinagorina.preapprovedoffer.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "first_name")
    @NonNull
    public String firstName;

    @Column(name = "middle_name")
    public String middleName;

    @Column(name = "second_name")
    @NonNull
    public String secondName;

    @Column(name = "passport")
    @NonNull
    public String passport;

    @Column(name = "phone")
    @NonNull
    public String phone;

    @OneToMany(mappedBy = "client")
    public List<Offer> offers;

    @OneToOne(mappedBy = "client")
    public ClientStatus clientStatus;

    @ManyToMany
    @JoinTable(name = "client_address",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses;

}
