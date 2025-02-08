INSERT INTO engineer (id, name)
VALUES (nextval('engineer_seq'), 'John Doe'),
       (nextval('engineer_seq'), 'Jane Doe');

INSERT INTO tooling (id, name)
VALUES (nextval('tooling_seq'), 'High-torque electric screwdriver'),
       (nextval('tooling_seq'), 'Soldering Iron'),
       (nextval('tooling_seq'), 'Multimeter'),
       (nextval('tooling_seq'), 'Hot-Air rework station'),
       (nextval('tooling_seq'), 'Solder Wick'),
       (nextval('tooling_seq'), 'Solder Flux'),
       (nextval('tooling_seq'), 'Solder Tin 1mm');

INSERT INTO part (id, number, OEM, description)
VALUES (nextval('part_seq'), '472181A', 'Nokia', 'FSMF - Flexi Multiradio 10 BTS System Module, Outdoor'),
       (nextval('part_seq'), '473440A', 'Nokia',
        'FRGX - Flexi RF Module, 3T3R, 3x80W, 2100MHz, 7/16 DIN Connectors'),
       (nextval('part_seq'), '472924A', 'Nokia',
        'FXED - Flexi RF Module, 6 Pipe, 6T6R, 1800MHz, 6x60W, 7/16 DIN Connectors'),
       (nextval('part_seq'), '473475A', 'Nokia',
        'FHEL - AirScale Remote Radio Head, 120W (2x60W), 2T2R, 1800MHz, B3'),
       (nextval('part_seq'), '472573A', 'Nokia',
        'FXDB - Flexi RF Module, 3TX, 2T2R, 900MHz (TX 925-960MHz, RX 880-915MHz), 3x80W, 7/16 DIN Connectors'),
       (nextval('part_seq'), '472649A', 'Nokia', 'FHDB - Remote Radio Head, 2T2R, 900MHz, 2x60W'),
       (nextval('part_seq'), '086385A', 'Nokia', 'FBBC - Capacity Extension Module'),
       (nextval('part_seq'), '473995A', 'Nokia',
        'AHEGA - Airscale Dual Remote Radio Head, 2T4R, 240W (2x2x80W), 1800/2100MHz, B1/n1 / B3/n3'),
       (nextval('part_seq'), '473825A', 'Nokia', 'FHDI - AirScale Remote Radio Head, 120W (2x60W), 900MHz, B8'),
       (nextval('part_seq'), '472501A', 'Nokia',
        'FXEB - Flexi RF Module, 3T3R, 1800MHz, 3x80W, 7/16 DIN Connectors'),
       (nextval('part_seq'), '087211A', 'Nokia', 'FRGY - Remote Radio Head, 2T2R, 2100MHz, 2x60W'),
       (nextval('part_seq'), '474198A', 'Nokia', 'AREA - Remote Radio Head, 480W (6x80w) 6T6R, 1800MHz, B3'),
       (nextval('part_seq'), '474074A', 'Nokia', 'FYGC - GNSS Receiver Antenna'),
       (nextval('part_seq'), 'CS7136001', 'Nokia', 'FPFH - Flexi Power Distribution Module'),
       (nextval('part_seq'), '473980A', 'Nokia', 'FMNG - AirScale Book Mount Kit'),
       (nextval('part_seq'), '472084A', 'Nokia', 'FXEA - Flexi RF Module, 3T3R, 1800MHz, 3x70W'),
       (nextval('part_seq'), '474254A', 'Nokia',
        'AHGA - AirScale Dual Remote Radio Head, 2T4R, 120W (2X60W), 2100MHz, B1'),
       (nextval('part_seq'), '473807A', 'Nokia',
        'AHED - AirScale Dual Remote Radio Head, 2T4R, 160W (2x80W), 1800MHz, B3'),
       (nextval('part_seq'), '475122A', 'Nokia', 'ARDB - AirScale RF Module, 480W (6x80w) 6T6R, 900MHz, B8'),
       (nextval('part_seq'), '472649A', 'Nokia', 'FHDB - Remote Radio Head, 2T2R, 900MHz, 2x60W'),
       (nextval('part_seq'), '473234A', 'Nokia',
        'FWGM - Flexi Zone Pico Indoor BTS, 2x250mW, B2 FDD, 2100MHz, Integrated Antenna');
