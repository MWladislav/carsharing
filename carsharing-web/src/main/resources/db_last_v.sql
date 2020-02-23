DROP TABLE IF EXISTS addresses;
create table `addresses`
(
    `id`    int auto_increment,
    `country`      varchar(45)          not null,
    `city`         varchar(45)          not null,
    `street`       varchar(45)          not null,
    `house_number` int(11)              not null,
    `deleted`      tinyint(1) default 0 null,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS car_profiles;
create table `car_profiles`
(
    `id` int(11) auto_increment,
    `manufacturer`   varchar(45)          not null,
    `model`          varchar(45)          not null,
    `body_type`      varchar(45)          not null,
    `engine_type`    varchar(45)          not null,
    `year_of_issue`  int(11)              not null,
    `deleted`        tinyint(1) default 0 null,
    PRIMARY KEY (`id`)
);

# ALTER TABLE `car_images` drop foreign key `fk_carImage_to_carProfile`;
DROP TABLE IF EXISTS car_images;
create table `car_images`
(
    `id`     int auto_increment,
    `image_url`            varchar(45)    not null,
    `is_main_image` tinyint(1) default 0 null,
    `car_profile_id` int(11)              not null,
    `deleted`        tinyint(1) default 0 null,
    PRIMARY KEY (`id`, `car_profile_id`),
    KEY `CarImageToCar_idx` (`car_profile_id`),
    CONSTRAINT `fk_carImage_to_carProfile` FOREIGN KEY (`car_profile_id`) REFERENCES car_profiles (id)
);

# ALTER TABLE `cars` drop foreign key `fk_car_to_carProfile`;
DROP TABLE IF EXISTS cars;
create table `cars`
(
    `id`               int auto_increment,
    `available`           tinyint(1) default 0 null,
    `deleted`             tinyint(1) default 0 null,
    `registration_number` varchar(10)          null,
    `car_profile_id`      int(11)              not null,
    PRIMARY KEY (`id`, `car_profile_id`),
    KEY `CarToCarProfile_idx` (`car_profile_id`),
    CONSTRAINT `fk_car_to_carProfile` FOREIGN KEY (`car_profile_id`) REFERENCES car_profiles (id)
);

# ALTER TABLE `passport_data` drop foreign key `fk_passportData_to_address`;
DROP TABLE IF EXISTS passport_data;
create table `passport_data`
(
    `id`        int auto_increment,
    `first_name`            varchar(45)          not null,
    `last_name`             varchar(45)          not null,
    `patronymic`            varchar(45)          null,
    `birth_date`            date                 not null,
    `passport_number`       varchar(45)          not null,
    `identification_number` varchar(45)          not null,
    `gender`                varchar(45)          not null,
    `address_id`            int(11)              not null,
    `deleted`               tinyint(1) default 0 null,
     PRIMARY KEY (`id`),
     KEY `fk_passportData_to_address_idx` (address_id),
     CONSTRAINT `fk_passportData_to_address` FOREIGN KEY (`address_id`) REFERENCES addresses (`id`)
);

DROP TABLE IF EXISTS roles;
create table `roles`
(
    `id`    int auto_increment not null,
    `role_name` varchar(45) not null,
    PRIMARY KEY (`id`)
);

# ALTER TABLE `users` drop foreign key `fk_user_to_passportData`;
DROP TABLE IF EXISTS users;
create table `users`
(
    `id`                      int auto_increment,
    `username`                    varchar(45)          not null,
    `password`                    varchar(150)         not null,
    `email`                       varchar(45)          not null,
    `is_active`                   tinyint(1) default 0 null,
    `passport_data_id`            int(11)              null,
    `deleted`                     tinyint(1) default 0 null,
    PRIMARY KEY (`id`),
    KEY `fk_user_to_passportData_idx` (`passport_data_id`),
    CONSTRAINT `fk_user_to_passportData` FOREIGN KEY (`passport_data_id`) REFERENCES passport_data (`id`)
);

# ALTER TABLE `orders` drop foreign key `fk_order_to_car` ;
# ALTER TABLE `orders` drop foreign key `fk_order_to_user`;
DROP TABLE IF EXISTS orders;
create table `orders`
(
    `id`           int auto_increment,
    `confirmation_date` timestamp(2)         null,
    `payment_date`      timestamp(2)         null,
    `order_duration`     int(11)                  not null,
    `order_status`      varchar(45)          not null,
    `price`             int(11)                  null,
    `user_id`           int(11)                  not null,
    `car_id`            int(11)                  not null,
    `deleted`           tinyint(1) default 0 null,
    PRIMARY KEY (`id`),
    KEY `fk_order_to_car_idx` (`car_id`),
    KEY `fk_order_to_user_idx` (`user_id`),
    CONSTRAINT `fk_order_to_car` FOREIGN KEY (`car_id`) REFERENCES cars (`id`),
    CONSTRAINT `fk_order_to_user` FOREIGN KEY (`user_id`) REFERENCES users (`id`)
);

# ALTER TABLE `order_additional_info` drop foreign key `fk_orderAdditionalInfo_to_order`;
DROP TABLE IF EXISTS order_additional_info;
create table `order_additional_info`
(
    `id` int auto_increment,
    `info_details`          varchar(45)          not null,
    `payment_for_violation` int(11)                  null,
    `order_id`              int(11)                  null,
    `deleted`               tinyint(1) default 0 null,
    PRIMARY KEY (`id`),
    KEY `fk_orderAdditionalInfo_to_order_idx` (`order_id`),
    CONSTRAINT `fk_orderAdditionalInfo_to_order` FOREIGN KEY (`order_id`) REFERENCES orders (`id`)
);

# ALTER TABLE `cars` drop foreign key `fk_car_to_carProfile`;
# ALTER TABLE `cars` drop foreign key `fk_car_to_carProfile`;
DROP TABLE IF EXISTS user_roles;
create table `user_roles`
(
    `user_id` int not null,
    `role_id` int not null,
    constraint `user_roles_roles_idRole_fk`
        foreign key (role_id) references roles (id),
    constraint `user_roles_users_idUser_fk`
        foreign key (user_id) references users (id)
);
