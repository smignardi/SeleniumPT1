import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Log;

public class StatusCodesTests {
    public WebDriver driver;
    private String url = "http://the-internet.herokuapp.com/status_codes";
    private Log log= new Log();

    @BeforeMethod
    public void setup() {
        log.debug("Seteando el driver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        log.debug("Maximizando pantalla");
        driver.manage().window().maximize();
        log.debug("Borrando coockies");
        driver.manage().deleteAllCookies();
    }

    @Test
    public void statusTest() {
        log.info("Abriendo la pagina "+url);
        driver.get(url);

        log.info("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        var locator404 = By.linkText("404");
        var element404 = driver.findElement(locator404);

        log.info("Haciendo click en el elemento 404");
        element404.click();

        log.info("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        var hereElementLocator = By.linkText("here");
        var hereElement = driver.findElement(hereElementLocator);

        log.info("Haciendo click en el elemento: here");
        hereElement.click();

        log.info("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        log.info("Vericando si la current url es igual a la del inicio");
        Assert.assertEquals(driver.getCurrentUrl(),url,"Los urls no son iguales");

    }

    @AfterMethod
    public void teardown() {
        log.debug("Matando el driver");
        driver.quit();
        log.printSeparator();
    }
}
