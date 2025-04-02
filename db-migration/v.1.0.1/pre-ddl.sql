CREATE SEQUENCE test_report_seq START WITH 1;

CREATE TABLE test_report
(
    id                  BIGINT,
    created             TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated             TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    version             INT          NOT NULL DEFAULT 0,
    part_id             BIGINT       NOT NULL,
    serial              VARCHAR(255) NOT NULL,
    received_date       DATE         NOT NULL,
    repair_date         DATE         NOT NULL,
    test_report_pdf_ref VARCHAR(255),

    CONSTRAINT test_report_pk PRIMARY KEY (id),
    CONSTRAINT fk_part FOREIGN KEY (part_id) REFERENCES part (id)
);