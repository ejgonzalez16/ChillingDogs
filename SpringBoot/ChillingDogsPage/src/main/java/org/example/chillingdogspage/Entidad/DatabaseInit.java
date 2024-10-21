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
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Transactional
@Profile("dev")     // Solo se ejecuta cuando el perfil de desarrollo está activo
public class DatabaseInit implements ApplicationRunner {
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
        // Descomentar para actualizar las historias de clientes Chilling
        // generarHistoriasDeUsuario();
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

    // Método para generar historias de usuario
    public void generarHistoriasDeUsuario() {
        List<Cliente> clientes = clienteRepository.findAll();

        // Crear carpeta "Historias de clientes Chilling" si no existe
        String folderPath = "Historias de clientes Chilling";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Para cada cliente generar una historia en formato markdown
        for (Cliente cliente : clientes) {
            StringBuilder historia = new StringBuilder();

            // Encabezado de la historia de usuario
            historia.append("# ").append(cliente.getNombre()).append("\n\n");

            // Sección "Mis datos"
            historia.append("## Mis datos\n\n");
            historia.append("Hola, soy ").append(cliente.getNombre()).append(", ");
            historia.append("mi cédula es ").append(cliente.getCedula()).append(", ");
            historia.append("mi correo es ").append(cliente.getCorreo()).append(" y ");
            historia.append("mi celular es ").append(cliente.getCelular()).append(".\n\n");

            // Sección "Mis peluditos"
            historia.append("## Mis peluditos\n\n");
            List<Mascota> mascotas = mascotaRepository.findAllByClienteId(cliente.getId());
            if (mascotas.isEmpty()) {
                historia.append("No tengo peluditos registrados.\n\n");
            } else {
                historia.append("Tengo ").append(mascotas.size()).append(" peluditos:\n\n");
            }

            for (Mascota mascota : mascotas) {
                historia.append("### ").append(mascota.getNombre()).append("\n\n");
                historia.append("Hola, soy ").append(mascota.getNombre()).append(", un ");
                historia.append(mascota.getRaza()).append(" de ").append(mascota.getEdad()).append(" años y ");
                historia.append(mascota.getPeso()).append(" kg.\n");
                if (mascota.getEnfermedad().equals("vacio")) {
                    historia.append("Soy un peludito sano y feliz.\n");
                } else {
                    historia.append("Actualmente estoy diagnosticado con ").append(mascota.getEnfermedad()).append(".\n");
                }
                if (mascota.getEstado().equals("activo")) {
                    historia.append("Me encuentro en Chilling Dogs para recibir tratamiento.\n\n");
                } else {
                    historia.append("Me encuentro en casa.\n\n");
                }

                historia.append("#### Mis tratamientos\n\n");
                List<Tratamiento> tratamientos = tratamientoRepository.findAllByMascotaId(mascota.getId());
                if (tratamientos.isEmpty()) {
                    historia.append("No he recibido ningún tratamiento.\n\n");
                } else {
                    historia.append("He recibido ").append(tratamientos.size()).append(" tratamientos:\n");
                    for (Tratamiento tratamiento : tratamientos) {
                        Droga droga = tratamiento.getDroga();
                        Veterinario veterinario = tratamiento.getVeterinario();
                        historia.append("- ").append(tratamiento.getFecha()).append(": ");
                        historia.append("El veterinario ").append(veterinario.getNombre());
                        historia.append(" me recetó ").append(droga.getNombre()).append(".\n");
                    }
                    historia.append("\n");
                }
            }

            // Nombre del archivo basándonos en el nombre del cliente
            String fileName = folderPath + "/" + cliente.getNombre().replace(" ", "_") + "_historia.md";

            // Escribir el archivo Markdown
            escribirArchivo(fileName, historia.toString());
        }
    }

    // Método para escribir el contenido en un archivo .md
    private void escribirArchivo(String filePath, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
