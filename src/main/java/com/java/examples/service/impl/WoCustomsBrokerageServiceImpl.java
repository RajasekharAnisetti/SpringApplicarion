package com.java.examples.service.impl;

import com.java.examples.service.WoCustomsBrokerageService;
import com.java.examples.domain.WoCustomsBrokerage;
import com.java.examples.repository.WoCustomsBrokerageRepository;
import com.java.examples.service.dto.WoCustomsBrokerageDTO;
import com.java.examples.service.mapper.WoCustomsBrokerageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link WoCustomsBrokerage}.
 */
@Service
@Transactional
public class WoCustomsBrokerageServiceImpl implements WoCustomsBrokerageService {

    private final Logger log = LoggerFactory.getLogger(WoCustomsBrokerageServiceImpl.class);

    private final WoCustomsBrokerageRepository woCustomsBrokerageRepository;

    private final WoCustomsBrokerageMapper woCustomsBrokerageMapper;

    public WoCustomsBrokerageServiceImpl(WoCustomsBrokerageRepository woCustomsBrokerageRepository, WoCustomsBrokerageMapper woCustomsBrokerageMapper) {
        this.woCustomsBrokerageRepository = woCustomsBrokerageRepository;
        this.woCustomsBrokerageMapper = woCustomsBrokerageMapper;
    }

    /**
     * Save a woCustomsBrokerage.
     *
     * @param woCustomsBrokerageDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WoCustomsBrokerageDTO save(WoCustomsBrokerageDTO woCustomsBrokerageDTO) {
        log.debug("Request to save WoCustomsBrokerage : {}", woCustomsBrokerageDTO);
        WoCustomsBrokerage woCustomsBrokerage = woCustomsBrokerageMapper.toEntity(woCustomsBrokerageDTO);
        woCustomsBrokerage = woCustomsBrokerageRepository.save(woCustomsBrokerage);
        return woCustomsBrokerageMapper.toDto(woCustomsBrokerage);
    }

    /**
     * Get all the woCustomsBrokerages.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoCustomsBrokerageDTO> findAll() {
        log.debug("Request to get all WoCustomsBrokerages");
        return woCustomsBrokerageRepository.findAll().stream()
            .map(woCustomsBrokerageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the woCustomsBrokerages where WoWorkOrder is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<WoCustomsBrokerageDTO> findAllWhereWoWorkOrderIsNull() {
        log.debug("Request to get all woCustomsBrokerages where WoWorkOrder is null");
        return StreamSupport
            .stream(woCustomsBrokerageRepository.findAll().spliterator(), false)
            .filter(woCustomsBrokerage -> woCustomsBrokerage.getWoWorkOrder() == null)
            .map(woCustomsBrokerageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one woCustomsBrokerage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoCustomsBrokerageDTO> findOne(Long id) {
        log.debug("Request to get WoCustomsBrokerage : {}", id);
        return woCustomsBrokerageRepository.findById(id)
            .map(woCustomsBrokerageMapper::toDto);
    }

    /**
     * Delete the woCustomsBrokerage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoCustomsBrokerage : {}", id);
        woCustomsBrokerageRepository.deleteById(id);
    }
}
