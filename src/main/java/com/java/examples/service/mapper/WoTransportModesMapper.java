package com.java.examples.service.mapper;

import com.java.examples.domain.*;
import com.java.examples.service.dto.WoTransportModesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoTransportModes} and its DTO {@link WoTransportModesDTO}.
 */
@Mapper(componentModel = "spring", uses = {TransportModesMapper.class, WoCustomsBrokerageMapper.class})
public interface WoTransportModesMapper extends EntityMapper<WoTransportModesDTO, WoTransportModes> {

    @Mapping(source = "transportModes.id", target = "transportModesId")
    @Mapping(source = "woCustomsBrokerage.id", target = "woCustomsBrokerageId")
    WoTransportModesDTO toDto(WoTransportModes woTransportModes);

    @Mapping(source = "transportModesId", target = "transportModes")
    @Mapping(source = "woCustomsBrokerageId", target = "woCustomsBrokerage")
    WoTransportModes toEntity(WoTransportModesDTO woTransportModesDTO);

    default WoTransportModes fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoTransportModes woTransportModes = new WoTransportModes();
        woTransportModes.setId(id);
        return woTransportModes;
    }
}
