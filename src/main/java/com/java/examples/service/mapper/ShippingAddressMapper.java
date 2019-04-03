package com.java.examples.service.mapper;

import com.java.examples.domain.*;
import com.java.examples.service.dto.ShippingAddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ShippingAddress and its DTO ShippingAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class, ProvinceMapper.class, CityMapper.class})
public interface ShippingAddressMapper extends EntityMapper<ShippingAddressDTO, ShippingAddress> {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "city.id", target = "cityId")
    ShippingAddressDTO toDto(ShippingAddress shippingAddress);

    @Mapping(source = "countryId", target = "country")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "cityId", target = "city")
    ShippingAddress toEntity(ShippingAddressDTO shippingAddressDTO);

    default ShippingAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(id);
        return shippingAddress;
    }
}
