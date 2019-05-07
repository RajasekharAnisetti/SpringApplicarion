package com.java.examples.service.impl;

import com.java.examples.service.TransportModesService;
import com.java.examples.domain.TransportModes;
import com.java.examples.repository.TransportModesRepository;
import com.java.examples.service.dto.TransportModesDTO;
import com.java.examples.service.mapper.TransportModesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TransportModes}.
 */
@Service
@Transactional
public class TransportModesServiceImpl implements TransportModesService {

    private final Logger log = LoggerFactory.getLogger(TransportModesServiceImpl.class);

    private final TransportModesRepository transportModesRepository;

    private final TransportModesMapper transportModesMapper;

    public TransportModesServiceImpl(TransportModesRepository transportModesRepository, TransportModesMapper transportModesMapper) {
        this.transportModesRepository = transportModesRepository;
        this.transportModesMapper = transportModesMapper;
    }

    /**
     * Save a transportModes.
     *
     * @param transportModesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TransportModesDTO save(TransportModesDTO transportModesDTO) {
        log.debug("Request to save TransportModes : {}", transportModesDTO);
        TransportModes transportModes = transportModesMapper.toEntity(transportModesDTO);
        transportModes = transportModesRepository.save(transportModes);
        return transportModesMapper.toDto(transportModes);
    }

    /**
     * Get all the transportModes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransportModesDTO> findAll() {
        log.debug("Request to get all TransportModes");
        return transportModesRepository.findAll().stream()
            .map(transportModesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one transportModes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransportModesDTO> findOne(Long id) {
        log.debug("Request to get TransportModes : {}", id);
        return transportModesRepository.findById(id)
            .map(transportModesMapper::toDto);
    }

    /**
     * Delete the transportModes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransportModes : {}", id);
        transportModesRepository.deleteById(id);
    }
}
