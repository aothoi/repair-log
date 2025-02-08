package com.bumblebee.repairlog.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
@Table(name = "report_repair_process")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportRepairProcess extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportRepairProcessSeq")
    @SequenceGenerator(name = "reportRepairProcessSeq", sequenceName = "report_repair_process_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @NotNull
    @Size(max = 255)
    private String action;

    @NotNull
    @Size(max = 255)
    private String result;

    @Size(max = 255)
    private String imageRef;
}
