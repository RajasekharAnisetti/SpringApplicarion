package com.java.examples.web.rest;
import com.java.examples.domain.WoWorkOrder;
import com.java.examples.repository.WoWorkOrderRepository;
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
 * REST controller for managing WoWorkOrder.
 */
@RestController
@RequestMapping("/api")
public class WoWorkOrderResource {

    private final Logger log = LoggerFactory.getLogger(WoWorkOrderResource.class);

    private static final String ENTITY_NAME = "woWorkOrder";

    private final WoWorkOrderRepository woWorkOrderRepository;

    public WoWorkOrderResource(WoWorkOrderRepository woWorkOrderRepository) {
        this.woWorkOrderRepository = woWorkOrderRepository;
    }

    /**
     * POST  /wo-work-orders : Create a new woWorkOrder.
     *
     * @param woWorkOrder the woWorkOrder to create
     * @return the ResponseEntity with status 201 (Created) and with body the new woWorkOrder, or with status 400 (Bad Request) if the woWorkOrder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wo-work-orders")
    public ResponseEntity<WoWorkOrder> createWoWorkOrder(@Valid @RequestBody WoWorkOrder woWorkOrder) throws URISyntaxException {
        log.debug("REST request to save WoWorkOrder : {}", woWorkOrder);
        if (woWorkOrder.getId() != null) {
            throw new BadRequestAlertException("A new woWorkOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WoWorkOrder result = woWorkOrderRepository.save(woWorkOrder);
        return ResponseEntity.created(new URI("/api/wo-work-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wo-work-orders : Updates an existing woWorkOrder.
     *
     * @param woWorkOrder the woWorkOrder to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated woWorkOrder,
     * or with status 400 (Bad Request) if the woWorkOrder is not valid,
     * or with status 500 (Internal Server Error) if the woWorkOrder couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wo-work-orders")
    public ResponseEntity<WoWorkOrder> updateWoWorkOrder(@Valid @RequestBody WoWorkOrder woWorkOrder) throws URISyntaxException {
        log.debug("REST request to update WoWorkOrder : {}", woWorkOrder);
        if (woWorkOrder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WoWorkOrder result = woWorkOrderRepository.save(woWorkOrder);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, woWorkOrder.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wo-work-orders : get all the woWorkOrders.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of woWorkOrders in body
     */
    @GetMapping("/wo-work-orders")
    public List<WoWorkOrder> getAllWoWorkOrders() {
        log.debug("REST request to get all WoWorkOrders");
        return woWorkOrderRepository.findAll();
    }

    /**
     * GET  /wo-work-orders/:id : get the "id" woWorkOrder.
     *
     * @param id the id of the woWorkOrder to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the woWorkOrder, or with status 404 (Not Found)
     */
    @GetMapping("/wo-work-orders/{id}")
    public ResponseEntity<WoWorkOrder> getWoWorkOrder(@PathVariable Long id) {
        log.debug("REST request to get WoWorkOrder : {}", id);
        Optional<WoWorkOrder> woWorkOrder = woWorkOrderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(woWorkOrder);
    }

    /**
     * DELETE  /wo-work-orders/:id : delete the "id" woWorkOrder.
     *
     * @param id the id of the woWorkOrder to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wo-work-orders/{id}")
    public ResponseEntity<Void> deleteWoWorkOrder(@PathVariable Long id) {
        log.debug("REST request to delete WoWorkOrder : {}", id);
        woWorkOrderRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
