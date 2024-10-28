package org.example.chillingdogspage.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
public class UseCaseTest {
    private final String BASE_URL = "http://localhost:4200";
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
    public void ClientesTest_registrarCliente_listSize(){
        // AGREGAR AL CLIENTE ----------------------------------------------
        // Given
        driver.get(BASE_URL + "/buscar");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnCrear")));

        // When
        List<WebElement> rows = driver.findElements(By.tagName("tr"));
        int initialSize = rows.size();
        WebElement btnCrear = driver.findElement(By.id("btnCrear"));
        btnCrear.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedula")));
        WebElement inputCedula = driver.findElement(By.id("cedula"));
        WebElement inputNombre = driver.findElement(By.id("nombre"));
        WebElement inputEmail = driver.findElement(By.id("email"));
        WebElement inputCelular = driver.findElement(By.id("celular"));
        WebElement inputFoto = driver.findElement(By.id("foto"));

        inputCedula.sendKeys(Keys.BACK_SPACE);
        inputCedula.sendKeys("123456789");
        inputNombre.sendKeys("Juan");
        inputEmail.sendKeys("juan@juan.juan");
        inputCelular.sendKeys(Keys.BACK_SPACE);
        inputCelular.sendKeys("3006647463");
        inputFoto.sendKeys("https://www.geetanjaliinstitute.com/wp-content/uploads/2020/01/student_PNG62561.png");

        WebElement btnRegistrar = driver.findElement(By.id("btnRegistrar"));
        btnRegistrar.click();

        wait.until(lambda -> driver.findElements(By.tagName("tr")).size() == initialSize + 1);
        rows = driver.findElements(By.tagName("tr"));
        Assertions.assertThat(rows.size()).isEqualTo(initialSize + 1);

        // VER DETALLES DEL CLIENTE ----------------------------------------------
        // Given
        List<WebElement> btnsDetalles = driver.findElements(By.className("btnDetalles"));
        btnsDetalles.get(btnsDetalles.size() - 1).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("liNombre")));

        // When
        WebElement liNombre = driver.findElement(By.id("idNombre"));

        String nombreEsperado = "Nombre: Juan";
        Assertions.assertThat(liNombre.getText()).isEqualTo(nombreEsperado);
    }

    @AfterEach  // Se ejecuta después de cada prueba
    void tearDown() {
        // Cerrar el navegador
        if (driver != null) {
            driver.quit();
        }
    }
}
