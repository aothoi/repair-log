CREATE SEQUENCE part_seq START WITH 1;
CREATE SEQUENCE engineer_seq START WITH 1;
CREATE SEQUENCE tooling_seq START WITH 1;
CREATE SEQUENCE report_seq START WITH 1;
CREATE SEQUENCE report_repair_process_seq START WITH 1;
CREATE SEQUENCE report_tooling_seq START WITH 1;

CREATE TABLE part
(
    id          BIGINT,
    created     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    version     INT          NOT NULL DEFAULT 0,
    number      VARCHAR(32)  NOT NULL,
    OEM         VARCHAR(32)  NOT NULL,
    description VARCHAR(512) NOT NULL,

    CONSTRAINT part_pk PRIMARY KEY (id)
);

CREATE TABLE engineer
(
    id      BIGINT,
    created TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    version INT          NOT NULL DEFAULT 0,
    name    VARCHAR(255) NOT NULL,

    CONSTRAINT engineer_pk PRIMARY KEY (id)
);

CREATE TABLE tooling
(
    id      BIGINT,
    created TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    version INT          NOT NULL DEFAULT 0,
    name    VARCHAR(255) NOT NULL,

    CONSTRAINT tooling_pk PRIMARY KEY (id)
);

CREATE TABLE report
(
    id                                BIGINT,
    created                           TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated                           TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    version                           INT          NOT NULL DEFAULT 0,
    part_id                           BIGINT       NOT NULL,
    engineer_id                       BIGINT       NOT NULL,
    unit_does_not_power_up            BOOLEAN,
    date                              DATE         NOT NULL,
    serial                            VARCHAR(255) NOT NULL,
    has_external_visual_damages       BOOLEAN,
    external_visual_damages_image_ref VARCHAR(255),
    has_external_water_ingress        BOOLEAN,
    external_water_ingress_image_ref  VARCHAR(255),
    has_external_power_surge          BOOLEAN,
    external_power_surge_image_ref    VARCHAR(255),
    has_damaged_connectors            BOOLEAN,
    damaged_connectors_image_ref      VARCHAR(255),
    has_internal_visual_damages       BOOLEAN,
    internal_visual_damages_image_ref VARCHAR(255),
    has_internal_water_ingress        BOOLEAN,
    internal_water_ingress_image_ref  VARCHAR(255),
    has_internal_power_surge          BOOLEAN,
    internal_power_surge_image_ref    VARCHAR(255),

    CONSTRAINT pk_report PRIMARY KEY (id),
    CONSTRAINT fk_part FOREIGN KEY (part_id) REFERENCES part (id),
    CONSTRAINT fk_engineer FOREIGN KEY (engineer_id) REFERENCES engineer (id)
);

CREATE TABLE report_repair_process
(
    id        BIGINT,
    created   TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated   TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    version   INT          NOT NULL DEFAULT 0,
    report_id BIGINT       NOT NULL,
    action    VARCHAR(255) NOT NULL,
    result    VARCHAR(255) NOT NULL,
    image_ref VARCHAR(255),

    CONSTRAINT pk_report_repair_process PRIMARY KEY (id),
    CONSTRAINT fk_report_report_repair_process FOREIGN KEY (report_id) REFERENCES report (id)
);

CREATE TABLE report_tooling
(
    id         BIGINT,
    report_id  BIGINT NOT NULL,
    tooling_id BIGINT NOT NULL,
    comment    VARCHAR(512),

    CONSTRAINT pk_report_tooling PRIMARY KEY (id),
    CONSTRAINT fk_report FOREIGN KEY (report_id) REFERENCES report (id),
    CONSTRAINT fk_tooling FOREIGN KEY (tooling_id) REFERENCES tooling (id)
);
