package com.challengescrd.challenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Address() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address addresses() {
        return addresses();
    } // loop infinito


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) { // exemplo do updateAdress
        if (street != null) {
            this.street = street;
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void updateAddresses(Address newAddresses) {

        // Podia colocar essas validações nos próprios setters da classe e chamá-los nesse método:
//        setStreet(newAddresses.getStreet());

        if (newAddresses.getStreet() != null) {
            this.street = newAddresses.getStreet();
        }
        if (newAddresses.getNumber() != null) {
            this.number = newAddresses.getNumber();
        }
        if (newAddresses.getNeighborhood() != null) {
            this.neighborhood = newAddresses.getNeighborhood();
        }
        if (newAddresses.getCity() != null) {
            this.city = newAddresses.getCity();
        }
        if (newAddresses.getState() != null) {
            this.state = newAddresses.getState();
        }
    }
}