package com.makersacademy.acebook.feature;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

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
    public void testLikeFeature() throws InterruptedException {
        page.setDefaultTimeout(6000);

        // Sign up
        page.getByText("Sign up").click();
        String email = faker.name().username() + "@email.com";
        page.locator("#email").fill(email);
        page.locator("#password").fill("P@s5W0rd");
        page.getByText("Continue").nth(1).click();
        page.getByText("Accept").click();

        // Create a post
        page.locator("#content").nth(0).fill("This is a test post.");
        page.getByText("Post").nth(0).click();


        // Test like functionality
        page.locator(".like-group").nth(0).click(); // Click like button

//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get("screenshot1.png"))
//                .setFullPage(true));
        TimeUnit.SECONDS.sleep(1);
        assert page.locator(".liked-group").nth(0).isVisible(); // Verify button changes to liked


        page.navigate("http://localhost:8080/");// Reload page
        assert page.locator(".liked-group").nth(0).isVisible(); // Verify liked persists

        // Test unlike functionality
        page.locator(".liked-group").nth(0).click(); // Click liked button to unlike
        TimeUnit.SECONDS.sleep(1);
        boolean located=page.locator(".like-group").nth(0).isVisible(); // Verify button changes back to like
        assert located;
    }
}
