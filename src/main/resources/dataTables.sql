CREATE TABLE client
(
    client_id  SERIAL PRIMARY KEY,
    first_name VARCHAR(30),
    surname    VARCHAR(30),
    last_name  VARCHAR(30),
    phone      VARCHAR(25),
    email      VARCHAR(150),
    address    VARCHAR(255)
);

CREATE TABLE device
(
    device_id   SERIAL PRIMARY KEY,
    type        VARCHAR(50),
    brand       VARCHAR(50),
    model       VARCHAR(50),
    description VARCHAR(100)
);

CREATE TABLE orders
(
    order_id      SERIAL PRIMARY KEY,
    client_id     INTEGER REFERENCES client (client_id),
    device_id     INTEGER REFERENCES device (device_id),
    serial_number VARCHAR(255),
    problem       VARCHAR(255),
    date_in       DATE,
    date_out      DATE,
    warranty      VARCHAR(25)
);

CREATE TABLE suppliers
(
    supplier_id  SERIAL PRIMARY KEY,
    name_company VARCHAR(50),
    first_name   VARCHAR(25),
    surname      VARCHAR(30),
    last_name    VARCHAR(30),
    phone        VARCHAR(25),
    address      VARCHAR(50),
    email        VARCHAR(60)
);

CREATE TABLE storage
(
    part_id     SERIAL PRIMARY KEY,
    part_name   VARCHAR(50),
    part_code   VARCHAR(100),
    price       NUMERIC(10, 2),
    count       INTEGER,
    supplier_id INTEGER REFERENCES suppliers (supplier_id)
);

CREATE TABLE employ
(
    id_employ  SERIAL PRIMARY KEY,
    first_name VARCHAR(25),
    surname    VARCHAR(30),
    last_name  VARCHAR(30),
    position   VARCHAR(25),
    phone      VARCHAR(25),
    email      VARCHAR(60)
);

CREATE TABLE services
(
    id_service   SERIAL PRIMARY KEY,
    name_service VARCHAR(30),
    description  VARCHAR(255),
    price        NUMERIC(10, 2)
);

CREATE TABLE payments
(
    id_payment     SERIAL PRIMARY KEY,
    order_id       INTEGER REFERENCES orders (order_id),
    amount         NUMERIC(10, 2),
    payment_date   DATE,
    payment_method VARCHAR(30)
);


--Данные для таблиц

INSERT INTO client (first_name, surname, last_name, phone, email, address)
VALUES ('Иван', 'Петров', 'Сергеевич', '+79991234567', 'ivan.petrov@example.com', 'Москва, ул. Ленина, 10'),
       ('Мария', 'Сидорова', 'Алексеевна', '+79997654321', 'm.sidorova@example.com', 'СПб, Невский проспект, 22'),
       ('Андрей', 'Кузнецов', 'Игоревич', '+79005554433', 'a.kuz@example.com', 'Казань, ул. Баумана, 5'),
       ('Екатерина', 'Орлова', 'Вадимовна', '+79112223344', 'katya.orlova@example.com', 'Екатеринбург, ул. Мира, 77'),
       ('Дмитрий', 'Власов', 'Олегович', '+79881231212', 'd.vlasov@example.com', 'Новосибирск, ул. Фрунзе, 12');


INSERT INTO device (type, brand, model, description)
VALUES ('Смартфон', 'Samsung', 'Galaxy S21', 'Не включается'),
       ('Ноутбук', 'Lenovo', 'ThinkPad T480', 'Перегрев'),
       ('Планшет', 'Apple', 'iPad Air', 'Разбит экран'),
       ('ПК', 'HP', 'ProDesk 400', 'Не грузится Windows'),
       ('Смарт-часы', 'Xiaomi', 'Mi Watch', 'Не держит заряд');


INSERT INTO orders (client_id, device_id, serial_number, problem, date_in, date_out, warranty)
VALUES (1, 1, 'SN-S21-001', 'Не включается', '2025-01-10', NULL, 'Нет'),
       (2, 2, 'SN-LT480-332', 'Перегрев', '2025-01-12', '2025-01-15', 'Да'),
       (3, 3, 'SN-IPAD-555', 'Разбит экран', '2025-01-13', NULL, 'Нет'),
       (4, 4, 'SN-HP400-991', 'Проблемы с загрузкой', '2025-01-14', NULL, 'Да'),
       (5, 5, 'SN-XMW-200', 'Быстро разряжается', '2025-01-15', '2025-01-18', 'Нет');


INSERT INTO suppliers (name_company, first_name, surname, last_name, phone, address, email)
VALUES ('ТехСнаб', 'Олег', 'Кириллов', 'Павлович', '+79994561234', 'Москва, Марксистская 12', 'info@techsnab.ru'),
       ('КомплектПлюс', 'Светлана', 'Горлова', 'Ильинична', '+79876541234', 'СПб, Литейный 40', 'sale@komplekt.ru'),
       ('МегаПоставка', 'Ирина', 'Федорова', 'Степановна', '+79002221100', 'Казань, Товарная 3', 'contact@mega.ru'),
       ('СервисДеталь', 'Петр', 'Орлов', 'Семёнович', '+79335556677', 'Новосибирск, Промышленная 8',
        'orlov@service.ru'),
       ('ТехМаркет', 'Алексей', 'Романов', 'Антонович', '+79214445566', 'Екатеринбург, Заводская 14',
        'alex@tmarket.ru');


INSERT INTO storage (part_name, part_code, price, count, supplier_id)
VALUES ('Экран Samsung S21', 'SCR-S21-01', 9500.00, 10, 1),
       ('Кулер Lenovo T480', 'FAN-T480-02', 3200.00, 15, 2),
       ('Стекло iPad Air', 'GL-IPAD-03', 4500.00, 8, 3),
       ('Блок питания HP 400', 'PS-HP400-04', 5200.00, 12, 4),
       ('Аккумулятор Mi Watch', 'BAT-MW-05', 1800.00, 20, 5);


INSERT INTO employ (first_name, surname, last_name, position, phone, email)
VALUES ('Антон', 'Рыбаков', 'Владимирович', 'Мастер', '+79993334455', 'anton.rv@example.com'),
       ('Сергей', 'Миронов', 'Александрович', 'Приёмщик', '+79996667788', 'sergey.mir@example.com'),
       ('Владислав', 'Корнилов', 'Игоревич', 'Менеджер', '+79003332211', 'vlad.kor@example.com'),
       ('Наталья', 'Соколова', 'Петровна', 'Администратор', '+79114445522', 'n.sokolova@example.com'),
       ('Денис', 'Грачев', 'Олегович', 'Инженер', '+79218889900', 'd.grachev@example.com');


INSERT INTO services (name_service, description, price)
VALUES ('Диагностика', 'Полная проверка устройства', 500.00),
       ('Замена экрана', 'Замена дисплейного модуля', 4500.00),
       ('Чистка системы охлаждения', 'Полная очистка от пыли', 1500.00),
       ('Замена аккумулятора', 'Снятие/установка батареи', 2000.00),
       ('Настройка ПО', 'Установка и настройка систем', 1000.00);


INSERT INTO payments (order_id, amount, payment_date, payment_method)
VALUES (1, 500.00, '2025-01-11', 'Наличные'),
       (2, 4500.00, '2025-01-15', 'Карта'),
       (3, 0.00, '2025-01-13', 'Не оплачено'),
       (4, 1500.00, '2025-01-14', 'Карта'),
       (5, 2000.00, '2025-01-18', 'Онлайн');