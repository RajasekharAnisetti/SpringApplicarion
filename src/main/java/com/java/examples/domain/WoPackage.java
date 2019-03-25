package com.java.examples.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WoPackage.
 */
@Entity
@Table(name = "wo_package")
public class WoPackage implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @Max(value = 11)
    @Column(name = "length")
    private Integer length;

    @Max(value = 11)
    @Column(name = "width")
    private Integer width;

    @Max(value = 11)
    @Column(name = "height")
    private Integer height;

    @Max(value = 11)
    @Column(name = "weight")
    private Integer weight;

    @Max(value = 11)
    @Column(name = "cubed_weight")
    private Integer cubedWeight;

    @Size(max = 255)
    @Column(name = "tracking_number", length = 255)
    private String trackingNumber;

    @Column(name = "cod_value")
    private Double codValue;

    @Column(name = "insurance_amount")
    private Double insuranceAmount;

    @Max(value = 20)
    @Column(name = "jhi_oid")
    private Integer oid;

    @Max(value = 11)
    @Column(name = "position")
    private Integer position;

    @Size(max = 10)
    @Column(name = "freight_class", length = 10)
    private String freightClass;

    @Size(max = 255)
    @Column(name = "jhi_type", length = 255)
    private String type;

    @ManyToOne
    @JsonIgnoreProperties("woPackages")
    private WoPackageType woPackageType;

    @ManyToOne
    @JsonIgnoreProperties("woPackages")
    private WoWorkOrder woWorkOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public WoPackage description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLength() {
        return length;
    }

    public WoPackage length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public WoPackage width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public WoPackage height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public WoPackage weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getCubedWeight() {
        return cubedWeight;
    }

    public WoPackage cubedWeight(Integer cubedWeight) {
        this.cubedWeight = cubedWeight;
        return this;
    }

    public void setCubedWeight(Integer cubedWeight) {
        this.cubedWeight = cubedWeight;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public WoPackage trackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Double getCodValue() {
        return codValue;
    }

    public WoPackage codValue(Double codValue) {
        this.codValue = codValue;
        return this;
    }

    public void setCodValue(Double codValue) {
        this.codValue = codValue;
    }

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public WoPackage insuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
        return this;
    }

    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public Integer getOid() {
        return oid;
    }

    public WoPackage oid(Integer oid) {
        this.oid = oid;
        return this;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPosition() {
        return position;
    }

    public WoPackage position(Integer position) {
        this.position = position;
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getFreightClass() {
        return freightClass;
    }

    public WoPackage freightClass(String freightClass) {
        this.freightClass = freightClass;
        return this;
    }

    public void setFreightClass(String freightClass) {
        this.freightClass = freightClass;
    }

    public String getType() {
        return type;
    }

    public WoPackage type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WoPackageType getWoPackageType() {
        return woPackageType;
    }

    public WoPackage woPackageType(WoPackageType woPackageType) {
        this.woPackageType = woPackageType;
        return this;
    }

    public void setWoPackageType(WoPackageType woPackageType) {
        this.woPackageType = woPackageType;
    }

    public WoWorkOrder getWoWorkOrder() {
        return woWorkOrder;
    }

    public WoPackage woWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrder = woWorkOrder;
        return this;
    }

    public void setWoWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrder = woWorkOrder;
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
        WoPackage woPackage = (WoPackage) o;
        if (woPackage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woPackage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoPackage{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", cubedWeight=" + getCubedWeight() +
            ", trackingNumber='" + getTrackingNumber() + "'" +
            ", codValue=" + getCodValue() +
            ", insuranceAmount=" + getInsuranceAmount() +
            ", oid=" + getOid() +
            ", position=" + getPosition() +
            ", freightClass='" + getFreightClass() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
