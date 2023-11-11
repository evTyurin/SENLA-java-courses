-- -----------------------------------------------------
-- Filling craftsman
-- -----------------------------------------------------
INSERT INTO electronic_car_service_administrator.craftsman (name, surname) VALUES
('John', 'Constantine'),
('Jim', 'Bo'),
('Bot', 'Cinnic'),
('Clark', 'Kent');

-- -----------------------------------------------------
-- Filling garage_place
-- -----------------------------------------------------
INSERT INTO electronic_car_service_administrator.garage_place (number, space) VALUES
(1, 15.00),
(2, 25.00),
(3, 15.00),
(4, 10.00);

-- -----------------------------------------------------
-- Filling order
-- -----------------------------------------------------
INSERT INTO electronic_car_service_administrator.orders (price, submission_date, start_date, completion_date, order_status_id, garage_place_id) VALUES
(100, '2023-11-01 14:25:00', '2023-11-10 18:00:00', '2023-11-30 10:00:00', 'NEW', 1),
(300, '2023-10-01 17:29:00', '2023-10-15 16:00:00', '2023-10-26 10:00:00', 'COMPLETED', 1),
(500, '2023-10-26 13:56:00', '2023-11-01 14:00:00', '2023-11-05 10:00:00', 'IN_PROGRESS', 2),
(400, '2023-11-05 11:11:00', '2023-11-10 10:00:00', '2023-11-15 12:00:00', 'CANCELED', 1),
(200, '2023-11-05 14:29:06', '2023-11-10 18:00:00', '2023-11-20 10:00:00', 'NEW', 3);

-- -----------------------------------------------------
-- Filling order_craftsman
-- -----------------------------------------------------
INSERT INTO electronic_car_service_administrator.order_craftsman (order_id, craftsman_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 3),
(4, 1),
(4, 4);

-- -----------------------------------------------------
-- Filling order_status
-- -----------------------------------------------------
INSERT INTO electronic_car_service_administrator.order_status (id, order_status) VALUES
(1, 'NEW'),
(2, 'COMPLETED'),
(3, 'IN_PROGRESS'),
(4, 'CANCELED'),
(4, 'DELETED');