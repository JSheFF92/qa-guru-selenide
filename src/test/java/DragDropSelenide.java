import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

public class DragDropSelenide {
    static {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        Configuration.pageLoadStrategy = "eager";
    }


    // не отрабатывает а должен
    @Test
    void dragAndDropAction() {

        SelenideElement a = $("#column-a");
        SelenideElement b = $("#column-b");
        actions().clickAndHold(a).moveToElement(b).release().build().perform();

        $("#column-b").shouldHave(text("A"));
        $("#column-a").shouldHave(text("B"));
    }


    // отрабатывает
    @Test
    void dragAndDropActionSelenide() {
        $("#column-a").dragAndDrop(to($("#column-b")));
        $("#column-b").shouldHave(text("A"));
        $("#column-a").shouldHave(text("B"));
    }
}