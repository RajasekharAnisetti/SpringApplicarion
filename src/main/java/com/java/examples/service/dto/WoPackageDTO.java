package com.java.examples.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the WoPackage entity.
 */
public class WoPackageDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String description;

    @Max(value = 11)
    private Integer length;

    @Max(value = 11)
    private Integer width;

    @Max(value = 11)
    private Integer height;

    @Max(value = 11)
    private Integer weight;

    @Max(value = 11)
    private Integer cubedWeight;

    @Size(max = 255)
    private String trackingNumber;

    private Double codValue;

    private Double insuranceAmount;

    @Max(value = 20)
    private Integer oid;

    @Max(value = 11)
    private Integer position;

    @Size(max = 10)
    private String freightClass;

    @Size(max = 255)
    private String type;


    private Long woPackageTypeId;

    private Long woWorkOrderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getCubedWeight() {
        return cubedWeight;
    }

    public void setCubedWeight(Integer cubedWeight) {
        this.cubedWeight = cubedWeight;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Double getCodValue() {
        return codValue;
    }

    public void setCodValue(Double codValue) {
        this.codValue = codValue;
    }

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getFreightClass() {
        return freightClass;
    }

    public void setFreightClass(String freightClass) {
        this.freightClass = freightClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getWoPackageTypeId() {
        return woPackageTypeId;
    }

    public void setWoPackageTypeId(Long woPackageTypeId) {
        this.woPackageTypeId = woPackageTypeId;
    }

    public Long getWoWorkOrderId() {
        return woWorkOrderId;
    }

    public void setWoWorkOrderId(Long woWorkOrderId) {
        this.woWorkOrderId = woWorkOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoPackageDTO woPackageDTO = (WoPackageDTO) o;
        if (woPackageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woPackageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoPackageDTO{" +
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
            ", woPackageType=" + getWoPackageTypeId() +
            ", woWorkOrder=" + getWoWorkOrderId() +
            "}";
    }
}
