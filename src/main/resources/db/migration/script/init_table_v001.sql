
CREATE TABLE t_role (
	id serial,
	name_role VARCHAR(30) NOT NULL,
	code_role VARCHAR(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_role ADD CONSTRAINT pk_role PRIMARY KEY (id);

CREATE TABLE t_file (
	id serial,
	ext VARCHAR(5) NOT NULL,
	files text NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_file ADD CONSTRAINT pk_file PRIMARY KEY (id);

CREATE TABLE t_profile (
	id serial,
	full_name VARCHAR(30) NOT NULL,
	phone VARCHAR(15) NOT NULL,
	photo_id int NOT NULL, 
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_profile ADD CONSTRAINT pk_profile PRIMARY KEY (id);
ALTER TABLE t_profile ADD CONSTRAINT fk_profile_photo FOREIGN KEY (photo_id) REFERENCES t_file (id);

CREATE TABLE t_company (
	id serial,
	name_company VARCHAR(30) NOT NULL,
	company_address text NOT NULL,
	company_code VARCHAR(10) NOT NULL,
	photo_id int,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_company ADD CONSTRAINT pk_company PRIMARY KEY (id);
ALTER TABLE t_company ADD CONSTRAINT fk_company_photo FOREIGN KEY (photo_id) REFERENCES t_file (id);

CREATE TABLE t_users (
	id serial,
	role_id int NOT NULL,
	profile_id int NOT NULL,
	company_id int NOT NULL,
	email VARCHAR(30) NOT NULL,
	password_user text NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_users ADD CONSTRAINT pk_users PRIMARY KEY (id);
ALTER TABLE t_users ADD CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES t_role (id);
ALTER TABLE t_users ADD CONSTRAINT fk_users_profile FOREIGN KEY (profile_id) REFERENCES t_profile (id);
ALTER TABLE t_users ADD CONSTRAINT fk_users_company FOREIGN KEY (company_id) REFERENCES t_company (id);

CREATE TABLE t_priority (
	id serial,
	code_priority VARCHAR(5) NOT NULL,
	name_priority VARCHAR(25) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_priority ADD CONSTRAINT pk_priority PRIMARY KEY (id);

CREATE TABLE t_status(
	id serial,
	status_code VARCHAR(5),
	status_name VARCHAR(25),
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_status ADD CONSTRAINT pk_status PRIMARY KEY (id);

CREATE TABLE t_product(
	id serial,
	product_code VARCHAR (5),
	product_name VARCHAR (50),
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_product ADD CONSTRAINT pk_product PRIMARY KEY (id);

CREATE TABLE t_ticket(
	id serial,
	user_id int NOT NULL,
	priority_id int NOT NULL,
	ticket_status_id int NOT NULL,
	product_id int NOT NULL,
	ticket_code VARCHAR(10) NOT NULL,
	ticket_title VARCHAR(30) NOT NULL,
	ticket_body text NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_ticket ADD CONSTRAINT pk_ticket PRIMARY KEY (id);
ALTER TABLE t_ticket ADD CONSTRAINT fk_ticket_user FOREIGN KEY (user_id) REFERENCES t_users (id);
ALTER TABLE t_ticket ADD CONSTRAINT fk_ticket_priority FOREIGN KEY (priority_id) REFERENCES t_priority (id);
ALTER TABLE t_ticket ADD CONSTRAINT fk_ticket_status FOREIGN KEY (ticket_status_id) REFERENCES t_status (id);
ALTER TABLE t_ticket ADD CONSTRAINT fk_ticket_product FOREIGN KEY (product_id) REFERENCES t_product (id);

CREATE TABLE t_comment(
	id serial,
	user_id int NOT NULL,
	ticket_id int NOT NULL,
	message VARCHAR(225),
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_comment ADD CONSTRAINT pk_coment PRIMARY KEY (id);
ALTER TABLE t_comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES t_users (id);
ALTER TABLE t_comment ADD CONSTRAINT fk_comment_ticket FOREIGN KEY (ticket_id) REFERENCES t_ticket (id);

CREATE TABLE t_comment_attach(
	id serial,
	comment_id int NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_comment_attach ADD CONSTRAINT pk_coment_attach PRIMARY KEY (id);
ALTER TABLE t_comment_attach ADD CONSTRAINT fk_comment_attach_comment FOREIGN KEY (comment_id) REFERENCES t_comment (id);
ALTER TABLE t_comment_attach ADD CONSTRAINT fk_comment_attach_file FOREIGN KEY (file_id) REFERENCES t_file (id);

CREATE TABLE t_file_ticket(
	id serial,
	ticket_id int NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_file_ticket ADD CONSTRAINT pk_file_ticket PRIMARY KEY (id);
ALTER TABLE t_file_ticket ADD CONSTRAINT fk_file_ticket_ticket FOREIGN KEY (ticket_id) REFERENCES t_ticket (id);
ALTER TABLE t_file_ticket ADD CONSTRAINT fk_file_ticket_file FOREIGN KEY (file_id) REFERENCES t_file (id);

CREATE TABLE t_products_cust(
	id serial,
	product_id int NOT NULL,
	customer_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_products_cust ADD CONSTRAINT pk_product_cust PRIMARY KEY (id);
ALTER TABLE t_products_cust ADD CONSTRAINT fk_products_cust_product FOREIGN KEY (product_id) REFERENCES t_product (id);
ALTER TABLE t_products_cust ADD CONSTRAINT fk_products_cust_customer FOREIGN KEY (customer_id) REFERENCES t_users (id);

CREATE TABLE t_ticket_developer(
	id serial,
	ticket_id int,
	developer_id int,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_ticket_developer ADD CONSTRAINT pk_ticket_dev PRIMARY KEY (id);
ALTER TABLE t_ticket_developer ADD CONSTRAINT fk_dev_ticket FOREIGN KEY (ticket_id) REFERENCES t_ticket (id);
ALTER TABLE t_ticket_developer ADD CONSTRAINT fk_ticket_developer FOREIGN KEY (developer_id) REFERENCES t_users (id);

CREATE TABLE t_pic_customer(
	id serial,
	pic_id int,
	customer_id int,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active bool NOT NULL,
	ver int NOT NULL
);
ALTER TABLE t_pic_customer ADD CONSTRAINT pk_pic_cust PRIMARY KEY (id);
ALTER TABLE t_pic_customer ADD CONSTRAINT fk_pic_customer FOREIGN KEY (pic_id) REFERENCES t_users (id);
ALTER TABLE t_pic_customer ADD CONSTRAINT fk_customer_pic FOREIGN KEY (customer_id) REFERENCES t_users (id);
