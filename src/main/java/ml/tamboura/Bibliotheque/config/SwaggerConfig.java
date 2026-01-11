package ml.tamboura.Bibliotheque.config;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI bibliothequeOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API Bibliothèque en ligne")
                        .description("Documentation de l’API REST de gestion de bibliothèque")
                        .version("1.0"));
    }
}
