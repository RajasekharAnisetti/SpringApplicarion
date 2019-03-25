package com.java.examples.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A City.
 */
@Entity
@Table(name = "city")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "city", length = 255)
    private String city;

    @ManyToOne
    @JsonIgnoreProperties("cities")
    private Province province;

    @OneToMany(mappedBy = "city")
    private Set<ShippingAddress> shippingAddresses = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public City city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Province getProvince() {
        return province;
    }

    public City province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Set<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public City shippingAddresses(Set<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
        return this;
    }

    public City addShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddresses.add(shippingAddress);
        shippingAddress.setCity(this);
        return this;
    }

    public City removeShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddresses.remove(shippingAddress);
        shippingAddress.setCity(null);
        return this;
    }

    public void setShippingAddresses(Set<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        if (city.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), city.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "City{" +
            "id=" + getId() +
            ", city='" + getCity() + "'" +
            "}";
    }
}
