-- Création de l'utilisateur s'il n'existe pas
CREATE USER IF NOT EXISTS 'gestionnaire_formation'@'localhost' IDENTIFIED BY 'V7Tz)VG*v)b72ENu';

-- Création de la base de données si elle n'existe pas
CREATE DATABASE IF NOT EXISTS gestionnaire_formation;

-- Attribution des privilèges
GRANT ALL PRIVILEGES ON gestionnaire_formation.* TO 'gestionnaire_formation'@'localhost';
FLUSH PRIVILEGES; 