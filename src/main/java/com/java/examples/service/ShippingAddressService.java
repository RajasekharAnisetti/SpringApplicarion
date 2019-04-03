package com.java.examples.service;

import com.java.examples.service.dto.ShippingAddressDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ShippingAddress.
 */
public interface ShippingAddressService {

    /**
     * Save a shippingAddress.
     *
     * @param shippingAddressDTO the entity to save
     * @return the persisted entity
     */
    ShippingAddressDTO save(ShippingAddressDTO shippingAddressDTO);

    /**
     * Get all the shippingAddresses.
     *
     * @return the list of entities
     */
    List<ShippingAddressDTO> findAll();


    /**
     * Get the "id" shippingAddress.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ShippingAddressDTO> findOne(Long id);

    /**
     * Delete the "id" shippingAddress.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
