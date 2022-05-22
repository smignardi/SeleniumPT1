import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Logs;

public class RemoveElementsTests {

    public WebDriver driver;
    private String url = "http://the-internet.herokuapp.com/add_remove_elements/";
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
        int i;
        for (i=0; i<10 ; i++) {
            addElementBtn.click();
        }

        var deleteElementsLocator = By.className("added-manually");
        var listDeleteElements = driver.findElements(deleteElementsLocator);

        log.info("Eliminando cada elemento en pantalla");
        for (WebElement x : listDeleteElements) {
            x.click();
        }

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
        log.info("Fin del current test");
        log.printSeparator();
    }

}
