package com.bumblebee.repairlog.domain.entity;

import com.bumblebee.repairlog.domain.dto.ReportDto;
import com.bumblebee.repairlog.domain.dto.TestReportDto;
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
 * @since 02-Apr-25
 */
@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "test_report")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestReport extends Auditable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "testReportSeq")
    @SequenceGenerator(name = "testReportSeq", sequenceName = "test_report_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "part_id")
    @NotNull
    private Part part;

    @NotNull
    @Column(name = "received_date")
    private LocalDate receivedDate;

    @NotNull
    @Column(name = "repair_date")
    private LocalDate repairDate;

    @NotNull
    private String serial;

    @NotNull
    @Column(name = "test_report_pdf_ref")
    private String testReportPdfRef;

    public static TestReport initialize(TestReportDto testReportDto) {
        return TestReport.builder()
                .part(testReportDto.getPart())
                .receivedDate(testReportDto.getReceivedDate())
                .repairDate(testReportDto.getRepairDate())
                .serial(testReportDto.getSerial())
                .build();
    }
}
