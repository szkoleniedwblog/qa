package pl.jsystems.qa.frontend.factory.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPasswordPage extends BasePage {

    // WebDriver driver;

    public LoginPasswordPage(WebDriver driver) {
        // this.driver = driver;
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(css = "button.button.form-button.is-primary")
    public WebElement passContinueButton;

}
