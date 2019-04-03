package com.java.examples.web.rest;
import com.java.examples.service.WoPackageTypeService;
import com.java.examples.web.rest.errors.BadRequestAlertException;
import com.java.examples.web.rest.util.HeaderUtil;
import com.java.examples.service.dto.WoPackageTypeDTO;
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

    private final WoPackageTypeService woPackageTypeService;

    public WoPackageTypeResource(WoPackageTypeService woPackageTypeService) {
        this.woPackageTypeService = woPackageTypeService;
    }

    /**
     * POST  /wo-package-types : Create a new woPackageType.
     *
     * @param woPackageTypeDTO the woPackageTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new woPackageTypeDTO, or with status 400 (Bad Request) if the woPackageType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wo-package-types")
    public ResponseEntity<WoPackageTypeDTO> createWoPackageType(@Valid @RequestBody WoPackageTypeDTO woPackageTypeDTO) throws URISyntaxException {
        log.debug("REST request to save WoPackageType : {}", woPackageTypeDTO);
        if (woPackageTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new woPackageType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoPackageTypeDTO result = woPackageTypeService.save(woPackageTypeDTO);
        return ResponseEntity.created(new URI("/api/wo-package-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wo-package-types : Updates an existing woPackageType.
     *
     * @param woPackageTypeDTO the woPackageTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated woPackageTypeDTO,
     * or with status 400 (Bad Request) if the woPackageTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the woPackageTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wo-package-types")
    public ResponseEntity<WoPackageTypeDTO> updateWoPackageType(@Valid @RequestBody WoPackageTypeDTO woPackageTypeDTO) throws URISyntaxException {
        log.debug("REST request to update WoPackageType : {}", woPackageTypeDTO);
        if (woPackageTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoPackageTypeDTO result = woPackageTypeService.save(woPackageTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, woPackageTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wo-package-types : get all the woPackageTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of woPackageTypes in body
     */
    @GetMapping("/wo-package-types")
    public List<WoPackageTypeDTO> getAllWoPackageTypes() {
        log.debug("REST request to get all WoPackageTypes");
        return woPackageTypeService.findAll();
    }

    /**
     * GET  /wo-package-types/:id : get the "id" woPackageType.
     *
     * @param id the id of the woPackageTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the woPackageTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/wo-package-types/{id}")
    public ResponseEntity<WoPackageTypeDTO> getWoPackageType(@PathVariable Long id) {
        log.debug("REST request to get WoPackageType : {}", id);
        Optional<WoPackageTypeDTO> woPackageTypeDTO = woPackageTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(woPackageTypeDTO);
    }

    /**
     * DELETE  /wo-package-types/:id : delete the "id" woPackageType.
     *
     * @param id the id of the woPackageTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wo-package-types/{id}")
    public ResponseEntity<Void> deleteWoPackageType(@PathVariable Long id) {
        log.debug("REST request to delete WoPackageType : {}", id);
        woPackageTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
