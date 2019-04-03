package com.java.examples.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the WoWorkOrder entity.
 */
public class WoWorkOrderDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String customsBrokerName;

    @Size(max = 255)
    private String customsContactName;

    @Size(max = 255)
    private String customsCurrency;

    @Size(max = 255)
    private String customsPhoneNumber;

    private Double customsValue;

    @Size(max = 255)
    private String fromAirportCode;

    @Max(value = 11)
    private Integer fromLocationType;

    @Size(max = 255)
    private String jobContentDescr;

    @Max(value = 11)
    private Integer shipQuantity;

    @Max(value = 11)
    private Integer jobOriginalCost;

    @Size(max = 255)
    private String quotedAmount;

    @Size(max = 255)
    private String assignTo;

    @Size(max = 255)
    private String toCompany;

    @Max(value = 11)
    private Integer toLocationType;

    private ZonedDateTime dateCreated;

    @Size(max = 255)
    private String jobNumber;

    @Size(max = 255)
    private String jobCustomer;

    private Long serviceType;

    @Size(max = 255)
    private String jobSales;

    @Max(value = 11)
    private Integer woRequestType;


    private Long shipToId;

    private Long shipFromId;

    private Long woPackageTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomsBrokerName() {
        return customsBrokerName;
    }

    public void setCustomsBrokerName(String customsBrokerName) {
        this.customsBrokerName = customsBrokerName;
    }

    public String getCustomsContactName() {
        return customsContactName;
    }

    public void setCustomsContactName(String customsContactName) {
        this.customsContactName = customsContactName;
    }

    public String getCustomsCurrency() {
        return customsCurrency;
    }

    public void setCustomsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
    }

    public String getCustomsPhoneNumber() {
        return customsPhoneNumber;
    }

    public void setCustomsPhoneNumber(String customsPhoneNumber) {
        this.customsPhoneNumber = customsPhoneNumber;
    }

    public Double getCustomsValue() {
        return customsValue;
    }

    public void setCustomsValue(Double customsValue) {
        this.customsValue = customsValue;
    }

    public String getFromAirportCode() {
        return fromAirportCode;
    }

    public void setFromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
    }

    public Integer getFromLocationType() {
        return fromLocationType;
    }

    public void setFromLocationType(Integer fromLocationType) {
        this.fromLocationType = fromLocationType;
    }

    public String getJobContentDescr() {
        return jobContentDescr;
    }

    public void setJobContentDescr(String jobContentDescr) {
        this.jobContentDescr = jobContentDescr;
    }

    public Integer getShipQuantity() {
        return shipQuantity;
    }

    public void setShipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    public Integer getJobOriginalCost() {
        return jobOriginalCost;
    }

    public void setJobOriginalCost(Integer jobOriginalCost) {
        this.jobOriginalCost = jobOriginalCost;
    }

    public String getQuotedAmount() {
        return quotedAmount;
    }

    public void setQuotedAmount(String quotedAmount) {
        this.quotedAmount = quotedAmount;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getToCompany() {
        return toCompany;
    }

    public void setToCompany(String toCompany) {
        this.toCompany = toCompany;
    }

    public Integer getToLocationType() {
        return toLocationType;
    }

    public void setToLocationType(Integer toLocationType) {
        this.toLocationType = toLocationType;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobCustomer() {
        return jobCustomer;
    }

    public void setJobCustomer(String jobCustomer) {
        this.jobCustomer = jobCustomer;
    }

    public Long getServiceType() {
        return serviceType;
    }

    public void setServiceType(Long serviceType) {
        this.serviceType = serviceType;
    }

    public String getJobSales() {
        return jobSales;
    }

    public void setJobSales(String jobSales) {
        this.jobSales = jobSales;
    }

    public Integer getWoRequestType() {
        return woRequestType;
    }

    public void setWoRequestType(Integer woRequestType) {
        this.woRequestType = woRequestType;
    }

    public Long getShipToId() {
        return shipToId;
    }

    public void setShipToId(Long shippingAddressId) {
        this.shipToId = shippingAddressId;
    }

    public Long getShipFromId() {
        return shipFromId;
    }

    public void setShipFromId(Long shippingAddressId) {
        this.shipFromId = shippingAddressId;
    }

    public Long getWoPackageTypeId() {
        return woPackageTypeId;
    }

    public void setWoPackageTypeId(Long woPackageTypeId) {
        this.woPackageTypeId = woPackageTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoWorkOrderDTO woWorkOrderDTO = (WoWorkOrderDTO) o;
        if (woWorkOrderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woWorkOrderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoWorkOrderDTO{" +
            "id=" + getId() +
            ", customsBrokerName='" + getCustomsBrokerName() + "'" +
            ", customsContactName='" + getCustomsContactName() + "'" +
            ", customsCurrency='" + getCustomsCurrency() + "'" +
            ", customsPhoneNumber='" + getCustomsPhoneNumber() + "'" +
            ", customsValue=" + getCustomsValue() +
            ", fromAirportCode='" + getFromAirportCode() + "'" +
            ", fromLocationType=" + getFromLocationType() +
            ", jobContentDescr='" + getJobContentDescr() + "'" +
            ", shipQuantity=" + getShipQuantity() +
            ", jobOriginalCost=" + getJobOriginalCost() +
            ", quotedAmount='" + getQuotedAmount() + "'" +
            ", assignTo='" + getAssignTo() + "'" +
            ", toCompany='" + getToCompany() + "'" +
            ", toLocationType=" + getToLocationType() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", jobNumber='" + getJobNumber() + "'" +
            ", jobCustomer='" + getJobCustomer() + "'" +
            ", serviceType=" + getServiceType() +
            ", jobSales='" + getJobSales() + "'" +
            ", woRequestType=" + getWoRequestType() +
            ", shipTo=" + getShipToId() +
            ", shipFrom=" + getShipFromId() +
            ", woPackageType=" + getWoPackageTypeId() +
            "}";
    }
}
