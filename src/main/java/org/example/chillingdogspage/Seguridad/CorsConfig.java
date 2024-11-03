package org.example.chillingdogspage.Seguridad;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration  // Se usa porque vamos a crear beans
public class CorsConfig {
    // En esta clase se configura el filtro CORS para permitir que SOLO el Frontend (Angular) se conecte al Backend (Spring Boot)

    @Bean   // Método para registrar la configuración de CORS, que se guarda en su cajita de beans
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = getCorsConfiguration();

        source.registerCorsConfiguration("/**", config);    // Configurar el filtro CORS para todas las rutas
        /* Se quitó porque se espera CorsFilter en lugar de FilterRegistrationBean<CorsFilter>
        // Crear un filtro CORS con el source configurado
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

        bean.setOrder(-100);    // Orden de prioridad del filtro (que se ejecute antes que todos los demás)*/
        return new CorsFilter(source);
    }

    private static CorsConfiguration getCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        // Permitir que el Frontend (local y desplegado) se conecte al Backend (Spring Boot)
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin("https://chillingdogs.netlify.app/");
        config.setAllowedHeaders(List.of(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT
        ));
        config.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()
        ));
        return config;
    }
}
