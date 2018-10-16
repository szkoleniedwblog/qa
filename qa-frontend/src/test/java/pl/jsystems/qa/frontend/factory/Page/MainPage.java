package pl.jsystems.qa.frontend.factory.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    // WebDriver driver;

    public MainPage(WebDriver driver) {
        // this.driver = driver;
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "li.x-nav-item.x-nav-item--wide.x-nav-item--logged-in a")
    public WebElement logIn;

}
