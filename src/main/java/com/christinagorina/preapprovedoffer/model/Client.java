package com.christinagorina.preapprovedoffer.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
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
    @ToString.Exclude
    public List<Offer> offers;

    @OneToOne(mappedBy = "client", cascade = CascadeType.PERSIST)
    public ClientStatus clientStatus;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "client_address",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addresses;

}
