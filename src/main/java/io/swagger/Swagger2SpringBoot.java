package io.swagger;

import io.swagger.model.Service;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" })
public class Swagger2SpringBoot implements CommandLineRunner {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Swagger2SpringBoot.class);

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
