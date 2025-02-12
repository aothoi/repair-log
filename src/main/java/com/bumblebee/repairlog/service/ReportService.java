package com.bumblebee.repairlog.service;

import com.bumblebee.repairlog.domain.dto.ReportDto;
import com.bumblebee.repairlog.domain.dto.ReportRepairProcessDto;
import com.bumblebee.repairlog.domain.dto.ToolingCommentDto;
import com.bumblebee.repairlog.domain.entity.Report;
import com.bumblebee.repairlog.domain.entity.ReportRepairProcess;
import com.bumblebee.repairlog.domain.entity.ReportTooling;
import com.bumblebee.repairlog.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.bumblebee.repairlog.util.Constant.*;
import static java.util.Objects.isNull;

/**
 * @author aothoi
 * @since 7/2/2025
 */
@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    private final ImageUploadService imageUploadService;

    @Transactional
    public void save(ReportDto reportDto) {
        Report report = Report.initialize(reportDto);
        String fileBaseName = report.getPart().getNumber() + "-" + report.getSerial() + "-";

        report.setReportToolings(populateTooling(reportDto.getToolingComments(), report));
        report.setRepairProcesses(populateRepairProcess(reportDto.getRepairProcesses(), report, fileBaseName));

        handleImageUpload(reportDto, report, fileBaseName);

        reportRepository.saveAndFlush(report);
    }

    public Report getById(Long id) {
        return reportRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    private List<ReportRepairProcess> populateRepairProcess(List<ReportRepairProcessDto> reportRepairProcessList,
                                                            Report report,
                                                            String fileBaseName) {

        if (isNull(reportRepairProcessList)) {
            return null;
        }

        List<ReportRepairProcess> repairProcesses = new ArrayList<>();
        int count = 1;

        for (ReportRepairProcessDto repairProcessDto : reportRepairProcessList) {
            String fileName = fileBaseName + count + "-" + REPAIR_PROCESS;
            ReportRepairProcess reportRepairProcess = new ReportRepairProcess();
            reportRepairProcess.setReport(report);
            reportRepairProcess.setAction(repairProcessDto.getAction());
            reportRepairProcess.setResult(repairProcessDto.getResult());

            if (!repairProcessDto.getImage().isEmpty()) {
                fileName = imageUploadService.uploadImage(repairProcessDto.getImage(), fileName);
                reportRepairProcess.setImageRef(fileName);
            }

            repairProcesses.add(reportRepairProcess);
        }

        return repairProcesses;
    }

    private List<ReportTooling> populateTooling(List<ToolingCommentDto> toolingCommentList, Report report) {
        return toolingCommentList.stream()
                .filter(ToolingCommentDto::isChecked)
                .map(toolingComment -> ReportTooling.initialize(toolingComment, report))
                .collect(Collectors.toList());
    }

    private void handleImageUpload(ReportDto reportDto, Report report, String fileBaseName) {
        if (!reportDto.getExternalVisualDamagesImage().isEmpty()) {
            String fileName = imageUploadService.uploadImage(reportDto.getExternalVisualDamagesImage(), fileBaseName + EXTERNAL_VISUAL_DAMAGE);
            report.setExternalVisualDamagesImageRef(fileName);
        }

        if (!reportDto.getExternalWaterIngressImage().isEmpty()) {
            String fileName = imageUploadService.uploadImage(reportDto.getExternalWaterIngressImage(), fileBaseName + EXTERNAL_WATER_INGRESS);
            report.setExternalWaterIngressImageRef(fileName);
        }

        if (!reportDto.getExternalPowerSurgeImage().isEmpty()) {
            String fileName = imageUploadService.uploadImage(reportDto.getExternalPowerSurgeImage(), fileBaseName + EXTERNAL_POWER_SURGE);
            report.setExternalPowerSurgeImageRef(fileName);
        }

        if (!reportDto.getDamagedConnectorsImage().isEmpty()) {
            String fileName = imageUploadService.uploadImage(reportDto.getDamagedConnectorsImage(), fileBaseName + DAMAGED_CONNECTOR);
            report.setDamagedConnectorsImageRef(fileName);
        }

        if (!reportDto.getInternalPowerSurgeImage().isEmpty()) {
            String fileName = imageUploadService.uploadImage(reportDto.getInternalPowerSurgeImage(), fileBaseName + INTERNAL_POWER_SURGE);
            report.setInternalPowerSurgeImageRef(fileName);
        }

        if (!reportDto.getInternalVisualDamagesImage().isEmpty()) {
            String fileName = imageUploadService.uploadImage(reportDto.getInternalVisualDamagesImage(), fileBaseName + INTERNAL_VISUAL_DAMAGE);
            report.setInternalVisualDamagesImageRef(fileName);
        }

        if (!reportDto.getInternalWaterIngressImage().isEmpty()) {
            String fileName = imageUploadService.uploadImage(reportDto.getInternalWaterIngressImage(), fileBaseName + INTERNAL_WATER_INGRESS);
            report.setInternalWaterIngressImageRef(fileName);
        }
    }
}
