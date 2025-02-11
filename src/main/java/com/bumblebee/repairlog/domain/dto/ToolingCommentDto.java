package com.bumblebee.repairlog.domain.dto;

import com.bumblebee.repairlog.domain.entity.Tooling;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author aothoi
 * @since 11-Feb-25
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToolingCommentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long toolingId;

    @NotNull
    private String toolingName;

    private boolean isChecked;

    @Size(max = 512)
    private String comment;

    public static ToolingCommentDto initialize(Tooling tooling) {
        return ToolingCommentDto.builder()
                .toolingId(tooling.getId())
                .toolingName(tooling.getName())
                .build();
    }
}
