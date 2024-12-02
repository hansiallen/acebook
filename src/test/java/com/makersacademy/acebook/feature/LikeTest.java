package com.makersacademy.acebook.feature;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
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
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate("http://localhost:8080/");
    }

    @AfterEach
    public void tearDown() {
        playwright.close();
    }

    @Test
    public void testLikeFeature() {
        page.setDefaultTimeout(6000);

        // Sign up
        page.getByText("Sign up").click();
        String email = faker.name().username() + "@email.com";
        page.locator("#email").fill(email);
        page.locator("#password").fill("P@s5W0rd");
        page.getByText("Continue").nth(1).click();
        page.getByText("Accept").click();

        // Create a post
        page.locator("#postContent").fill("This is a test post.");
        page.getByText("Post").click();
        page.waitForSelector(".post-container");

        // Test like functionality
        page.locator(".like-group").click(); // Click like button
        assert page.locator(".liked-group").isVisible(); // Verify button changes to liked

        page.reload(); // Reload page
        assert page.locator(".liked-group").isVisible(); // Verify liked persists

        // Test unlike functionality
        page.locator(".liked-group").click(); // Click liked button to unlike
        assert page.locator(".like-group").isVisible(); // Verify button changes back to like
    }
}
