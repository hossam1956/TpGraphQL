package TP.tpgraphql.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/graphql")
                .allowedOrigins("*") // Allow all origins
                .allowedMethods("*") // Allow all HTTP methods
                .allowedHeaders("*")
                .allowCredentials(false); // Disable credentials sharing

            }
        };
    }
}
