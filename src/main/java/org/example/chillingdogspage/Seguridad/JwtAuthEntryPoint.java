package org.example.chillingdogspage.Seguridad;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    // Para no tener ese mensaje 403 Forbidden, sino un mensaje personalizado
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Imprimir en consola más detalles del error
        System.out.println("Error: " + authException.getMessage());
        System.out.println("Causa: " + authException.getCause());
        System.out.println("Request: " + request.getRequestURI());
        System.out.println("Response: " + response.getStatus());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: No autorizado");
    }
}
