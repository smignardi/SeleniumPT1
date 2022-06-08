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

public class LoginTests {

    public WebDriver driver;
    private String url = "http://the-internet.herokuapp.com/login";
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
    public void validLogin() {
        log.info("TEST: LOGIN VALIDO");
        log.info("Abriendo la pagina "+url);
        driver.get(url);

        log.info("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        log.info("Verificando si se muestra el input del username");
        var usernameInputLocator = By.id("username");
        var usernameInput = driver.findElement(usernameInputLocator);
        Assert.assertTrue(usernameInput.isDisplayed(),"No se muestra el input del username");

        log.info("Verificando si se muestra el input del password");
        var passwordInputLocator = By.id("password");
        var passwordInput = driver.findElement(passwordInputLocator);
        Assert.assertTrue(passwordInput.isDisplayed(),"No se muestra el input del password");

        log.info("Verificando si se muestra el boton del login");
        var loginBtnLocator = By.className("radius");
        var loginBtn = driver.findElement(loginBtnLocator);
        Assert.assertTrue(loginBtn.isDisplayed(),"No se muestra el boton del Login");

        log.info("Ingresando el username");
        var usernameValue = "tomsmith";
        usernameInput.sendKeys(usernameValue);

        log.info("Ingresando el password");
        var passwordValue = "SuperSecretPassword!";
        passwordInput.sendKeys(passwordValue);

        log.info("Dandole click al boton login");
        loginBtn.click();

        log.info("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        log.info("Verificando que se muestre el green box");
        var greenBoxLocator = By.id("flash");
        var greenBox = driver.findElement(greenBoxLocator);
        Assert.assertTrue(greenBox.isDisplayed(),"No se muestra el green box");

        log.info("Verificando que se muestre el boton del logout");
        var logoutBtnLocator = By.className("button");
        var logoutBtn = driver.findElement(logoutBtnLocator);
        Assert.assertTrue(logoutBtn.isDisplayed(),"No se muestra el boton de logout");

        log.info("Dandole click al boton del logout");
        logoutBtn.click();

        log.info("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        log.info("Verificando si se muestra el input del username luego del logout");
        var usernameInputLocator2 = By.id("username");
        var usernameInput2 = driver.findElement(usernameInputLocator2);
        Assert.assertTrue(usernameInput2.isDisplayed(),"No se muestra el input del username luego del logout");
    }

    @Test
    public void invalidLogin() {
        log.info("TEST: LOGIN INVALIDO");
        log.info("Abriendo la pagina "+url);
        driver.get(url);

        log.info("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        log.info("Verificando si se muestra el input del username");
        var usernameInputLocator = By.id("username");
        var usernameInput = driver.findElement(usernameInputLocator);
        Assert.assertTrue(usernameInput.isDisplayed(),"No se muestra el input del username");

        log.info("Verificando si se muestra el input del password");
        var passwordInputLocator = By.id("password");
        var passwordInput = driver.findElement(passwordInputLocator);
        Assert.assertTrue(passwordInput.isDisplayed(),"No se muestra el input del password");

        log.info("Verificando si se muestra el boton del login");
        var loginBtnLocator = By.className("radius");
        var loginBtn = driver.findElement(loginBtnLocator);
        Assert.assertTrue(loginBtn.isDisplayed(),"No se muestra el boton del Login");

        log.info("Ingresando el username");
        var usernameValue = "hola123";
        usernameInput.sendKeys(usernameValue);

        log.info("Ingresando el password");
        var passwordValue = "hehehe";
        passwordInput.sendKeys(passwordValue);

        log.info("Dandole click al boton login");
        loginBtn.click();

        log.info("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        var redBoxLocator = By.id("flash");
        var redBox = driver.findElement(redBoxLocator);
        Assert.assertTrue(redBox.isDisplayed(),"No se muestra el red box de error");
    }


    @AfterMethod
    public void teardown() {
        log.debug("Matando el driver");
        driver.quit();
        log.printSeparator();
    }

}
