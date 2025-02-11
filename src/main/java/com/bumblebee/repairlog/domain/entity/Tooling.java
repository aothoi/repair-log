package com.bumblebee.repairlog.domain.entity;

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
 * @since 7/2/2025
 */

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tooling")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tooling extends Auditable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "toolingSeq")
    @SequenceGenerator(name = "toolingSeq", sequenceName = "tooling_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    public Tooling(Long id) {
        this.id = id;
    }
}
