package com.bumblebee.repairlog.domain.dto;

import com.bumblebee.repairlog.domain.entity.Report;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author aothoi
 * @since 11-Feb-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportListDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String partNumber;
    private String engineerName;
    private LocalDate date;
    private String serial;

    public static ReportListDto initialize(Report report) {
        return ReportListDto.builder()
                .id(report.getId())
                .partNumber(report.getPart().getNumber())
                .engineerName(report.getEngineer().getName())
                .date(report.getDate())
                .serial(report.getSerial())
                .build();
    }
}
