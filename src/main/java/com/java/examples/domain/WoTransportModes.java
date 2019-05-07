package com.java.examples.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WoTransportModes.
 */
@Entity
@Table(name = "wo_transport_modes")
public class WoTransportModes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("woTransportModes")
    private TransportModes transportModes;

    @ManyToOne
    @JsonIgnoreProperties("woTransportModes")
    private WoCustomsBrokerage woCustomsBrokerage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportModes getTransportModes() {
        return transportModes;
    }

    public WoTransportModes transportModes(TransportModes transportModes) {
        this.transportModes = transportModes;
        return this;
    }

    public void setTransportModes(TransportModes transportModes) {
        this.transportModes = transportModes;
    }

    public WoCustomsBrokerage getWoCustomsBrokerage() {
        return woCustomsBrokerage;
    }

    public WoTransportModes woCustomsBrokerage(WoCustomsBrokerage woCustomsBrokerage) {
        this.woCustomsBrokerage = woCustomsBrokerage;
        return this;
    }

    public void setWoCustomsBrokerage(WoCustomsBrokerage woCustomsBrokerage) {
        this.woCustomsBrokerage = woCustomsBrokerage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WoTransportModes)) {
            return false;
        }
        return id != null && id.equals(((WoTransportModes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoTransportModes{" +
            "id=" + getId() +
            "}";
    }
}
