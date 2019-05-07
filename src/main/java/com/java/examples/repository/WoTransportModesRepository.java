package com.java.examples.repository;

import com.java.examples.domain.WoTransportModes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoTransportModes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoTransportModesRepository extends JpaRepository<WoTransportModes, Long> {

}
