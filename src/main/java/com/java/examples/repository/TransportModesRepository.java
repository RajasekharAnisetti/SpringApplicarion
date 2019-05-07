package com.java.examples.repository;

import com.java.examples.domain.TransportModes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TransportModes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransportModesRepository extends JpaRepository<TransportModes, Long> {

}
