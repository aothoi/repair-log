package com.bumblebee.repairlog.domain.dto;

import com.bumblebee.repairlog.annotation.NotEmptyFile;
import com.bumblebee.repairlog.domain.entity.Part;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author aothoi
 * @since 02-Apr-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestReportDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private Part part;

    @NotNull
    private LocalDate receivedDate;

    @NotNull
    private LocalDate repairDate;

    @NotNull
    private String serial;

    @NotEmptyFile
    private MultipartFile testReportPdf;
}
