package com.java.examples.web.rest;

import com.java.examples.service.TransportModesService;
import com.java.examples.web.rest.errors.BadRequestAlertException;
import com.java.examples.service.dto.TransportModesDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.java.examples.domain.TransportModes}.
 */
@RestController
@RequestMapping("/api")
public class TransportModesResource {

    private final Logger log = LoggerFactory.getLogger(TransportModesResource.class);

    private static final String ENTITY_NAME = "transportModes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransportModesService transportModesService;

    public TransportModesResource(TransportModesService transportModesService) {
        this.transportModesService = transportModesService;
    }

    /**
     * {@code POST  /transport-modes} : Create a new transportModes.
     *
     * @param transportModesDTO the transportModesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transportModesDTO, or with status {@code 400 (Bad Request)} if the transportModes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transport-modes")
    public ResponseEntity<TransportModesDTO> createTransportModes(@Valid @RequestBody TransportModesDTO transportModesDTO) throws URISyntaxException {
        log.debug("REST request to save TransportModes : {}", transportModesDTO);
        if (transportModesDTO.getId() != null) {
            throw new BadRequestAlertException("A new transportModes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransportModesDTO result = transportModesService.save(transportModesDTO);
        return ResponseEntity.created(new URI("/api/transport-modes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transport-modes} : Updates an existing transportModes.
     *
     * @param transportModesDTO the transportModesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transportModesDTO,
     * or with status {@code 400 (Bad Request)} if the transportModesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transportModesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transport-modes")
    public ResponseEntity<TransportModesDTO> updateTransportModes(@Valid @RequestBody TransportModesDTO transportModesDTO) throws URISyntaxException {
        log.debug("REST request to update TransportModes : {}", transportModesDTO);
        if (transportModesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransportModesDTO result = transportModesService.save(transportModesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transportModesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transport-modes} : get all the transportModes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transportModes in body.
     */
    @GetMapping("/transport-modes")
    public List<TransportModesDTO> getAllTransportModes() {
        log.debug("REST request to get all TransportModes");
        return transportModesService.findAll();
    }

    /**
     * {@code GET  /transport-modes/:id} : get the "id" transportModes.
     *
     * @param id the id of the transportModesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transportModesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transport-modes/{id}")
    public ResponseEntity<TransportModesDTO> getTransportModes(@PathVariable Long id) {
        log.debug("REST request to get TransportModes : {}", id);
        Optional<TransportModesDTO> transportModesDTO = transportModesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transportModesDTO);
    }

    /**
     * {@code DELETE  /transport-modes/:id} : delete the "id" transportModes.
     *
     * @param id the id of the transportModesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transport-modes/{id}")
    public ResponseEntity<Void> deleteTransportModes(@PathVariable Long id) {
        log.debug("REST request to delete TransportModes : {}", id);
        transportModesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
