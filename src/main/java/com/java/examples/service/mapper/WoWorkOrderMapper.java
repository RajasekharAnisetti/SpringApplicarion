package com.java.examples.service.mapper;

import com.java.examples.domain.*;
import com.java.examples.service.dto.WoWorkOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WoWorkOrder and its DTO WoWorkOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {ShippingAddressMapper.class, WoPackageTypeMapper.class})
public interface WoWorkOrderMapper extends EntityMapper<WoWorkOrderDTO, WoWorkOrder> {

    @Mapping(source = "shipTo.id", target = "shipToId")
    @Mapping(source = "shipFrom.id", target = "shipFromId")
    @Mapping(source = "woPackageType.id", target = "woPackageTypeId")
    WoWorkOrderDTO toDto(WoWorkOrder woWorkOrder);

    @Mapping(target = "woPackages", ignore = true)
    @Mapping(source = "shipToId", target = "shipTo")
    @Mapping(source = "shipFromId", target = "shipFrom")
    @Mapping(source = "woPackageTypeId", target = "woPackageType")
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
