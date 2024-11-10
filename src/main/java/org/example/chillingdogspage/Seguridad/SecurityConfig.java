package org.example.chillingdogspage.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration  // Indica que en esta clase se van a crear beans de Spring
@EnableWebSecurity  // Indica que en esta clase se va a configurar la seguridad de la aplicación
public class SecurityConfig {

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    // Filter Chain: Serie de filtros que se aplican a las peticiones HTTP (una especie de middleware)
    // Define quiénes pueden conectarse a nuestra API y a qué Controladores pueden acceder

    // En este proyecto manejamos solo Authority, no Roles
    // ADMIN, VETERINARIO, CLIENTE son Authorities

    // requestMatchers.permitAll()  // Permitir todas las peticiones
    // requestMatchers.authenticated()  // Todas las peticiones requieren autenticación
    // requestMatchers.hasRole("ADMIN")  // Todas las peticiones requieren el rol ADMIN
    // requestMatchers.hasAuthority("ADMIN o READ?")  // Todas las peticiones requieren el permiso READ
    @Bean   // Crear un objeto que Spring guarda en su cajita de beans
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)  // Deshabilitar la protección CSRF (un tipo de ataque a aplicaciones web que se da en MPA), solo se debería activar si el Backend y el Frontend están en el mismo nodo
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))  // Para acceder a la consola de H2
            .authorizeHttpRequests(requests -> requests
                // Peticiones que pueden hacer todos los usuarios
                .requestMatchers("/h2/**").permitAll()    // Acceso a la consola de H2
                .requestMatchers("/clientes/login", "/veterinarios/login", "/admin/login").permitAll()
                // Peticiones que solo pueden hacer los usuarios autenticados
                .requestMatchers("mascotas/cliente/**", "/mascotas/{id}", "/tratamientos/mascota/**").authenticated()
                // Peticiones que solo pueden hacer los clientes
                .requestMatchers("/clientes/perfil").hasAuthority("CLIENTE")
                // Peticiones que solo pueden hacer los veterinarios
                .requestMatchers("/veterinarios/perfil", "/tratamientos/**").hasAuthority("VETERINARIO")
                // Peticiones que pueden hacer veterinarios y administradores
                .requestMatchers("/clientes/**", "/mascotas/**", "/drogas/**").hasAnyAuthority("VETERINARIO", "ADMIN")
                // Peticiones que solo pueden hacer los administradores
                .requestMatchers("/admin/perfil", "/dashboard/**", "/veterinarios/**", "/tratamientos/contarDroga/**").hasAuthority("ADMIN")
                // El resto de rutas permitir a todos
                .anyRequest().permitAll()
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(jwtAuthEntryPoint)  // Para no tener ese mensaje 403 Forbidden, sino un mensaje personalizado
            );
        // Antes de que se haga la autenticación por usuario y contraseña, añadir el filtro de autenticación JWT
        // Para verificar si tenemos un JWT y si es válido
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // Codificar las contraseñas
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Permite autenticar a los usuarios con usuario y contraseña
     * Al autenticar devuelve un objeto Authentication que posteriormente se puede usar a traves de SecurityContextHolder
     * para obtener el usuario autenticado
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
}
