package com.java.examples.repository;

import com.java.examples.domain.WoPackage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoPackage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoPackageRepository extends JpaRepository<WoPackage, Long> {

}
