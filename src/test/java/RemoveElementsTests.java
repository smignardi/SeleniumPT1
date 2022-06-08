import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.Logs;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Log;

public class RemoveElementsTests {

    public WebDriver driver;
    private String url = "http://the-internet.herokuapp.com/add_remove_elements/";
    private Log log = new Log();

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
    public void removeElements() {
        log.info("Ingresando a la pagina "+url);
        driver.get(url);

        log.info("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        var addElementBtnLocator = By.tagName("button");
        var addElementBtn = driver.findElement(addElementBtnLocator);

        log.info("Haciendo click 10 veces el en btn add element");
        for (var i=0; i<10 ; i++) {
            addElementBtn.click();
        }

        var deleteElementsLocator = By.className("added-manually");
        var listDeleteElements = driver.findElements(deleteElementsLocator);

        log.info("Eliminando cada elemento en pantalla");
        for (var x : listDeleteElements) {
            x.click();
        }

    }

    @AfterMethod
    public void teardown() {
        log.debug("Matando el driver");
        driver.quit();
        log.printSeparator();
    }

}
