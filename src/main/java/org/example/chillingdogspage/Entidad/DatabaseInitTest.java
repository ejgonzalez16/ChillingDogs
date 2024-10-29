package org.example.chillingdogspage.Entidad;

import jakarta.transaction.Transactional;
import org.example.chillingdogspage.Repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@Transactional
@Profile("test")     // Solo se ejecuta cuando el perfil de test está activo
public class DatabaseInitTest implements ApplicationRunner {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private DrogaRepository drogaRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public void run(ApplicationArguments args) {
        // Save the data to the database
        leerClientes();
        leerMascotas();
        leerVeterinarios();
        leerDrogas();
        leerTratamientos();
        leerAdmins();
    }

    public void leerClientes() {
        // Read the data from the database
        String rutaArchivo = "static/sources/datos-quemados/clientes.csv";

        try {
            ClassPathResource resource = new ClassPathResource(rutaArchivo);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String linea;
                // NO HAY ENCABEZADO HDP
                // Leer y descartar el encabezado (QUE HIJO DE PUTA)
                // br.readLine();

                while ((linea = br.readLine()) != null) {
                    // Dividir la línea por el delimitador ';'
                    String[] datos = linea.split(";");

                    // Asegúrate de que el archivo CSV tenga la misma cantidad de columnas
                    if (datos.length == 5) {
                        Cliente cliente = new Cliente(
                                datos[0], // cedula
                                datos[1], // nombre
                                datos[2], // correo
                                datos[3], // celular
                                datos[4]  // foto
                        );
                        clienteRepository.save(cliente); // Guarda el cliente en el repositorio
                    } else {
                        System.out.println("Error en los datos de la fila: " + String.join(";", datos));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerMascotas() {
        // Read the data from the database
        String rutaArchivo = "static/sources/datos-quemados/mascotas.csv";

        try {
            ClassPathResource resource = new ClassPathResource(rutaArchivo);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String linea;
                // NO HAY ENCABEZADO HDP
                // Leer y descartar el encabezado (QUE HIJO DE PUTA)
                // br.readLine();

                while ((linea = br.readLine()) != null) {
                    // Dividir la línea por el delimitador ';'
                    String[] datos = linea.split(";");

                    // Asegúrate de que el archivo CSV tenga la misma cantidad de columnas
                    if (datos.length == 8) {
                        Mascota mascota = new Mascota(
                                datos[0], // nombre
                                datos[1], // raza
                                Integer.parseInt(datos[2]), // edad
                                Float.parseFloat(datos[3]), // peso
                                datos[4], // enfermedad
                                datos[5], // foto
                                datos[6],  // estado
                                clienteRepository.findById(Long.parseLong(datos[7])) // cliente
                        );
                        mascotaRepository.save(mascota); // Guarda la mascota en el repositorio
                        /* Así no va JAJA
                        Cliente cliente = clienteRepository.findById(Long.parseLong(datos[7])).get();
                        cliente.getMascotas().add(mascota);*/
                    } else {
                        System.out.println("Error en los datos de la fila: " + String.join(";", datos));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leerVeterinarios() {
        // Read the data from the database
        String rutaArchivo = "static/sources/datos-quemados/veterinarios.csv";

        try {
            ClassPathResource resource = new ClassPathResource(rutaArchivo);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String linea;
                // NO HAY ENCABEZADO HDP
                // Leer y descartar el encabezado (QUE HIJO DE PUTA)
                // br.readLine();

                while ((linea = br.readLine()) != null) {
                    // Dividir la línea por el delimitador ';'
                    String[] datos = linea.split(";");

                    // Asegúrate de que el archivo CSV tenga la misma cantidad de columnas
                    if (datos.length == 6) {
                        Veterinario veterinario = new Veterinario(
                                datos[0], // cedula
                                datos[1], // contrasena
                                datos[2], // especialidad
                                datos[3],  // nombre
                                datos[4],  // estado
                                datos[5]  // foto
                        );
                        veterinarioRepository.save(veterinario); // Guarda el veterinario en el repositorio
                    } else {
                        System.out.println("Error en los datos de la fila: " + String.join(";", datos));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leerDrogas() {
        // Read the data from the database
        String rutaArchivo = "static/sources/datos-quemados/medicamentos_veterinaria.csv";

        try {
            ClassPathResource resource = new ClassPathResource(rutaArchivo);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String linea;
                // Leer y descartar el encabezado
                br.readLine();

                while ((linea = br.readLine()) != null) {
                    // Dividir la línea por el delimitador ';'
                    String[] datos = linea.split(";");

                    // Asegúrate de que el archivo CSV tenga la misma cantidad de columnas
                    // Tiene 5 columnas, pero no se lee la última (unidades vendidas)
                    if (datos.length == 5) {
                        Droga droga = new Droga(
                                datos[0], // nombre
                                Double.parseDouble(datos[1]), // precioVenta
                                Double.parseDouble(datos[2]), // precioCompra
                                Integer.parseInt(datos[3])  // unidadesDisponibles
                        );
                        // Guarda la droga en el repositorio
                        drogaRepository.save(droga);
                    } else {
                        System.out.println("Error en la cantidad de datos de la fila: " + String.join(";", datos));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leerTratamientos() {
        // Read the data from the database
        String rutaArchivo = "static/sources/datos-quemados/tratamientos.csv";

        try {
            ClassPathResource resource = new ClassPathResource(rutaArchivo);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String linea;
                // NO HAY ENCABEZADO HDP
                // Leer y descartar el encabezado (QUE HIJO DE PUTA)
                // br.readLine();

                while ((linea = br.readLine()) != null) {
                    // Dividir la línea por el delimitador ';'
                    String[] datos = linea.split(";");

                    // Asegúrate de que el archivo CSV tenga la misma cantidad de columnas
                    if (datos.length == 4) {
                        Tratamiento tratamiento = new Tratamiento(
                                LocalDate.parse(datos[0]), // fecha
                                mascotaRepository.findById(Long.parseLong(datos[1])).get(), // mascota
                                drogaRepository.findById(Long.parseLong(datos[2])).get(), // droga
                                veterinarioRepository.findById(Long.parseLong(datos[3])) // veterinario
                        );
                        // Guarda el tratamiento en el repositorio
                        tratamientoRepository.save(tratamiento);
                    } else {
                        System.out.println("Error en los datos de la fila: " + String.join(";", datos));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leerAdmins() {
        // Read the data from the database
        String rutaArchivo = "static/sources/datos-quemados/admins.csv";

        try {
            ClassPathResource resource = new ClassPathResource(rutaArchivo);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String linea;
                // Leer y descartar el encabezado
                br.readLine();

                while ((linea = br.readLine()) != null) {
                    // Dividir la línea por el delimitador ';'
                    String[] datos = linea.split(";");

                    // Asegúrate de que el archivo CSV tenga la misma cantidad de columnas
                    if (datos.length == 4) {
                        Administrador admin = new Administrador(
                                datos[0], // cedula
                                datos[1], // nombre
                                datos[2], // contrasena
                                datos[3]  // foto
                        );
                        // Guarda el administrador en el repositorio
                        administradorRepository.save(admin);
                    } else {
                        System.out.println("Error en los datos de la fila: " + String.join(";", datos));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}