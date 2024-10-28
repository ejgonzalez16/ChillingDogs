package org.example.chillingdogspage.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.util.List;

// Crea un contexto de Spring (instancia de la aplicación) para las pruebas
// Esa instancia se levanta en el puerto definido en application-test.properties
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test") // Usa las configuraciones de application-test.properties
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // Reinicia el contexto de Spring antes de cada prueba
public class DetallesClienteTest {
    private final String BASE_URL = "http://localhost:4200/clientes";
    private WebDriver driver;   // Simula un navegador, hace los clicks, los formularios y captura la información
    private WebDriverWait wait; // Espera a que ocurra algo en la página para seguir con la prueba

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
//        chromeOptions.addArguments("--headless"); // Ejecuta el navegador en modo headless (sin interfaz gráfica)

        // Instanciar el driver y el wait
        this.driver = new ChromeDriver(chromeOptions);
        // Espera máximo 10 segundos a que algo ocurra antes de lanzar una excepción
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void DetallesClienteTest_detallesCliente_nombreCliente(){
        // Navegar a la página de detalles del cliente
        driver.get(BASE_URL + "/buscar/1");

        String idNombre = "liNombre";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idNombre)));
        WebElement liNombre = driver.findElement(By.id(idNombre));

        String nombreEsperado = "Nombre: Juan";
        Assertions.assertThat(liNombre.getText()).isEqualTo(nombreEsperado);
    }

    @Test
    public void DetallesClienteTest_tablaMascotas_mascotasSize(){
        // Navegar a la página de detalles del cliente
        driver.get(BASE_URL + "/buscar/1");

        String classNameNombreMascota = "NombreMascota";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(classNameNombreMascota)));
        List<WebElement> liMascotas = driver.findElements(By.className(classNameNombreMascota));

        Assertions.assertThat(liMascotas.size()).isEqualTo(2);
    }

    @AfterEach  // Se ejecuta después de cada prueba
    void tearDown() {
        // Cerrar el navegador
        if (driver != null) {
            driver.quit();
        }
    }
}
