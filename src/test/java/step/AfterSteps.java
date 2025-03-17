package step;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;

public class AfterSteps {
    @After
    public void tearDown(){
        WebDriverRunner.getWebDriver().close();
        WebDriverRunner.getWebDriver().quit();
    }
}
