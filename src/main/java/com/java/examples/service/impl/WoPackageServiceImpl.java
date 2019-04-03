package com.java.examples.service.impl;

import com.java.examples.service.WoPackageService;
import com.java.examples.domain.WoPackage;
import com.java.examples.repository.WoPackageRepository;
import com.java.examples.service.dto.WoPackageDTO;
import com.java.examples.service.mapper.WoPackageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing WoPackage.
 */
@Service
@Transactional
public class WoPackageServiceImpl implements WoPackageService {

    private final Logger log = LoggerFactory.getLogger(WoPackageServiceImpl.class);

    private final WoPackageRepository woPackageRepository;

    private final WoPackageMapper woPackageMapper;

    public WoPackageServiceImpl(WoPackageRepository woPackageRepository, WoPackageMapper woPackageMapper) {
        this.woPackageRepository = woPackageRepository;
        this.woPackageMapper = woPackageMapper;
    }

    /**
     * Save a woPackage.
     *
     * @param woPackageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WoPackageDTO save(WoPackageDTO woPackageDTO) {
        log.debug("Request to save WoPackage : {}", woPackageDTO);
        WoPackage woPackage = woPackageMapper.toEntity(woPackageDTO);
        woPackage = woPackageRepository.save(woPackage);
        return woPackageMapper.toDto(woPackage);
    }

    /**
     * Get all the woPackages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WoPackageDTO> findAll() {
        log.debug("Request to get all WoPackages");
        return woPackageRepository.findAll().stream()
            .map(woPackageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one woPackage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WoPackageDTO> findOne(Long id) {
        log.debug("Request to get WoPackage : {}", id);
        return woPackageRepository.findById(id)
            .map(woPackageMapper::toDto);
    }

    /**
     * Delete the woPackage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WoPackage : {}", id);
        woPackageRepository.deleteById(id);
    }
}
