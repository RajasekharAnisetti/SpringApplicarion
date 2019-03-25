package com.java.examples.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ShippingAddress.
 */
@Entity
@Table(name = "shipping_address")
public class ShippingAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "attention", length = 255)
    private String attention;

    @Size(max = 255)
    @Column(name = "company", length = 255)
    private String company;

    @Size(max = 255)
    @Column(name = "address_1", length = 255)
    private String address1;

    @Size(max = 255)
    @Column(name = "address_2", length = 255)
    private String address2;

    @Size(max = 255)
    @Column(name = "phone", length = 255)
    private String phone;

    @Size(max = 255)
    @Column(name = "email", length = 255)
    private String email;

    @Size(max = 255)
    @Column(name = "postal_code", length = 255)
    private String postalCode;

    @Column(name = "confirm_delivery")
    private Boolean confirmDelivery;

    @Size(max = 255)
    @Column(name = "instructions", length = 255)
    private String instructions;

    @Column(name = "notify_recipient")
    private Boolean notifyRecipient;

    @Column(name = "res")
    private Boolean res;

    @Column(name = "tailgate")
    private Boolean tailgate;

    @ManyToOne
    @JsonIgnoreProperties("shippingAddresses")
    private Country country;

    @ManyToOne
    @JsonIgnoreProperties("shippingAddresses")
    private Province province;

    @ManyToOne
    @JsonIgnoreProperties("shippingAddresses")
    private City city;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttention() {
        return attention;
    }

    public ShippingAddress attention(String attention) {
        this.attention = attention;
        return this;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getCompany() {
        return company;
    }

    public ShippingAddress company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress1() {
        return address1;
    }

    public ShippingAddress address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public ShippingAddress address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone() {
        return phone;
    }

    public ShippingAddress phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public ShippingAddress email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public ShippingAddress postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean isConfirmDelivery() {
        return confirmDelivery;
    }

    public ShippingAddress confirmDelivery(Boolean confirmDelivery) {
        this.confirmDelivery = confirmDelivery;
        return this;
    }

    public void setConfirmDelivery(Boolean confirmDelivery) {
        this.confirmDelivery = confirmDelivery;
    }

    public String getInstructions() {
        return instructions;
    }

    public ShippingAddress instructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Boolean isNotifyRecipient() {
        return notifyRecipient;
    }

    public ShippingAddress notifyRecipient(Boolean notifyRecipient) {
        this.notifyRecipient = notifyRecipient;
        return this;
    }

    public void setNotifyRecipient(Boolean notifyRecipient) {
        this.notifyRecipient = notifyRecipient;
    }

    public Boolean isRes() {
        return res;
    }

    public ShippingAddress res(Boolean res) {
        this.res = res;
        return this;
    }

    public void setRes(Boolean res) {
        this.res = res;
    }

    public Boolean isTailgate() {
        return tailgate;
    }

    public ShippingAddress tailgate(Boolean tailgate) {
        this.tailgate = tailgate;
        return this;
    }

    public void setTailgate(Boolean tailgate) {
        this.tailgate = tailgate;
    }

    public Country getCountry() {
        return country;
    }

    public ShippingAddress country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Province getProvince() {
        return province;
    }

    public ShippingAddress province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public ShippingAddress city(City city) {
        this.city = city;
        return this;
    }

    public void setCity(City city) {
        this.city = city;
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
        ShippingAddress shippingAddress = (ShippingAddress) o;
        if (shippingAddress.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shippingAddress.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
            "id=" + getId() +
            ", attention='" + getAttention() + "'" +
            ", company='" + getCompany() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", confirmDelivery='" + isConfirmDelivery() + "'" +
            ", instructions='" + getInstructions() + "'" +
            ", notifyRecipient='" + isNotifyRecipient() + "'" +
            ", res='" + isRes() + "'" +
            ", tailgate='" + isTailgate() + "'" +
            "}";
    }
}
