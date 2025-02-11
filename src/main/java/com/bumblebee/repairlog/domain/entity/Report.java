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

    @Column(name = "unit_does_not_power_up")
    private boolean unitDoesNotPowerUp;

    @NotNull
    private LocalDate date;

    @NotNull
    private String serial;

    @Column(name = "has_external_visual_damages")
    private boolean hasExternalVisualDamages;

    @Column(name = "external_visual_damages_image_ref")
    private String externalVisualDamagesImageRef;

    @Column(name = "has_external_water_ingress")
    private boolean hasExternalWaterIngress;

    @Column(name = "external_water_ingress_image_ref")
    private String externalWaterIngressImageRef;

    @Column(name = "has_external_power_surge")
    private boolean hasExternalPowerSurge;

    @Column(name = "external_power_surge_image_ref")
    private String externalPowerSurgeImageRef;

    @Column(name = "has_damaged_connectors")
    private boolean hasDamagedConnectors;

    @Column(name = "damaged_connectors_image_ref")
    private String damagedConnectorsImageRef;

    @Column(name = "has_internal_visual_damages")
    private boolean hasInternalVisualDamages;

    @Column(name = "internal_visual_damages_image_ref")
    private String internalVisualDamagesImageRef;

    @Column(name = "has_internal_water_ingress")
    private boolean hasInternalWaterIngress;

    @Column(name = "internal_water_ingress_image_ref")
    private String internalWaterIngressImageRef;

    @Column(name = "has_internal_power_surge")
    private boolean hasInternalPowerSurge;

    @Column(name = "internal_power_surge_image_ref")
    private String internalPowerSurgeImageRef;

    public static Report initialize(ReportDto reportDto) {
        return Report.builder()
                .part(reportDto.getPart())
                .engineer(reportDto.getEngineer())
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
