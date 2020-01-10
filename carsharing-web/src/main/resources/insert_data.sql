INSERT INTO carsharing.addresses (idAddress, country, city, street, house_number, flat_number, deleted)
VALUES (1, 'adminland', 'admincity', 'adminstreet', 1, 1, 0),
       (2, 'testcountry', 'testcity', 'teststreet', 9, 9, 0),
       (3, 'Belarus', 'Grodno', 'Vrublevskogo', 1, 1, 0),
       (4, 'Belarus', 'Grodno', 'Dombrovskogo', 20, 21, 0),
       (5, 'Belarus', 'Minsk', 'Sovetskaya', 2, 3, 0),
       (6, 'Belarus', 'Lida', 'Pobedy', 5, 108, 0);
INSERT INTO carsharing.car_profiles (id_car_profile, manufacturer, model, body_type, engine_type, year_of_issue,
                                     deleted)
VALUES (1, 'honda', 'civic', 'sedan', 'fuel', 2010, 0),
       (2, 'audi', 'r8', 'sedan', 'electric', 2016, 0),
       (3, 'lada', 'priora', 'sedan', 'fuel', 1980, 0),
       (4, 'testman', 'testmodel', 'wagon', 'fuel', 1999, 0),
       (5, 'kia', 'rio', 'hatchback', 'fuel', 2012, 0),
       (6, 'volkswagen', 'b5', 'sedan', 'fuel', 2005, 0);
INSERT INTO carsharing.car_images (idCarImage, image_url, car_profile_id, deleted)
VALUES (1, '/resources/imgs/audiR8_1.jpg', 2, 0),
       (2, '/resources/imgs/audiR8_2.jpg', 2, 0),
       (3, '/resources/imgs/audiR8_3.jpg', 2, 0),
       (4, '/resources/imgs/hondaCivic_1.png', 1, 0),
       (5, '/resources/imgs/hondaCivic_2.jpg', 1, 0),
       (6, '/resources/imgs/ladaPriora_1.jpg', 3, 0),
       (7, '/resources/imgs/testCar_1.jpg', 4, 0);
INSERT INTO carsharing.cars (idCar, available, deleted, registration_number, car_profile_id)
VALUES (1, 1, 0, '1234BP-4', 1),
       (2, 1, 0, '1337AI-4', 2),
       (3, 1, 0, '1891AO-7', 1),
       (4, 0, 0, '7628BK-4', 3),
       (5, 0, 0, '2731KI-7', 4),
       (6, 1, 0, '7981AT-4', 5),
       (7, 0, 0, '8720BT-4', 6);
INSERT INTO carsharing.passport_data (idPassportData, first_name, last_name, patronymic, birth_date, passport_number,
                                      identification_number, gender, address_id, deleted)
VALUES (1, 'a', 'd', 'min', '1999-05-22', '1337', '1337228', 'male', 1, 0),
       (2, 'test_name', 'test_l_name', 'test_patr', '2000-01-01', '351213b21', '123510', 'female', 2, 0),
       (3, 'andrey', 'yaskelevich', 'olegovich', '1998-05-16', '231cz23q2321', '31021160', 'male', 4, 0);
INSERT INTO carsharing.roles (idRole, role_name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');
INSERT INTO carsharing.users (idUser, username, password, email, is_active, passport_data_id, deleted)
VALUES (1, 'admin', '$2a$11$MJtj2BFcyPyJJUMO0fYjoeGF1RgaD1uBTCSYyIDkfRrFVIjX329b2', 'admin@mail.ru', 1, 1, 0),
       (2, 'testuser1', '$2a$11$X322vbhLNKHzwrl12g8csuhGeM6/AW/LNgWt.XjImA3iuwIN3us0K', 'test@mail.ru', 1, 2, 0),
       (3, 'testuser2', '$2a$11$BDG.Bo/VKwrT38AsZdL3Zu4pNhAgnO7BSVQq6ppBwiNWYdKIfvVWy', 'test1@mail.ru', 1, 3,0);
INSERT INTO carsharing.orders (idOrder, confirmation_date, payment_date, orderDuration, order_status, price, user_id,
                               car_id, deleted)
VALUES (1, '2000-12-31 21:00:00', '2000-12-31 21:00:00', 6, 'complete', 200, 1, 3, 0),
       (2, '2005-05-04 21:00:00', '2005-05-04 21:00:00', 2, 'complete', 123, 1, 2, 0),
       (3, '2011-09-07 21:00:00', '2011-09-07 21:00:00', 2, 'complete', 123, 2, 1, 0),
       (4, '2019-09-07 21:23:05', '2019-09-07 22:02:00', 4, 'complete', 100, 3, 5, 0),
       (5, '2019-10-10 18:55:33', '2019-10-10 19:00:00', 10, 'complete', 300, 3, 6, 0),
       (6, '2019-11-29 12:12:31', '2019-10-29 15:38:00', 6, 'complete', 190, 2, 7, 0);
INSERT INTO carsharing.order_additional_info (idOrderAdditionalInfo, info_details, payment_for_violation, order_id,
                                              deleted)
VALUES (1, 'light carProfile crash', 100, 1, 0);
INSERT INTO carsharing.user_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1);
