package com.example.ecommercefinal.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(

        info = @Info(title = "Ecommerce JAVA - Di Ciocco",
                     version = "1.0",
                    description = "CRUD Clientes / Productos / Carrito / Facturas")

)

public class OpenApi {
}
