package com.bumblebee.repairlog.domain.dto;

import com.bumblebee.repairlog.domain.entity.Engineer;
import com.bumblebee.repairlog.domain.entity.Part;
import com.bumblebee.repairlog.domain.entity.ReportRepairProcess;
import com.bumblebee.repairlog.domain.entity.ReportTooling;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

    @NotNull
    private Part part;

    @NotNull
    private Engineer engineer;

    @Valid
    private List<ReportTooling> reportToolings;

    @Valid
    private List<ReportRepairProcess> repairProcesses;

    @NotNull
    private LocalDate date;

    @NotNull
    private String serial;

    private boolean unitDoesNotPowerUp;
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
