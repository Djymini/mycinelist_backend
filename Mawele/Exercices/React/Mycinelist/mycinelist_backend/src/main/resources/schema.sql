DROP TABLE IF EXISTS `seen`;
DROP TABLE IF EXISTS `movie_selection_day`;
DROP TABLE IF EXISTS `movie_list`;
DROP TABLE IF EXISTS `favorite`;
DROP TABLE IF EXISTS `list`;
DROP TABLE IF EXISTS `movie`;
DROP TABLE IF EXISTS `user`;

-- Création de la table user en premier
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_UNIQUE` (`mail`)
);

-- Création de la table movie
CREATE TABLE `movie` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `runtime` int NOT NULL,
  `release_date` date NOT NULL,
  `poster_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
);

-- Création de la table favorite après movie et user
CREATE TABLE `favorite` (
  `user_id` int NOT NULL,
  `movie_id` int NOT NULL,
  PRIMARY KEY (`movie_id`, `user_id`),
  CONSTRAINT `favorite_movie_movie_id_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE,
  CONSTRAINT `favorite_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);

-- Création de la table list après user
CREATE TABLE `list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `list_user_id_fk` (`user_id`),
  CONSTRAINT `list_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

-- Création de la table movie_list après list et movie
CREATE TABLE `movie_list` (
  `list_id` int NOT NULL,
  `movie_id` int NOT NULL,
  PRIMARY KEY (`list_id`, `movie_id`),
  CONSTRAINT `movie_list_list_id_fk` FOREIGN KEY (`list_id`) REFERENCES `list` (`id`) ON DELETE CASCADE,
  CONSTRAINT `movie_list_movie_id_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE
);

-- Création de la table movie_selection_day après movie
CREATE TABLE `movie_selection_day` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `movie_selection_day_movie_id_fk` (`movie_id`),
  CONSTRAINT `movie_selection_day_movie_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE
);

-- Création de la table seen après movie et user
CREATE TABLE `seen` (
  `user_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `like` varchar(50) NOT NULL,
  `recommandation` varchar(50) NOT NULL,
  `commentary` text,
  PRIMARY KEY (`user_id`, `movie_id`),
  CONSTRAINT `seen_movie_id_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE,
  CONSTRAINT `seen_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);