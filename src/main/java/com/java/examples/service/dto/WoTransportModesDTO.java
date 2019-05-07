package com.java.examples.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.java.examples.domain.WoTransportModes} entity.
 */
public class WoTransportModesDTO implements Serializable {

    private Long id;


    private Long transportModesId;

    private Long woCustomsBrokerageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransportModesId() {
        return transportModesId;
    }

    public void setTransportModesId(Long transportModesId) {
        this.transportModesId = transportModesId;
    }

    public Long getWoCustomsBrokerageId() {
        return woCustomsBrokerageId;
    }

    public void setWoCustomsBrokerageId(Long woCustomsBrokerageId) {
        this.woCustomsBrokerageId = woCustomsBrokerageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WoTransportModesDTO woTransportModesDTO = (WoTransportModesDTO) o;
        if (woTransportModesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woTransportModesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoTransportModesDTO{" +
            "id=" + getId() +
            ", transportModes=" + getTransportModesId() +
            ", woCustomsBrokerage=" + getWoCustomsBrokerageId() +
            "}";
    }
}
