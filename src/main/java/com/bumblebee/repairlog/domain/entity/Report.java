package com.bumblebee.repairlog.domain.entity;

import com.bumblebee.repairlog.domain.dto.ReportDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @author aothoi
 * @since 7/2/2025
 */

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Report extends Auditable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "reportSeq")
    @SequenceGenerator(name = "reportSeq", sequenceName = "report_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "part_id")
    @NotNull
    private Part part;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "engineer_id")
    @NotNull
    private Engineer engineer;

    @Valid
    @OneToMany(mappedBy = "report",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            orphanRemoval = true)
    private List<ReportTooling> reportToolings;

    @Valid
    @OneToMany(mappedBy = "report",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            orphanRemoval = true)
    private List<ReportRepairProcess> repairProcesses;

    private boolean unitDoesNotPowerUp;

    @NotNull
    private LocalDate date;

    @NotNull
    private String serial;

    private boolean hasExternalVisualDamages;
    private String externalVisualDamagesImageRef;

    private boolean hasExternalWaterIngress;
    private String externalWaterIngressImageRef;

    private boolean hasExternalPowerSurge;
    private String externalPowerSurgeImageRef;

    private boolean hasDamagedConnectors;
    private String damagedConnectorsImageRef;

    private boolean hasInternalVisualDamages;
    private String internalVisualDamagesImageRef;

    private boolean hasInternalWaterIngress;
    private String internalWaterIngressImageRef;

    private boolean hasInternalPowerSurge;
    private String internalPowerSurgeImageRef;

    public static Report initialize(ReportDto reportDto) {
        return Report.builder()
                .part(reportDto.getPart())
                .engineer(reportDto.getEngineer())
                .reportToolings(reportDto.getReportToolings())
                .repairProcesses(reportDto.getRepairProcesses())
                .unitDoesNotPowerUp(reportDto.isUnitDoesNotPowerUp())
                .date(reportDto.getDate())
                .serial(reportDto.getSerial())
                .hasExternalVisualDamages(reportDto.isHasExternalVisualDamages())
                .hasExternalWaterIngress(reportDto.isHasExternalWaterIngress())
                .hasExternalPowerSurge(reportDto.isHasExternalPowerSurge())
                .hasDamagedConnectors(reportDto.isHasDamagedConnectors())
                .hasInternalVisualDamages(reportDto.isHasInternalVisualDamages())
                .hasInternalWaterIngress(reportDto.isHasInternalWaterIngress())
                .hasInternalPowerSurge(reportDto.isHasInternalPowerSurge())
                .build();
    }
}
