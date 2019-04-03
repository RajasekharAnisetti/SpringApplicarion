package com.java.examples.service;

import com.java.examples.service.dto.WoPackageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing WoPackage.
 */
public interface WoPackageService {

    /**
     * Save a woPackage.
     *
     * @param woPackageDTO the entity to save
     * @return the persisted entity
     */
    WoPackageDTO save(WoPackageDTO woPackageDTO);

    /**
     * Get all the woPackages.
     *
     * @return the list of entities
     */
    List<WoPackageDTO> findAll();


    /**
     * Get the "id" woPackage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<WoPackageDTO> findOne(Long id);

    /**
     * Delete the "id" woPackage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
