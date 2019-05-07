package com.java.examples.service;

import com.java.examples.service.dto.TransportModesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.examples.domain.TransportModes}.
 */
public interface TransportModesService {

    /**
     * Save a transportModes.
     *
     * @param transportModesDTO the entity to save.
     * @return the persisted entity.
     */
    TransportModesDTO save(TransportModesDTO transportModesDTO);

    /**
     * Get all the transportModes.
     *
     * @return the list of entities.
     */
    List<TransportModesDTO> findAll();


    /**
     * Get the "id" transportModes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransportModesDTO> findOne(Long id);

    /**
     * Delete the "id" transportModes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
