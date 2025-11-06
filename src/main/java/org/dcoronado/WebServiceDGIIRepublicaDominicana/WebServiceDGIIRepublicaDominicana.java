package org.dcoronado.WebServiceDGIIRepublicaDominicana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebServiceDGIIRepublicaDominicana {
    static {
        // Forzar uso de Xerces como SAX parser
        System.setProperty(
                "javax.xml.parsers.SAXParserFactory",
                "org.apache.xerces.jaxp.SAXParserFactoryImpl"
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(WebServiceDGIIRepublicaDominicana.class, args);
    }
}
