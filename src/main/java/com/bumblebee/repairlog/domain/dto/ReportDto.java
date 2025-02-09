package com.bumblebee.repairlog.domain.dto;

import com.bumblebee.repairlog.domain.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @author aothoi
 * @since 8/2/2025
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Part part;
    private Engineer engineer;

    @Valid
    private List<ReportTooling> reportToolings;

    @Valid
    private List<ReportRepairProcess> repairProcesses;

    private boolean unitDoesNotPowerUp;
    private LocalDate date;
    private String serial;
    private boolean hasExternalVisualDamages;
    private MultipartFile externalVisualDamagesImage;
    private boolean hasExternalWaterIngress;
    private MultipartFile externalWaterIngressImage;
    private boolean hasExternalPowerSurge;
    private MultipartFile externalPowerSurgeImage;
    private boolean hasDamagedConnectors;
    private MultipartFile damagedConnectorsImage;
    private boolean hasInternalVisualDamages;
    private MultipartFile internalVisualDamagesImage;
    private boolean hasInternalWaterIngress;
    private MultipartFile internalWaterIngressImage;
    private boolean hasInternalPowerSurge;
    private MultipartFile internalPowerSurgeImage;
}
