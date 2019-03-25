package com.java.examples.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WoWorkOrder.
 */
@Entity
@Table(name = "wo_work_order")
public class WoWorkOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "customs_broker_name", length = 255)
    private String customsBrokerName;

    @Size(max = 255)
    @Column(name = "customs_contact_name", length = 255)
    private String customsContactName;

    @Size(max = 255)
    @Column(name = "customs_currency", length = 255)
    private String customsCurrency;

    @Size(max = 255)
    @Column(name = "customs_phone_number", length = 255)
    private String customsPhoneNumber;

    @Column(name = "customs_value")
    private Double customsValue;

    @Size(max = 255)
    @Column(name = "from_airport_code", length = 255)
    private String fromAirportCode;

    @Max(value = 11)
    @Column(name = "from_location_type")
    private Integer fromLocationType;

    @Size(max = 255)
    @Column(name = "job_content_descr", length = 255)
    private String jobContentDescr;

    @Max(value = 11)
    @Column(name = "ship_quantity")
    private Integer shipQuantity;

    @Max(value = 11)
    @Column(name = "job_original_cost")
    private Integer jobOriginalCost;

    @Size(max = 255)
    @Column(name = "quoted_amount", length = 255)
    private String quotedAmount;

    @Size(max = 255)
    @Column(name = "assign_to", length = 255)
    private String assignTo;

    @Size(max = 255)
    @Column(name = "to_company", length = 255)
    private String toCompany;

    @Max(value = 11)
    @Column(name = "to_location_type")
    private Integer toLocationType;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @Size(max = 255)
    @Column(name = "job_number", length = 255)
    private String jobNumber;

    @Size(max = 255)
    @Column(name = "job_customer", length = 255)
    private String jobCustomer;

    @Column(name = "service_type")
    private Long serviceType;

    @Size(max = 255)
    @Column(name = "job_sales", length = 255)
    private String jobSales;

    @Max(value = 11)
    @Column(name = "wo_request_type")
    private Integer woRequestType;

    @OneToMany(mappedBy = "woWorkOrder")
    private Set<WoPackage> woPackages = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("woWorkOrders")
    private ShippingAddress shipTo;

    @ManyToOne
    @JsonIgnoreProperties("woWorkOrders")
    private ShippingAddress shipFrom;

    @ManyToOne
    @JsonIgnoreProperties("woWorkOrders")
    private WoPackageType woPackageType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomsBrokerName() {
        return customsBrokerName;
    }

    public WoWorkOrder customsBrokerName(String customsBrokerName) {
        this.customsBrokerName = customsBrokerName;
        return this;
    }

    public void setCustomsBrokerName(String customsBrokerName) {
        this.customsBrokerName = customsBrokerName;
    }

    public String getCustomsContactName() {
        return customsContactName;
    }

    public WoWorkOrder customsContactName(String customsContactName) {
        this.customsContactName = customsContactName;
        return this;
    }

    public void setCustomsContactName(String customsContactName) {
        this.customsContactName = customsContactName;
    }

    public String getCustomsCurrency() {
        return customsCurrency;
    }

    public WoWorkOrder customsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
        return this;
    }

    public void setCustomsCurrency(String customsCurrency) {
        this.customsCurrency = customsCurrency;
    }

    public String getCustomsPhoneNumber() {
        return customsPhoneNumber;
    }

    public WoWorkOrder customsPhoneNumber(String customsPhoneNumber) {
        this.customsPhoneNumber = customsPhoneNumber;
        return this;
    }

    public void setCustomsPhoneNumber(String customsPhoneNumber) {
        this.customsPhoneNumber = customsPhoneNumber;
    }

    public Double getCustomsValue() {
        return customsValue;
    }

    public WoWorkOrder customsValue(Double customsValue) {
        this.customsValue = customsValue;
        return this;
    }

    public void setCustomsValue(Double customsValue) {
        this.customsValue = customsValue;
    }

    public String getFromAirportCode() {
        return fromAirportCode;
    }

    public WoWorkOrder fromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
        return this;
    }

    public void setFromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
    }

    public Integer getFromLocationType() {
        return fromLocationType;
    }

    public WoWorkOrder fromLocationType(Integer fromLocationType) {
        this.fromLocationType = fromLocationType;
        return this;
    }

    public void setFromLocationType(Integer fromLocationType) {
        this.fromLocationType = fromLocationType;
    }

    public String getJobContentDescr() {
        return jobContentDescr;
    }

    public WoWorkOrder jobContentDescr(String jobContentDescr) {
        this.jobContentDescr = jobContentDescr;
        return this;
    }

    public void setJobContentDescr(String jobContentDescr) {
        this.jobContentDescr = jobContentDescr;
    }

    public Integer getShipQuantity() {
        return shipQuantity;
    }

    public WoWorkOrder shipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
        return this;
    }

    public void setShipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    public Integer getJobOriginalCost() {
        return jobOriginalCost;
    }

    public WoWorkOrder jobOriginalCost(Integer jobOriginalCost) {
        this.jobOriginalCost = jobOriginalCost;
        return this;
    }

    public void setJobOriginalCost(Integer jobOriginalCost) {
        this.jobOriginalCost = jobOriginalCost;
    }

    public String getQuotedAmount() {
        return quotedAmount;
    }

    public WoWorkOrder quotedAmount(String quotedAmount) {
        this.quotedAmount = quotedAmount;
        return this;
    }

    public void setQuotedAmount(String quotedAmount) {
        this.quotedAmount = quotedAmount;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public WoWorkOrder assignTo(String assignTo) {
        this.assignTo = assignTo;
        return this;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getToCompany() {
        return toCompany;
    }

    public WoWorkOrder toCompany(String toCompany) {
        this.toCompany = toCompany;
        return this;
    }

    public void setToCompany(String toCompany) {
        this.toCompany = toCompany;
    }

    public Integer getToLocationType() {
        return toLocationType;
    }

    public WoWorkOrder toLocationType(Integer toLocationType) {
        this.toLocationType = toLocationType;
        return this;
    }

    public void setToLocationType(Integer toLocationType) {
        this.toLocationType = toLocationType;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public WoWorkOrder dateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public WoWorkOrder jobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
        return this;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobCustomer() {
        return jobCustomer;
    }

    public WoWorkOrder jobCustomer(String jobCustomer) {
        this.jobCustomer = jobCustomer;
        return this;
    }

    public void setJobCustomer(String jobCustomer) {
        this.jobCustomer = jobCustomer;
    }

    public Long getServiceType() {
        return serviceType;
    }

    public WoWorkOrder serviceType(Long serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(Long serviceType) {
        this.serviceType = serviceType;
    }

    public String getJobSales() {
        return jobSales;
    }

    public WoWorkOrder jobSales(String jobSales) {
        this.jobSales = jobSales;
        return this;
    }

    public void setJobSales(String jobSales) {
        this.jobSales = jobSales;
    }

    public Integer getWoRequestType() {
        return woRequestType;
    }

    public WoWorkOrder woRequestType(Integer woRequestType) {
        this.woRequestType = woRequestType;
        return this;
    }

    public void setWoRequestType(Integer woRequestType) {
        this.woRequestType = woRequestType;
    }

    public Set<WoPackage> getWoPackages() {
        return woPackages;
    }

    public WoWorkOrder woPackages(Set<WoPackage> woPackages) {
        this.woPackages = woPackages;
        return this;
    }

    public WoWorkOrder addWoPackage(WoPackage woPackage) {
        this.woPackages.add(woPackage);
        woPackage.setWoWorkOrder(this);
        return this;
    }

    public WoWorkOrder removeWoPackage(WoPackage woPackage) {
        this.woPackages.remove(woPackage);
        woPackage.setWoWorkOrder(null);
        return this;
    }

    public void setWoPackages(Set<WoPackage> woPackages) {
        this.woPackages = woPackages;
    }

    public ShippingAddress getShipTo() {
        return shipTo;
    }

    public WoWorkOrder shipTo(ShippingAddress shippingAddress) {
        this.shipTo = shippingAddress;
        return this;
    }

    public void setShipTo(ShippingAddress shippingAddress) {
        this.shipTo = shippingAddress;
    }

    public ShippingAddress getShipFrom() {
        return shipFrom;
    }

    public WoWorkOrder shipFrom(ShippingAddress shippingAddress) {
        this.shipFrom = shippingAddress;
        return this;
    }

    public void setShipFrom(ShippingAddress shippingAddress) {
        this.shipFrom = shippingAddress;
    }

    public WoPackageType getWoPackageType() {
        return woPackageType;
    }

    public WoWorkOrder woPackageType(WoPackageType woPackageType) {
        this.woPackageType = woPackageType;
        return this;
    }

    public void setWoPackageType(WoPackageType woPackageType) {
        this.woPackageType = woPackageType;
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
        WoWorkOrder woWorkOrder = (WoWorkOrder) o;
        if (woWorkOrder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woWorkOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoWorkOrder{" +
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
            "}";
    }
}
