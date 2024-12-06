package com.makersacademy.acebook.feature;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SignUpTest {

    Page page;
    Faker faker;
    Playwright playwright;
    BrowserContext context;

    @BeforeEach
    public void setup() {
        faker = new Faker();
        playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("http://localhost:8080/");
    }

    @AfterEach
    public void tearDown() {
            playwright.close();
    }

    @Test
    public void successfulSignUpAlsoLogsInUser(){
        page.setDefaultTimeout(6000);
        page.getByText("Sign up").click();
        String email = faker.name().username() + "@email.com";

//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get("screenshot0.png"))
//                .setFullPage(true));

        page.locator("#email").fill(email);
        page.locator("#password").fill("P@s5W0rd");

//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get("screenshot1.png"))
//                .setFullPage(true));

        page.getByText("Continue").nth(1).click();
        page.getByText("Accept").click();

        page.locator(".logo").click();


        Assert.assertEquals("http://localhost:8080/posts",page.url());
    }
}
