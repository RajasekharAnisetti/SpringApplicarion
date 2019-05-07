package com.java.examples.service.mapper;

import com.java.examples.domain.*;
import com.java.examples.service.dto.WoWorkOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoWorkOrder} and its DTO {@link WoWorkOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {WoCustomsBrokerageMapper.class})
public interface WoWorkOrderMapper extends EntityMapper<WoWorkOrderDTO, WoWorkOrder> {

    @Mapping(source = "woCustomsBrokerage.id", target = "woCustomsBrokerageId")
    WoWorkOrderDTO toDto(WoWorkOrder woWorkOrder);

    @Mapping(source = "woCustomsBrokerageId", target = "woCustomsBrokerage")
    WoWorkOrder toEntity(WoWorkOrderDTO woWorkOrderDTO);

    default WoWorkOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoWorkOrder woWorkOrder = new WoWorkOrder();
        woWorkOrder.setId(id);
        return woWorkOrder;
    }
}
