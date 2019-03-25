package com.java.examples.web.rest;
import com.java.examples.domain.WoPackage;
import com.java.examples.repository.WoPackageRepository;
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
 * REST controller for managing WoPackage.
 */
@RestController
@RequestMapping("/api")
public class WoPackageResource {

    private final Logger log = LoggerFactory.getLogger(WoPackageResource.class);

    private static final String ENTITY_NAME = "woPackage";

    private final WoPackageRepository woPackageRepository;

    public WoPackageResource(WoPackageRepository woPackageRepository) {
        this.woPackageRepository = woPackageRepository;
    }

    /**
     * POST  /wo-packages : Create a new woPackage.
     *
     * @param woPackage the woPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new woPackage, or with status 400 (Bad Request) if the woPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wo-packages")
    public ResponseEntity<WoPackage> createWoPackage(@Valid @RequestBody WoPackage woPackage) throws URISyntaxException {
        log.debug("REST request to save WoPackage : {}", woPackage);
        if (woPackage.getId() != null) {
            throw new BadRequestAlertException("A new woPackage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoPackage result = woPackageRepository.save(woPackage);
        return ResponseEntity.created(new URI("/api/wo-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wo-packages : Updates an existing woPackage.
     *
     * @param woPackage the woPackage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated woPackage,
     * or with status 400 (Bad Request) if the woPackage is not valid,
     * or with status 500 (Internal Server Error) if the woPackage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wo-packages")
    public ResponseEntity<WoPackage> updateWoPackage(@Valid @RequestBody WoPackage woPackage) throws URISyntaxException {
        log.debug("REST request to update WoPackage : {}", woPackage);
        if (woPackage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoPackage result = woPackageRepository.save(woPackage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, woPackage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wo-packages : get all the woPackages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of woPackages in body
     */
    @GetMapping("/wo-packages")
    public List<WoPackage> getAllWoPackages() {
        log.debug("REST request to get all WoPackages");
        return woPackageRepository.findAll();
    }

    /**
     * GET  /wo-packages/:id : get the "id" woPackage.
     *
     * @param id the id of the woPackage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the woPackage, or with status 404 (Not Found)
     */
    @GetMapping("/wo-packages/{id}")
    public ResponseEntity<WoPackage> getWoPackage(@PathVariable Long id) {
        log.debug("REST request to get WoPackage : {}", id);
        Optional<WoPackage> woPackage = woPackageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(woPackage);
    }

    /**
     * DELETE  /wo-packages/:id : delete the "id" woPackage.
     *
     * @param id the id of the woPackage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wo-packages/{id}")
    public ResponseEntity<Void> deleteWoPackage(@PathVariable Long id) {
        log.debug("REST request to delete WoPackage : {}", id);
        woPackageRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
