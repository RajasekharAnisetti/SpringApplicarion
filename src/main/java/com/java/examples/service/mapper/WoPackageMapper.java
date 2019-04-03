package com.java.examples.service.mapper;

import com.java.examples.domain.*;
import com.java.examples.service.dto.WoPackageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WoPackage and its DTO WoPackageDTO.
 */
@Mapper(componentModel = "spring", uses = {WoPackageTypeMapper.class, WoWorkOrderMapper.class})
public interface WoPackageMapper extends EntityMapper<WoPackageDTO, WoPackage> {

    @Mapping(source = "woPackageType.id", target = "woPackageTypeId")
    @Mapping(source = "woWorkOrder.id", target = "woWorkOrderId")
    WoPackageDTO toDto(WoPackage woPackage);

    @Mapping(source = "woPackageTypeId", target = "woPackageType")
    @Mapping(source = "woWorkOrderId", target = "woWorkOrder")
    WoPackage toEntity(WoPackageDTO woPackageDTO);

    default WoPackage fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoPackage woPackage = new WoPackage();
        woPackage.setId(id);
        return woPackage;
    }
}
