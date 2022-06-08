import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Log;

public class CheckBoxesTests {
    public WebDriver driver;
    private String url = "http://the-internet.herokuapp.com/checkboxes";
    private Log log = new Log();

    @BeforeMethod
    public void setup() {
        log.debug("Seteando el driver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void aea() {
        log.debug("Abriendo la pagina "+url);
        driver.get(url);

        try {
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.debug(e.getLocalizedMessage());
        }

        var checkboxesLocator = By.tagName("input");
        var listCheckboxes = driver.findElements(checkboxesLocator);

        log.debug("Verificando si el primer checkbox esta marcado");
        var checkbox1 = listCheckboxes.get(0);
        Assert.assertFalse(checkbox1.isSelected(),"El checkbox 1 está marcado");

        log.debug("Verificando si el segundo checkbox esta marcado");
        var checkbox2 = listCheckboxes.get(1);
        Assert.assertTrue(checkbox2.isSelected(),"El checbox 2 no está marcado");

        log.debug("Marcando el primer checbox");
        checkbox1.click();

        log.debug("Desmarcado el segundo checbox");
        checkbox2.click();

        log.debug("Verificando si el primer checkbox esta desmarcado");
        Assert.assertTrue(checkbox1.isSelected(),"El checbox 1 está desmarcado");

        log.debug("Verificando si el segundo checkbox esta desmarcado");
        Assert.assertFalse(checkbox2.isSelected(),"El segundo checkbox sigue marcado");

    }


    @AfterMethod
    public void teardown() {
        driver.quit();
        log.debug("Fin del current test");
        log.printSeparator();
    }



}
