package com.java.examples.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WoPackageType.
 */
@Entity
@Table(name = "wo_package_type")
public class WoPackageType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy = "woPackageType")
    private Set<WoPackage> woPackages = new HashSet<>();
    @OneToMany(mappedBy = "woPackageType")
    private Set<WoWorkOrder> woWorkOrders = new HashSet<>();
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

    public WoPackageType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WoPackage> getWoPackages() {
        return woPackages;
    }

    public WoPackageType woPackages(Set<WoPackage> woPackages) {
        this.woPackages = woPackages;
        return this;
    }

    public WoPackageType addWoPackage(WoPackage woPackage) {
        this.woPackages.add(woPackage);
        woPackage.setWoPackageType(this);
        return this;
    }

    public WoPackageType removeWoPackage(WoPackage woPackage) {
        this.woPackages.remove(woPackage);
        woPackage.setWoPackageType(null);
        return this;
    }

    public void setWoPackages(Set<WoPackage> woPackages) {
        this.woPackages = woPackages;
    }

    public Set<WoWorkOrder> getWoWorkOrders() {
        return woWorkOrders;
    }

    public WoPackageType woWorkOrders(Set<WoWorkOrder> woWorkOrders) {
        this.woWorkOrders = woWorkOrders;
        return this;
    }

    public WoPackageType addWoWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrders.add(woWorkOrder);
        woWorkOrder.setWoPackageType(this);
        return this;
    }

    public WoPackageType removeWoWorkOrder(WoWorkOrder woWorkOrder) {
        this.woWorkOrders.remove(woWorkOrder);
        woWorkOrder.setWoPackageType(null);
        return this;
    }

    public void setWoWorkOrders(Set<WoWorkOrder> woWorkOrders) {
        this.woWorkOrders = woWorkOrders;
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
        WoPackageType woPackageType = (WoPackageType) o;
        if (woPackageType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woPackageType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoPackageType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
