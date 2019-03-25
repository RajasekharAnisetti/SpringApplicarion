package com.java.examples.web.rest;
import com.java.examples.domain.ShippingAddress;
import com.java.examples.repository.ShippingAddressRepository;
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
 * REST controller for managing ShippingAddress.
 */
@RestController
@RequestMapping("/api")
public class ShippingAddressResource {

    private final Logger log = LoggerFactory.getLogger(ShippingAddressResource.class);

    private static final String ENTITY_NAME = "shippingAddress";

    private final ShippingAddressRepository shippingAddressRepository;

    public ShippingAddressResource(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    /**
     * POST  /shipping-addresses : Create a new shippingAddress.
     *
     * @param shippingAddress the shippingAddress to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shippingAddress, or with status 400 (Bad Request) if the shippingAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddress> createShippingAddress(@Valid @RequestBody ShippingAddress shippingAddress) throws URISyntaxException {
        log.debug("REST request to save ShippingAddress : {}", shippingAddress);
        if (shippingAddress.getId() != null) {
            throw new BadRequestAlertException("A new shippingAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShippingAddress result = shippingAddressRepository.save(shippingAddress);
        return ResponseEntity.created(new URI("/api/shipping-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shipping-addresses : Updates an existing shippingAddress.
     *
     * @param shippingAddress the shippingAddress to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shippingAddress,
     * or with status 400 (Bad Request) if the shippingAddress is not valid,
     * or with status 500 (Internal Server Error) if the shippingAddress couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddress> updateShippingAddress(@Valid @RequestBody ShippingAddress shippingAddress) throws URISyntaxException {
        log.debug("REST request to update ShippingAddress : {}", shippingAddress);
        if (shippingAddress.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShippingAddress result = shippingAddressRepository.save(shippingAddress);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, shippingAddress.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shipping-addresses : get all the shippingAddresses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of shippingAddresses in body
     */
    @GetMapping("/shipping-addresses")
    public List<ShippingAddress> getAllShippingAddresses() {
        log.debug("REST request to get all ShippingAddresses");
        return shippingAddressRepository.findAll();
    }

    /**
     * GET  /shipping-addresses/:id : get the "id" shippingAddress.
     *
     * @param id the id of the shippingAddress to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shippingAddress, or with status 404 (Not Found)
     */
    @GetMapping("/shipping-addresses/{id}")
    public ResponseEntity<ShippingAddress> getShippingAddress(@PathVariable Long id) {
        log.debug("REST request to get ShippingAddress : {}", id);
        Optional<ShippingAddress> shippingAddress = shippingAddressRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(shippingAddress);
    }

    /**
     * DELETE  /shipping-addresses/:id : delete the "id" shippingAddress.
     *
     * @param id the id of the shippingAddress to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shipping-addresses/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable Long id) {
        log.debug("REST request to delete ShippingAddress : {}", id);
        shippingAddressRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
