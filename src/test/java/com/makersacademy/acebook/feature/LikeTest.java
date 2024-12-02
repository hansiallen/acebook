package com.makersacademy.acebook.feature;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LikeTest {

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
    public void like(){
        page.setDefaultTimeout(6000);
        page.getByText("Sign up").click();
        String email = faker.name().username() + "@email.com";
        page.locator("#email").fill(email);
        page.locator("#password").fill("P@s5W0rd");
        page.getByText("Continue").nth(1).click();
        page.getByText("Accept").click();
        //used like-group and liked-group

    }
}
