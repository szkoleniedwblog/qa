package pl.jsystems.qa.frontend.factory.Test;

import org.junit.jupiter.api.Test;
import pl.jsystems.qa.frontend.Configuration;
import pl.jsystems.qa.frontend.factory.FrontConfig;
import pl.jsystems.qa.frontend.factory.Page.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrontEndTest extends FrontConfig {

    @Test
    public void firstFrontendTest() {

        MainPage mainPage = new MainPage(driver);

        assertTrue(driver.getTitle().contains("WordPress.com"));
        assertEquals(mainPage.logIn.getText(), "Log In");
        assertTrue(mainPage.logIn.isDisplayed());

        mainPage.logIn.click();
        LoginEmailPage loginEmailPage = new LoginEmailPage(driver);

        loginEmailPage.waitForVisibilityOfElement(loginEmailPage.loginEmail, 30);
        loginEmailPage.waitForVisibilityOfElement(loginEmailPage.emailContinueButton, 30);

        assertTrue(loginEmailPage.loginEmail.isDisplayed());
        assertTrue(loginEmailPage.emailContinueButton.isDisplayed());

        loginEmailPage.loginEmail.clear();
        loginEmailPage.loginEmail.sendKeys(Configuration.WORDPRESS_LOGIN);
        loginEmailPage.emailContinueButton.click();

        LoginPasswordPage loginPasswordPage = new LoginPasswordPage(driver);

        loginPasswordPage.waitForVisibilityOfElement(loginPasswordPage.passwordInput, 30);
        loginPasswordPage.waitForVisibilityOfElement(loginPasswordPage.passContinueButton, 30);

        assertTrue(loginPasswordPage.passwordInput.isDisplayed());
        assertTrue(loginPasswordPage.passContinueButton.isDisplayed());

        loginPasswordPage.passwordInput.clear();
        loginPasswordPage.passwordInput.sendKeys(Configuration.WORDPRESS_PASSWORD);
        loginPasswordPage.passContinueButton.click();

        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.waitForVisibilityOfElement(userMainPage.userAvatar, 30);
        userMainPage.userAvatar.click();

        UserPersonalPage userPersonalPage = new UserPersonalPage(driver);
        userPersonalPage.waitForVisibilityOfElement(userPersonalPage.privateLink, 30);
        userPersonalPage.privateLink.click();

        PrivatPage privatPage = new PrivatPage(driver);
        privatPage.waitForVisibilityOfElement(privatPage.checkbox, 30);

        assertFalse(privatPage.saveButton.isEnabled());

        privatPage.checkbox.click();

        privatPage.waitForVisibilityOfElement(privatPage.saveButton, 30);
        assertTrue(privatPage.saveButton.isEnabled());

        privatPage.checkbox.click();
        assertFalse(privatPage.saveButton.isEnabled());



    }

    @Test
    public void secondFrontendTest() {

        MainPage mainPage = new MainPage(driver);

        assertTrue(driver.getTitle().contains("WordPress.com"));
        assertEquals(mainPage.logIn.getText(), "Log In");
        assertTrue(mainPage.logIn.isDisplayed());

        mainPage.logIn.click();
        LoginEmailPage loginEmailPage = new LoginEmailPage(driver);

        loginEmailPage.waitForVisibilityOfElement(loginEmailPage.loginEmail, 30);
        loginEmailPage.waitForVisibilityOfElement(loginEmailPage.emailContinueButton, 30);

        assertTrue(loginEmailPage.loginEmail.isDisplayed());
        assertTrue(loginEmailPage.emailContinueButton.isDisplayed());

        loginEmailPage.loginEmail.clear();
        loginEmailPage.loginEmail.sendKeys(Configuration.WORDPRESS_LOGIN);
        loginEmailPage.emailContinueButton.click();

        LoginPasswordPage loginPasswordPage = new LoginPasswordPage(driver);

        loginPasswordPage.waitForVisibilityOfElement(loginPasswordPage.passwordInput, 30);
        loginPasswordPage.waitForVisibilityOfElement(loginPasswordPage.passContinueButton, 30);

        assertTrue(loginPasswordPage.passwordInput.isDisplayed());
        assertTrue(loginPasswordPage.passContinueButton.isDisplayed());

        loginPasswordPage.passwordInput.clear();
        loginPasswordPage.passwordInput.sendKeys(Configuration.WORDPRESS_PASSWORD);
        loginPasswordPage.passContinueButton.click();

        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.waitForVisibilityOfElement(userMainPage.userAvatar, 30);
        userMainPage.userAvatar.click();

        UserPersonalPage userPersonalPage = new UserPersonalPage(driver);
        userPersonalPage.waitForVisibilityOfElement(userPersonalPage.privateLink, 30);
        userPersonalPage.notification.click();

        NotificationPage notificationPage = new NotificationPage(driver);
        notificationPage.waitForVisibilityOfElement(notificationPage.secondSelector, 30);

        assertTrue(notificationPage.secondSelector.isSelected());
        notificationPage.secondSelector.click();
        assertFalse(notificationPage.secondSelector.isSelected());
        notificationPage.secondSelector.click();
        assertTrue(notificationPage.secondSelector.isSelected());

    }

 }
