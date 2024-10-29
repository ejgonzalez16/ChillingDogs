package org.example.chillingdogspage.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.example.chillingdogspage.Entidad.Cliente;
import org.example.chillingdogspage.Entidad.Mascota;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Crea un contexto de Spring (instancia de la aplicación) para las pruebas
// Esa instancia se levanta en el puerto definido en application-test.properties
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test") // Usa las configuraciones de application-test.properties
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // Reinicia el contexto de Spring antes de cada prueba
public class UseCaseTest {
    private final String BASE_URL = "http://localhost:4200";
    private WebDriver driver;   // Simula un navegador, hace los clicks, los formularios y captura la información
    private WebDriverWait wait; // Espera a que ocurra algo en la página para seguir con la prueba
    private int cantidadTratamientos;
    private double gananciasTotales;
    private String nombreDroga;

    /*
    Notación para pruebas:
    PaginaProbando_elementoProbando_cosaProbando
     */

    /*
    Orden de mejor a peor forma para obtener un WebElement:
    1. ID
    2. ClassName
    3. CSS
    4. LinkText
    5. TagName
    6. XPath
     */

    @BeforeEach  // Se ejecuta antes de cada prueba
    public void init() {
        // Inicializar configuraciones
        WebDriverManager.chromedriver().setup();

        // Configurar el navegador
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications"); // Deshabilita las notificaciones
        chromeOptions.addArguments("--disable-extensions"); // Deshabilita las extensiones
        chromeOptions.addArguments("--start-maximized"); // Inicia el navegador maximizado
//        chromeOptions.addArguments("--headless"); // Ejecuta el navegador en modo headless (sin interfaz gráfica)

        // Instanciar el driver y el wait
        this.driver = new ChromeDriver(chromeOptions);
        // Espera máximo 10 segundos a que algo ocurra antes de lanzar una excepción
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void CasoUso1_registrarClienteYMascotaNuevos_registroCorrecto() {
        // VETERINARIO INICIA SESIÓN
        String cedulaVeterinario = "1234567893";
        String contrasenaVeterinario = "contrasena4";
        iniciarSesionComoVeterinario(cedulaVeterinario, contrasenaVeterinario);

        // CREAR NUEVO CLIENTE
        Cliente cliente = new Cliente("1", "Juan", "juan@juan.juan", "3006647463", "https://www.geetanjaliinstitute.com/wp-content/uploads/2020/01/student_PNG62561.png");
        crearNuevoCliente(cliente);

        // CREAR NUEVA MASCOTA
        Mascota mascota = new Mascota("Firulais", "Pitbull", 5, 10.4f, "Gonorrea", "https://i.pinimg.com/736x/a8/6a/18/a86a1866f25798180ee1a9b247afb3c7.jpg", "Activo", cliente);
        crearNuevaMascota(mascota);

        // CERRAR SESIÓN Y HACER LOGIN COMO CLIENTE
        cerrarSesion();
        iniciarSesionComoCliente(cliente.getCedula());

        // VERIFICAR DETALLES DE LA MASCOTA
        verificarDetallesMascotaComoCliente(mascota);
    }

    private void iniciarSesionComoVeterinario(String cedula, String contrasenaValida) {
        // Given estoy en la página de login
        driver.get(BASE_URL + "/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("type-user-buttons")));

        // When elijo hacer login como veterinario
        WebElement btnLoginVet = driver.findElement(By.id("btnLoginVet"));
        btnLoginVet.click();

        // When hago login con credenciales inválidas
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contrasenaTxt")));
        WebElement inputCedula = driver.findElement(By.id("cedulaTxt"));
        WebElement inputContrasena = driver.findElement(By.id("contrasenaTxt"));
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));

        inputCedula.sendKeys(cedula);
        inputContrasena.sendKeys("contrasenaInvalida");
        btnLogin.click();

        // Then veo un mensaje de error
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("alertLoginError")));
        Assertions.assertThat(driver.findElement(By.id("alertLoginError")).getText()).isEqualTo("Credenciales inválidas");

        // When hago login con credenciales válidas...
        inputContrasena.clear();
        inputContrasena.sendKeys(contrasenaValida);
        btnLogin.click();

        // Then veo la página de buscar clientes
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnBuscarClientes")));
    }

    private void crearNuevoCliente(Cliente cliente) {
        // When hago click en el botón de buscar clientes
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnBuscarClientes"))).click();
        // Then veo la página de buscar clientes
        // When hago click en el botón de crear cliente
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnCrear"))).click();

        // Then veo el formulario para crear un cliente
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modificarForm")));
        WebElement inputCedulaCliente = driver.findElement(By.id("cedula"));
        WebElement inputNombreCliente = driver.findElement(By.id("nombre"));
        WebElement inputEmailCliente = driver.findElement(By.id("email"));
        WebElement inputCelularCliente = driver.findElement(By.id("celular"));
        WebElement inputFotoCliente = driver.findElement(By.id("foto"));
        WebElement btnRegistrarCliente = driver.findElement(By.id("btnRegistrar"));

        // When lleno el formulario con un email inválido
        inputCedulaCliente.sendKeys(cliente.getCedula());
        inputNombreCliente.sendKeys(cliente.getNombre());
        inputEmailCliente.sendKeys("email_invalido");
        inputCelularCliente.sendKeys(cliente.getCelular());
        inputFotoCliente.sendKeys(cliente.getFoto());

        Actions actions = new Actions(driver);
        actions.moveToElement(btnRegistrarCliente).click().build().perform();

        // Then veo un mensaje de error que dice "Email inválido"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("alertFormError")));
        Assertions.assertThat(driver.findElement(By.id("alertFormError")).getText()).isEqualTo("Email inválido");

        // When lleno el formulario con un email válido
        inputEmailCliente.clear();
        inputEmailCliente.sendKeys(cliente.getCorreo());
        btnRegistrarCliente.click();

        // Then veo la tabla de clientes con el nuevo cliente
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tablaClientes")));
        List<WebElement> rows = driver.findElements(By.tagName("tr"));
        List<WebElement> tds = rows.get(rows.size() - 1).findElements(By.tagName("td"));

        Assertions.assertThat(tds.get(0).getText()).isEqualTo(cliente.getCedula());
        Assertions.assertThat(tds.get(2).getText()).isEqualTo(cliente.getNombre());
        Assertions.assertThat(tds.get(3).getText()).isEqualTo(cliente.getCorreo());
    }

    private void crearNuevaMascota(Mascota mascota) {
        // When hago click en el botón de buscar mascotas
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnBuscarMascotas"))).click();
        // Then veo la página de buscar mascotas
        // When hago click en el botón de crear mascota
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnCrearMascota"))).click();

        // Then veo el formulario para crear una mascota
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modificarForm")));
        WebElement inputNombreMascota = driver.findElement(By.id("nombre"));
        WebElement btnEstadoActivoMascota = driver.findElement(By.id("btnActivo"));
        WebElement inputRazaMascota = driver.findElement(By.id("raza"));
        WebElement inputEdadMascota = driver.findElement(By.id("edad"));
        WebElement inputPesoMascota = driver.findElement(By.id("peso"));
        WebElement inputEnfermedadMascota = driver.findElement(By.id("enfermedad"));
        WebElement inputFotoMascota = driver.findElement(By.id("foto"));
        WebElement elemSelectDueno = driver.findElement(By.id("cliente"));
        Select selectDueno = new Select(elemSelectDueno);
        WebElement btnRegistrarMascota = driver.findElement(By.id("btnRegistrar"));

        // When lleno el formulario con datos válidos
        inputNombreMascota.sendKeys(mascota.getNombre());
        btnEstadoActivoMascota.click();
        inputRazaMascota.sendKeys(mascota.getRaza());
        inputEdadMascota.sendKeys(String.valueOf(mascota.getEdad()));
        inputPesoMascota.sendKeys(String.valueOf(mascota.getPeso()));
        inputEnfermedadMascota.sendKeys(mascota.getEnfermedad());
        inputFotoMascota.sendKeys(mascota.getFoto());
        selectDueno.selectByVisibleText(mascota.getCliente().getNombre());

        hacerScrollAElemento(btnRegistrarMascota);
        btnRegistrarMascota.click();

        // Then veo la tabla de mascotas con la nueva mascota (falta verificar que esté la mascota)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tablaMascotas")));
    }

    private void cerrarSesion() {
        driver.findElement(By.id("btnLogout")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("type-user-buttons")));
    }

    private void iniciarSesionComoCliente(String cedula) {
        // Given estoy en la página de login
        driver.get(BASE_URL + "/login");
        WebElement inputCedula = driver.findElement(By.id("cedulaTxt"));
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));

        // When hago login como cliente con credenciales válidas
        inputCedula.sendKeys(cedula);
        btnLogin.click();

        // Then veo la página de mis-mascotas
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btnDetallesMascota")));
    }

    private void verificarDetallesMascotaComoCliente(Mascota mascota) {
        // Given estoy en la página de mis-mascotas
        // When hago click en el botón de detalles de la primera mascota que tengo
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btnDetallesMascota"))).click();

        // Then veo los detalles de la mascota que seleccioné
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("estadoPeludo")));
        WebElement estadoDeNombreMascota = driver.findElement(By.id("nombreMascota"));
        WebElement estadoMascota = driver.findElement(By.className("estadoMascota"));
        List<WebElement> tdsRow = driver.findElements(By.tagName("tr")).stream()
                .map(row -> row.findElements(By.tagName("td")).get(2))
                .toList();

        Assertions.assertThat(estadoDeNombreMascota.getText()).isEqualTo("Estado de " + mascota.getNombre());
        Assertions.assertThat(estadoMascota.getText()).isEqualTo(mascota.getEstado());
        Assertions.assertThat(tdsRow.get(1).getText()).isEqualTo(mascota.getRaza());

        String edadMascotaSinAnios = tdsRow.get(2).getText().substring(0, tdsRow.get(2).getText().length() - 8);
        Assertions.assertThat(edadMascotaSinAnios).isEqualTo(String.valueOf(mascota.getEdad()));
        Assertions.assertThat(tdsRow.get(3).getText()).isEqualTo(mascota.getPeso() + " kg");
        Assertions.assertThat(tdsRow.get(4).getText()).isEqualTo(mascota.getEnfermedad());
        Assertions.assertThat(tdsRow.get(5).getText()).isEqualTo(mascota.getCliente().getNombre());
    }

    @Test
    public void CasoUso2_registrarTratamientoVet_registroCorrecto(){
        // ADMIN INICIA SESIÓN --------------------------------------------------------------
        // Given estoy en la página de login
        obtenerDatosAdmin();
        //Cerrar Sesión del admin
        WebElement logOutBtn = driver.findElement(By.className("btnVolver"));
        hacerScrollAElemento(logOutBtn);
        logOutBtn.click();
        // VETERINARIO INICIA SESIÓN --------------------------------------------------------------
        // Given estoy en la página de login
        registrarTratamiento();
        revisarTratamientoCreado();
        revisarDashboard();
    }

    public void obtenerDatosAdmin(){
        driver.get(BASE_URL + "/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("type-user-buttons")));

        WebElement btnAdmin = driver.findElement(By.id("btnLoginAdmin"));
        btnAdmin.click();
        iniciarSesion("12345678","churumbelo");

        obtenerDatosDashBoard();
    }

    public void iniciarSesion(String cedula, String contrasena){
        WebElement cedulaInput = driver.findElement(By.id("cedulaTxt"));
        cedulaInput.sendKeys(cedula);

        WebElement contrasenaInput = driver.findElement(By.id("contrasenaTxt"));
        contrasenaInput.sendKeys(contrasena);

        WebElement botonIniciarSesion = driver.findElement(By.id("btnLogin"));
        botonIniciarSesion.click();
    }

    public void obtenerDatosDashBoard(){
        //Espera a que cantidad de tratamientos deje de ser vacio
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(By.id("cantidadTratamientosTotales"));
                return !element.getText().trim().isEmpty(); // Retorna verdadero si no está vacío
            }
        });
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cantidadTratamientosTotales")));
        WebElement textoCantidadTratamientos = driver.findElement(By.id("cantidadTratamientosTotales"));
        cantidadTratamientos = Integer.parseInt(textoCantidadTratamientos.getText());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cantidadGananciasTotales")));
        WebElement textoGananciasTotales = driver.findElement(By.id("cantidadGananciasTotales"));
        String textoGananciasSinSimbolo = textoGananciasTotales.getText().substring(1);
        textoGananciasSinSimbolo  = textoGananciasSinSimbolo.replaceAll(",", "");
        gananciasTotales = Double.parseDouble(textoGananciasSinSimbolo);
    }

    public void hacerScrollAElemento(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();  // Mueve hacia el elemento
    }

    public void registrarTratamiento(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("type-user-buttons")));

        WebElement btnVeterinario = driver.findElement(By.id("btnLoginVet"));
        btnVeterinario.click();
        iniciarSesion("1234567890","contrasena1");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnBuscarTratamientos")));
        WebElement btnTratamientos = driver.findElement(By.id("btnBuscarTratamientos"));
        btnTratamientos.click();

        WebElement barraBusqueda = driver.findElement(By.id("nombrePerroField"));
        barraBusqueda.sendKeys("Rex");
        WebElement botonBuscar = driver.findElement(By.id("btnForm"));
        botonBuscar.click();
        WebElement primerElemento = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("tratarBtns")));
        primerElemento.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("droga")));
        WebElement drogaSelect = driver.findElement(By.id("droga"));
        Select select = new Select(drogaSelect);
        select.selectByIndex(0);
        WebElement opcionSeleccionada = select.getFirstSelectedOption();
        nombreDroga =  opcionSeleccionada.getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("registrarBtn")));
        WebElement registrarBtn = driver.findElement(By.id("registrarBtn"));
        hacerScrollAElemento(registrarBtn);
        registrarBtn.click();

        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        // Acepta la alerta (equivalente a presionar "Aceptar")
        alerta.accept();
    }

    public void revisarTratamientoCreado(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnBuscarMascotas")));
        WebElement btnMascotas = driver.findElement(By.id("btnBuscarMascotas"));
        btnMascotas.click();

        WebElement barraBusqueda = driver.findElement(By.id("nombrePerroField"));
        barraBusqueda.sendKeys("Rex");
        WebElement botonBuscar = driver.findElement(By.id("btnForm"));
        botonBuscar.click();
        WebElement primerElemento = wait.until(ExpectedConditions.elementToBeClickable(By.className("detallesMascotasBtns")));
        primerElemento.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btnTratamientos")));
        WebElement btnVerTratamientos = driver.findElement(By.className("btnTratamientos"));
        hacerScrollAElemento(btnVerTratamientos);
        btnVerTratamientos.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("drogasSuministradas")));
        List<WebElement> drogasSuministradas = driver.findElements(By.className("drogasSuministradas"));
        Assertions.assertThat(drogasSuministradas.get(drogasSuministradas.size() - 1).getText()).isEqualTo(nombreDroga);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fechasTratamientos")));
        List<WebElement> fechasTratamientos = driver.findElements(By.className("fechasTratamientos"));
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaDeHoy = fechaHoy.format(formatter);
        Assertions.assertThat(fechasTratamientos.get(drogasSuministradas.size() - 1).getText()).isEqualTo(fechaDeHoy);
    }

    public void revisarDashboard(){
        WebElement btnLogOut = driver.findElement(By.id("btnLogout"));
        btnLogOut.click();

        int cantidadTratamientosVieja = this.cantidadTratamientos;
        double gananciasTotalesVieja = this.gananciasTotales;

        WebElement btnAdmin = driver.findElement(By.id("btnLoginAdmin"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", btnAdmin);
        iniciarSesion("12345678","churumbelo");

        obtenerDatosDashBoard();

        Assertions.assertThat(this.cantidadTratamientos).isEqualTo(cantidadTratamientosVieja+1);
        Assertions.assertThat(this.gananciasTotales).isGreaterThan(gananciasTotalesVieja);
    }


    @AfterEach  // Se ejecuta después de cada prueba
    void tearDown() {
        // Cerrar el navegador
       /* if (driver != null) {
            driver.quit();
        }*/
    }
}
