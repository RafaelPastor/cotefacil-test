package br.com.rafaelpastor.swp;

import br.com.rafaelpastor.swp.services.SwService;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.UUID;

@Log
@SpringBootApplication
public class SWPTestApplication {

    private final SwService swService;

    public SWPTestApplication(SwService swService) {
        this.swService = swService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SWPTestApplication.class, args);
    }

    @PostConstruct
    public void init() {
        log.info(String.valueOf(UUID.randomUUID()));
        log.info("Initializing project");
        log.info("Count service: " + swService.getCount());
        if (swService.getCount() == 0) {
            log.info("Mocking default data");
            swService.saveFirstData();
        }
    }
}
