package org.dcoronado.WebServiceDGIIRepublicaDominicana;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebServiceDGIIRepublicaDominicana {
    public static void main(String[] args) {
        SpringApplication.run(WebServiceDGIIRepublicaDominicana.class, args);
    }
}
