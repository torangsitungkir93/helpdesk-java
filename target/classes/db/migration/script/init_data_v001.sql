
INSERT INTO t_status (status_code, status_name, created_by, created_at, is_active, ver)
VALUES 
  ('OPEN', 'Open', 1, NOW(), true, 1),
  ('PEAG', 'Pending Agent', 1, NOW(), true, 1),
  ('ONPR', 'On Progress', 1, NOW(), true, 1),
  ('PNCU', 'Pending Customer', 1, NOW(), true, 1),
  ('CLSD', 'Closed', 1, NOW(), true, 1),
  ('ROPN', 'Re-Open', 1, NOW(), true, 1);
 
INSERT INTO t_priority (code_priority, name_priority, created_by, created_at, is_active, ver)
VALUES ('HIH', 'High Priority', 1, NOW(), true, 1),
       ('MED', 'Medium Priority', 1, NOW(), true, 1),
       ('LOW', 'Low Priority', 1, NOW(), true, 1);

INSERT INTO t_role (name_role, code_role, created_by, created_at, updated_by, updated_at, is_active, ver)
VALUES
  ('Super Admin', 'ADM', 1,now(), NULL, NULL, true, 1),
  ('PIC', 'PIC', 1, now(), NULL, NULL, true, 1),
  ('Developer', 'DEV', 1,now(), NULL, NULL, true, 1),
  ('Customer', 'CST', 1,now(), NULL, NULL, true, 1);

INSERT INTO t_file (ext, files, created_by, created_at, updated_by, updated_at, is_active, ver)
VALUES
  ('jpg', 'image1.jpg', 1, now(), NULL, NULL, true, 1),
  ('png', 'image2.png', 1, now(), NULL, NULL, true, 1),
  ('pdf', 'document.pdf', 1, now(), NULL, NULL, true, 1),
  ('docx', 'document2.docx', 1, now(), NULL, NULL, true, 1),
  ('xlsx', 'data.xlsx', 1, now(), NULL, NULL, true, 1);
 
INSERT INTO t_company (name_company,company_address,company_code,photo_id,created_by, created_at, is_active, ver)
VALUES 
  ('Lawencon','Jln Kokas no 77','GHAJ2',1,1,'2023-06-26 10:00:00', true, 1),
  ('Indofood','Jln Dirgantara no 77','AHJSG',1,1, '2023-06-26 10:00:00', true, 1),
  ('Samsung','Jln Subur Dalam no 87','YAS2P',1,1,'2023-06-26 10:00:00', true, 1),
  ('Lenovo','Jln Casabalanca Raya no 11','JAS62',1,1,'2023-06-26 10:00:00', true, 1),
  ('Jago','Jln Alam Sutra no 26','DASQ2',1,1,'2023-06-26 10:00:00', true, 1);

INSERT INTO t_profile (full_name, phone, photo_id, created_by, created_at, updated_by, updated_at, is_active, ver)
VALUES
  ('John Doe', '123456789', 1, 1, now(), NULL, NULL, true, 1),
  ('Jane Smith', '987654321', 2, 1, now(), NULL, NULL, true, 1),
  ('Mike Johnson', '555555555', 3, 1, now(), NULL, NULL, true, 1),
  ('Sarah Williams', '111111111', 4, 1, now(), NULL, NULL, true, 1),
  ('Robert Brown', '999999999', 5, 1, now(), NULL, NULL, true, 1),
  ('Khalil Attala', '123456789', 1, 1, now(), NULL, NULL, true, 1),
  ('Julian Wicaksono', '987654321', 2, 1, now(), NULL, NULL, true, 1);
 
INSERT INTO t_file (ext, files, created_by, created_at, updated_by, updated_at, is_active, ver)
VALUES
  ('jpg', 'image1.jpg', 1, now(), NULL, NULL, true, 1),
  ('png', 'image2.png', 1, now(), NULL, NULL, true, 1),
  ('pdf', 'document.pdf', 1, now(), NULL, NULL, true, 1),
  ('docx', 'document2.docx', 1, now(), NULL, NULL, true, 1),
  ('xlsx', 'data.xlsx', 1, now(), NULL, NULL, true, 1);

INSERT INTO t_users (role_id, profile_id, company_id, email, password_user, created_by, created_at, updated_by, updated_at, is_active, ver)
VALUES
  (1, 1, 1, 'admin@gmail.com', '123', 1, now(), NULL, NULL, true, 1),
  (2, 2, 2, 'janesmith@gmail.com', '123', 1, now(), NULL, NULL, true, 1),
  (3, 3, 3, 'mike@gmail.com', '123', 1, now(), NULL, NULL, true, 1),
  (4, 4, 4, 'sarah@gmail.com', '123', 1, now(), NULL, NULL, true, 1),
  (1, 5, 5, 'robert@gmail.com', '123', 1, now(), NULL, NULL, true, 1),
  (4, 6, 1, 'khalil@gmail.com', '123', 1, now(), NULL, NULL, true, 1),
  (2, 7, 2, 'julian@gmail.com', '123', 1, now(), NULL, NULL, true, 1);
 
INSERT INTO t_product (product_code,product_name ,created_by, created_at, updated_by, updated_at, is_active, ver)
VALUES
  ('PR01', 'Indihome',1, now(), NULL, NULL, true, 1),
  ('PR02', 'Smartphone Samsung',1, now(), NULL, NULL, true, 1),
  ('PR03', 'Laptop Lenovo',1, now(), NULL, NULL, true, 1),
  ('PR04', 'Smart TV Samsung',1, now(), NULL, NULL, true, 1),
  ('PR05', 'Optic Fiber',1, now(), NULL, NULL, true, 1);