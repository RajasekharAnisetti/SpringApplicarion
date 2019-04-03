package com.java.examples.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ShippingAddress entity.
 */
public class ShippingAddressDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String attention;

    @Size(max = 255)
    private String company;

    @Size(max = 255)
    private String address1;

    @Size(max = 255)
    private String address2;

    @Size(max = 255)
    private String phone;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String postalCode;

    private Boolean confirmDelivery;

    @Size(max = 255)
    private String instructions;

    private Boolean notifyRecipient;

    private Boolean res;

    private Boolean tailgate;


    private Long countryId;

    private Long provinceId;

    private Long cityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean isConfirmDelivery() {
        return confirmDelivery;
    }

    public void setConfirmDelivery(Boolean confirmDelivery) {
        this.confirmDelivery = confirmDelivery;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Boolean isNotifyRecipient() {
        return notifyRecipient;
    }

    public void setNotifyRecipient(Boolean notifyRecipient) {
        this.notifyRecipient = notifyRecipient;
    }

    public Boolean isRes() {
        return res;
    }

    public void setRes(Boolean res) {
        this.res = res;
    }

    public Boolean isTailgate() {
        return tailgate;
    }

    public void setTailgate(Boolean tailgate) {
        this.tailgate = tailgate;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShippingAddressDTO shippingAddressDTO = (ShippingAddressDTO) o;
        if (shippingAddressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shippingAddressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShippingAddressDTO{" +
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
            ", country=" + getCountryId() +
            ", province=" + getProvinceId() +
            ", city=" + getCityId() +
            "}";
    }
}
