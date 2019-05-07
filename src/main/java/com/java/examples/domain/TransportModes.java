package com.java.examples.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TransportModes.
 */
@Entity
@Table(name = "transport_modes")
public class TransportModes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy = "transportModes")
    private Set<WoTransportModes> woTransportModes = new HashSet<>();

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

    public TransportModes name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WoTransportModes> getWoTransportModes() {
        return woTransportModes;
    }

    public TransportModes woTransportModes(Set<WoTransportModes> woTransportModes) {
        this.woTransportModes = woTransportModes;
        return this;
    }

    public TransportModes addWoTransportModes(WoTransportModes woTransportModes) {
        this.woTransportModes.add(woTransportModes);
        woTransportModes.setTransportModes(this);
        return this;
    }

    public TransportModes removeWoTransportModes(WoTransportModes woTransportModes) {
        this.woTransportModes.remove(woTransportModes);
        woTransportModes.setTransportModes(null);
        return this;
    }

    public void setWoTransportModes(Set<WoTransportModes> woTransportModes) {
        this.woTransportModes = woTransportModes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportModes)) {
            return false;
        }
        return id != null && id.equals(((TransportModes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TransportModes{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
