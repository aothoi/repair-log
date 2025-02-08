package com.bumblebee.repairlog.repository;

import com.bumblebee.repairlog.domain.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aothoi
 * @since 7/2/2025
 */

public interface PartRepository extends JpaRepository<Part, Long> {
}
