import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Logs;

public class StatusCodesTests {
    public WebDriver driver;
    private String url = "http://the-internet.herokuapp.com/status_codes";
    private Logs log = new Logs();

    @BeforeMethod
    public void setup() {
        log.info("Seteando el driver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        driver.quit();
        log.info("Fin del current test");
        log.printSeparator();
    }
}
