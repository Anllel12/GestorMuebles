CREATE DATABASE IF NOT EXISTS muebles;

USE muebles;

CREATE TABLE IF NOT EXISTS `mueble` (
	`modelo` INT(10) NOT NULL,
	`nombre` VARCHAR(100) NOT NULL,
	`precio` INT(10) NOT NULL,
	`paquetes` INT(5) NOT NULL,
	PRIMARY KEY (`modelo`)
);

CREATE TABLE IF NOT EXISTS `tamano` (
	`id` INT(10) AUTO_INCREMENT,
	`ancho` INT(10),
	`fondo` INT(10),
    `altura` INT(10),
	`peso_balda` INT(10),
	`mueble` INT(10) NOT NULL,
	PRIMARY KEY (`id`),
    FOREIGN KEY(`mueble`) REFERENCES mueble(modelo) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `material` (
	`id` INT(10) AUTO_INCREMENT,
	`principal` VARCHAR(100),
	`secundario` VARCHAR(100),
	`mueble` INT(10) NOT NULL,
	PRIMARY KEY (`id`),
    FOREIGN KEY(`mueble`) REFERENCES mueble(modelo) ON DELETE CASCADE
    );

INSERT IGNORE INTO `mueble` VALUES 
('00278578', 'HYLLIS', '10', '1'),
('60333850', 'BROR', '99', '1'),
('30409295', 'KOLBJÖRN', '69', '1');

INSERT IGNORE INTO `tamano` VALUES 
(0, '60', '27', '140', '25', '00278578'),
(0, '85', '55', '88', '50', '60333850'),
(0, '80', '35', '81', '55', '30409295');

INSERT IGNORE INTO `material` VALUES 
(0, 'Acero galvanizado', 'Plástico amídico', '00278578'),
(0, 'Acero', 'Contrachapado de pino', '60333850'),
(0, 'Acero galvanizado', 'Revestimiento en polvo de poliéster', '30409295');

SELECT * FROM mueble;
SELECT * FROM tamano;
SELECT * FROM material;