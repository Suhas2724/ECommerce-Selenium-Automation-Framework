package org.seleniumFramework.tests;

import org.seleniumFramework.pages.HomePage;
import org.testng.annotations.Test;

import static org.seleniumFramework.constants.Browser.CHROME;

public class SignUpTest {

    @Test(description = "Sign Up", groups = {"e2e", "sanity"})
    public void signUpTest() {

        HomePage homePage = new HomePage(CHROME,false);
        String successMessage = homePage
                .goToSignUpPage()
                .goToAccountCreationPage()
                .signUp();
        homePage.quitDriver();
        System.out.println(successMessage);


    }

}
