import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }
}
