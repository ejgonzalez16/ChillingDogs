package org.example.chillingdogspage.Database;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")     // Solo se ejecuta cuando el perfil de testing está activo
public class DatabaseInitTest extends AbstractDatabaseInit {
    // AbstractDatabaseInit ya realiza toda la lógica de inicialización de la base de datos
    // Como la inicialización de la base de datos para dev y test es la misma, se puede reutilizar el código
}
