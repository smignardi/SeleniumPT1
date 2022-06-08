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
        log.debug("Inicializando el driver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test(priority = 0)
    public void validLogin() {
        log.debug("TEST: LOGIN VALIDO");
        log.debug("Abriendo la pagina "+url);
        driver.get(url);

        log.debug("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        log.debug("Verificando si se muestra el input del username");
        var usernameInputLocator = By.id("username");
        var usernameInput = driver.findElement(usernameInputLocator);
        Assert.assertTrue(usernameInput.isDisplayed(),"No se muestra el input del username");

        log.debug("Verificando si se muestra el input del password");
        var passwordInputLocator = By.id("password");
        var passwordInput = driver.findElement(passwordInputLocator);
        Assert.assertTrue(passwordInput.isDisplayed(),"No se muestra el input del password");

        log.debug("Verificando si se muestra el boton del login");
        var loginBtnLocator = By.className("radius");
        var loginBtn = driver.findElement(loginBtnLocator);
        Assert.assertTrue(loginBtn.isDisplayed(),"No se muestra el boton del Login");

        log.debug("Ingresando el username");
        var usernameValue = "tomsmith";
        usernameInput.sendKeys(usernameValue);

        log.debug("Ingresando el password");
        var passwordValue = "SuperSecretPassword!";
        passwordInput.sendKeys(passwordValue);

        log.debug("Dandole click al boton login");
        loginBtn.click();

        log.debug("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        log.debug("Verificando que se muestre el green box");
        var greenBoxLocator = By.id("flash");
        var greenBox = driver.findElement(greenBoxLocator);
        Assert.assertTrue(greenBox.isDisplayed(),"No se muestra el green box");

        log.debug("Verificando que se muestre el boton del logout");
        var logoutBtnLocator = By.className("button");
        var logoutBtn = driver.findElement(logoutBtnLocator);
        Assert.assertTrue(logoutBtn.isDisplayed(),"No se muestra el boton de logout");

        log.debug("Dandole click al boton del logout");
        logoutBtn.click();

        log.debug("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        log.debug("Verificando si se muestra el input del username luego del logout");
        var usernameInputLocator2 = By.id("username");
        var usernameInput2 = driver.findElement(usernameInputLocator2);
        Assert.assertTrue(usernameInput2.isDisplayed(),"No se muestra el input del username luego del logout");
    }

    @Test(priority = 1)
    public void invalidLogin() {
        log.debug("TEST: LOGIN INVALIDO");
        log.debug("Abriendo la pagina "+url);
        driver.get(url);

        log.debug("Esperando 2 segundos a que cargue la pagina");
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            log.error(e.getLocalizedMessage());
        }

        log.debug("Verificando si se muestra el input del username");
        var usernameInputLocator = By.id("username");
        var usernameInput = driver.findElement(usernameInputLocator);
        Assert.assertTrue(usernameInput.isDisplayed(),"No se muestra el input del username");

        log.debug("Verificando si se muestra el input del password");
        var passwordInputLocator = By.id("password");
        var passwordInput = driver.findElement(passwordInputLocator);
        Assert.assertTrue(passwordInput.isDisplayed(),"No se muestra el input del password");

        log.debug("Verificando si se muestra el boton del login");
        var loginBtnLocator = By.className("radius");
        var loginBtn = driver.findElement(loginBtnLocator);
        Assert.assertTrue(loginBtn.isDisplayed(),"No se muestra el boton del Login");

        log.debug("Ingresando el username");
        var usernameValue = "hola123";
        usernameInput.sendKeys(usernameValue);

        log.debug("Ingresando el password");
        var passwordValue = "hehehe";
        passwordInput.sendKeys(passwordValue);

        log.debug("Dandole click al boton login");
        loginBtn.click();

        log.debug("Esperando 2 segundos a que cargue la pagina");
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
        driver.quit();
        log.debug("Fin del current test");
        log.printSeparator();
    }

}
