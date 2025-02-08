package com.bumblebee.repairlog.repository;

import com.bumblebee.repairlog.domain.entity.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aothoi
 * @since 7/2/2025
 */

public interface EngineerRepository extends JpaRepository<Engineer, Long> {
}
