CREATE TABLE IF NOT EXISTS `cliente` (
    `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
    `cliente_rnc` varchar(20) DEFAULT NULL,
    `cliente_urlrecepcioncomprobante_prod` varchar(200) DEFAULT NULL,
    `cliente_urlrecepcionaprobacioncomercial_prod` varchar(200) DEFAULT NULL,
    `cliente_urlautenticacion_prod` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`cliente_id`)
);

CREATE TABLE IF NOT EXISTS `sesioncliente` (
    `sesioncliente_id` bigint NOT NULL AUTO_INCREMENT,
    `sesioncliente_cliente_id` INT(11) NOT NULL,
    `sesioncliente_tokenexpedido` datetime(6) DEFAULT NULL,
    `sesioncliente_tokenexpira` datetime(6) DEFAULT NULL,
    `sesioncliente_token` varchar(600) DEFAULT NULL,
    `sesioncliente_fecharegistro` datetime(6) NOT NULL,
    `sesioncliente_esproduccion` varchar(1) DEFAULT '0',
    PRIMARY KEY (`sesioncliente_id`),
    KEY `fk_sesioncliente_cliente_idx` (`sesioncliente_cliente_id`),
    CONSTRAINT `fk_sesioncliente_cliente` FOREIGN KEY (`sesioncliente_cliente_id`) REFERENCES `cliente` (`cliente_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `documento` (
    `documento_id` int(11) NOT NULL AUTO_INCREMENT,
    `documento_serie` varchar(10) DEFAULT NULL,
    `documento_correlativo` varchar(20) DEFAULT NULL,
    `documento_codigotipo` varchar(3) DEFAULT NULL,
    `documento_urlxml` text,
    `documento_codigounico` varchar(145) DEFAULT NULL,
    `documento_codigorespuesta` varchar(20) DEFAULT NULL,
    `documento_fecha` datetime DEFAULT NULL,
    `documento_total` decimal(12,4) DEFAULT NULL,
    `documento_clienteid` int(11) DEFAULT NULL,
    `documento_mensaje` text,
    `documento_fechadocumento` date DEFAULT NULL,
    `documento_totalgravado` decimal(12,2) DEFAULT '0.00',
    `documento_totalexonerado` decimal(12,2) DEFAULT '0.00',
    `documento_totalinafecto` decimal(12,2) DEFAULT '0.00',
    `documento_totalisc` decimal(12,2) DEFAULT '0.00',
    `documento_totaligv` decimal(12,2) DEFAULT '0.00',
    `documento_totalotrostributos` decimal(12,2) DEFAULT '0.00',
    `documento_totalotroscargos` decimal(12,2) DEFAULT '0.00',
    `documento_totalgratuitas` decimal(12,2) DEFAULT '0.00',
    `documento_hash` text NULL,
    `documento_numerodocumentocliente` varchar(145) DEFAULT NULL,
    `documento_estado` char(1) DEFAULT '1',
    `documento_moneda` varchar(145) DEFAULT 'DOP',
    `documento_descuento` decimal(12,2) DEFAULT '0.00',
    `documento_totalicbper` decimal(12,2) DEFAULT '0.00',
    `documento_esresumen` varchar(1) DEFAULT '0',
    `documento_ambiente` varchar(50) DEFAULT NULL,
    `documento_estado_recibido` int(2) DEFAULT NULL,
    `documento_codigomotivo_norecibido` int(2) DEFAULT NULL,
    PRIMARY KEY (`documento_id`),
    UNIQUE KEY `documento_tipo_serie_correlativo` (`documento_codigotipo`,`documento_serie`,`documento_correlativo`),
    KEY `fk_cliente_documento_idx` (`documento_clienteid`),
    KEY `fk_documento_serie_correlativo_esproduccion_ambiente_idx` (`documento_serie`,`documento_correlativo`,`documento_ambiente`),
    CONSTRAINT `fk_cliente_documento` FOREIGN KEY (`documento_clienteid`) REFERENCES `cliente` (`cliente_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `documentorecepcion` (
    `documentorecepcion_id` int(11) NOT NULL AUTO_INCREMENT,
    `documento_serie` varchar(10) DEFAULT NULL,
    `documento_correlativo` varchar(20) DEFAULT NULL,
    `documento_codigotipo` varchar(3) DEFAULT NULL,
    `documento_urlxml` text,
    `documento_codigounico_aprobacioncomercial` varchar(145) DEFAULT NULL,
    `documento_codigorespuesta` varchar(20) DEFAULT NULL,
    `documento_fecha` datetime DEFAULT NULL,
    `documento_total` decimal(12,4) DEFAULT NULL,
    `documento_clienteid` int(11) DEFAULT NULL,
    `documento_mensaje` text,
    `documento_fechadocumento` date DEFAULT NULL,
    `documento_totalgravado` decimal(12,2) DEFAULT '0.00',
    `documento_totalexonerado` decimal(12,2) DEFAULT '0.00',
    `documento_totalinafecto` decimal(12,2) DEFAULT '0.00',
    `documento_totalisc` decimal(12,2) DEFAULT '0.00',
    `documento_totaligv` decimal(12,2) DEFAULT '0.00',
    `documento_totalotrostributos` decimal(12,2) DEFAULT '0.00',
    `documento_totalotroscargos` decimal(12,2) DEFAULT '0.00',
    `documento_totalgratuitas` decimal(12,2) DEFAULT '0.00',
    `documento_numerodocumentocliente` varchar(145) DEFAULT NULL,
    `documento_estado` char(1) DEFAULT '1',
    `documento_moneda` varchar(145) DEFAULT 'DOP',
    `documento_descuento` decimal(12,2) DEFAULT '0.00',
    `documento_totalicbper` decimal(12,2) DEFAULT '0.00',
    `documento_ambiente` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`documentorecepcion_id`),
    UNIQUE KEY `documento_tipo_serie_correlativo_recep` (`documento_codigotipo`,`documento_serie`,`documento_correlativo`),
    KEY `fk_cliente_documentorecepcion_idx` (`documento_clienteid`),
    KEY `fk_documento_recepcion_idx` (`documento_serie`,`documento_correlativo`,`documento_ambiente`),
    CONSTRAINT `fk_cliente_documentorecepcion` FOREIGN KEY (`documento_clienteid`) REFERENCES `cliente` (`cliente_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `jsondocumento` (
    `jsondocumento_id` int(11) NOT NULL AUTO_INCREMENT,
    `jsondocumento_documentoid` int(11) DEFAULT NULL,
    `jsondocumento_json` longtext,
    `jsonaprobacioncomercialrecepcionada_json` longtext,
    `jsondocumento_esresumen` CHAR(1) DEFAULT '0',
    PRIMARY KEY (`jsondocumento_id`),
    KEY `fk_doc_idx` (`jsondocumento_documentoid`),
    CONSTRAINT `fk_jsondocumento_document` FOREIGN KEY (`jsondocumento_documentoid`) REFERENCES `documento` (`documento_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS `jsondocumentorecepcionado` (
    `jsondocumentorecepcionado_id` int(11) NOT NULL AUTO_INCREMENT,
    `jsondocumentorecepcionado_documentorecepcionadoid` int(11) DEFAULT NULL,
    `jsondocumento_json` longtext,
    `jsonaprobacioncomercialemitida_json` longtext,
    `jsondocumento_esresumen` CHAR(1) DEFAULT '0',
    PRIMARY KEY (`jsondocumentorecepcionado_id`),
    KEY `fk_doc_recep_idx` (`jsondocumentorecepcionado_documentorecepcionadoid`),
    CONSTRAINT `fk_jsondocumento_recep_document` FOREIGN KEY (`jsondocumentorecepcionado_documentorecepcionadoid`) REFERENCES `documentorecepcion` (`documentorecepcion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
