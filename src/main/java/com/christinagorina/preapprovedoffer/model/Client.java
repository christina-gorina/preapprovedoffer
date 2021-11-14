package com.christinagorina.preapprovedoffer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @JsonBackReference
    public List<Offer> offers;

    @OneToOne(mappedBy = "client", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    public ClientStatus clientStatus;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 2)
    @JoinTable(name = "client_address",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    @JsonManagedReference
    private Set<Address> addresses;

}
