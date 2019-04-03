package com.java.examples.service.mapper;

import com.java.examples.domain.*;
import com.java.examples.service.dto.WoPackageTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WoPackageType and its DTO WoPackageTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoPackageTypeMapper extends EntityMapper<WoPackageTypeDTO, WoPackageType> {


    @Mapping(target = "woPackages", ignore = true)
    @Mapping(target = "woWorkOrders", ignore = true)
    WoPackageType toEntity(WoPackageTypeDTO woPackageTypeDTO);

    default WoPackageType fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoPackageType woPackageType = new WoPackageType();
        woPackageType.setId(id);
        return woPackageType;
    }
}
