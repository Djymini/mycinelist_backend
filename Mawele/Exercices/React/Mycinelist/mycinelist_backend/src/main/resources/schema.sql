CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);

CREATE TABLE IF NOT EXISTS `movie` (
  `movie_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `runtime` INT NOT NULL,
  `release_date` DATE NOT NULL,
  `poster_path` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
);

CREATE TABLE IF NOT EXISTS `favorite` (
  `user_id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  PRIMARY KEY (`movie_id`, `user_id`),
  KEY `favorite_user_id_fk` (`user_id`),
  CONSTRAINT `favorite_movie_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE,
  CONSTRAINT `favorite_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `movie_list` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `movie_list_user_id_fk` (`user_id`),
  CONSTRAINT `movie_list_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `movie_list_movies` (
  `list_id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  PRIMARY KEY (`list_id`, `movie_id`),
  KEY `movie_list_movie_id_fk` (`movie_id`),
  CONSTRAINT `movie_list_list_fk` FOREIGN KEY (`list_id`) REFERENCES `movie_list` (`id`) ON DELETE CASCADE,
  CONSTRAINT `movie_list_movie_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `movie_selection_day` (
  `id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  `date` DATE DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `seen` (
  `user_id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  `like` VARCHAR(50) NOT NULL,
  `recommandation` VARCHAR(50) NOT NULL,
  `commentary` TEXT,
  PRIMARY KEY (`user_id`, `movie_id`),
  KEY `seen_movie_id_fk` (`movie_id`),
  CONSTRAINT `seen_movie_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE,
  CONSTRAINT `seen_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);
