package com.java.examples.web.rest;
import com.java.examples.domain.WoPackageType;
import com.java.examples.repository.WoPackageTypeRepository;
import com.java.examples.web.rest.errors.BadRequestAlertException;
import com.java.examples.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WoPackageType.
 */
@RestController
@RequestMapping("/api")
public class WoPackageTypeResource {

    private final Logger log = LoggerFactory.getLogger(WoPackageTypeResource.class);

    private static final String ENTITY_NAME = "woPackageType";

    private final WoPackageTypeRepository woPackageTypeRepository;

    public WoPackageTypeResource(WoPackageTypeRepository woPackageTypeRepository) {
        this.woPackageTypeRepository = woPackageTypeRepository;
    }

    /**
     * POST  /wo-package-types : Create a new woPackageType.
     *
     * @param woPackageType the woPackageType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new woPackageType, or with status 400 (Bad Request) if the woPackageType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wo-package-types")
    public ResponseEntity<WoPackageType> createWoPackageType(@Valid @RequestBody WoPackageType woPackageType) throws URISyntaxException {
        log.debug("REST request to save WoPackageType : {}", woPackageType);
        if (woPackageType.getId() != null) {
            throw new BadRequestAlertException("A new woPackageType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoPackageType result = woPackageTypeRepository.save(woPackageType);
        return ResponseEntity.created(new URI("/api/wo-package-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wo-package-types : Updates an existing woPackageType.
     *
     * @param woPackageType the woPackageType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated woPackageType,
     * or with status 400 (Bad Request) if the woPackageType is not valid,
     * or with status 500 (Internal Server Error) if the woPackageType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wo-package-types")
    public ResponseEntity<WoPackageType> updateWoPackageType(@Valid @RequestBody WoPackageType woPackageType) throws URISyntaxException {
        log.debug("REST request to update WoPackageType : {}", woPackageType);
        if (woPackageType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoPackageType result = woPackageTypeRepository.save(woPackageType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, woPackageType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wo-package-types : get all the woPackageTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of woPackageTypes in body
     */
    @GetMapping("/wo-package-types")
    public List<WoPackageType> getAllWoPackageTypes() {
        log.debug("REST request to get all WoPackageTypes");
        return woPackageTypeRepository.findAll();
    }

    /**
     * GET  /wo-package-types/:id : get the "id" woPackageType.
     *
     * @param id the id of the woPackageType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the woPackageType, or with status 404 (Not Found)
     */
    @GetMapping("/wo-package-types/{id}")
    public ResponseEntity<WoPackageType> getWoPackageType(@PathVariable Long id) {
        log.debug("REST request to get WoPackageType : {}", id);
        Optional<WoPackageType> woPackageType = woPackageTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(woPackageType);
    }

    /**
     * DELETE  /wo-package-types/:id : delete the "id" woPackageType.
     *
     * @param id the id of the woPackageType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wo-package-types/{id}")
    public ResponseEntity<Void> deleteWoPackageType(@PathVariable Long id) {
        log.debug("REST request to delete WoPackageType : {}", id);
        woPackageTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
