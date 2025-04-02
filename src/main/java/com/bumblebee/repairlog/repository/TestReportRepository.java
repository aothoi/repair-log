package com.bumblebee.repairlog.repository;

/*
 * @author Aothoi
 * @created 02-Apr-25
 */

import com.bumblebee.repairlog.domain.entity.TestReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestReportRepository extends JpaRepository<TestReport, Long> {
}
