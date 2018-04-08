create database logservice;

create table logentry (
	id BIGINT NOT NULL AUTO_INCREMENT,
	DATE VARCHAR(30) NOT NULL,
	IP VARCHAR(25) NOT NULL,
	REQUEST VARCHAR(500),
    PRIMARY KEY (id)
)