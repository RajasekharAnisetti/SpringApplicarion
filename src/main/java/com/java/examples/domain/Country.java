package com.java.examples.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "is_restricted")
    private Boolean isRestricted;

    @OneToMany(mappedBy = "country")
    private Set<ShippingAddress> shippingAddresses = new HashSet<>();
    @OneToMany(mappedBy = "country")
    private Set<Province> provinces = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Country name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public Country fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean isIsRestricted() {
        return isRestricted;
    }

    public Country isRestricted(Boolean isRestricted) {
        this.isRestricted = isRestricted;
        return this;
    }

    public void setIsRestricted(Boolean isRestricted) {
        this.isRestricted = isRestricted;
    }

    public Set<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public Country shippingAddresses(Set<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
        return this;
    }

    public Country addShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddresses.add(shippingAddress);
        shippingAddress.setCountry(this);
        return this;
    }

    public Country removeShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddresses.remove(shippingAddress);
        shippingAddress.setCountry(null);
        return this;
    }

    public void setShippingAddresses(Set<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public Set<Province> getProvinces() {
        return provinces;
    }

    public Country provinces(Set<Province> provinces) {
        this.provinces = provinces;
        return this;
    }

    public Country addProvince(Province province) {
        this.provinces.add(province);
        province.setCountry(this);
        return this;
    }

    public Country removeProvince(Province province) {
        this.provinces.remove(province);
        province.setCountry(null);
        return this;
    }

    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
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
        Country country = (Country) o;
        if (country.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), country.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", isRestricted='" + isIsRestricted() + "'" +
            "}";
    }
}
