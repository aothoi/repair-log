package com.bumblebee.repairlog.service;

import com.bumblebee.repairlog.domain.dto.ReportDto;
import com.bumblebee.repairlog.domain.dto.TestReportDto;
import com.bumblebee.repairlog.domain.entity.Report;
import com.bumblebee.repairlog.domain.entity.TestReport;
import com.bumblebee.repairlog.repository.TestReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.bumblebee.repairlog.util.Constant.EXTERNAL_VISUAL_DAMAGE;

/**
 * @author aothoi
 * @since 02-Apr-25
 */
@Service
@RequiredArgsConstructor
public class TestReportService {

    private final TestReportRepository testReportRepository;

    private final FileUploadService fileUploadService;

    @Transactional
    public void save(TestReportDto testReportDto) {
        TestReport report = TestReport.initialize(testReportDto);
        String fileName = "test_report - " + report.getPart().getOem() + report.getPart().getDescription().split("-")[0]
                + "-" + report.getSerial() + "-" + report.getRepairDate();

        String fileNameWithExt = fileUploadService.uploadFile(testReportDto.getTestReportPdf(), fileName, true);
        report.setTestReportPdfRef(fileNameWithExt);

        testReportRepository.saveAndFlush(report);
    }

    public TestReport getById(Long id) {
        return testReportRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
