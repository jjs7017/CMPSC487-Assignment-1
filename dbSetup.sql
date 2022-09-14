use mydb;

DROP TABLE IF EXISTS labLog;

CREATE TABLE IF NOT EXISTS labLog (
    role VARCHAR(100),
    id INT(9),
    startDate VARCHAR(20),
    endDate VARCHAR(20),
    
    PRIMARY KEY (id, startDate)
);

INSERT INTO labLog
(role, id, startDate, endDate)
VALUES
('student',	'359458885',	'01/04/2015 10:24:34',	'01/04/2015 11:24:34'),
('staff',	'274236475',	'02/08/2016 09:35:17',	'02/08/2016 10:35:17'),
('teacher',	'192337128',	'03/12/2016 11:17:48',	'03/12/2016 12:17:48'),
('student',	'161727898',	'04/21/2017 15:03:21',	'04/21/2017 16:03:21'),
('student',	'435350929',	'04/30/2017 17:51:09',	'04/30/2017 18:51:09');
