package com.java.examples.service.mapper;

import com.java.examples.domain.*;
import com.java.examples.service.dto.TransportModesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransportModes} and its DTO {@link TransportModesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TransportModesMapper extends EntityMapper<TransportModesDTO, TransportModes> {


    @Mapping(target = "woTransportModes", ignore = true)
    TransportModes toEntity(TransportModesDTO transportModesDTO);

    default TransportModes fromId(Long id) {
        if (id == null) {
            return null;
        }
        TransportModes transportModes = new TransportModes();
        transportModes.setId(id);
        return transportModes;
    }
}
