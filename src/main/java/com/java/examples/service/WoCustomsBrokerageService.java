package com.java.examples.service;

import com.java.examples.service.dto.WoCustomsBrokerageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.java.examples.domain.WoCustomsBrokerage}.
 */
public interface WoCustomsBrokerageService {

    /**
     * Save a woCustomsBrokerage.
     *
     * @param woCustomsBrokerageDTO the entity to save.
     * @return the persisted entity.
     */
    WoCustomsBrokerageDTO save(WoCustomsBrokerageDTO woCustomsBrokerageDTO);

    /**
     * Get all the woCustomsBrokerages.
     *
     * @return the list of entities.
     */
    List<WoCustomsBrokerageDTO> findAll();
    /**
     * Get all the WoCustomsBrokerageDTO where WoWorkOrder is {@code null}.
     *
     * @return the list of entities.
     */
    List<WoCustomsBrokerageDTO> findAllWhereWoWorkOrderIsNull();


    /**
     * Get the "id" woCustomsBrokerage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoCustomsBrokerageDTO> findOne(Long id);

    /**
     * Delete the "id" woCustomsBrokerage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
