/*
SQLyog Enterprise - MySQL GUI v8.12 
MySQL - 5.5.37-0ubuntu0.12.04.1 : Database - estacionamento
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`estacionamento` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `estacionamento`;

/*Table structure for table `endereco` */

DROP TABLE IF EXISTS `endereco`;

CREATE TABLE `endereco` (
  `idEndereco` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rua` varchar(100) NOT NULL,
  `numCasa` int(10) unsigned NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `cep` int(11) NOT NULL,
  `estado` varchar(20) NOT NULL,
  PRIMARY KEY (`idEndereco`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `endereco` */

insert  into `endereco`(`idEndereco`,`rua`,`numCasa`,`bairro`,`cidade`,`cep`,`estado`) values (1,'rua',123,'bairro','cidade',123,'sp');

/*Table structure for table `ficha` */

DROP TABLE IF EXISTS `ficha`;

CREATE TABLE `ficha` (
  `idFicha` int(11) NOT NULL AUTO_INCREMENT,
  `placa` varchar(8) NOT NULL,
  `dtHrEntrada` datetime NOT NULL,
  `dtHrSaida` datetime DEFAULT NULL,
  `valorHora` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`idFicha`),
  KEY `idFicha` (`idFicha`),
  KEY `FK_ficha` (`placa`),
  CONSTRAINT `FK_ficha` FOREIGN KEY (`placa`) REFERENCES `veiculo` (`placa`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `ficha` */

insert  into `ficha`(`idFicha`,`placa`,`dtHrEntrada`,`dtHrSaida`,`valorHora`) values (1,'aaa-0123','2018-06-24 08:02:01',NULL,NULL),(2,'BBB-0123','2018-06-24 13:03:20','2018-06-24 21:16:42','9.00'),(3,'CCC-0123','2018-06-24 10:03:40',NULL,NULL),(4,'DDD-0123','2018-06-24 15:03:59',NULL,NULL),(5,'EEE-0123','2018-06-24 18:04:27',NULL,NULL),(6,'FFF-0123','2018-06-24 20:04:47',NULL,NULL),(7,'BBB-0123','2018-06-24 21:17:06',NULL,NULL);

/*Table structure for table `funcionario` */

DROP TABLE IF EXISTS `funcionario`;

CREATE TABLE `funcionario` (
  `cartTrabalho` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idEndereco` int(10) unsigned DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `cpf` int(11) NOT NULL,
  `telefone` int(11) DEFAULT NULL,
  `cargo` varchar(50) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(50) NOT NULL,
  PRIMARY KEY (`cartTrabalho`),
  KEY `FK_funcionario` (`idEndereco`),
  CONSTRAINT `FK_funcionario` FOREIGN KEY (`idEndereco`) REFERENCES `endereco` (`idEndereco`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=latin1;

/*Data for the table `funcionario` */

insert  into `funcionario`(`cartTrabalho`,`idEndereco`,`nome`,`cpf`,`telefone`,`cargo`,`login`,`senha`) values (1,NULL,'123456789',31563156,123456789,'Gerente','analima','ana123'),(123,1,'joao',123,0,'ajudante','joao','joao');

/*Table structure for table `motorista` */

DROP TABLE IF EXISTS `motorista`;

CREATE TABLE `motorista` (
  `cnh` int(10) unsigned NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`cnh`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `motorista` */

insert  into `motorista`(`cnh`,`nome`) values (11111,'Mario'),(22222,'Ana'),(33333,'celio'),(44444,'Fulano'),(55555,'Ciclano'),(66666,'Beltrano');

/*Table structure for table `vagas` */

DROP TABLE IF EXISTS `vagas`;

CREATE TABLE `vagas` (
  `tipoVaga` varchar(50) NOT NULL,
  `qtdVaga` smallint(5) unsigned NOT NULL,
  `precoVaga` decimal(5,2) unsigned NOT NULL,
  PRIMARY KEY (`tipoVaga`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vagas` */

insert  into `vagas`(`tipoVaga`,`qtdVaga`,`precoVaga`) values ('Carro',10,'1.00'),('Moto',15,'2.00');

/*Table structure for table `veiculo` */

DROP TABLE IF EXISTS `veiculo`;

CREATE TABLE `veiculo` (
  `placa` varchar(8) NOT NULL,
  `tipoVaga` varchar(50) NOT NULL,
  `cnh` int(10) unsigned NOT NULL,
  `modelo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`placa`),
  KEY `FK_veiculo` (`tipoVaga`),
  KEY `FK_cnh` (`cnh`),
  CONSTRAINT `FK_cnh` FOREIGN KEY (`cnh`) REFERENCES `motorista` (`cnh`) ON UPDATE CASCADE,
  CONSTRAINT `FK_veiculo` FOREIGN KEY (`tipoVaga`) REFERENCES `vagas` (`tipoVaga`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `veiculo` */

insert  into `veiculo`(`placa`,`tipoVaga`,`cnh`,`modelo`) values ('AAA-0123','Carro',11111,'Sandero'),('BBB-0123','Carro',44444,'Palio'),('CCC-0123','Carro',33333,'Civic'),('DDD-0123','Moto',22222,'Bis'),('EEE-0123','Carro',66666,'Montana'),('FFF-0123','Moto',55555,'Falcon');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
