package com.bumblebee.repairlog.service;

import com.bumblebee.repairlog.domain.dto.ReportDto;
import com.bumblebee.repairlog.domain.entity.Report;
import com.bumblebee.repairlog.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bumblebee.repairlog.util.Constant.*;
import static java.util.Objects.nonNull;

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
        handleImageUpload(reportDto, report, fileBaseName);

//        reportRepository.saveAndFlush(report);
    }

    private void handleImageUpload(ReportDto reportDto, Report report, String fileBaseName) {
        if (!reportDto.getExternalVisualDamagesImage().isEmpty()) {
            imageUploadService.uploadImage(reportDto.getExternalVisualDamagesImage(), fileBaseName+EXTERNAL_VISUAL_DAMAGE);
            report.setExternalVisualDamagesImageRef(fileBaseName+EXTERNAL_VISUAL_DAMAGE);
        }

        if (!reportDto.getExternalWaterIngressImage().isEmpty()) {
            imageUploadService.uploadImage(reportDto.getExternalWaterIngressImage(), fileBaseName+EXTERNAL_WATER_INGRESS);
            report.setExternalWaterIngressImageRef(fileBaseName+EXTERNAL_WATER_INGRESS);
        }

        if (!reportDto.getExternalPowerSurgeImage().isEmpty()) {
            imageUploadService.uploadImage(reportDto.getExternalPowerSurgeImage(), fileBaseName+EXTERNAL_POWER_SURGE);
            report.setExternalPowerSurgeImageRef(fileBaseName+EXTERNAL_POWER_SURGE);
        }

        if (!reportDto.getDamagedConnectorsImage().isEmpty()) {
            imageUploadService.uploadImage(reportDto.getDamagedConnectorsImage(), fileBaseName+DAMAGED_CONNECTOR);
            report.setDamagedConnectorsImageRef(fileBaseName+DAMAGED_CONNECTOR);
        }

        if (!reportDto.getInternalPowerSurgeImage().isEmpty()) {
            imageUploadService.uploadImage(reportDto.getInternalPowerSurgeImage(), fileBaseName+INTERNAL_POWER_SURGE);
            report.setInternalPowerSurgeImageRef(fileBaseName+INTERNAL_POWER_SURGE);
        }

        if (!reportDto.getInternalVisualDamagesImage().isEmpty()) {
            imageUploadService.uploadImage(reportDto.getInternalVisualDamagesImage(), fileBaseName+INTERNAL_VISUAL_DAMAGE);
            report.setInternalVisualDamagesImageRef(fileBaseName+INTERNAL_VISUAL_DAMAGE);
        }

        if (!reportDto.getInternalWaterIngressImage().isEmpty()) {
            imageUploadService.uploadImage(reportDto.getInternalWaterIngressImage(), fileBaseName+INTERNAL_WATER_INGRESS);
            report.setInternalWaterIngressImageRef(fileBaseName+INTERNAL_WATER_INGRESS);
        }
    }
}
