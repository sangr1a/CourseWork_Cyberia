USE cyberiadb;

-- Inserts for ADMIN
INSERT INTO users (email, phone_number, name, legal_address, active, password)
VALUES ('admin@admin.ru', '+1 234 567 89 01', 'admin', null, true, '$2a$10$5sMyWpBPsjg1wHzxYV.Kb.IpLvCqfKrSjP5vtMyOVQUOq7IVd2kPa');

INSERT INTO user_role (user_id, roles) VALUES (1, 'ROLE_ADMIN');

-- Inserts for SELLER
INSERT INTO users (email, phone_number, name, legal_address, active, password)
VALUES ('seller@seller.ru', '+7 456 789 01 23', 'seller', 'Москва, Картмазовские пруды, д. 2, к.6, кв. 93', true, '$2a$10$5sMyWpBPsjg1wHzxYV.Kb.IpLvCqfKrSjP5vtMyOVQUOq7IVd2kPa');

INSERT INTO user_role (user_id, roles) VALUES (2, 'ROLE_ORG');

-- Inserts for BUYER
INSERT INTO users (email, phone_number, name, legal_address, active, password)
VALUES ('buyer@buyer.ru', '+7 985 172 25 05', 'buyer', null, true, '$2a$10$5sMyWpBPsjg1wHzxYV.Kb.IpLvCqfKrSjP5vtMyOVQUOq7IVd2kPa');