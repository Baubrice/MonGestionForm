-- Suppression des tables existantes
DROP TABLE IF EXISTS utilisateur_role;
DROP TABLE IF EXISTS utilisateur_formation;
DROP TABLE IF EXISTS formation_module;
DROP TABLE IF EXISTS module_sequence;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS utilisateur;
DROP TABLE IF EXISTS formation;
DROP TABLE IF EXISTS module;
DROP TABLE IF EXISTS sequence;

-- Création des tables
CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    libelle VARCHAR(50) NOT NULL,
    statut BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
);

CREATE TABLE utilisateur (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    adresse_mail VARCHAR(255),
    adresse_postal VARCHAR(255),
    code_postal VARCHAR(10),
    ville VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE formation (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nom VARCHAR(255),
    description TEXT,
    numero_offre VARCHAR(50),
    libelle VARCHAR(255),
    date_debut DATETIME,
    date_fin DATETIME,
    date_debut_pe DATETIME,
    date_fin_pe DATETIME,
    statut BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE module (
    id BIGINT NOT NULL AUTO_INCREMENT,
    libelle VARCHAR(255) NOT NULL,
    description TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE sequence (
    id BIGINT NOT NULL AUTO_INCREMENT,
    libelle VARCHAR(255) NOT NULL,
    description TEXT,
    duree INT,
    PRIMARY KEY (id)
);

-- Tables de jointure
CREATE TABLE utilisateur_role (
    id_utilisateur BIGINT NOT NULL,
    id_role BIGINT NOT NULL,
    PRIMARY KEY (id_utilisateur, id_role),
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id),
    FOREIGN KEY (id_role) REFERENCES roles(id)
);

CREATE TABLE utilisateur_formation (
    id_utilisateur BIGINT NOT NULL,
    id_formation BIGINT NOT NULL,
    PRIMARY KEY (id_utilisateur, id_formation),
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id),
    FOREIGN KEY (id_formation) REFERENCES formation(id)
);

CREATE TABLE formation_module (
    id_formation BIGINT NOT NULL,
    id_module BIGINT NOT NULL,
    PRIMARY KEY (id_formation, id_module),
    FOREIGN KEY (id_formation) REFERENCES formation(id),
    FOREIGN KEY (id_module) REFERENCES module(id)
);

CREATE TABLE module_sequence (
    id_module BIGINT NOT NULL,
    id_sequence BIGINT NOT NULL,
    PRIMARY KEY (id_module, id_sequence),
    FOREIGN KEY (id_module) REFERENCES module(id),
    FOREIGN KEY (id_sequence) REFERENCES sequence(id)
); 