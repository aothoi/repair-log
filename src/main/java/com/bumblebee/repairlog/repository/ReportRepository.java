package com.bumblebee.repairlog.repository;

import com.bumblebee.repairlog.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aothoi
 * @since 09/02/2025
 */

public interface ReportRepository extends JpaRepository<Report, Long> {
}
