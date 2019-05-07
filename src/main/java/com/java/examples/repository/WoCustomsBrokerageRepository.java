package com.java.examples.repository;

import com.java.examples.domain.WoCustomsBrokerage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoCustomsBrokerage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoCustomsBrokerageRepository extends JpaRepository<WoCustomsBrokerage, Long> {

}
