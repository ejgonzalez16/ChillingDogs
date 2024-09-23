package org.example.chillingdogspage.Entidad;

import jakarta.transaction.Transactional;
import org.example.chillingdogspage.Repositorio.ClienteRepository;
import org.example.chillingdogspage.Repositorio.DrogaRepository;
import org.example.chillingdogspage.Repositorio.MascotaRepository;
import org.example.chillingdogspage.Repositorio.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
@Transactional
public class DatabaseInit implements ApplicationRunner {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private DrogaRepository drogaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Save the data to the database
        leerClientes();
        leerMascotas();
        leerVeterinarios();
        leerDrogas();
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
                                clienteRepository.findById(Long.parseLong(datos[7])).get() // cliente
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
        String rutaArchivo = "static/sources/datos-quemados/drogas.csv";

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
                        Droga droga = new Droga(
                                datos[0], // nombre
                                Double.parseDouble(datos[1]), // precioCompra
                                Double.parseDouble(datos[2]), // precioVenta
                                Integer.parseInt(datos[3])  // unidadesDisponibles
                        );
                        // Guarda la droga en el repositorio
                        drogaRepository.save(droga);
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
