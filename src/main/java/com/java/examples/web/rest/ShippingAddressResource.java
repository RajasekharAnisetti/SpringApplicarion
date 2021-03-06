package com.java.examples.web.rest;
import com.java.examples.service.ShippingAddressService;
import com.java.examples.web.rest.errors.BadRequestAlertException;
import com.java.examples.web.rest.util.HeaderUtil;
import com.java.examples.service.dto.ShippingAddressDTO;
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

    private final ShippingAddressService shippingAddressService;

    public ShippingAddressResource(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    /**
     * POST  /shipping-addresses : Create a new shippingAddress.
     *
     * @param shippingAddressDTO the shippingAddressDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shippingAddressDTO, or with status 400 (Bad Request) if the shippingAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddressDTO> createShippingAddress(@Valid @RequestBody ShippingAddressDTO shippingAddressDTO) throws URISyntaxException {
        log.debug("REST request to save ShippingAddress : {}", shippingAddressDTO);
        if (shippingAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new shippingAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShippingAddressDTO result = shippingAddressService.save(shippingAddressDTO);
        return ResponseEntity.created(new URI("/api/shipping-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shipping-addresses : Updates an existing shippingAddress.
     *
     * @param shippingAddressDTO the shippingAddressDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shippingAddressDTO,
     * or with status 400 (Bad Request) if the shippingAddressDTO is not valid,
     * or with status 500 (Internal Server Error) if the shippingAddressDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shipping-addresses")
    public ResponseEntity<ShippingAddressDTO> updateShippingAddress(@Valid @RequestBody ShippingAddressDTO shippingAddressDTO) throws URISyntaxException {
        log.debug("REST request to update ShippingAddress : {}", shippingAddressDTO);
        if (shippingAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShippingAddressDTO result = shippingAddressService.save(shippingAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, shippingAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shipping-addresses : get all the shippingAddresses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of shippingAddresses in body
     */
    @GetMapping("/shipping-addresses")
    public List<ShippingAddressDTO> getAllShippingAddresses() {
        log.debug("REST request to get all ShippingAddresses");
        return shippingAddressService.findAll();
    }

    /**
     * GET  /shipping-addresses/:id : get the "id" shippingAddress.
     *
     * @param id the id of the shippingAddressDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shippingAddressDTO, or with status 404 (Not Found)
     */
    @GetMapping("/shipping-addresses/{id}")
    public ResponseEntity<ShippingAddressDTO> getShippingAddress(@PathVariable Long id) {
        log.debug("REST request to get ShippingAddress : {}", id);
        Optional<ShippingAddressDTO> shippingAddressDTO = shippingAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shippingAddressDTO);
    }

    /**
     * DELETE  /shipping-addresses/:id : delete the "id" shippingAddress.
     *
     * @param id the id of the shippingAddressDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shipping-addresses/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable Long id) {
        log.debug("REST request to delete ShippingAddress : {}", id);
        shippingAddressService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
