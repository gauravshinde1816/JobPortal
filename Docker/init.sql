-- create database jobportal;

use jobportal;


CREATE TABLE users (
    id bigint auto_increment PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);


ALTER TABLE users ADD bio varchar(255) , ADD current_company varchar(255);



Create table job (
	job_id bigint auto_increment primary key,
    job_title varchar(255) not null,
    job_description varchar(255) not null,
    job_category varchar(255) not null, 
    company_details varchar(255), 
    hiring_manager bigint, 
    foreign key(hiring_manager) references users(id) ON delete cascade
);


Create table education (
   id bigint auto_increment primary key not null,
   institute_name varchar(255) ,
   description varchar(255),
   user_id bigint not null, 
   start_date date,
   end_date date , 
   foreign key(user_id) references users(id)  ON delete cascade
);



Create table experience (
   id bigint auto_increment primary key not null,
   company_name varchar(255) ,
   description varchar(255),
   user_id bigint not null, 
   start_date date,
   end_date date , 
   skills varchar(255), 
   foreign key(user_id) references users(id)  on delete cascade
);


Create table job_application (
	application_id bigint auto_increment primary key,
    applicant bigint not null,
    job_details bigint, 
    status boolean not null,
    foreign key(job_details) references job(job_id) on delete cascade,
    foreign key(applicant) references users(id)  on delete cascade
);