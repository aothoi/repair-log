package com.bumblebee.repairlog.repository;

import com.bumblebee.repairlog.domain.entity.Tooling;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aothoi
 * @since 7/2/2025
 */

public interface ToolingRepository extends JpaRepository<Tooling, Long> {
}
