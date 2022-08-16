import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

class CallbackNegativeTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    // негативные сценарии

    @Test
    void testName1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Masha");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79995554466");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testName2() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("0795");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79995554466");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testName3() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys(" "); //один пробел
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79995554466");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testName4() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("       "); //семь пробелов
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79995554466");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testName5() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("♣☺♂");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79995554466");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testName6() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("<script>alert(\"I hacked this!\")</script>");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79995554466");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void testNameBug1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys(" Маша"); // пробел перед первой буквой
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79995554466");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Пробелы перед именем недопустимы";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testNameBug2() {
        driver.get("http://localhost:9999/");
        //ввести 2793 буквы
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("жжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжжж");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79995554466");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Текст превышает лимит";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testPhone1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testPhone2() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("89997776655");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPhone3() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997776");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPhone4() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys(""); // пустое поле
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPhone5() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPhone6() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys(" "); // один пробел
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPhone7() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+           "); //плюс и одиннадцать пробелов
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPhone8() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+01236547890123654789");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPhone9() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("<script>alert(\"I hacked this!\")</script>");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void testPhoneBug1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+89997776655");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Код страны указан неверно";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testPhoneBug2() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+70000000000"); // Плюс семь и одиннадцать нулей
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testCheckbox1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79012345678");
        //driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id=agreement] .checkbox__text")).getText();
        Assertions.assertEquals(expected, actual);

    }


    @Test
    void testAllEmptyFields() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        //driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testTwoEmptyFields() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        //driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone] .input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testOneEmptyFields() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маша");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79996665544");
        //driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id=agreement] .checkbox__text")).getText();
        Assertions.assertEquals(expected, actual);
    }


}
