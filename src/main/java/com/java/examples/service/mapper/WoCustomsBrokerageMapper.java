package com.java.examples.service.mapper;

import com.java.examples.domain.*;
import com.java.examples.service.dto.WoCustomsBrokerageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoCustomsBrokerage} and its DTO {@link WoCustomsBrokerageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoCustomsBrokerageMapper extends EntityMapper<WoCustomsBrokerageDTO, WoCustomsBrokerage> {


    @Mapping(target = "woTransportModes", ignore = true)
    @Mapping(target = "woWorkOrder", ignore = true)
    WoCustomsBrokerage toEntity(WoCustomsBrokerageDTO woCustomsBrokerageDTO);

    default WoCustomsBrokerage fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoCustomsBrokerage woCustomsBrokerage = new WoCustomsBrokerage();
        woCustomsBrokerage.setId(id);
        return woCustomsBrokerage;
    }
}
