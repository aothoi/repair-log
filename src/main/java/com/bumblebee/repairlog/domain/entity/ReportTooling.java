package com.bumblebee.repairlog.domain.entity;

import com.bumblebee.repairlog.domain.dto.ToolingCommentDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @author aothoi
 * @since 8/2/2025
 */

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report_tooling")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportTooling extends Auditable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "reportToolingSeq")
    @SequenceGenerator(name = "reportToolingSeq", sequenceName = "report_tooling_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tooling_id")
    private Tooling tooling;

    @Size(max = 512)
    private String comment;

    public static ReportTooling initialize(ToolingCommentDto toolingComment, Report report) {
        return ReportTooling.builder()
                .report(report)
                .tooling(new Tooling(toolingComment.getToolingId()))
                .comment(toolingComment.getComment())
                .build();
    }
}
