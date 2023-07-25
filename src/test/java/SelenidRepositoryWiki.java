import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SelenidRepositoryWiki {

    static {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void shouldFindSelenideSoftAssertionsJunit5() {
        open("https://github.com/");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("[data-target='query-builder.input']").setValue("selenide").pressEnter();
        $(".lmmHGT").click();
        $("[data-testid='nav-item-wikis']").click();
        $("a[href='/YuriKopshev/selenide/wiki/SoftAssertions'].bJBoUI").click();
        $(".markdown-body").
                $(byText("3. Using JUnit5 extend test class:"));
        $(".markdown-body")
                .$(byText("""
                        public class Tests {
                          @Rule\s
                          public SoftAsserts softAsserts = new SoftAsserts();
                                                
                          @Test
                          public void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");
                                                
                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }"""));
    }
}